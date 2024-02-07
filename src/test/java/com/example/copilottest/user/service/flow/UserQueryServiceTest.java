package com.example.copilottest.user.service.flow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.example.copilottest.user.domain.Team;
import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.service.crud.TeamService;
import com.example.copilottest.user.service.crud.UserService;
import com.example.copilottest.user.spec.sdo.TeamRdo;
import com.example.copilottest.user.spec.sdo.UserRdo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserQueryServiceTest {
    @Mock
    UserService userService;

    @Mock
    TeamService teamService;

    @InjectMocks
    UserQueryService userQueryService;

    @Test
    void shouldReturnUserRdoForGivenUserId() {
        // Given
        Team team = Team.sample("c-team");
        Team team2 = Team.sample("b-team");
        User user = User.sample("userID");
        user.getTeamIds().add("b-team");
        when(userService.find("userID")).thenReturn(user);
        when(teamService.find("c-team")).thenReturn(team);
        when(teamService.find("b-team")).thenReturn(team2);

        // When
        UserRdo resultUserRdo = userQueryService.find("userID");

        // Then
        assertThat(resultUserRdo.getId()).isEqualTo(user.getId());
        assertThat(resultUserRdo.getTeams()).containsExactly(new TeamRdo(team), new TeamRdo(team2));
    }

    @Test
    void testFindAll() {
        // Setting up mock values
        User user1 = User.sample("user1");
        User user2 = User.sample("user2");

        Team team1 = Team.sample("c-team");
        Team team2 = Team.sample2();

        given(userService.findAll())
            .willReturn(Arrays.asList(user1, user2));
        given(teamService.findAll())
            .willReturn(Arrays.asList(team1, team2));

        // Creating the instance to be tested
        UserQueryService userQueryService = new UserQueryService(userService, teamService);

        // Act
        List<UserRdo> result = userQueryService.findAll();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0)).extracting(UserRdo::getId, UserRdo::getName)
            .containsExactly(user1.getId(), user1.getName());
        assertThat(result.get(1)).extracting(UserRdo::getId, UserRdo::getName)
            .containsExactly(user2.getId(), user2.getName());
    }
}