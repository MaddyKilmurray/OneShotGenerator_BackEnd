package com.kilmurray.dnd_createservice.proxy;

import com.kilmurray.dnd_createservice.dto.CharacterDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("randomiser-service")
public interface RandomiserServiceProxy {

    @GetMapping("/api/random/d4")
    public int randomD4();

    @GetMapping("/api/random/d6")
    public int randomD6();

    @GetMapping("/api/random/d8")
    public int randomD8();

    @GetMapping("/api/random/d12")
    public int randomD12();

    @GetMapping("/api/random/d20")
    public int randomD20();

    @GetMapping("/api/random/d100")
    public int randomD100();

    @GetMapping("/api/random/weapon")
    public String getRandomStarterWeapon();

    @GetMapping("/api/random/armour")
    public String getRandomStarterArmour();

    @GetMapping("/api/random/gear")
    public String getRandomStarterGear();

    @GetMapping("/api/random/gear/{gear}")
    public String getStarterGear(@PathVariable(name = "gear") String gear);

    @GetMapping("/api/random/trinket")
    public String getRandomStarterTrinket();

    @GetMapping("/api/random/newStat")
    public int getStat();

    @GetMapping("/api/random/alignment")
    public String getRandomAlignment();

    @GetMapping("/api/random/statModifier/{stat}")
    public int getStatModifier(@PathVariable(name = "stat") int stat);

    @GetMapping("/api/random/generateCharDetails")
    public CharacterDetailDTO generateChar();

}
