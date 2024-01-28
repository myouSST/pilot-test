package com.example.copilottest.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void getAllSkillNames() {
        User user = User.sample();
        assertEquals(List.of("Java", "Python"), user.getAllSkillNames());
    }

    @Test
    void canSalesWithSalesSkill() {
        User user = User.sample();
        assertTrue(user.canSales());
    }

    @Test
    void canSalesWithoutSalesSkill() {
        User user = User.sample2();
        assertFalse(user.canSales());
    }

    @Test
    void addSkill() {
        User user = User.sample();
        assertEquals(2, user.getSkills().size());

        Skill newSkill = new Skill("New Skill", SkillType.WEB_DEVELOP);
        user.addSkill(newSkill);

        assertEquals(3, user.getSkills().size());
        assertTrue(user.getSkills().contains(newSkill));
    }

    @Test
    void removeSkill() {
        User user = User.sample2();
        assertEquals(2, user.getSkills().size());

        Skill skillToRemove = user.getSkills().get(0);
        user.removeSkill(skillToRemove);

        assertEquals(1, user.getSkills().size());
        assertFalse(user.getSkills().contains(skillToRemove));
    }

    // Additional tests can be added as needed

}