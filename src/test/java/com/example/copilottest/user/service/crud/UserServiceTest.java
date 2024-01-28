package com.example.copilottest.user.service.crud;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.example.copilottest.user.domain.IdList;
import com.example.copilottest.user.domain.Skill;
import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.spec.store.UserStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserStore userStore;

    @InjectMocks
    private UserService userService;

    @Test
    void find() {
        // 가짜 데이터 및 가짜 동작 정의
        String userId = "testUserId";
        User expectedUser = new User(userId, "Test User", "http://test-profile.co.kr", Arrays.asList(Skill.sample(), Skill.sample2()), IdList.of("c-team"));
        when(userStore.findUser(userId)).thenReturn(expectedUser);

        // 테스트
        User actualUser = userService.find(userId);

        // 검증
        assertEquals(expectedUser, actualUser);
        verify(userStore, times(1)).findUser(userId);
    }

    @Test
    void findAll() {
        // 가짜 데이터 및 가짜 동작 정의
        List<User> expectedUsers = Arrays.asList(User.sample(), User.sample2());
        when(userStore.findAll()).thenReturn(expectedUsers);

        // 테스트
        List<User> actualUsers = userService.findAll();

        // 검증
        assertEquals(expectedUsers, actualUsers);
        verify(userStore, times(1)).findAll();
    }

    @Test
    void save() {
        // 가짜 데이터 및 가짜 동작 정의
        User userToSave = User.sample();
        when(userStore.saveUser(userToSave)).thenReturn(userToSave);

        // 테스트
        User savedUser = userService.save(userToSave);

        // 검증
        assertEquals(userToSave, savedUser);
        verify(userStore, times(1)).saveUser(userToSave);
    }

    @Test
    void delete() {
        // 가짜 데이터 및 가짜 동작 정의
        String userIdToDelete = "testUserId";
        doNothing().when(userStore).deleteUser(userIdToDelete);

        // 테스트
        userService.delete(userIdToDelete);

        // 검증
        verify(userStore, times(1)).deleteUser(userIdToDelete);
    }

    // 추가적인 테스트 케이스를 필요에 따라 작성할 수 있습니다.

}