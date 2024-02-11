package com.example.copilottest.user.service.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.example.copilottest.user.domain.IdList;
import com.example.copilottest.user.domain.Team;
import com.example.copilottest.user.domain.User;
import com.example.copilottest.user.service.crud.TeamService;
import com.example.copilottest.user.service.crud.UserService;
import com.example.copilottest.user.spec.sdo.UserRdo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserQueryServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private UserQueryService userQueryService;

    @Test
    public void testFind() {
        // Mocking userService and teamService to return expected values
        User expectedUser = new User("userId", "userName", "", IdList.empty(), IdList.of("a-team", "c-team"));
        when(userService.find("userId")).thenReturn(expectedUser);
        when(teamService.find("a-team")).thenReturn(Team.sample());
        when(teamService.find("c-team")).thenReturn(Team.sample1());

        UserRdo result = userQueryService.find("userId");

        assertEquals("userId", result.getId());
    }

    @Test
    public void testFindAll() {
        // Mocking userService and teamService to return expected values
        User expectedUser = new User("userId", "userName", "", IdList.empty(), IdList.of( "c-team"));
        User expectedUser2 = new User("userId2", "userName", "", IdList.empty(), IdList.of("a-team"));
        List<User> expectedUsers = Arrays.asList(expectedUser, expectedUser2);
        when(userService.findAll()).thenReturn(expectedUsers);
        when(teamService.findAll()).thenReturn(Arrays.asList(Team.sample(), Team.sample1()));

        List<UserRdo> result = userQueryService.findAll();

        assertEquals(2, result.size());
    }
}