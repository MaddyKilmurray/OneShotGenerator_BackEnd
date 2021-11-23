package com.kilmurray.dnd_userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kilmurray.dnd_userservice.dao.UserDao;
import com.kilmurray.dnd_userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        testDMUser1 = new UserDao(1L,"testDM1","password","dm@one.com",true,1L);
        testDMUser2 = new UserDao(2L,"testDM2","password","dm@two.com",true,2L);
        testPlayerUser1 = new UserDao(3L,"testPlayer1","password","player@one.com",true,1L);
        testPlayerUser2 = new UserDao(4L,"testPlayer2","password","player@two.com",true,1L);

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
}
