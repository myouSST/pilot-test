package com.example.copilottest.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void sample() {
        User user = User.sample();
        assertEquals("myou", user.getId());
        assertEquals("유민", user.getName());
        assertEquals("http://test-profile.co.kr", user.getProfileUrl());
        assertEquals(IdList.of("c-team"), user.getTeamIds());
    }

    @Test
    void sample2() {
        User user = User.sample2();
        assertEquals("hong", user.getId());
    }

    @Test
    void testSample() {
        User user = User.sample("test");
        assertEquals("test", user.getId());
    }

    @Test
    void getAllSkillNames() {
        User user = User.sample();
        assertEquals(List.of("REACT", "ANDROID"), user.getAllSkillNames());
    }

    @Test
    void canSales() {
        User user = new User("myou", "유민", "http://test-profile.co.kr", new ArrayList<>(List.of(new Skill("sales", SkillType.SALES))), IdList.empty());
        assertTrue(user.canSales());
    }

    @Test
    void addSkill() {
        User user = User.sample();
        Skill newSkill = new Skill("test", SkillType.TEST);
        user.addSkill(newSkill);
        assertTrue(user.getSkills().contains(newSkill));
    }

    @Test
    void removeSkill() {
        User user = User.sample();
        Skill removeSkill = new Skill("REACT", SkillType.WEB_DEVELOP);
        assertTrue(user.getSkills().contains(removeSkill));

        user.removeSkill(removeSkill);
        assertFalse(user.getSkills().contains(removeSkill));
    }
}