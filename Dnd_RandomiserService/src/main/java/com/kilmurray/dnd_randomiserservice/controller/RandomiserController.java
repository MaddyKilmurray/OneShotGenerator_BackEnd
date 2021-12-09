package com.kilmurray.dnd_randomiserservice.controller;

import com.kilmurray.dnd_randomiserservice.dto.CharacterDTO;
import com.kilmurray.dnd_randomiserservice.service.RandomiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/random")
public class RandomiserController {

    final
    RandomiserService randomiserService;

    public RandomiserController(RandomiserService randomiserService) {
        this.randomiserService = randomiserService;
    }

    @GetMapping("/d4")
    @ResponseStatus(HttpStatus.OK)
    public int randomD4() {
        return randomiserService.randomiserInt(4);
    }

    @GetMapping("/d6")
    @ResponseStatus(HttpStatus.OK)
    public int randomD6() {
        return randomiserService.randomiserInt(6);
    }

    @GetMapping("/d8")
    @ResponseStatus(HttpStatus.OK)
    public int randomD8() {
        return randomiserService.randomiserInt(8);
    }

    @GetMapping("/d12")
    @ResponseStatus(HttpStatus.OK)
    public int randomD12() {
        return randomiserService.randomiserInt(12);
    }

    @GetMapping("/d20")
    @ResponseStatus(HttpStatus.OK)
    public int randomD20() {
        return randomiserService.randomiserInt(20);
    }

    @GetMapping("/d100")
    @ResponseStatus(HttpStatus.OK)
    public int randomD100() {
        return randomiserService.randomiserInt(100);
    }

    @GetMapping("/weapon")
    @ResponseStatus(HttpStatus.OK)
    public String getRandomStarterWeapon() {
        return randomiserService.getRandomStarterWeapon();
    }

    @GetMapping("/armour")
    @ResponseStatus(HttpStatus.OK)
    public String getRandomStarterArmour() {
        return randomiserService.getRandomStarterArmour();
    }

    @GetMapping("/gear")
    @ResponseStatus(HttpStatus.OK)
    public String getRandomStarterGear() {
        return randomiserService.getRandomStarterGear();
    }

    @GetMapping("/gear/{gear}")
    @ResponseStatus(HttpStatus.OK)
    public String getStarterGear(@PathVariable(name = "gear") String gear) {
        return randomiserService.getSpecificStarterGear(gear);
    }

    @GetMapping("/trinket")
    @ResponseStatus(HttpStatus.OK)
    public String getRandomStarterTrinket() {
        return randomiserService.getRandomTrinket();
    }

    @GetMapping("/newStat")
    @ResponseStatus(HttpStatus.OK)
    public int getStat() {
        return randomiserService.statGenerator();
    }

    @GetMapping("/alignment")
    @ResponseStatus(HttpStatus.OK)
    public String getRandomAlignment() {
        return randomiserService.getRandomAlignment();
    }

    @GetMapping("/statModifier/{stat}")
    @ResponseStatus(HttpStatus.OK)
    public int getStatModifier(@PathVariable(name = "stat") int stat) {
        return randomiserService.getStatModifier(stat);
    }

    @GetMapping("/generateCharDetails")
    @ResponseStatus(HttpStatus.OK)
    public CharacterDTO generateChar() {
        return randomiserService.generateDetails();
    }

}
