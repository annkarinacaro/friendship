package com.example.friendshipneverends.Controller;

import com.example.friendshipneverends.Entity.Protocol;
import com.example.friendshipneverends.Service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class RelationshipController {

    RelationshipService relationshipService;
    @Autowired
    public RelationshipController( RelationshipService relationshipService){
       this.relationshipService = relationshipService;
    }

    private static String personalHost = "${IP_ADDRESS}";

    @PostMapping("/relationship")
    public void relationship(@RequestBody String body){
        Protocol protocol = new Protocol(body);

        //Check if the destinationHost is the same as ours
        if(protocol.getDestinationHost().equals(personalHost)){
            //If that's the case, create a request in the db
            relationshipService.manipulateRelationship(protocol);
            //Send a response back to the source user
        }
        else {
            //Otherwise send a request to the destinationHost with the same protocol

            //When response is received, create a copy on own database
            relationshipService.manipulateRelationship(protocol);
        }
    }
}
