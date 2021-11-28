package com.kilmurray.dnd_userservice.controller;

import com.kilmurray.dnd_userservice.dto.EmailDto;
import com.kilmurray.dnd_userservice.dto.UserDto;
import com.kilmurray.dnd_userservice.dto.UsernameDto;
import com.kilmurray.dnd_userservice.security.CustomUserDetails;
import com.kilmurray.dnd_userservice.security.models.JwtRequest;
import com.kilmurray.dnd_userservice.security.models.JwtResponse;
import com.kilmurray.dnd_userservice.security.service.CustomUserDetailService;
import com.kilmurray.dnd_userservice.security.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    final JwtUtil jwtUtil;

    final CustomUserDetailService customUserDetailsService;

    final AuthenticationManager authenticationManager;

    public UserController(UserService userService, JwtUtil jwtUtil, CustomUserDetailService customUserDetailsService, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationManager = authenticationManager;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        }
        catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Username not found");
        }
        catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("Bad credentials");
        }

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

}
