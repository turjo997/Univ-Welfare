package com.suffixit.kieb.service.impl;


import com.suffixit.kieb.entities.Users;
import com.suffixit.kieb.repository.UserRepository;
import com.suffixit.kieb.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<Users> addAll(List<Users> users) {
        return userRepository.saveAll(users);
    }
}
