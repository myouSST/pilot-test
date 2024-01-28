package com.example.copilottest.user.service.crud;

import java.util.List;

import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.spec.store.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStore userStore;

    @Cacheable(value = "user", key = "#userId")
    public User find(String userId) {
        return userStore.findUser(userId);
    }

    public List<User> findAll() {
        return userStore.findAll();
    }

    @CachePut(value = "user", key = "#user.id")
    public User save(User user) {
        return userStore.saveUser(user);
    }

    @CacheEvict(value = "user", key = "#userId")
    public void delete(String userId) {
        userStore.deleteUser(userId);
    }
}
