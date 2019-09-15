package com.consumer_reports.codetest.core;

import com.consumer_reports.codetest.daos.AddressDao;
import com.consumer_reports.codetest.model.User;

public class Utils {
    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static String replaceIfNonEmpty(String oldValue, String newValue) {
        return isEmpty(newValue) ? oldValue : newValue;
    }

    public static Integer replaceIfNonEmpty(Integer oldValue, Integer newValue) {
        return newValue == null || newValue <= 0 ? oldValue : newValue;
    }

    public static AddressDao updateAddressWithRequest(AddressDao addrDao, User user){
        addrDao.setStreet(replaceIfNonEmpty(addrDao.getStreet(), user.getAddress().getStreet()));
        addrDao.setCity(replaceIfNonEmpty(addrDao.getCity(), user.getAddress().getCity()));
        addrDao.setState(replaceIfNonEmpty(addrDao.getState(), user.getAddress().getState()));
        addrDao.setZip(replaceIfNonEmpty(addrDao.getZip(), user.getAddress().getZip()));
        return addrDao;
    }
}
