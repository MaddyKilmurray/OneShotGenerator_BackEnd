package com.kilmurray.dnd_userservice.proxy;

import com.kilmurray.dnd_userservice.dto.CharacterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient("character-service")
public interface CharacterProxy {

    @GetMapping("/api/character/byPlayer/{id}")
    public List<CharacterDto> getByEmail(@PathVariable(name = "id") Long playerId);
}
