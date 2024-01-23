package com.example.copilottest.user.service.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.spec.store.UserStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserStore userStore;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFind() {
        // Arrange
        String userId = "testUserId";
        User expectedUser = User.sample();

        when(userStore.findUser(userId)).thenReturn(expectedUser);

        // Act
        User actualUser = userService.find(userId);

        // Assert
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testFindAll() {
        // Arrange
        User user1 = User.sample("1");
        User user2 = User.sample("2");
        List<User> expectedUsers = Arrays.asList(user1, user2);

        when(userStore.findAll()).thenReturn(expectedUsers);

        // Act
        List<User> actualUsers = userService.findAll();

        // Assert
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testSave() {
        // Arrange
        User user = User.sample("testUserId");
        when(userStore.saveUser(user)).thenReturn(user);

        // Act
        User savedUser = userService.save(user);

        // Assert
        assertEquals(user, savedUser);
    }

    @Test
    public void testDelete() {
        // Arrange
        String userId = "testUserId";

        // Act
        userService.delete(userId);

        // Verify
        verify(userStore, times(1)).deleteUser(userId);
    }
}