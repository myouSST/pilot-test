package com.example.copilottest.user.domain.spec.sdo;

import com.example.copilottest.user.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamRdo {

    private String id;

    private String name;

    private String description;

    public TeamRdo(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.description = team.getDescription();
    }
}
