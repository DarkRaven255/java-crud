package pl.kdulemba.javacrud.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "f_name")
    private String FirstName;

    @Column(name = "l_name")
    private String LastName;

    @Column(name = "email")
    private String Email;

    public User() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public User(String firstName, String lastName, String email) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
    }
}
