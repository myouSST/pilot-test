package com.example.copilottest.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
public class Skill {
    private String name;

    private SkillType type;

    public static Skill sample() {
        return new Skill("REACT", SkillType.WEB_DEVELOP);
    }

    public static Skill sample2() {
        return new Skill("ANDROID", SkillType.APP_DEVELOP);
    }
}
