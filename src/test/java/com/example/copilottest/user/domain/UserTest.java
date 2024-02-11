package com.example.copilottest.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    public void testSampleUserCreation() {
        User user = User.sample();
        assertNotNull(user);
        assertEquals("myou", user.getId());
        assertEquals("유민", user.getName());
        assertEquals("http://test-profile.co.kr", user.getProfileUrl());
        assertEquals(2, user.getSkills().size());
        assertEquals("REACT", user.getSkills().get(0).getName()); // Replace "Skill1" with the expected name of the first skill
        assertEquals("ANDROID", user.getSkills().get(1).getName()); // Replace "Skill2" with the expected name of the second skill
        assertEquals(1, user.getTeamIds().size());
        assertEquals("c-team", user.getTeamIds().get(0));
    }

    @Test
    public void testSample2UserCreation() {
        User user = User.sample2();
        assertNotNull(user);
        assertEquals("hong", user.getId());
        assertEquals("홍길동", user.getName());
        assertEquals("http://test-profile.co.kr/hong", user.getProfileUrl());
        assertEquals(2, user.getSkills().size());
        assertEquals("REACT", user.getSkills().get(0).getName()); // Replace "Skill1" with the expected name of the first skill
        assertEquals("ANDROID", user.getSkills().get(1).getName()); // Replace "Skill2" with the expected name of the second skill
        assertEquals(3, user.getTeamIds().size());
        assertEquals("t1", user.getTeamIds().get(0));
        assertEquals("t2", user.getTeamIds().get(1));
        assertEquals("t3", user.getTeamIds().get(2));
    }

    @Test
    public void testSampleWithUserIdUserCreation() {
        User user = User.sample("testId");
        assertNotNull(user);
        assertEquals("testId", user.getId());
        assertEquals("testId", user.getName());
        assertEquals("http://test-profile.co.kr", user.getProfileUrl());
        assertEquals(2, user.getSkills().size());
        assertEquals("REACT", user.getSkills().get(0).getName()); // Replace "Skill1" with the expected name of the first skill
        assertEquals("ANDROID", user.getSkills().get(1).getName()); // Replace "Skill2" with the expected name of the second skill
        assertEquals(1, user.getTeamIds().size());
        assertEquals("c-team", user.getTeamIds().get(0));
    }

    @Test
    public void testGetAllSkillNames() {
        User user = new User("testId", "Test User", "http://test-profile.co.kr", List.of(Skill.sample(), Skill.sample2()), IdList.of("c-team"));
        List<String> skillNames = user.getAllSkillNames();
        assertEquals(2, skillNames.size());
        assertEquals("REACT", skillNames.get(0)); // Replace "Skill1" with the expected name of the first skill
        assertEquals("ANDROID", skillNames.get(1)); // Replace "Skill2" with the expected name of the second skill
    }

    @Test
    public void testCanSales() {
        User user = new User(
            "testId",
            "Test User",
            "http://test-profile.co.kr",
            List.of(new Skill("Skill1", SkillType.WEB_DEVELOP), new Skill("Skill2", SkillType.SALES)),
            IdList.of("c-team")
        );
        assertTrue(user.canSales());
    }

    @Test
    public void testAddSkill() {
        User user = User.sample();
        int initialSize = user.getSkills().size();
        user.addSkill(new Skill("NewSkill", SkillType.WEB_DEVELOP));
        assertEquals(initialSize + 1, user.getSkills().size());
        assertEquals("NewSkill", user.getSkills().get(initialSize).getName());
    }

    @Test
    public void testRemoveSkill() {
        User user = User.sample();
        Skill skillToRemove = user.getSkills().get(0);
        int initialSize = user.getSkills().size();
        user.removeSkill(skillToRemove);
        assertEquals(initialSize - 1, user.getSkills().size());
        assertFalse(user.getSkills().contains(skillToRemove));
    }
}