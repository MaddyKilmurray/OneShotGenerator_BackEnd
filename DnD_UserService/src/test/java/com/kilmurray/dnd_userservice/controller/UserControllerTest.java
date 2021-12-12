package com.kilmurray.dnd_userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kilmurray.dnd_userservice.dao.UserDao;
import com.kilmurray.dnd_userservice.dto.EmailDto;
import com.kilmurray.dnd_userservice.dto.UsernameDto;
import com.kilmurray.dnd_userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UserDao testDMUser1;
    private UserDao testDMUser2;
    private UserDao testPlayerUser1;
    private UserDao testPlayerUser2;
    private UserDao testUser;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        testDMUser1 = new UserDao(1L,"testDM1","dm@one.com",true,123456L,"DM");
        testDMUser2 = new UserDao(2L,"testDM2","dm@two.com",true,123456L,"DM");
        testPlayerUser1 = new UserDao(3L,"testPlayer1","player@one.com",true,123456L,"DM");
        testPlayerUser2 = new UserDao(4L,"testPlayer2","player@two.com",true,123456L,"DM");

        userRepository.save(testDMUser1);
        userRepository.save(testDMUser2);
        userRepository.save(testPlayerUser1);
        userRepository.save(testPlayerUser2);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Test: GET all users. Returns all users as expected.")
    public void UserController_GetAllUsers_AsExpected() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users")
        ).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("testDM1"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("testPlayer2"));
    }

    @Test
    @DisplayName("Test: GET all users. Returns empty list when no characters exist.")
    public void UserController_GetAllUsers_Empty() throws Exception {
        userRepository.deleteAll();
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users")
        ).andExpect(status().isOk()).andReturn();
        assertFalse(mvcResult.getResponse().getContentAsString().contains("testDM1"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("testPlayer2"));
        assertTrue(mvcResult.getResponse().getContentAsString().equals("[]"));
    }

    @Test
    @DisplayName("Test: GET user by ID. Returns user as expected.")
    public void UserController_GetUserById_Success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users/" + 1L)
        ).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("testDM1"));
    }

    @Test
    @DisplayName("Test: GET user by ID. Throws error as user doesn't exist.")
    public void UserController_GetUserById_Fail() throws Exception {
        ResultActions mvcResult = mockMvc.perform( MockMvcRequestBuilders.get("/api/users/" + 999))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));
    }

    @Test
    @DisplayName("Test: POST a new user. Creates user as expected")
    public void UserController_PostUser_Success() throws Exception {
        testUser = new UserDao(5L,"newTestUser","test@user.com",false,2L,"PLAYER");

        String body = objectMapper.writeValueAsString(testUser);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users").content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("newTestUser"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("test@user.com"));
    }

    @Test
    @DisplayName("Test: POST checks for valid username. Returns true.")
    public void UserController_VerifyUsername_Success() throws Exception {
        UsernameDto usernameDto = new UsernameDto(testDMUser1.getUsername());

        String body = objectMapper.writeValueAsString(usernameDto);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users/validate/username").content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("true"));
    }

    @Test
    @DisplayName("Test: POST checks for valid username. Returns false.")
    public void UserController_VerifyUsername_Fail() throws Exception {
        UsernameDto usernameDto = new UsernameDto("banana");

        String body = objectMapper.writeValueAsString(usernameDto);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users/validate/username").content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("false"));
    }

    @Test
    @DisplayName("Test: POST checks for valid email. Returns true.")
    public void UserController_VerifyEmail_Success() throws Exception {
        EmailDto email = new EmailDto(testDMUser1.getEmail());

        String body = objectMapper.writeValueAsString(email);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users/validate/email").content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("true"));
    }

    @Test
    @DisplayName("Test: POST checks for valid email. Returns false.")
    public void UserController_VerifyEmail_Fail() throws Exception {
        EmailDto email = new EmailDto("banana@test.com");

        String body = objectMapper.writeValueAsString(email);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/users/validate/email").content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("false"));
    }

}
