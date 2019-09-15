package com.consumer_reports.codetest.controller;

import com.consumer_reports.codetest.core.ApiError;
import com.consumer_reports.codetest.core.HateoasResponse;
import com.consumer_reports.codetest.model.User;
import com.consumer_reports.codetest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService service;

    @Autowired
    private ApiError error;

    @RequestMapping(method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public HateoasResponse<List<User>> getAllUsersProfile() {
        List<User> users = service.getAllUsersProfile();

        return new HateoasResponse<List<User>>(
                linkTo(methodOn(UserResource.class).getAllUsersProfile()).withSelfRel(),
                users,
                Optional.of(error));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaTypes.HAL_JSON_VALUE)
    public HateoasResponse<User> createNewUserProfile(@RequestBody User user) {
        User createdUser = this.service.createNewUserProfile(user);

        return new HateoasResponse<User>(
                linkTo(this.getClass()).slash(createdUser.getUserId()).withSelfRel(),
                createdUser,
                Optional.of(error));
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{userId}", produces = MediaTypes.HAL_JSON_VALUE)
    public HateoasResponse<User> updateExistingUser(@PathVariable Integer userId, @RequestBody User user) {
        User modifiedUser = this.service.updateUserProfile(userId, user);

        return new HateoasResponse<User>(
                linkTo(this.getClass()).slash(userId).withSelfRel(),
                modifiedUser,
                Optional.of(error));
    }

    @RequestMapping(method = RequestMethod.GET, value="/{userId}", produces = MediaTypes.HAL_JSON_VALUE)
    public HateoasResponse<User> getUserById(@PathVariable Integer userId) {
        User user = service.getUserProfileById(userId);

        return new HateoasResponse<User>(
                linkTo(UserResource.class).slash(userId).withSelfRel(),
                user,
                Optional.of(error));
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{userId}", produces = MediaTypes.HAL_JSON_VALUE)
    public boolean deleteUserById(@PathVariable Integer userId) {
        return this.service.deleteUserProfile(userId);
    }
}
