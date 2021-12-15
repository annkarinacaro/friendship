package com.example.friendshipneverends.Service;

import com.example.friendshipneverends.Entity.Protocol;
import com.example.friendshipneverends.Entity.Relationship;
import com.example.friendshipneverends.Entity.User;
import com.example.friendshipneverends.Repository.RelationshipRepository;
import com.example.friendshipneverends.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationshipService {

    private final RelationshipRepository relationshipRepository;
    private final UserRepository userRepository;

    @Autowired
    public RelationshipService(RelationshipRepository relationshipRepository, UserRepository userRepository) {
        this.relationshipRepository = relationshipRepository;
        this.userRepository = userRepository;
    }

    public void manipulateRelationship(Protocol protocol){
        User source = userRepository.findByEmail(protocol.getSourceEmail());
        if(source == null) source = new User(protocol.getSourceEmail(), protocol.getSourceHost());

        User destination = userRepository.findByEmail(protocol.getDestinationEmail());
        if(destination == null) destination = new User(protocol.getDestinationEmail(), protocol.getDestinationHost());
        Relationship relationship  = relationshipRepository.findBySourceUserAndDestinationUser(source, destination);
        //Check both ways
        Relationship backwardsRelationship = relationshipRepository.findBySourceUserAndDestinationUser(destination, source);
        if(relationship == null){
            relationship = backwardsRelationship;
        }

        switch(protocol.getMethod()){
            case "ADD":             //send a request to add a friend
                //If there isn't another relationship between them
                if(relationship == null){
                    relationship = new Relationship(source, destination, "REQUEST");
                    relationshipRepository.save(relationship);
                }
                break;
            case "ACCEPT":          //accept a frien request you have received
                //If their current relationship is a pending friendship
                if(relationship != null){
                    if(relationship.getConnection().equals("REQUEST") && relationship.getSourceUser().getEmail().equals(protocol.getDestinationEmail())){
                        relationship.setConnection("FRIEND");
                        relationshipRepository.save(relationship);
                    }
                }
                break;
            case "DELETE":          //delete a friend request you have received
                //If their current relationship is a pending friendship
                if(relationship.getConnection().equals("REQUEST")){
                    relationshipRepository.delete(relationship);
                }
                break;
            case "REMOVE":          //remove a friend request you have sent out
                //If the user changes their mind about the pending friendship
                if(relationship.getConnection().equals("FRIEND")){
                    relationshipRepository.delete(relationship);
                }
                break;
            case "BLOCK":

                if (relationship == null) {
                    relationship = new Relationship(source, destination, "BLOCK");
                    relationshipRepository.save(relationship);
                }
                else {
                    relationship.setConnection("BLOCK");
                    relationshipRepository.save(relationship);
                }
                break;
            default:

                break;
        }
    }
}
