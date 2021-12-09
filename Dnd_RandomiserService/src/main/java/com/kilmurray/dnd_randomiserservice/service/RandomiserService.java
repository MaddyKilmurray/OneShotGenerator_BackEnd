package com.kilmurray.dnd_randomiserservice.service;

import com.kilmurray.dnd_randomiserservice.data.DataSet;
import com.kilmurray.dnd_randomiserservice.dto.CharacterDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@Service
public class RandomiserService {

    private DataSet dataSet;

    public int randomiserInt(int diceNumber) {
        int dice_roll = (int) Math.floor(Math.random()*(diceNumber-1+1)+1);
        return dice_roll;
    }

    public String getRandomStarterWeapon() {
        dataSet = new DataSet();
        Random generator = new Random();
        int randomIndex = generator.nextInt(dataSet.getStarterWeapons().length);
        return dataSet.getStarterWeapons()[randomIndex][0] + ", " + dataSet.getStarterWeapons()[randomIndex][1];
    }

    public String getRandomStarterArmour() {
        dataSet = new DataSet();
        Random generator = new Random();
        int randomIndex = generator.nextInt(dataSet.getStarterArmour().length);
        return dataSet.getStarterArmour()[randomIndex][0]  + ", " + dataSet.getStarterArmour()[randomIndex][1];
    }

    public String getRandomStarterGear() {
        dataSet = new DataSet();
        Random generator = new Random();
        int randomIndex = generator.nextInt(dataSet.getStarterGear().length);
        return dataSet.getStarterGear()[randomIndex];
    }

    public String getSpecificStarterGear(String gear) {
        dataSet = new DataSet();
        if (gear.equals(null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Gear cannot be null");
        }
        if (gear.length() < 6) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please enter a valid gear term");
        }
        for (String item : dataSet.getStarterGear()) {
            if (item.contains(StringUtils.capitalize(gear))) {
                return item;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Requested gear could not be found");
    }

    public String getRandomTrinket() {
        dataSet = new DataSet();
        Random generator = new Random();
        int randomIndex = generator.nextInt(dataSet.getStarterTrinkets().length);
        return dataSet.getStarterTrinkets()[randomIndex];
    }

    public String getRandomAlignment() {
        dataSet = new DataSet();
        Random generator = new Random();
        int randomIndex = generator.nextInt(dataSet.getAlignment().length);
        return dataSet.getAlignment()[randomIndex];
    }

    public int statGenerator() {
        return (int) Math.floor(Math.random()*(18-3+1)+3);
    }

    public int getStatModifier(int stat) {
        return (stat - 10) /2;
    }

    public CharacterDTO generateDetails() {
        CharacterDTO newCharacter = new CharacterDTO();
        newCharacter.setAlignment(getRandomAlignment());
        newCharacter.setStrength(statGenerator()); newCharacter.setStrengthModifier(getStatModifier(newCharacter.getStrength()));
        newCharacter.setDexterity(statGenerator()); newCharacter.setDexterityModifier(getStatModifier(newCharacter.getDexterity()));
        newCharacter.setConstitution(statGenerator()); newCharacter.setConstitutionModifier(getStatModifier(newCharacter.getConstitution()));
        newCharacter.setIntelligence(statGenerator()); newCharacter.setIntelligenceModifier(getStatModifier(newCharacter.getIntelligence()));
        newCharacter.setWisdom(statGenerator()); newCharacter.setWisdomModifier(getStatModifier(newCharacter.getWisdom()));
        newCharacter.setCharisma(statGenerator()); newCharacter.setCharismaModifier(getStatModifier(newCharacter.getCharisma()));
        newCharacter.setArmourClass(10 + newCharacter.getDexterityModifier());
        newCharacter.setStartingWeapon(getRandomStarterWeapon());
        newCharacter.setStartingArmour(getRandomStarterArmour());
        newCharacter.setStartingGear(getRandomStarterGear());
        newCharacter.setStartingTrinket(getRandomTrinket());
        return newCharacter;
    }
}
