package com.kilmurray.dnd_randomiserservice.service;

import com.kilmurray.dnd_randomiserservice.data.DataSet;
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
        Random rand = new Random();
        int dice_roll = rand.nextInt(diceNumber + 1);
        return dice_roll;
    }

    public String getRandomStarterWeapon() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(dataSet.getStarterWeapons().length);
        return dataSet.getStarterWeapons()[randomIndex][0];
    }

    public String getRandomStarterArmour() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(dataSet.getStarterArmour().length);
        return dataSet.getStarterArmour()[randomIndex][0];
    }

    public String getRandomStarterGear() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(dataSet.getStarterGear().length);
        return dataSet.getStarterGear()[randomIndex];
    }

    public String getSpecificStarterGear(String gear) {
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
        Random generator = new Random();
        int randomIndex = generator.nextInt(dataSet.getStarterTrinkets().length);
        return dataSet.getStarterTrinkets()[randomIndex];
    }

    public int statGenerator() {
        return (int) Math.floor(Math.random()*(18-3+1)+3);
    }

    public int getStatModifier(int stat) {
        return (stat - 10) /2;
    }
}
