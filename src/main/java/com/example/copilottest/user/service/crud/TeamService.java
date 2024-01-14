package com.example.copilottest.user.service.crud;

import java.util.Arrays;
import java.util.List;

import com.example.copilottest.user.domain.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final List<Team> teams = Arrays.asList(Team.sample1(), Team.sample2());

    public Team find(String teamId) {
        return teams.stream()
            .filter(team -> team.getId().equals(teamId))
            .findFirst()
            .orElse(Team.sample1());
    }

    public List<Team> findAll() {
        return teams;
    }
}
