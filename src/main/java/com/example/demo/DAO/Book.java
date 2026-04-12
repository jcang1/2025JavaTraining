/*
 * This should correspond to a table in database. The table design and initial contents are found in "Postgresql Database Notes.txt"
 */

package com.example.demo.DAO;



import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "author", length = 255)
    private String author;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(
        name = "library_record_created_date",
        nullable = false
    )
    private LocalDate libraryRecordCreatedDate;

    @Column(
        name = "read_flag",
        nullable = false
    )
    private boolean readFlag = false;

    /** Many books can belong to one user */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        foreignKey = @ForeignKey(name = "fk_user")
    )
    private User user;

    public Book() {}

    // getters and setters

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getAuthor() {
        return author;
    }
 
    public void setAuthor(String author) {
        this.author = author;
    }
 
    public LocalDate getPublishedDate() {
        return publishedDate;
    }
 
    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }
 
    public LocalDate getLibraryRecordCreatedDate() {
        return libraryRecordCreatedDate;
    }
 
    public void setLibraryRecordCreatedDate(LocalDate libraryRecordCreatedDate) {
        this.libraryRecordCreatedDate = libraryRecordCreatedDate;
    }
 
    public boolean isReadFlag() {
        return readFlag;
    }
 
    public void setReadFlag(boolean readFlag) {
        this.readFlag = readFlag;
    }
 
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
}
