package com.example.novindemo.service;

import com.example.novindemo.dto.CreateInvoiceDto;
import com.example.novindemo.dto.InvoiceResponseDto;
import com.example.novindemo.entity.Invoice;
import com.example.novindemo.repository.InvoiceRepository;
import com.example.novindemo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, UserRepository userRepository) {
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
    }

    public List<InvoiceResponseDto> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<InvoiceResponseDto> responseDtos = new ArrayList<>();
        for (Invoice invoice : invoices) {
            InvoiceResponseDto response = new InvoiceResponseDto(
                    invoice.getId(),
                    invoice.getIssueDate(),
                    invoice.getDueDate(),
                    invoice.getItemName(),
                    invoice.getPrice(),
                    invoice.getComment(),
                    invoice.getBuyer()
            );
            responseDtos.add(response);
        }
        return responseDtos;
    }

    public Invoice createInvoice(CreateInvoiceDto createInvoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setBuyer(createInvoiceDto.getBuyer());
        invoice.setComment(createInvoiceDto.getComment());
        invoice.setPrice(createInvoiceDto.getPrice());
        invoice.setDueDate(createInvoiceDto.getDueDate());
        invoice.setIssueDate(createInvoiceDto.getIssueDate());
        invoice.setItemName(createInvoiceDto.getItemName());
        return invoiceRepository.save(invoice);
    }

    public InvoiceResponseDto getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        return new InvoiceResponseDto(
                invoice.getId(),
                invoice.getIssueDate(),
                invoice.getDueDate(),
                invoice.getItemName(),
                invoice.getPrice(),
                invoice.getComment(),
                invoice.getBuyer());
    }
}
