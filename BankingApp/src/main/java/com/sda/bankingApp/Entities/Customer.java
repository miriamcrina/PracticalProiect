package com.sda.bankingApp.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "cnp")
    private String cnp;

    @Column (name = "email")
    private String email;

    @Column (name = "username")
    private String username;

    @Column (name = "password")
    private String password;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<Accounts> accounts = new ArrayList<>();


    public Customer() {
    }

    public Customer(String firstName, String lastName, String cnp, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = cnp;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCnp() {
        return cnp;
    }

    public String getEmail() {
        return email;
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

    public List<Accounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cnp=" + cnp +
                ", email='" + email + '\'' +
                '}';
    }
}
