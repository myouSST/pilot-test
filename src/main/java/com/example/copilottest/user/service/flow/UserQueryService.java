package com.example.copilottest.user.service.flow;

import java.util.List;
import java.util.Map;

import com.example.copilottest.user.domain.Team;
import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.spec.sdo.UserRdo;
import com.example.copilottest.user.domain.util.ListUtil;
import com.example.copilottest.user.service.crud.TeamService;
import com.example.copilottest.user.service.crud.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {
    private final UserService userService;

    private final TeamService teamService;

    public UserRdo find(String userId) {
        User user = userService.find(userId);
        List<Team> teams = user.getTeamIds().stream()
            .map(this::getTeam)
            .toList();

        return new UserRdo(user, teams);
    }

    public List<UserRdo> findAll() {
        List<User> users = userService.findAll();
        Map<String, Team> teamMap = ListUtil.toMap(teamService.findAll(), Team::getId);

        return users.stream()
            .map(user -> new UserRdo(user, getTeams(user, teamMap)))
            .toList();
    }

    private List<Team> getTeams(User user, Map<String, Team> teamMap) {
        return user.getTeamIds().stream()
            .map(teamMap::get)
            .toList();
    }

    private Team getTeam(String teamId) {
        return teamService.find(teamId);
    }
}
