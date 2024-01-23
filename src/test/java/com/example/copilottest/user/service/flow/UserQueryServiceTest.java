package com.example.copilottest.user.service.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.example.copilottest.user.domain.IdList;
import com.example.copilottest.user.domain.Skill;
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
public class UserQueryServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private UserQueryService userQueryService;


    @Test
    public void testFind() {
        // Arrange
        String userId = "testUserId";
        IdList teamIds = IdList.of("team1", "team2");
        User user = new User(userId, userId, "http://test-profile.co.kr", List.of(Skill.sample(), Skill.sample2()), teamIds);

        Team team1 = new Team("team1", "team1", "team1");
        Team team2 = new Team("team2", "team2", "team2");

        when(userService.find(userId)).thenReturn(user);
        when(teamService.find("team1")).thenReturn(team1);
        when(teamService.find("team2")).thenReturn(team2);

        // Act
        UserRdo userRdo = userQueryService.find(userId);

        // Assert
        assertEquals(user.getName(), userRdo.getName());
        assertEquals(Arrays.asList(new TeamRdo(team1), new TeamRdo(team2)), userRdo.getTeams());
    }

    @Test
    public void testFindAll() {
        // Arrange
        IdList teamIds = IdList.of("team1", "team2");
        User user1 = new User("user1", "user1", "http://test-profile.co.kr", List.of(Skill.sample(), Skill.sample2()), teamIds);
        User user2 = new User("user2", "user2", "http://test-profile.co.kr", List.of(Skill.sample(), Skill.sample2()), teamIds);

        List<User> users = Arrays.asList(user1, user2);

        Team team1 = new Team("team1", "team1", "team1");
        Team team2 = new Team("team2", "team2", "team2");
        List<Team> teams = Arrays.asList(team1, team2);

        List<TeamRdo> expectedTeams = Arrays.asList(new TeamRdo(team1), new TeamRdo(team2));

        when(userService.findAll()).thenReturn(users);
        when(teamService.findAll()).thenReturn(teams);

        // Act
        List<UserRdo> userRdos = userQueryService.findAll();

        // Assert
        assertEquals(2, userRdos.size());
        assertEquals(user1.getName(), userRdos.get(0).getName());
        assertEquals(user2.getName(), userRdos.get(1).getName());
        assertEquals(expectedTeams, userRdos.get(0).getTeams());
        assertEquals(expectedTeams, userRdos.get(1).getTeams());
    }
}