/*
 * This should correspond to a table in database. The table design are found in "Postgresql Database Notes.txt"
 * Initial data is in DataInitializer.java
 */
package com.example.demo.DAO;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "role", nullable = false, length = 50)
    private String role;

    /** One user can have many books */
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    private List<Book> books = new ArrayList<>();

    public User() {}

    // getters and setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }

    public List<Book> getBooks() {
        return books;
    }
}
