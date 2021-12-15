package com.example.friendshipneverends.Repository;

import com.example.friendshipneverends.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    String findByEmail(String email);
}
