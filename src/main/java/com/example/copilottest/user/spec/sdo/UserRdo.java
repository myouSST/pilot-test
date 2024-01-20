package com.example.copilottest.user.spec.sdo;

import java.util.List;

import com.example.copilottest.user.domain.Skill;
import com.example.copilottest.user.domain.Team;
import com.example.copilottest.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRdo {
    public String id;

    public String name;

    private String profileUrl;

    private List<Skill> skills;

    private List<TeamRdo> teams;

    public UserRdo(User user, List<Team> teams) {
        this.id = user.getId();
        this.name = user.getName();
        this.profileUrl = user.getProfileUrl();
        this.skills = user.getSkills();
        this.teams = teams.stream().map(TeamRdo::new).toList();
    }
}
