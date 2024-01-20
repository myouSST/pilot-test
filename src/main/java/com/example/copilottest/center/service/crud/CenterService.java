package com.example.copilottest.center.service.crud;

import java.util.List;

import com.example.copilottest.center.domain.Center;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CenterService {
    public Center findCenter(String centerId) {
        if (centerId.equals("exception")) {
            throw new RuntimeException("exception");
        }

        return Center.sample();
    }

    public List<Center> findAll() {
        return List.of(Center.sample());
    }

    public Center saveCenter(Center center) {
        if (center.getId().equals("exception")) {
            throw new RuntimeException("exception");
        }

        return center;
    }

    //joinMember
    public void joinMember(String centerId, String userId) {
        Center center = findCenter(centerId);
        List<String> members = center.getMembers();
        if(members.contains(userId)){
            throw new IllegalArgumentException("User already a member of the center: " + centerId);
        }

        center.addMember(userId);
    }

    public void deleteCenter(String centerId) {
        // TODO
    }
}
