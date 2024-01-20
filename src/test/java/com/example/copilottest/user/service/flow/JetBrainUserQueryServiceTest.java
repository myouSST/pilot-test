package com.example.copilottest.user.service.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
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
public class JetBrainUserQueryServiceTest {
    @Mock
    private UserService userService;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private UserQueryService userQueryService;

    @Test
    void testFind() {
        // Given
        User user = User.sample();
        when(userService.find(anyString())).thenReturn(user);

        Team team1 = Team.sample1();
        Team team2 = Team.sample2();

        when(teamService.find(anyString())).thenReturn(team1);

        // When
        UserRdo result = userQueryService.find("myou");

        // Then
        assertEquals("myou", result.getId());
        assertEquals("유민", result.getName());
        List<TeamRdo> teams = result.getTeams();
        assertEquals(1, teams.size());

        TeamRdo resultTeam1 = teams.get(0);
        assertEquals("c-team", resultTeam1.getId());
        assertEquals("C 팀", resultTeam1.getName());
    }

    @Test
    public void testFindAll() {
        // Given
        User user1 = new User("myou", "유민", "http://test-profile.co.kr", List.of(Skill.sample(), Skill.sample2()), IdList.of("team1", "team2"));
        User user2 = new User("myou2", "유민2", "http://test-profile.co.kr", List.of(Skill.sample()), IdList.of("team3", "team4"));

        Team team1 = new Team("team1", "팀1", "팀1");
        Team team2 = new Team("team2", "팀2", "팀2");
        Team team3 = new Team("team3", "팀3", "팀3");
        Team team4 = new Team("team4", "팀4", "팀4");

        when(userService.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(teamService.findAll()).thenReturn(Arrays.asList(team1, team2, team3, team4));

        // When
        List<UserRdo> userRdos = userQueryService.findAll();

        // Then
        assertEquals(2, userRdos.size());

        assertTrue(userRdos.stream().allMatch(rdo -> rdo.getTeams().size() == 2));
    }
}