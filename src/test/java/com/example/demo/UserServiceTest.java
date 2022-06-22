package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.Models.User;
import com.example.demo.mappers.user.UserMapper;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("anonymous")
class UserServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserMapper mapper;

    private static final String BASE_URL = "/api/user/";


    @Test
    @DisplayName("test find all User")
    void testFinAllUser() throws Exception {
        User user1 = new User();
        user1.setUserId(1);
        user1.setUserNm("ABc");
        List<User> users = new ArrayList<>();
        users.add(user1);
        when(mapper.searchAllUser(any())).thenReturn(users);

        mockMvc.perform(get(BASE_URL + "findAll").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
}
