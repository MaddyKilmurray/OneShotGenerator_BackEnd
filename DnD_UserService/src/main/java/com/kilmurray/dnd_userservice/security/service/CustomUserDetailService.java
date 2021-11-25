package com.kilmurray.dnd_userservice.security.service;

import com.kilmurray.dnd_userservice.dao.UserDao;
import com.kilmurray.dnd_userservice.repository.UserRepository;
import com.kilmurray.dnd_userservice.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDao> storedUser = userRepository.findByUsername(username);
        return storedUser.
                map(CustomUserDetails::new).
                orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
}
