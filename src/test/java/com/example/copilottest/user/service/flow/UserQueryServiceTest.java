package com.example.copilottest.user.service.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.example.copilottest.user.domain.Team;
import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.service.crud.TeamService;
import com.example.copilottest.user.service.crud.UserService;
import com.example.copilottest.user.spec.sdo.UserRdo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserQueryServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private UserQueryService userQueryService;

    @Test
    void find() {
        // 가짜 데이터 및 가짜 동작 정의
        String userId = "testUserId";
        User user = User.sample();
        when(userService.find(userId)).thenReturn(user);
        when(teamService.find(anyString())).thenReturn(Team.sample());

        // 테스트
        UserRdo userRdo = userQueryService.find(userId);

        // 검증
        assertEquals(user.getId(), userRdo.getId());
        verify(userService, times(1)).find(userId);
        verify(teamService, times(1)).find(anyString());
    }

    @Test
    void findAll() {
        // 가짜 데이터 및 가짜 동작 정의
        List<User> users = Arrays.asList(User.sample(), User.sample2());
        List<Team> teams = Arrays.asList(Team.sample(), Team.sample2());
        when(userService.findAll()).thenReturn(users);
        when(teamService.findAll()).thenReturn(teams);

        // 테스트
        List<UserRdo> userRdos = userQueryService.findAll();

        // 검증
        assertEquals(users.size(), userRdos.size());
        verify(userService, times(1)).findAll();
        verify(teamService, times(1)).findAll();
    }

    // 추가적인 테스트 케이스를 필요에 따라 작성할 수 있습니다.

}