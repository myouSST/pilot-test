package com.example.copilottest.user.service.crud;

import java.util.List;

import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.domain.spec.store.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStore userStore;

    public User findUser(String userId) {
        return userStore.findUser(userId);
    }

    public List<User> findAll() {
        return userStore.findAll();
    }

    public User saveUser(User user) {
        return userStore.saveUser(user);
    }

    public void deleteUser(String userId) {
        userStore.deleteUser(userId);
    }
}
