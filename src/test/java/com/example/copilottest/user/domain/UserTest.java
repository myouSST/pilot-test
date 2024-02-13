package com.example.copilottest.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void sampleShouldCreateUserWithAllFields() {
        String userId = "customUserId";
        User user = User.sample(userId);

        assertEquals(userId, user.getId());
        assertEquals(userId, user.getName());
        assertEquals("http://test-profile.co.kr", user.getProfileUrl());
        assertEquals(Role.USER, user.getRole());

        List<Skill> skills = user.getSkills();
        assertEquals(2, skills.size());
        assertTrue(skills.stream().anyMatch(skill -> skill.getType() == SkillType.WEB_DEVELOP));
        assertTrue(skills.stream().anyMatch(skill -> skill.getType() == SkillType.APP_DEVELOP));

        assertEquals(List.of("c-team"), user.getTeamIds());
    }

    @Test
    void shouldNotRemoveEssentialSkill() {
        User admin = User.sample2();
        User user = User.sample();
        Skill essentialSkill = new Skill("essential", SkillType.COMMUNICATION);
        user.addSkill(essentialSkill);
        assertThrows(IllegalArgumentException.class, () -> user.removeSkill(essentialSkill, admin));
    }

    @Test
    void shouldNotAllowNonAdminToRemoveSkill() {
        User nonAdmin = User.sample();
        User user = User.sample2();
        Skill skill = new Skill("skill", SkillType.SALES);
        user.addSkill(skill);
        assertThrows(IllegalArgumentException.class, () -> user.removeSkill(skill, nonAdmin));
    }

    @Test
    void shouldRemoveSkillByAdmin() {
        User admin = User.sample2();
        User user = User.sample();
        Skill skill = new Skill("skill", SkillType.SALES);
        user.addSkill(skill);
        user.removeSkill(skill, admin);
        assertFalse(user.getSkills().contains(skill));
    }
}