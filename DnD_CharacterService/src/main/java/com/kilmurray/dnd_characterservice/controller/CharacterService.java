package com.kilmurray.dnd_characterservice.controller;

import com.kilmurray.dnd_characterservice.dao.CharacterDao;
import com.kilmurray.dnd_characterservice.dto.CharacterDto;
import com.kilmurray.dnd_characterservice.repository.CharacterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Character cannot be found");
        }
        return convertToDto(foundChar.get());
    }

    public List<CharacterDto> getByPlayerId(Long playerId) {
        List<CharacterDao> foundChars = characterRepository.findCharacterDaoByPlayerId(playerId);
        List<CharacterDto> allCharsDto = new ArrayList<>();
        for (CharacterDao charDao : foundChars) {
            allCharsDto.add(convertToDto(charDao));
        }
        return allCharsDto;
    }

    public void createCharacter(CharacterDto characterDto) {
        CharacterDao newChar = convertToDao(characterDto);
        characterRepository.save(newChar);
    }

    public CharacterDto updateCharacter(Long charId, Optional<String> characterName, Optional<Integer> level, Optional<Integer> experience,
                                        Optional<String> alignment, Optional<String> startingWeapon, Optional<String> startingArmour,
                                        Optional<String> startingGear, Optional<String> startingTrinket) {
        Optional<CharacterDao> updateChar = characterRepository.findById(charId);
        CharacterDao updatedChar = updateChar
                .map(chars -> updateCharacterDetails(characterName,level,experience,alignment,startingWeapon,startingArmour,startingGear,startingTrinket, chars))
                .orElseThrow(() -> new RuntimeException("Character not found."));
        return convertToDto(updatedChar);
    }

    public CharacterDao updateCharacterDetails(Optional<String> characterName, Optional<Integer> level, Optional<Integer> experience,
                                               Optional<String> alignment, Optional<String> startingWeapon, Optional<String> startingArmour,
                                               Optional<String> startingGear, Optional<String> startingTrinket, CharacterDao updateChar) {
        if (characterName.isPresent()) {
            updateChar.setCharacterName(characterName.get());
            characterRepository.save(updateChar);
        }
        if (level.isPresent()) {
            updateChar.setLevel(level.get());
            characterRepository.save(updateChar);
        }
        if (experience.isPresent()) {
            updateChar.setExperience(experience.get());
            characterRepository.save(updateChar);
        }
        if (alignment.isPresent()) {
            updateChar.setAlignment(alignment.get());
            characterRepository.save(updateChar);
        }
        if (startingWeapon.isPresent()) {
            updateChar.setStartingWeapon(startingWeapon.get());
            characterRepository.save(updateChar);
        }
        if (startingArmour.isPresent()) {
            updateChar.setStartingArmour(startingArmour.get());
            characterRepository.save(updateChar);
        }
        if (startingGear.isPresent()) {
            updateChar.setStartingGear(startingGear.get());
            characterRepository.save(updateChar);
        }
        if (startingTrinket.isPresent()) {
            updateChar.setStartingTrinket(startingTrinket.get());
            characterRepository.save(updateChar);
        }
        return updateChar;
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
