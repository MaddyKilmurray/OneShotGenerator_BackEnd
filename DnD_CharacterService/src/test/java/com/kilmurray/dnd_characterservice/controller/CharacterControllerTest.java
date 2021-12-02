package com.kilmurray.dnd_characterservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kilmurray.dnd_characterservice.dao.CharacterDao;
import com.kilmurray.dnd_characterservice.dto.CharacterDto;
import com.kilmurray.dnd_characterservice.repository.CharacterRepository;
import org.aspectj.lang.annotation.After;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CharacterControllerTest {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private CharacterDao testCharacter1;
    private CharacterDao testCharacter2;
    private CharacterDto newTestChar;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        testCharacter1 = new CharacterDao(1L,1L,"test character",1,0,"neutral good",
                "sword","shield","gloves","necklace",1,"dwarf",25,"con",2,"medium",
                "proficiency","weapon proficiencies", "languages", "traits",
                "paladin",12,50,"class skills","class proficiencies","saving throws","",
                15,15,15,15,15,15,15,15,15,15,15,15,15);
        testCharacter2 = new CharacterDao(2L,1L,"another test character",1,0,"neutral good",
                "bow","shield","gloves","necklace",1,"dwarf",25,"con",2,"medium",
                "proficiency","weapon proficiencies", "languages", "traits",
                "paladin",12,50,"class skills","class proficiencies","saving throws","",
                15,15,15,15,15,15,15,15,15,15,15,15,15);

        characterRepository.save(testCharacter1);
        characterRepository.save(testCharacter2);
    }

    @AfterEach
    public void tearDown() {characterRepository.deleteAll();}

    @Test
    @DisplayName("Test: GET all characters. Returns all characters as expected.")
    public void CharacterController_GetAllCharacters_AsExpected() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/character")
        ).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("another test character"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("bow"));
    }

    @Test
    @DisplayName("Test: GET all users. Returns empty list when no characters exist.")
    public void CharacterController_GetAllUsers_Empty() throws Exception {
        characterRepository.deleteAll();
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/character")
        ).andExpect(status().isOk()).andReturn();
        assertFalse(mvcResult.getResponse().getContentAsString().contains("another test character"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("bow"));
        assertTrue(mvcResult.getResponse().getContentAsString().equals("[]"));
    }

    @Test
    @DisplayName("Test: GET character by ID. Returns character as expected.")
    public void CharacterController_GetCharacterById_Success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/character/" + 1L)
        ).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("sword"));
    }

    @Test
    @DisplayName("Test: GET character by ID. Throws error as character doesn't exist.")
    public void CharacterController_GetCharacterById_Fail() throws Exception {
        ResultActions mvcResult = mockMvc.perform( MockMvcRequestBuilders.get("/api/character/" + 999))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));
    }

    @Test
    @DisplayName("Test: GET character by player ID. Returns character as expected.")
    public void CharacterController_GetCharacterByPlayerId_Success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/character/byPlayer/" + 1L)
        ).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("sword"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("bow"));
    }

    @Test
    @DisplayName("Test: GET character by player ID. Returns empty list. ")
    public void CharacterController_GetCharacterByPlayerId_Fail() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/character/byPlayer/" + 999)
                ).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().equals("[]"));
    }

    @Test
    @DisplayName("Test: POST new character. Success.")
    public void CharacterController_PostNewCharacter_Success() throws Exception {
        newTestChar = new CharacterDto(3L,1L,"third test character",1,0,"neutral good",
                "hammer","shield","gloves","necklace",1,"dwarf",25,"con",2,"medium",
                "proficiency","weapon proficiencies", "languages", "traits",
                "paladin",12,50,"class skills","class proficiencies","saving throws","",
                15,15,15,15,15,15,15,15,15,15,15,15,15);

        String body = objectMapper.writeValueAsString(newTestChar);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/character").content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();

        Optional<CharacterDao> foundChar = characterRepository.findById(newTestChar.getId());
        assertTrue(foundChar.isPresent());
        assertTrue(foundChar.get().getCharacterName().contains("third"));
    }
}
