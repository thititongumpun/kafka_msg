package com.github.thititongumpun.SpringLabs.Services;

import com.github.thititongumpun.SpringLabs.Entities.User;
import com.github.thititongumpun.SpringLabs.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User create(User user) {
        log.debug("Request to create User : {}", user);
        return this.userRepository.save(user);
    }

    public List<User> findAll() {
        log.debug("Request to get all Users");
        return this.userRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    public List<String> getDummyData() {
        return Arrays.asList("one", "two", "three");
    }
}
