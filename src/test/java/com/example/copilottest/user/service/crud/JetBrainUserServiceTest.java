package com.example.copilottest.user.service.crud;

import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.domain.spec.store.UserStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JetBrainUserServiceTest {

    @Mock
    UserStore userStore;

    @InjectMocks
    UserService userService;

    @Test
    public void findUserTest() {
        User user = User.sample();
        when(userStore.findUser("TEST_ID")).thenReturn(user);

        User result = userService.findUser("TEST_ID");
        assertEquals(user, result);
        verify(userStore, times(1)).findUser("TEST_ID");
    }

    @Test
    public void findAllTest() {
        User user1 = User.sample();
        User user2 = User.sample2();
        when(userStore.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> result = userService.findAll();
        assertEquals(2, result.size());
        verify(userStore, times(1)).findAll();
    }

    @Test
    public void saveUserTest() {
        User user = User.sample();
        when(userStore.saveUser(user)).thenReturn(user);

        User result = userService.saveUser(user);
        assertEquals(user, result);
        verify(userStore, times(1)).saveUser(user);
    }

    @Test
    public void deleteUserTest() {
        doNothing().when(userStore).deleteUser("TEST_ID");

        userService.deleteUser("TEST_ID");
        verify(userStore, times(1)).deleteUser("TEST_ID");
    }
}