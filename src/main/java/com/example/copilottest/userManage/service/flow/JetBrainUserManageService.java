package com.example.copilottest.userManage.service.flow;

import com.example.copilottest.center.domain.Center;
import com.example.copilottest.center.service.crud.CenterService;
import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.service.crud.UserService;
import com.example.copilottest.userManage.spec.UserManageCdo;
import com.example.copilottest.userManage.spec.UserManageRdo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JetBrainUserManageService {
    private final CenterService centerService;

    private final UserService userService;

    public UserManageRdo createUser(UserManageCdo userManageCdo) {
        // validate input
        if (userManageCdo == null || userManageCdo.getUserId() == null || userManageCdo.getCenterId() == null) {
            throw new IllegalArgumentException("Invalid input!");
        }

        // check if user exists & center exists before saving & joining
        User current = userService.find(userManageCdo.getUserId());
        if (current != null) {
            throw new IllegalArgumentException("User with ID " + userManageCdo.getUserId() + " already exists!");
        }

        Center center = centerService.findCenter(userManageCdo.getCenterId());
        if (center == null) {
            throw new IllegalArgumentException("Center with ID " + userManageCdo.getCenterId() + " could not be found!");
        }

        // catch exceptions for already existing members
        try {
            centerService.joinMember(userManageCdo.getCenterId(), userManageCdo.getUserId());
        } catch(IllegalArgumentException e) {
            // user already exists, handle accordingly
            System.out.println("User is already a member!");
            throw e;
        }

        User user = userService.save(User.sample(userManageCdo.getUserId()));

        return new UserManageRdo(user, center);
    }
}
