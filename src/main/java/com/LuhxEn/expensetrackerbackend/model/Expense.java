package com.LuhxEn.expensetrackerbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="expenses_generator")
    private long id;

    @Column(name="title")
    private String title;
    @Column(name="recipient")
    private String recipient;
    @Column(name="allowance")
    private double allowance;
    @Lob
    private String description;

    public Expense() {
    }

    public Expense(String title, String recipient, double allowance, String description) {
        this.title = title;
        this.recipient = recipient;
        this.allowance = allowance;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
