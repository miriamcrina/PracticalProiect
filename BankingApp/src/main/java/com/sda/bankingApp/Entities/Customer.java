package com.sda.bankingApp.Entities;

import javax.persistence.*;


@Entity(name = "Customer")
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column (name = "first_name")
    private String firstName;
}
