package com.praveen.splitwise.repositories;

import com.praveen.splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        public Optional<User> findByPhoneNumber(String phoneNumber);
        public User save(User user);
}
