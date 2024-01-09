package com.example.copilottest.domain;

import com.example.copilottest.domain.organization.Organization;
import com.example.copilottest.domain.organization.OrganizationType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class User {

    private String id;

    private String name;

    private String profileUrl;

    private Organization organization;

    public static User sample() {
        return new User("myou", "유민", "http://test-profile.co.kr", Organization.sample());
    }

    public void isTechOrganization() {
        return organization.has(OrganizationType.TECH);
    }
}
