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
        checkInputValidity(userManageCdo);
        validateUserExistence(userManageCdo.getUserId());
        Center center = validateCenterExistence(userManageCdo.getCenterId());

        joinCenter(userManageCdo.getCenterId(), userManageCdo.getUserId());
        User user = saveUser(userManageCdo.getUserId());
        return new UserManageRdo(user, center);
    }

    private void checkInputValidity(UserManageCdo userManageCdo) {
        if (userManageCdo == null || userManageCdo.getUserId() == null || userManageCdo.getCenterId() == null) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    private void validateUserExistence(String userId) {
        User current = userService.find(userId);
        if (current != null) {
            throw new IllegalArgumentException("User with ID " + userId + " already exists!");
        }
    }

    private Center validateCenterExistence(String centerId) {
        Center center = centerService.findCenter(centerId);
        if (center == null) {
            throw new IllegalArgumentException("Center with ID " + centerId + " could not be found!");
        }
        return center;
    }

    private void joinCenter(String centerId, String userId) {
        try {
            centerService.joinMember(centerId, userId);
        } catch (IllegalArgumentException e) {
            System.out.println("User is already a member!");
            throw e;
        }
    }

    private User saveUser(String userId) {
        return userService.save(User.sample(userId));
    }
}
