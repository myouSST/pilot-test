package com.example.copilottest.userManage.spec;

import java.util.List;

import com.example.copilottest.center.domain.Center;
import com.example.copilottest.user.domain.Skill;
import com.example.copilottest.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserManageRdo {
    private String userId;

    private String userName;

    private List<Skill> userSkills;

    private String centerId;

    private String centerName;


    public UserManageRdo(User user, Center center) {
        this.userId = user.getId();
        this.userName = user.getName();
        this.userSkills = user.getSkills();
        this.centerId = center.getId();
        this.centerName = center.getName();
    }
}
