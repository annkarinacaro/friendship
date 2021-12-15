package com.example.friendshipneverends.Repository;

import com.example.friendshipneverends.Entity.Relationship;
import com.example.friendshipneverends.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationshipRepository extends JpaRepository<Relationship, Integer>{
    Relationship findBySourceUserAndDestinationUser(String source, String destination);
}
