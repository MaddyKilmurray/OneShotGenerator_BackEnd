package com.kilmurray.dnd_characterservice.controller;

import com.kilmurray.dnd_characterservice.dto.CharacterDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/character")
@CrossOrigin(origins = "*")
public class CharacterController {

    final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CharacterDto> getAll() {
        return characterService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CharacterDto getById(@PathVariable(name = "id") Long characterId) {
        return characterService.getByCharId(characterId);
    }

    @GetMapping("/byPlayer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CharacterDto> getByEmail(@PathVariable(name = "id") Long playerId) {
        return characterService.getByPlayerId(playerId);
    }

    @GetMapping("/byPartyId/{partyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CharacterDto> getByPartyId(@PathVariable(name = "partyId") Long partyId) {
        return characterService.getByPartyId(partyId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCharacter(@RequestBody CharacterDto characterDto) {
        characterService.createCharacter(characterDto);
    }

    @PatchMapping("/update/{charId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CharacterDto updateCharacter(@PathVariable(name = "charId") Long charId, @RequestParam Optional<String> characterName,
                                        @RequestParam Optional<Integer> level, @RequestParam Optional<Integer> experience,
                                        @RequestParam Optional<String> alignment, @RequestParam Optional<String> startingWeapon,
                                        @RequestParam Optional<String> startingArmour,
                                        @RequestParam Optional<String> startingGear, @RequestParam Optional<String> startingTrinket) {
        return characterService.updateCharacter(charId,characterName,level,experience,alignment,startingWeapon, startingArmour, startingGear, startingTrinket);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCharacter(@PathVariable(name = "id") Long id) {
        characterService.deleteCharacter(id);
    }
}
