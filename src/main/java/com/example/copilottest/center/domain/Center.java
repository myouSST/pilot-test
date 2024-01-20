package com.example.copilottest.center.domain;

import com.example.copilottest.user.domain.IdList;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Center {
    private String id;

    private String name;

    private String address;

    private String tel;

    private String description;

    private IdList members;

    public static Center sample() {
        return new Center("spectra", "스펙트라", "서초", "02-1111-2222", "서초에 위치한 좋은 회사", IdList.of("hong", "lee"));
    }

    public static Center sample(String centerId, String memberId) {
        return new Center(centerId, centerId, "서초", "02-1111-2222", "서초에 위치한 좋은 회사", IdList.of(memberId));
    }

    public void addMember(String memberId) {
        members.add(memberId);
    }

    public void removeMember(String memberId) {
        members.remove(memberId);
    }
}
