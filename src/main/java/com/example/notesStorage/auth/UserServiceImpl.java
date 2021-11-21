package com.example.notesStorage.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public abstract class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private final UserRepository userRepository;

//        @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    protected UserServiceImpl(EntityManager em, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        System.out.println("UserServiceImpl loadUserByUsername");

        this.em = em;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> findAll() {
        System.out.println("UserServiceImpl loadUserByUsername");

        return userRepository.findAll();
    }

    @Override
    public <S extends User> S save(S user) {
        System.out.println("UserServiceImpl loadUserByUsername");

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        System.out.println("UserServiceImpl findById");

        return userRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        System.out.println("UserServiceImpl deleteById");

        userRepository.deleteById(id);
    }

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) findByUserName(username).get(0);
    }*/

    /*public List<User> findByUserName(String username) {
        List<User> userList = userRepository.findByUserName(username);
        return !userList.isEmpty() ? userList : null;
    }*/
}
