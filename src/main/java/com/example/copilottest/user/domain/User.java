package com.example.copilottest.user.domain;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class User {

    private String id;

    private String name;

    private String profileUrl;

    private List<Skill> skills;

    private IdList teamIds;

    public static User sample() {
        return new User("myou", "유민", "http://test-profile.co.kr", List.of(Skill.sample(), Skill.sample2()), IdList.of("c-team"));
    }

    public static User sample2() {
        return new User(
            "hong", // 사용자 아이디
            "홍길동", // 사용자 이름
            "http://test-profile.co.kr/hong", // 프로필 URL
            List.of(Skill.sample(), Skill.sample2()), // 사용자 스킬
            IdList.of("t1", "t2", "t3") // 사용자가 속한 팀의 ID 리스트
        );
    }

    public static User sample(String userId) {
        return new User(userId, userId, "http://test-profile.co.kr", List.of(Skill.sample(), Skill.sample2()), IdList.of("c-team"));
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
