package com.example.friendshipneverends.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "relationship", schema = "sql11459227")
public class Relationship {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "relationship_id", length = 11, nullable = false)
  private int relationshipId;

  @JoinColumn(name= "sourceUser",referencedColumnName = "email", nullable =  false)
  private String sourceUser;

  @JoinColumn(name = "destinationUser", referencedColumnName = "email", nullable = false)
  private String destinationUser;
  @Basic
  @Column(name="connection", length =200, nullable = false)
  private String connection;

  public Relationship(String sourceUser, String destinationUser, String connection) {
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

  public String getSourceUser() {
    return sourceUser;
  }

  public void setSourceUser(String sourceUser) {
    this.sourceUser = sourceUser;
  }

  public String getDestinationUser() {
    return destinationUser;
  }

  public void setDestinationUser(String destinationUser) {
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