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
public class UserManageService {
    private final CenterService centerService;

    private final UserService userService;

    public UserManageRdo createUser(UserManageCdo userManageCdo) {
        User user = userService.save(User.sample(userManageCdo.getUserId()));
        centerService.joinMember(userManageCdo.getCenterId(), userManageCdo.getUserId());

        Center center = centerService.findCenter(userManageCdo.getCenterId());
        return new UserManageRdo(user, center);
    }
}
