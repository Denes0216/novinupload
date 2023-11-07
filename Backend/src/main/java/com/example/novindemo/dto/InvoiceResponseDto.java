package com.example.novindemo.dto;

import lombok.*;

import java.util.Date;
@Data
@NoArgsConstructor
@Getter
@Setter
public class InvoiceResponseDto {
    private Long id;
    private String username;
    private Date issueDate;
    private Date dueDate;
    private String itemName;
    private double price;
    private String comment;

    public InvoiceResponseDto(Long id, Date issueDate, Date dueDate, String itemName, double price, String comment, String username) {
        this.id = id;
        this.username = username;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.itemName = itemName;
        this.price = price;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

