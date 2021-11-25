package com.kilmurray.dnd_randomiserservice.controller;

import com.kilmurray.dnd_randomiserservice.service.RandomiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/random")
public class RandomiserController {

    final
    RandomiserService randomiserService;

    public RandomiserController(RandomiserService randomiserService) {
        this.randomiserService = randomiserService;
    }

    @GetMapping("/d4")
    public int randomD4() {
        return randomiserService.randomiserInt(4);
    }

    @GetMapping("/d6")
    public int randomD6() {
        return randomiserService.randomiserInt(6);
    }

    @GetMapping("/d8")
    public int randomD8() {
        return randomiserService.randomiserInt(8);
    }

    @GetMapping("/d12")
    public int randomD12() {
        return randomiserService.randomiserInt(12);
    }

    @GetMapping("/d20")
    public int randomD20() {
        return randomiserService.randomiserInt(20);
    }

    @GetMapping("/d100")
    public int randomD100() {
        return randomiserService.randomiserInt(100);
    }

    @GetMapping("/weapon")
    public String getRandomStarterWeapon() {
        return randomiserService.getRandomStarterWeapon();
    }

    @GetMapping("/armour")
    public String getRandomStarterArmour() {
        return randomiserService.getRandomStarterArmour();
    }

    @GetMapping("/gear")
    public String getRandomStarterGear() {
        return randomiserService.getRandomStarterGear();
    }

    @GetMapping("/gear/{gear}")
    public String getStarterGear(@PathVariable(name = "gear") String gear) {
        return randomiserService.getSpecificStarterGear(gear);
    }

    @GetMapping("/trinket")
    public String getRandomStarterTrinket() {
        return randomiserService.getRandomTrinket();
    }

    @GetMapping("/newStat")
    public int getStat() {
        return randomiserService.statGenerator();
    }

    @GetMapping("/statModifier/{stat}")
    public int getStatModifier(@PathVariable(name = "stat") int stat) {
        return randomiserService.getStatModifier(stat);
    }

}
