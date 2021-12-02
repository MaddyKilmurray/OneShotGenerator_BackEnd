package com.kilmurray.dnd_userservice.controller;

import com.kilmurray.dnd_userservice.dao.Roles;
import com.kilmurray.dnd_userservice.dao.UserDao;
import com.kilmurray.dnd_userservice.dto.UserDto;
import com.kilmurray.dnd_userservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final Environment environment;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder, Environment environment) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.environment = environment;
    }

    public List<UserDto> getAll() {
        List<UserDao> userDaos = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (UserDao userDao : userDaos) {
            userDtos.add(convertToDto(userDao));
        }
        return userDtos;
    }

    public UserDto getUserById(Long id) {
        Optional<UserDao> foundUser = userRepository.findById(id);
        if (!foundUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User cannot be found");
        }
        return convertToDto(foundUser.get());
    }

    public UserDto create(UserDto userDto) {
        UserDao newUser = convertToDao(userDto);
        userRepository.save(newUser);
        Optional<UserDao> createdUser = userRepository.findByUsername(newUser.getUsername());
        return createdUser.map(this::convertToDto).orElse(null);
    }

    public UserDto updateUser(String token, Optional<String> password, Optional<Boolean> isDm, Optional<Long> partyId) {
        token = token.replace("Bearer","");
        String tokenSubject = Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        Optional<UserDao> updateUser = userRepository.findByUsername(token);
        UserDao updatedUser = updateUser
                .map(users -> updateUserDetails(password, isDm, partyId, users)) // update the user
                .orElseThrow(() -> new RuntimeException("User not found."));
        return convertToDto(updatedUser);
    }

    private UserDao updateUserDetails(Optional<String> password, Optional<Boolean> isDm, Optional<Long> partyId, UserDao updateUser) {
        if (password.isPresent()) {
            String newPassword = encoder.encode(password.get());
            updateUser.setPassword(password.get());
            userRepository.save(updateUser);
        }
        if (isDm.isPresent()) {
            updateUser.setIsDm(isDm.get());
            userRepository.save(updateUser);
        }
        if (partyId.isPresent()) {
            updateUser.setPartyId(partyId.get());
            userRepository.save(updateUser);
        }
        return updateUser;
    }

    public boolean validateUsernameExists(String username) {
        Optional<UserDao> checkUser = userRepository.findByUsername(username);
        return checkUser.isPresent();
    }

    public boolean validateEmailExists(String email) {
        Optional<UserDao> checkUser = userRepository.findByEmail(email);
        return checkUser.isPresent();
    }

    public UserDto convertToDto(UserDao userDao) {
        return new UserDto(userDao.getId(), userDao.getUsername(), userDao.getPassword(),
                userDao.getEmail(), userDao.getIsDm(),userDao.getPartyId());
    }

    public UserDao convertToDao(UserDto userDto) {
        if (userDto.isDm()) {
            return new UserDao(userDto.getId(), userDto.getUsername(),
                    userDto.getPassword(), userDto.getEmail(), userDto.isDm(),
                    userDto.getPartyId(), "DM");
        }
        else {
            return new UserDao(userDto.getId(), userDto.getUsername(),
                    userDto.getPassword(), userDto.getEmail(), userDto.isDm(),
                    userDto.getPartyId(), "PLAYER");
        }
    }

    public UserDto getUserByUsername(String token) {
        token = token.replace("Bearer","");
        String tokenSubject = Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        Optional<UserDao> foundUser = userRepository.findByUsername(tokenSubject);
        if (!foundUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User cannot be found.");
        }
        return convertToDto(foundUser.get());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDao> foundUser = userRepository.findByUsername(username);
        if (!foundUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User cannot be found.");
        }
        else {
            return new User(foundUser.get().getUsername(),foundUser.get().getPassword(),
                    true,true,true,true,new ArrayList<>());
        }

    }
}
