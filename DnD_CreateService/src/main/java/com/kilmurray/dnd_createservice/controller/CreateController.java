package com.kilmurray.dnd_createservice.controller;

import com.kilmurray.dnd_createservice.dto.CharacterIncomingDto;
import com.kilmurray.dnd_createservice.dto.CharacterOutgoingDto;
import com.kilmurray.dnd_createservice.service.CreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/create")
public class CreateController {

    @Autowired
    CreateService createService;

    @PostMapping("/view")
    @ResponseStatus(HttpStatus.OK)
    public CharacterOutgoingDto generateCompleteCharacter(CharacterIncomingDto characterIncomingDto) {
        return createService.generateOutgoing(characterIncomingDto);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCompleteCharacter(CharacterOutgoingDto characterOutgoingDto) {
        createService.saveCharacter(characterOutgoingDto);
    }

}
