package com.kilmurray.dnd_createservice.service;

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
        newCharacter.setHitPoints(newCharacter.getHitDice() + newCharacter.getConstitution());
        return newCharacter;
    }

    public void saveCharacter(CharacterOutgoingDto characterOutgoingDto) {
        characterServiceProxy.createCharacter(characterOutgoingDto);
    }

}
