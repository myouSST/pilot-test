package com.example.copilottest.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Team {

    private String id;

    private String name;

    private String description;

    public static Team sample(String teamId) {
        return new Team(teamId, "A 팀", "Avengers");
    }

    public static Team sample1() {
        return new Team("c-team", "C 팀", "salesbridge 서비스를 제공합니다.");
    }

    public static Team sample2() {
        return new Team("b-team", "B 팀", "CS talk 서비스를 제공합니다.");
    }
}
