package com.example.copilottest.userManage.service.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
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
public class JetBrainUserManageServiceUnitTest {

    @Mock
    private CenterService centerService;

    @Mock
    private UserService userService;

    @InjectMocks
    private JetBrainUserManageService userManageService;

    @Test
    void shouldCreateUserWhenDataIsValid() {
        UserManageCdo cdo = new UserManageCdo("userId1", "centerId1");

        User user = User.sample("userId1");
        Center center = Center.sample("centerId1", "userId");

        when(userService.find(anyString())).thenReturn(null);
        when(centerService.findCenter(anyString())).thenReturn(center);
        doNothing().when(centerService).joinMember(anyString(), anyString());

        when(userService.save(any(User.class))).thenReturn(user);

        UserManageRdo result = userManageService.createUser(cdo);

        assertEquals(user.getName(), result.getUserName());
        assertEquals(center.getName(), result.getCenterName());
    }

    @Test
    void shouldThrowExceptionWhenCdoIsNull() {
        assertThrows(IllegalArgumentException.class, () -> userManageService.createUser(null));
    }

    @Test
    void shouldThrowExceptionWhenUserIdorCenterIdNullInCdo() {
        UserManageCdo cdo = new UserManageCdo(null, null);

        assertThrows(IllegalArgumentException.class, () ->
            userManageService.createUser(cdo));
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        UserManageCdo cdo = new UserManageCdo("userId1", "centerId1");

        when(userService.find(anyString())).thenReturn(User.sample("userId1"));

        assertThrows(IllegalArgumentException.class, () ->
            userManageService.createUser(cdo));
    }

    @Test
    void shouldThrowExceptionWhenCenterNotFound() {
        UserManageCdo cdo = new UserManageCdo("userId1", "centerId1");

        when(userService.find(anyString())).thenReturn(null);
        when(centerService.findCenter(anyString())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () ->
            userManageService.createUser(cdo));
    }
}