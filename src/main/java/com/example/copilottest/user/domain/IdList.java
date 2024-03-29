package com.example.copilottest.user.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class IdList extends ArrayList<String> {

    public IdList(List<String> ids) {
        super(ids);
    }

    public IdList(String... ids) {
        super(Arrays.asList(ids));
    }

    public static IdList of(String... ids) {
        return new IdList(ids);
    }
}
