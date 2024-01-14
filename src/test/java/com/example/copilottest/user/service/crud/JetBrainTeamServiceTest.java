package com.example.copilottest.user.service.crud;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import com.example.copilottest.user.domain.Team;

@ExtendWith(MockitoExtension.class)
public class JetBrainTeamServiceTest {

    @InjectMocks
    private TeamService teamService;

    @Test
    void testFind() {
        String teamId = "c-team";
        Team team = teamService.find(teamId);
        assertEquals("C 팀", team.getName());
        assertEquals("salesbridge 서비스를 제공합니다.", team.getDescription());
    }

    @Test
    void testFind_notFound() {
        String teamId = "non-existent-team";
        Team team = teamService.find(teamId);
        assertEquals("C 팀", team.getName()); // default is sample1
        assertEquals("salesbridge 서비스를 제공합니다.", team.getDescription()); // default is sample1
    }

    @Test
    void testFindAll() {
        List<Team> allTeams = teamService.findAll();
        assertEquals(2, allTeams.size());
    }
}