package com.example.copilottest.user.domain;

public enum SkillType {
    WEB_DEVELOP(false),
    APP_DEVELOP(false),
    TEST(false),
    SALES(false),
    COMMUNICATION(true);

    private final boolean essential;

    SkillType(boolean essential) {
        this.essential = essential;
    }

    public boolean isEssential() {
        return essential;
    }
}