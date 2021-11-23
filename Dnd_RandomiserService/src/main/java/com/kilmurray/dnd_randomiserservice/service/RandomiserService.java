package com.kilmurray.dnd_randomiserservice.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomiserService {

    public int randomiserInt(int diceNumber) {
        Random rand = new Random();
        int dice_roll = rand.nextInt(diceNumber + 1);
        return dice_roll;
    }


}
