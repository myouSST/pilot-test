package com.example.copilottest.user.domain.util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.copilottest.user.domain.Team;

public class ListUtil {
    public static <K, V> Map<K, V> toMap(List<V> list, Function<V, K> keyFunction) {
        return list.stream().collect(Collectors.toMap(keyFunction, v -> v));
    }
}