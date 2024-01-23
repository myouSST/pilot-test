package com.example.copilottest.userManage.service.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
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
public class UserManageServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private CenterService centerService;

    @InjectMocks
    private UserManageService userManageService;

    @Test
    public void testCreateUser() {
        // Given
        UserManageCdo userManageCdo = new UserManageCdo("userId", "centerId");
        User user = User.sample();
        Center center = Center.sample();
        when(userService.save(any(User.class))).thenReturn(user);
        when(centerService.findCenter(anyString())).thenReturn(center);

        // When
        UserManageRdo result = userManageService.createUser(userManageCdo);

        // Then
        assertEquals(user.getName(), result.getUserName());
        assertEquals(center.getName(), result.getCenterName());

        verify(userService, times(1)).save(any(User.class));
        verify(centerService, times(1)).joinMember(eq("centerId"), eq("userId"));
        verify(centerService, times(1)).findCenter(eq("centerId"));
    }
}