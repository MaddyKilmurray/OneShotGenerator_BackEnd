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
        System.out.println("Test 1");
        newCharacter.setAlignment(randomiserServiceProxy.getRandomAlignment());
        System.out.println("Test 2");
        newCharacter.setStrength(randomiserServiceProxy.getStat()); newCharacter.setStrengthModifier(randomiserServiceProxy.getStatModifier(newCharacter.getStrength()));
        System.out.println("Test 3");
        newCharacter.setDexterity(randomiserServiceProxy.getStat()); newCharacter.setDexterityModifier(randomiserServiceProxy.getStatModifier(newCharacter.getDexterity()));
        System.out.println("Test 4");
        newCharacter.setConstitution(randomiserServiceProxy.getStat()); newCharacter.setConstitutionModifier(randomiserServiceProxy.getStatModifier(newCharacter.getConstitution()));
        newCharacter.setIntelligence(randomiserServiceProxy.getStat()); newCharacter.setIntelligenceModifier(randomiserServiceProxy.getStatModifier(newCharacter.getIntelligence()));
        newCharacter.setWisdom(randomiserServiceProxy.getStat()); newCharacter.setWisdomModifier(randomiserServiceProxy.getStatModifier(newCharacter.getWisdom()));
        newCharacter.setCharisma(randomiserServiceProxy.getStat()); newCharacter.setCharismaModifier(randomiserServiceProxy.getStatModifier(newCharacter.getCharisma()));
        newCharacter.setArmourClass(10 + newCharacter.getDexterityModifier());
        newCharacter.setStartingWeapon(randomiserServiceProxy.getRandomStarterWeapon());
        newCharacter.setStartingArmour(randomiserServiceProxy.getRandomStarterArmour());
        newCharacter.setStartingGear(randomiserServiceProxy.getRandomStarterGear());
        newCharacter.setStartingTrinket(randomiserServiceProxy.getRandomStarterTrinket());
        newCharacter.setHitPoints(newCharacter.getHitDice() + newCharacter.getConstitution());
        System.out.println("Test 5");
        return newCharacter;
    }

    public void saveCharacter(CharacterOutgoingDto characterOutgoingDto) {
        characterServiceProxy.createCharacter(characterOutgoingDto);
    }

}
