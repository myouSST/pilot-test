package com.example.copilottest.user.domain;

import java.util.List;

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
public class User {

    private String id;

    private String name;

    private String profileUrl;

    private List<Skill> skills;

    public static User sample() {
        return new User("myou", "유민", "http://test-profile.co.kr", List.of(Skill.sample(), Skill.sample2()));
    }

    public List<String> getAllSkillNames() {
        return skills.stream().map(Skill::getName).toList();
    }

    public boolean canSales() {
        return skills.stream().anyMatch(skill -> skill.getType() == SkillType.SALES);
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void removeSkill(Skill role) {
        skills.remove(role);
    }

    public static void main(String[] args) {
        System.out.println(sample());
        System.out.println(sample().getAllSkillNames());
    }
}
