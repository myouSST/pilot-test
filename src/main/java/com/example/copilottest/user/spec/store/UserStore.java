package com.example.copilottest.user.spec.store;

import java.util.List;

import com.example.copilottest.user.domain.User;

public interface UserStore {

    User findUser(String userId);

    User saveUser(User user);

    void deleteUser(String userId);

    List<User> findAll();
}
