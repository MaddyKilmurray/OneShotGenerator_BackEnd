package com.kilmurray.dnd_userservice.controller;

import com.kilmurray.dnd_userservice.dto.EmailDto;
import com.kilmurray.dnd_userservice.dto.UserDto;
import com.kilmurray.dnd_userservice.dto.UsernameDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/authenticated")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(Authentication authentication) {
        return userService.getUserByUsername(authentication.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @PatchMapping("/authenticated/update")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(Authentication authentication, @RequestParam Optional<String> password, @RequestParam Optional<String> email,
                              @RequestParam Optional<Boolean> isDm, @RequestParam Optional<Long> partyId) {
        return userService.updateUser(authentication.getName(),password,isDm,partyId);
    }

    @PostMapping("/validate/username")
    @ResponseStatus(HttpStatus.OK)
    public boolean isValidUsername(@RequestBody UsernameDto usernameDto) {
        return userService.validateUsernameExists(usernameDto.getEmail());
    }

    @PostMapping("/validate/email")
    @ResponseStatus(HttpStatus.OK)
    public boolean isValidEmail(@RequestBody EmailDto emailDto) {
        return userService.validateEmailExists(emailDto.getEmail());
    }

}
