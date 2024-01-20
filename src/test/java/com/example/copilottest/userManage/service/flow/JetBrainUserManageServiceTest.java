package com.example.copilottest.userManage.service.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.copilottest.center.domain.Center;
import com.example.copilottest.center.service.crud.CenterService;
import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.service.crud.UserService;
import com.example.copilottest.userManage.spec.UserManageCdo;
import com.example.copilottest.userManage.spec.UserManageRdo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JetBrainUserManageServiceTest {

    @InjectMocks
    UserManageService userManageService;

    @Mock
    UserService userService;

    @Mock
    CenterService centerService;

    @Test
    void createUserTest() {
        UserManageCdo userManageCdo = new UserManageCdo("user1", "center1");
        // User user = new User("user1");
        // Center center = new User("center1");
        User user = User.sample();
        Center center = Center.sample();

        when(userService.find(any())).thenReturn(null);
        when(userService.save(any())).thenReturn(user);
        doNothing().when(centerService).joinMember(any(), any());
        when(centerService.findCenter(any())).thenReturn(center);

        UserManageRdo result = userManageService.createUser(userManageCdo);

        assertNotNull(result);
        assertEquals(user.getName(), result.getUserName());
        //assertEquals(user, result.getUser());
        assertEquals(center.getName(), result.getCenterName());
        //assertEquals(center, result.getCenter());

        verify(userService, times(1)).find(any());
        verify(userService, times(1)).save(any());
        verify(centerService, times(1)).joinMember(any(), any());
        verify(centerService, times(1)).findCenter(any());
    }

    @Test
    void createUserTest_WhenUserExists() {
        UserManageCdo userManageCdo = new UserManageCdo("user1", "center1");
        //User user = new User("user1");
        User user = User.sample();

        when(userService.find(any())).thenReturn(user);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userManageService.createUser(userManageCdo);
        });

        String expectedMessage = "User already exists with ID: user1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}