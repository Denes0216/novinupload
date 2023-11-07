package com.example.novindemo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateInvoiceDto {
    @NotNull(message = "Price is mandatory")
    private String buyer;

    private Date issueDate = new Date();
    @NotNull(message = "Price is mandatory")
    private Date dueDate;
    @NotEmpty(message = "Price is mandatory")
    private String itemName;
    @NotEmpty(message = "Price is mandatory")
    private String comment;
    @NotNull(message = "Price is mandatory")
    @Min(value = 0)
    private double price;

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
}
