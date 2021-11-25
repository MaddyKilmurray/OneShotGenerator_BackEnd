package com.example.dnd_createservice.proxy;

import com.example.dnd_createservice.dto.CharacterOutgoingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient("character-service")
@RequestMapping("/api/character")
public interface CharacterServiceProxy {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CharacterOutgoingDto> getAll();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CharacterOutgoingDto getById(@PathVariable(name = "id") Long characterId);

    @GetMapping("/byPlayer/{playerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CharacterOutgoingDto> getByPlayerId(@PathVariable(name = "playerId") Long playerId);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCharacter(@RequestBody CharacterOutgoingDto characterDto);

    @PatchMapping("/update/{charId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CharacterOutgoingDto updateCharacter(@PathVariable(name = "charId") Long charId, @RequestParam Optional<String> characterName,
                                        @RequestParam Optional<Integer> level, @RequestParam Optional<Integer> experience,
                                        @RequestParam Optional<String> alignment, @RequestParam Optional<String> startingWeapon,
                                        @RequestParam Optional<String> startingArmour,
                                        @RequestParam Optional<String> startingGear, @RequestParam Optional<String> startingTrinket);
}
