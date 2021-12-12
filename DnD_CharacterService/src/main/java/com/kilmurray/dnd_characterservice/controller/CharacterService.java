package com.kilmurray.dnd_characterservice.controller;

import com.kilmurray.dnd_characterservice.dao.CharacterDao;
import com.kilmurray.dnd_characterservice.dto.CharacterDto;
import com.kilmurray.dnd_characterservice.dto.CharacterWithUserDTO;
import com.kilmurray.dnd_characterservice.dto.UserDto;
import com.kilmurray.dnd_characterservice.proxy.UserProxy;
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
    final UserProxy userProxy;

    public CharacterService(CharacterRepository characterRepository, ModelMapper modelMapper, UserProxy userProxy) {
        this.characterRepository = characterRepository;
        this.modelMapper = modelMapper;
        this.userProxy = userProxy;
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

    public List<CharacterDto> getByPartyId(Long partyID) {
        List<CharacterDao> foundChars = characterRepository.findCharacterDaoByPartyId(partyID);
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

    public void deleteCharacter(Long id) {
        Optional<CharacterDao> updateChar = characterRepository.findById(id);
        if (!updateChar.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Character could not be found");
        }
        characterRepository.delete(updateChar.get());
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

    public List<CharacterWithUserDTO> getCharactersByPartyIDWithUser(Long partyId) {
        List<CharacterDao> foundCharacters = characterRepository.findCharacterDaoByPartyId(partyId);
        List<CharacterWithUserDTO> charactersWithUser = new ArrayList<>();
        for (CharacterDao characterDao : foundCharacters) {
            CharacterWithUserDTO newChar = convertToUserDto(characterDao, characterDao.getPlayerId());
            charactersWithUser.add(newChar);
        }
        return charactersWithUser;
    }

    public CharacterDto convertToDto(CharacterDao characterDao) {
        CharacterDto characterDto = modelMapper.map(characterDao, CharacterDto.class);
        return characterDto;
    }

    public CharacterDao convertToDao(CharacterDto characterDto) {
        CharacterDao characterDao = modelMapper.map(characterDto, CharacterDao.class);
        return characterDao;
    }

    public CharacterWithUserDTO convertToUserDto(CharacterDao characterDao, Long id) {
        CharacterWithUserDTO characterWithUserDTO = modelMapper.map(characterDao,CharacterWithUserDTO.class);
        UserDto foundUser = userProxy.getUserById(id);
        characterWithUserDTO.setPlayerUsername(foundUser.getUsername());
        return characterWithUserDTO;
    }


}
