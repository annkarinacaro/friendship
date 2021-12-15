package com.example.friendshipneverends.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "relationship", schema = "customers6")
public class Relationship {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "relationship_id", length = 11, nullable = false)
  private int relationshipId;
  @ManyToOne
  @JoinColumn(name= "sourceUser",referencedColumnName = "email")
  private User sourceUser;
  @ManyToOne
  @JoinColumn(name = "destinationUser", referencedColumnName = "email")
  private User destinationUser;
  @Basic
  @Column(name="connection", length =200)
  private String connection;

  public Relationship(User sourceUser, User destinationUser, String connection) {
    this.sourceUser = sourceUser;
    this.destinationUser = destinationUser;
    this.connection = connection;
  }

  public int getRelationshipId() {
    return relationshipId;
  }

  public void setRelationshipId(int relationshipId) {
    this.relationshipId = relationshipId;
  }

  public User getSourceUser() {
    return sourceUser;
  }

  public void setSourceUser(User sourceUser) {
    this.sourceUser = sourceUser;
  }

  public User getDestinationUser() {
    return destinationUser;
  }

  public void setDestinationUser(User destinationUser) {
    this.destinationUser = destinationUser;
  }

  public String getConnection() {
    return connection;
  }

  public void setConnection(String connection) {
    this.connection = connection;
  }

  @Override
  public String toString() {
    return "Relationship{" +
            "relationshipId=" + relationshipId +
            ", sourceUser=" + sourceUser +
            ", destinationUser=" + destinationUser +
            ", connection='" + connection + '\'' +
            '}';
  }
}