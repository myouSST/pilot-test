package com.example.copilottest.user.service.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @InjectMocks
    private UserQueryService userQueryService;

    @Mock
    private UserService userService;

    @Mock
    private TeamService teamService;

    @Test
    void find() {
        when(userService.find("myou"))
            .thenReturn(User.sample());
        when(teamService.find("c-team"))
            .thenReturn(Team.sample());

        UserRdo userRdo = userQueryService.find("myou");

        assertEquals("myou", userRdo.getId());
    }

    @Test
    void findAll() {
        when(userService.findAll())
            .thenReturn(Arrays.asList(User.sample(), User.sample2()));
        when(teamService.findAll())
            .thenReturn(getTeamSamples());

        assertEquals(2, userQueryService.findAll().size());
    }

    private static List<Team> getTeamSamples() {
        return Arrays.asList(
            new Team("c-team", "C팀", ""),
            new Team("t1", "팀1", ""),
            new Team("t2", "팀2", ""),
            new Team("t3", "팀3", "")
        );
    }
}