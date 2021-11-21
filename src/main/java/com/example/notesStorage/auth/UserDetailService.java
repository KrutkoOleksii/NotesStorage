package com.example.notesStorage.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
//        Set<Role> roles= new HashSet<>();
//        roles.add(Role.ADMIN);
//        roles.add(Role.USER);
//        Set<Note> notes= new HashSet<>();
//        if (username.equalsIgnoreCase("admin")){
//            return new User(UUID.randomUUID(), "admin", "123", true, notes, roles);
//        }
        return userRepository.findByUsername(username).get();
    }

}
