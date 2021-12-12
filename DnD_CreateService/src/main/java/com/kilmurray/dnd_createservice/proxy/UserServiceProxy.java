package com.kilmurray.dnd_createservice.proxy;

import com.kilmurray.dnd_createservice.dto.EmailDto;
import com.kilmurray.dnd_createservice.dto.UserDto;
import com.kilmurray.dnd_createservice.dto.UsernameDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("user-service")
public interface UserServiceProxy {

    @GetMapping("/api/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers();

    @GetMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable(name = "id") Long id);

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto);

    @PostMapping("/api/users/validate/username")
    @ResponseStatus(HttpStatus.OK)
    public boolean isValidUsername(@RequestBody UsernameDto usernameDto);

    @PostMapping("/api/users/validate/email")
    @ResponseStatus(HttpStatus.OK)
    public boolean isValidEmail(@RequestBody EmailDto emailDto);
}
