package com.example.copilottest.user.service.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
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
    void shouldReturnUserRdoWhenFindIsCalled() {
        User user = User.sample();
        Team team = Team.sample();
        when(userService.find(user.getId())).thenReturn(user);
        when(teamService.find(team.getId())).thenReturn(team);

        UserRdo result = userQueryService.find(user.getId());

        assertEquals(user.getId(), result.getId());
        assertEquals(team.getId(), result.getTeams().get(0).getId());
    }

    @Test
    void shouldReturnEmptyWhenUserHasNoTeams() {
        User user = User.sample();
        when(userService.find(user.getId())).thenReturn(user);

        UserRdo result = userQueryService.find(user.getId());

        assertEquals(user.getId(), result.getId());
        assertEquals(Collections.emptyList(), result.getTeams());
    }

    @Test
    void shouldReturnListOfUserRdoWhenFindAllIsCalled() {
        User user1 = User.sample();
        User user2 = User.sample2();
        Team team1 = Team.sample();
        Team team2 = Team.sample2();
        when(userService.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(teamService.findAll()).thenReturn(Arrays.asList(team1, team2));

        List<UserRdo> result = userQueryService.findAll();

        assertEquals(2, result.size());
        assertEquals(user1.getId(), result.get(0).getId());
        assertEquals(user2.getId(), result.get(1).getId());
    }

    @Test
    void shouldReturnEmptyListWhenNoUsersExist() {
        when(userService.findAll()).thenReturn(Collections.emptyList());

        List<UserRdo> result = userQueryService.findAll();

        assertEquals(Collections.emptyList(), result);
    }
}