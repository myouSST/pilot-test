package com.example.copilottest.user.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class User {

    private String id;

    private String name;

    private String profileUrl;

    private Role role;

    private List<Skill> skills;

    private IdList teamIds;

    public static User sample() {
        return new User("myou", "유민", "http://test-profile.co.kr", Role.USER, new ArrayList<>(Arrays.asList(Skill.sample(), Skill.sample2())), IdList.of("c-team"));
    }

    public static User sample2() {
        return new User(
            "hong", // 사용자 아이디
            "홍길동", // 사용자 이름
            "http://test-profile.co.kr/hong", // 프로필 URL
            Role.USER, // 사용자 역할
            new ArrayList<>(Arrays.asList(Skill.sample(), Skill.sample2())), // 사용자 스킬
            IdList.of("t1", "t2", "t3") // 사용자가 속한 팀의 ID 리스트
        );
    }

    public static User sample(String userId) {
        return new User(userId, userId, "http://test-profile.co.kr", Role.USER, new ArrayList<>(Arrays.asList(Skill.sample(), Skill.sample2())), IdList.of("c-team"));
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

    public void addSkills(Skill... skills) {
        this.skills.addAll(Arrays.asList(skills));
    }

    public void removeSkill(Skill skill, User requester) {
        if (skill.isEssential()) {
            throw new IllegalArgumentException("필수 스킬은 삭제할 수 없습니다.");
        }

        if (!requester.isAdministrator()) {
            throw new IllegalArgumentException("관리자만 스킬을 삭제할 수 있습니다.");
        }

        skills.remove(skill);
    }

    public boolean isAdministrator() {
        return role == Role.ADMIN;
    }

    public static void main(String[] args) {
        System.out.println(sample());
        System.out.println(sample().getAllSkillNames());
    }
}
