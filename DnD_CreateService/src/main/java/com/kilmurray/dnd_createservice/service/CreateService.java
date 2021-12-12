package com.kilmurray.dnd_createservice.service;

import com.kilmurray.dnd_createservice.dto.CharacterDetailDTO;
import com.kilmurray.dnd_createservice.dto.CharacterIncomingDto;
import com.kilmurray.dnd_createservice.dto.CharacterOutgoingDto;
import com.kilmurray.dnd_createservice.proxy.CharacterServiceProxy;
import com.kilmurray.dnd_createservice.proxy.RandomiserServiceProxy;
import com.kilmurray.dnd_createservice.proxy.UserServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateService {

    @Autowired
    RandomiserServiceProxy randomiserServiceProxy;

    @Autowired
    CharacterServiceProxy characterServiceProxy;

    @Autowired
    UserServiceProxy userServiceProxy;

    public CharacterOutgoingDto createRandomCharacter(CharacterIncomingDto characterIncomingDto) {
        CharacterOutgoingDto newCharacter = generateOutgoing(characterIncomingDto);
        return newCharacter;
    }

    public CharacterOutgoingDto generateOutgoing(CharacterIncomingDto characterIncomingDto) {
        System.out.println(characterIncomingDto.getCharacterName());
        CharacterOutgoingDto newCharacter = new CharacterOutgoingDto(characterIncomingDto.getId(),
                characterIncomingDto.getPlayerId(), characterIncomingDto.getCharacterName(),
                characterIncomingDto.getRace(), characterIncomingDto.getSpeed(),
                characterIncomingDto.getAbilityScore(), characterIncomingDto.getAbilityBonus(),
                characterIncomingDto.getSize(), characterIncomingDto.getWeaponProficiencies(),
                characterIncomingDto.getProficiency(), characterIncomingDto.getLanguages(),
                characterIncomingDto.getTraits(), characterIncomingDto.getCharClass(),
                characterIncomingDto.getHitDice(), characterIncomingDto.getClassSkills(),
                characterIncomingDto.getClassProficiencies(),characterIncomingDto.getSavingThrows());
        CharacterDetailDTO characterDetailDTO = randomiserServiceProxy.generateChar();
        newCharacter.setAlignment(characterDetailDTO.getAlignment());
        newCharacter.setStartingWeapon(characterDetailDTO.getStartingWeapon());
        newCharacter.setStartingArmour(characterDetailDTO.getStartingArmour());
        newCharacter.setStartingGear(characterDetailDTO.getStartingGear());
        newCharacter.setStartingTrinket(characterDetailDTO.getStartingTrinket());
        newCharacter.setStrength(characterDetailDTO.getStrength()); newCharacter.setStrengthModifier(characterDetailDTO.getStrengthModifier());
        newCharacter.setDexterity(characterDetailDTO.getDexterity()); newCharacter.setDexterityModifier(characterDetailDTO.getDexterityModifier());
        newCharacter.setConstitution(characterDetailDTO.getConstitution()); newCharacter.setConstitutionModifier(characterDetailDTO.getConstitutionModifier());
        newCharacter.setIntelligence(characterDetailDTO.getIntelligence()); newCharacter.setIntelligenceModifier(characterDetailDTO.getIntelligenceModifier());
        newCharacter.setWisdom(characterDetailDTO.getWisdom()); newCharacter.setWisdomModifier(characterDetailDTO.getWisdomModifier());
        newCharacter.setCharisma(characterDetailDTO.getCharisma()); newCharacter.setCharismaModifier(characterDetailDTO.getCharismaModifier());
        newCharacter.setArmourClass(characterDetailDTO.getArmourClass());
        newCharacter.setHitPoints(newCharacter.getHitDice() + newCharacter.getConstitution());
        return newCharacter;
    }

    public void saveCharacter(CharacterOutgoingDto characterOutgoingDto) {
        characterServiceProxy.createCharacter(characterOutgoingDto);
    }

}
