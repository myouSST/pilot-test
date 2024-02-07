package com.example.copilottest.user.domain;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserUnitTest {
    @Test
    public void testUserSample() {
        User user = User.sample();
        Assertions.assertEquals("myou", user.getId());
        Assertions.assertEquals("유민", user.getName());
        Assertions.assertEquals("http://test-profile.co.kr", user.getProfileUrl());
        Assertions.assertTrue(user.getTeamIds().contains("c-team"));
    }
    @Test
    public void testUserSample2() {
        User user = User.sample2();
        Assertions.assertEquals("hong", user.getId());
        Assertions.assertEquals("홍길동", user.getName());
        Assertions.assertEquals("http://test-profile.co.kr/hong", user.getProfileUrl());
        Assertions.assertTrue(user.getTeamIds().contains("t1"));
    }
    @Test
    public void testUserSampleWithUserId() {
        String userId = "jay_TestUserId";
        User user = User.sample(userId);
        Assertions.assertEquals(userId, user.getId());
    }
    @Test
    public void testGetAllSkillNames() {
        User user = User.sample();
        Assertions.assertEquals(Arrays.asList("SampleSkill", "SampleSkill2"), user.getAllSkillNames());
    }
    @Test
    public void testCanSales() {
        User user = User.sample();
        Assertions.assertFalse(user.canSales());
    }
    @Test
    public void testAddSkill() {
        User user = User.sample();
        Skill newSkill = new Skill("Coding", SkillType.WEB_DEVELOP);
        user.addSkill(newSkill);
        Assertions.assertTrue(user.getAllSkillNames().contains(newSkill.getName()));
    }
    @Test
    public void testRemoveSkill() {
        User user = User.sample();
        Skill existingSkill = user.getSkills().get(0);
        user.removeSkill(existingSkill);
        Assertions.assertFalse(user.getAllSkillNames().contains(existingSkill.getName()));
    }
}