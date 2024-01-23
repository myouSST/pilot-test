package com.example.copilottest.user.service.crud;

import static org.junit.jupiter.api.Assertions.*;

import com.example.copilottest.user.domain.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {
    private final TeamService teamService = new TeamService();

    @Test
    public void testFind() {
        Team team = teamService.find("c-team");
        assertEquals("c-team", team.getId());
    }

    @Test
    public void testFindAll() {
        assertEquals(2, teamService.findAll().size());
    }
}