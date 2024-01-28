package com.example.copilottest.userManage.service.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
class UserManageServiceTest {

    @Mock
    private CenterService centerService;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserManageService userManageService;

    @Test
    void createUser() {
        // 가짜 데이터 및 가짜 동작 정의
        UserManageCdo userManageCdo = new UserManageCdo("testUserId", "testCenterId");
        User user = User.sample(userManageCdo.getUserId());
        Center center = Center.sample();
        when(userService.save(any(User.class))).thenReturn(user);
        when(centerService.findCenter(userManageCdo.getCenterId())).thenReturn(center);

        // 테스트
        UserManageRdo userManageRdo = userManageService.createUser(userManageCdo);

        // 검증
        assertEquals(user.getId(), userManageRdo.getUserId());
        assertEquals(center.getId(), userManageRdo.getCenterId());
        verify(userService, times(1)).save(any(User.class));
        verify(centerService, times(1)).joinMember(userManageCdo.getCenterId(), userManageCdo.getUserId());
        verify(centerService, times(1)).findCenter(userManageCdo.getCenterId());
    }

    // 추가적인 테스트 케이스를 필요에 따라 작성할 수 있습니다.

}