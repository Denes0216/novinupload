package com.example.novindemo.controller;

import com.example.novindemo.dto.CreateInvoiceDto;
import com.example.novindemo.dto.InvoiceResponseDto;
import com.example.novindemo.entity.Invoice;
import com.example.novindemo.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceResponseDto>> getAllInvoices() {
        List<InvoiceResponseDto> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ACCOUNTANT', 'ROLE_ADMIN')")
    public ResponseEntity<?> createInvoice(@Valid @RequestBody CreateInvoiceDto createInvoiceDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Invoice createdInvoice = invoiceService.createInvoice(createInvoiceDto);
        return ResponseEntity.ok(createdInvoice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDto> getInvoiceById(@PathVariable Long id) {
        InvoiceResponseDto invoice = invoiceService.getInvoiceById(id);
        if (invoice != null) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

