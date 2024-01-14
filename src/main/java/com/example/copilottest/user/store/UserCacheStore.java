package com.example.copilottest.user.store;

import java.util.ArrayList;
import java.util.List;

import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.domain.spec.store.UserStore;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class UserCacheStore implements UserStore {
    private List<User> userList;

    public UserCacheStore() {
        userList = new ArrayList<>();
    }

    @Cacheable(value = "users", key = "#userId")
    public User findUser(String userId) {
        return userList.stream()
            .filter(user -> user.getId().equals(userId))
            .findFirst()
            .orElse(null);
    }

    @Cacheable(value = "users", key = "#user.id")
    public User saveUser(User user) {
        userList.add(user);
        return user;
    }

    @CacheEvict(value = "users", key = "#userId")
    public void deleteUser(String userId) {
        userList.removeIf(user -> user.getId().equals(userId));
    }

    @Override
    public List<User> findAll() {
        return userList;
    }
}
