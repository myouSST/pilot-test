package com.example.copilottest.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UserTest {
    
    //generate tests for the methods in the User.class
    @Test
    void sample() {
        User user = User.sample();
        assertEquals("myou", user.getId());
        assertEquals("유민", user.getName());
        assertEquals("http://test-profile.co.kr", user.getProfileUrl());
    }

    @Test
    void sample2() {
        User user = User.sample2();
        assertEquals("hong", user.getId());
        assertEquals("홍길동", user.getName());
        assertEquals("http://test-profile.co.kr/hong", user.getProfileUrl());
    }

    @Test
    void sample3() {
        User user = User.sample("test");
        assertEquals("test", user.getId());
        assertEquals("test", user.getName());
        assertEquals("http://test-profile.co.kr", user.getProfileUrl());
    }

    @Test
    void getAllSkillNames() {
        User user = User.sample();
        assertEquals(2, user.getAllSkillNames().size());
    }

    @Test
    void canSales() {
        User user = User.sample();
        assertTrue(user.canSales());
    }

    @Test
    void addSkill() {
        User user = User.sample();
        user.addSkill(Skill.sample());
        assertEquals(3, user.getSkills().size());
    }

    @Test
    void removeSkill() {
        User user = User.sample();
        user.removeSkill(Skill.sample());
        assertEquals(1, user.getSkills().size());
    }
}