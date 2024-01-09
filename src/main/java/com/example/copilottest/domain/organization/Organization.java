package com.example.copilottest.domain.organization;

import java.util.Arrays;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
@ToString
public class Organization {

    private String name;

    private List<OrganizationType> types;

    public static Organization sample() {
        return new Organization("spectra", Arrays.asList(OrganizationType.TECH, OrganizationType.SALES));
    }
}
