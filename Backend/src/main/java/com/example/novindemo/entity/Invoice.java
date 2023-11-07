package com.example.novindemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Buyer date is mandatory")
    private String buyer;

    @Temporal(TemporalType.DATE)
    @Column(name = "issue_date")
    @NotNull(message = "Issue date is mandatory")
    private Date issueDate;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "Due date is mandatory")
    @Column(name = "due_date")
    private Date dueDate;

    @NotNull(message = "Item name is mandatory")
    private String itemName;

    @NotNull(message = "Comment is mandatory")
    private String comment;

    @Column(name = "item_price")
    @NotNull(message = "Price is mandatory")
    private double price;

    public Long getId() {
        return id;
    }

    public String getBuyer() {
        return buyer;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getItemName() {
        return itemName;
    }

    public String getComment() {
        return comment;
    }

    public double getPrice() {
        return price;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
