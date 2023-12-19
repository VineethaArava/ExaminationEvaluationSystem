package com.examination.app.controller;

import com.examination.app.dto.BasicDTO;
import com.examination.app.dto.LoginRequestDTO;
import com.examination.app.dto.LoginResponseDTO;
import com.examination.app.dto.UserDTO;
import com.examination.app.entity.User_App;
import com.examination.app.service.UserService;
import com.examination.app.util.UserRoleEnum;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    Gson gson = new Gson();
    @BeforeEach
    void setUp() {
    }

    @Test
    void registerUser() throws Exception{
        UserDTO user = new UserDTO(1,"user","user@gmail.com","123", UserRoleEnum.EXAMINER);
        when(userService.registerUser(user)).thenReturn(user);
        this.mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(user))
        ).andExpect(status().isCreated());
    }

    @Test
    void login() throws Exception{
        LoginResponseDTO lr = new LoginResponseDTO("someRangomToken", new User_App(1,"user","user@gmail.com",null, UserRoleEnum.EXAMINER));
        LoginRequestDTO rl = new LoginRequestDTO("user@gmail.com","123");
        when(userService.signIn("user@gmail.com","123")).thenReturn(lr);

        BasicDTO<LoginResponseDTO> res = new BasicDTO<>(true,"Successfully", lr);
        this.mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(rl)))
                .andDo(print()).andExpect(status().isOk());
//                .andExpect(content().json(gson.toJson(res)));
    }
}