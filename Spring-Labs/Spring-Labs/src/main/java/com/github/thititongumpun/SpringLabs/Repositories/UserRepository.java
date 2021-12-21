package com.github.thititongumpun.SpringLabs.Repositories;

import com.github.thititongumpun.SpringLabs.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
}
