package com.example.dnd_createservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("randomiser-service")
@RequestMapping("/api/random")
public interface RandomiserServiceProxy {

    @GetMapping("/d4")
    public int randomD4();

    @GetMapping("/d6")
    public int randomD6();

    @GetMapping("/d8")
    public int randomD8();

    @GetMapping("/d12")
    public int randomD12();

    @GetMapping("/d20")
    public int randomD20();

    @GetMapping("/d100")
    public int randomD100();

    @GetMapping("/weapon")
    public String getRandomStarterWeapon();

    @GetMapping("/armour")
    public String getRandomStarterArmour();

    @GetMapping("/gear")
    public String getRandomStarterGear();

    @GetMapping("/gear/{gear}")
    public String getStarterGear(@PathVariable(name = "gear") String gear);

    @GetMapping("/trinket")
    public String getRandomStarterTrinket();

    @GetMapping("/newStat")
    public int getStat();

    @GetMapping("/statModifier/{stat}")
    public int getStatModifier(@PathVariable(name = "stat") int stat);
}
