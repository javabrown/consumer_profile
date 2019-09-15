package com.consumer_reports.codetest.service;

import com.consumer_reports.codetest.core.ApiError;
import com.consumer_reports.codetest.model.User;

import java.util.List;
import java.util.Optional;

public interface ServiceI {
    List<User> getAllUsersProfile();
    User getUserProfileById(Integer userId);
    User createNewUserProfile(User user);
    User updateUserProfile(Integer userId, User user);
    boolean deleteUserProfile(Integer userId);
}
