package com.example.copilottest.user.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.domain.spec.store.UserStore;
import com.example.copilottest.user.service.crud.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class JetBrainUserServiceTest {

    private UserService userService;

    @Mock
    private UserStore userStore;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userStore);
    }

    @Test
    void testFindUser() {
        String userId = "testUserId";

        when(userStore.findUser(userId)).thenReturn(User.sample());

        User user = userService.findUser(userId);

        verify(userStore, times(1)).findUser(userId);
        assertNotNull(user);
    }

    @Test
    void testSaveUser() {
        User user = User.sample();

        when(userStore.saveUser(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        verify(userStore, times(1)).saveUser(user);
        assertNotNull(savedUser);
    }

    @Test
    void testDeleteUser() {
        String userId = "testUserId";

        assertDoesNotThrow(() -> userService.deleteUser(userId));

        verify(userStore, times(1)).deleteUser(userId);
    }
}