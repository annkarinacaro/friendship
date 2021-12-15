package com.example.friendshipneverends.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name= "user", schema= "sql11459227")
public class User {
  @Id
 // @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "email", length = 100, nullable = false)
  private String email;
  @Basic
  @Column(name="host", length = 250, nullable = false)
  private String host;


  public User(String email, String host) {
    this.email = email;
    this.host = host;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  @Override
  public String toString() {
    return "User{" +
            "email='" + email + '\'' +
            ", host='" + host + '\'' +
            '}';
  }
}
