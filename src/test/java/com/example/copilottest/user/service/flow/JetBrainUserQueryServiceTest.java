package com.example.copilottest.user.service.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import com.example.copilottest.user.domain.Team;
import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.domain.spec.sdo.UserRdo;
import com.example.copilottest.user.service.crud.TeamService;
import com.example.copilottest.user.service.crud.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JetBrainUserQueryServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private UserQueryService userQueryService;

    @BeforeEach
    public void setUp() {
        User user = User.sample();
        when(userService.findUser(user.getId())).thenReturn(user);

        List<Team> teams = List.of(Team.sample1());
        when(teamService.findAll()).thenReturn(teams);

        when(userService.findAll()).thenReturn(List.of(user));
    }

    @Test
    public void findUserTest() {
        String userId = "myou";
        UserRdo userRdo = userQueryService.find(userId);

        assertNotNull(userRdo);
        assertEquals(userId, userRdo.getId());
    }

    @Test
    public void findAllUsersTest() {
        List<UserRdo> users = userQueryService.findAll();

        assertNotNull(users);
    }
}