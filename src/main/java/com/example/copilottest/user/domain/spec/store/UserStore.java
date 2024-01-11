package com.example.copilottest.user.domain.spec.store;

import com.example.copilottest.user.domain.User;

public interface UserStore {

    User findUser(String userId);

    User saveUser(User user);

    void deleteUser(String userId);

}
