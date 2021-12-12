package com.kilmurray.dnd_characterservice.proxy;

import com.kilmurray.dnd_characterservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient("user-service")
public interface UserProxy {

    @GetMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable(name = "id") Long id);
}
