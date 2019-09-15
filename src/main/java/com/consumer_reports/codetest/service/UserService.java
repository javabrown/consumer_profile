package com.consumer_reports.codetest.service;

import com.consumer_reports.codetest.core.ApiError;
import com.consumer_reports.codetest.core.Utils;
import com.consumer_reports.codetest.daos.AddressDao;
import com.consumer_reports.codetest.daos.AddressRepository;
import com.consumer_reports.codetest.daos.UserDao;
import com.consumer_reports.codetest.daos.UserRepository;
import com.consumer_reports.codetest.model.User;
import com.consumer_reports.codetest.model.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import static com.consumer_reports.codetest.core.Utils.replaceIfNonEmpty;


@Service
@Qualifier("consumerService")
public class UserService implements ServiceI {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ApiError error;

    @Override
    public List<User> getAllUsersProfile() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach($ -> {
            Builder builder = new Builder();
            Optional<Integer> addressId = Optional.ofNullable($.getAddressId());

            addressId.ifPresent(id -> {
                addressRepository.findById(id).ifPresent($$->{
                    builder.with($$.builder());
                });
            });
            ;

            list.add(builder.with($.builder()).makeApiResponseUser());
        });
        return list;
    }

    @Override
    public User getUserProfileById(Integer userId) {
        AtomicReference<User> value = new AtomicReference<>();
        userRepository.findById(userId).ifPresent($->{
            Builder builder = new Builder();
            Optional<Integer> addressId = Optional.ofNullable($.getAddressId());

            addressId.ifPresent(id -> {
                addressRepository.findById(id).ifPresent($$->{
                    builder.with($$.builder());
                });
            });
            value.set(builder.with($.builder()).makeApiResponseUser());
        });
        return value.get();
    }

    @Override
    public User createNewUserProfile(User user) {
        Builder builder = new Builder();

        AtomicInteger newOrExistingAddressId = new AtomicInteger(-1);
        if(user.getAddress() != null && user.getAddress().isValid()){
            builder.with(user.getAddress().builder());
            AddressDao newOrExistingAddress = builder.makeAddressDao();
            newOrExistingAddress = addressRepository.save(newOrExistingAddress);
            newOrExistingAddressId.set(newOrExistingAddress.getId());
        }

        UserDao userDao = builder.with($->{
            $.userId = user.getUserId();
            $.firstName = user.getFirstName();
            $.lastName = user.getLastName();
            $.email = user.getEmail();
            $.addressId = newOrExistingAddressId.get() > 0 ? newOrExistingAddressId.get() : null;
        }).makeUserDao();

        userDao = userRepository.save(userDao);
        AtomicInteger generatedUserId = new AtomicInteger(userDao.getId());

        return builder.with($->{
            $.userId = generatedUserId.get();
        }).makeApiResponseUser();
    }

    @Override
    public User updateUserProfile(Integer userId, User user) {
        AtomicReference<UserDao> atomicUserDao = new AtomicReference<>();
        AtomicInteger atomicAddressId = new AtomicInteger(-1);

        userRepository.findById(userId).ifPresent($-> {

            Optional<Integer> addressId = Optional.ofNullable($.getAddressId());

            addressId.ifPresent(id -> {
                addressRepository.findById(id).ifPresent(addrDao -> {
                    addrDao = Utils.updateAddressWithRequest(addrDao, user);
                    addrDao = addressRepository.save(addrDao);
                    atomicAddressId.set(addrDao.getId());
                });
            });

            if(!addressId.isPresent() && user.getAddress() != null && user.getAddress().isValid()) {
                AddressDao addrDao = new AddressDao();
                addrDao = Utils.updateAddressWithRequest(addrDao, user);
                addrDao = addressRepository.save(addrDao);
                atomicAddressId.set(addrDao.getId());
            };


            $.setFirstName(replaceIfNonEmpty($.getFirstName(), user.getFirstName()));
            $.setLastName(replaceIfNonEmpty($.getLastName(), user.getLastName()));
            $.setEmail(replaceIfNonEmpty($.getEmail(), user.getEmail()));
            $.setAddressId(replaceIfNonEmpty($.getAddressId(), atomicAddressId.get()));
            atomicUserDao.set(userRepository.save($));
        });


        if(atomicUserDao.get() == null) {
            this.error.setError("User Not Found.");
            return null; //Return requested object along with error message
        }

        return this.getUserProfileById(userId);
    }

    @Override
    public boolean deleteUserProfile(Integer userId) {
        AtomicBoolean status = new AtomicBoolean(false);
        userRepository.findById(userId).ifPresent($-> {
           userRepository.delete($);
           Optional<Integer> addrId = Optional.ofNullable($.getAddressId());
           addrId.ifPresent(id -> addressRepository.findById(id).ifPresent($$ -> addressRepository.delete($$)));
           status.set(true);
        });

        return status.get();
    }
}
