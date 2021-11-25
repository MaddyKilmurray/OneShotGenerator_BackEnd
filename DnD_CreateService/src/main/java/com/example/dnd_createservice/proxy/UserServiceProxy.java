package com.example.dnd_createservice.proxy;

import com.example.dnd_createservice.dto.EmailDto;
import com.example.dnd_createservice.dto.UserDto;
import com.example.dnd_createservice.dto.UsernameDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient("user-service")
@RequestMapping("/api/users")
public interface UserServiceProxy {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable(name = "id") Long id);

    @GetMapping("/authenticated")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(Authentication authentication);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto);

    @PatchMapping("/authenticated/update")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(Authentication authentication, @RequestParam Optional<String> password, @RequestParam Optional<String> email,
                              @RequestParam Optional<Boolean> isDm, @RequestParam Optional<Long> partyId);

    @PostMapping("/validate/username")
    @ResponseStatus(HttpStatus.OK)
    public boolean isValidUsername(@RequestBody UsernameDto usernameDto);

    @PostMapping("/validate/email")
    @ResponseStatus(HttpStatus.OK)
    public boolean isValidEmail(@RequestBody EmailDto emailDto);
}
