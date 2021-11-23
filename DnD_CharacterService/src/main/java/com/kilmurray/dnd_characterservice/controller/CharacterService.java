package com.kilmurray.dnd_characterservice.controller;

import com.kilmurray.dnd_characterservice.dao.CharacterDao;
import com.kilmurray.dnd_characterservice.dto.CharacterDto;
import com.kilmurray.dnd_characterservice.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    final CharacterRepository characterRepository;
    final ModelMapper modelMapper;

    public CharacterService(CharacterRepository characterRepository, ModelMapper modelMapper) {
        this.characterRepository = characterRepository;
        this.modelMapper = modelMapper;
    }

    public List<CharacterDto> getAll() {
        List<CharacterDao> allCharsDao = characterRepository.findAll();
        List<CharacterDto> allCharsDto = new ArrayList<>();
        for (CharacterDao charDao : allCharsDao) {
            allCharsDto.add(convertToDto(charDao));
        }
        return allCharsDto;
    }

    public CharacterDto getByCharId(Long characterId) {
        Optional<CharacterDao> foundChar = characterRepository.findById(characterId);
        if (!foundChar.isPresent()) {
            return null;
        }
    }

    public List<CharacterDto> getByPlayerId(Long playerId) {
    }

    public CharacterDto createCharacter(CharacterDto characterDto) {
    }

    public CharacterDto updateCharacter(Long charId, Optional<String> characterName, Optional<Integer> level, Optional<Integer> experience, Optional<String> alignment, Optional<String> startingWeapon, Optional<String> startingArmour, Optional<String> startingGear, Optional<String> startingTrinket) {
    }

    public CharacterDto convertToDto(CharacterDao characterDao) {
        CharacterDto characterDto = modelMapper.map(characterDao, CharacterDto.class);
        return characterDto;
    }

    public CharacterDao convertToDao(CharacterDto characterDto) {
        CharacterDao characterDao = modelMapper.map(characterDto, CharacterDao.class);
        return characterDao;
    }

}
