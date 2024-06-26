package com.example.newstw.repository;

import com.example.newstw.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findById(Long id);

    Optional<User> findByLogin(String login);
}
