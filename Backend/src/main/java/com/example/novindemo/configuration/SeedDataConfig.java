package com.example.novindemo.configuration;

import com.example.novindemo.entity.Invoice;
import com.example.novindemo.entity.Role;
import com.example.novindemo.entity.UserEntity;
import com.example.novindemo.repository.InvoiceRepository;
import com.example.novindemo.repository.RoleRepository;
import com.example.novindemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public SeedDataConfig(UserRepository userRepository, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder, InvoiceRepository invoiceRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            createRoles();
            createUsers();
            createInvoices();
        }
    }

    private void createRoles() {
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        adminRole.setDescription("Admin Role");
        roleRepository.save(adminRole);

        Role accountantRole = new Role();
        accountantRole.setName("ROLE_ACCOUNTANT");
        accountantRole.setDescription("Accountant Role");
        roleRepository.save(accountantRole);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        userRole.setDescription("User Role");
        roleRepository.save(userRole);
    }

    private void createUsers() {
        List<UserEntity> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            UserEntity user = new UserEntity();
            user.setName("User " + i);
            user.setUsername("user" + i);
            user.setPassword(passwordEncoder.encode("password" + i));
            user.setLoginDate(new Date());

            if (i < 2) {
                user.setRoles(new HashSet<>(Collections.singletonList(
                        roleRepository.findByName("ROLE_USER").get())));
            } else if (i < 4) {
                user.setRoles(new HashSet<>(Collections.singletonList(
                        roleRepository.findByName("ROLE_ACCOUNTANT").get())));
            } else if (i < 6) {
                user.setRoles(new HashSet<>(Collections.singletonList(
                        roleRepository.findByName("ROLE_ADMIN").get())));
            } else if (i == 6) {
                user.setRoles(new HashSet<>(Arrays.asList(
                        roleRepository.findByName("ROLE_USER").get(),
                        roleRepository.findByName("ROLE_ACCOUNTANT").get()
                )));
            } else if (i == 7) {
                user.setRoles(new HashSet<>(Arrays.asList(
                        roleRepository.findByName("ROLE_USER").get(),
                        roleRepository.findByName("ROLE_ADMIN").get()
                )));
            } else if (i == 8) {
                user.setRoles(new HashSet<>(Arrays.asList(
                        roleRepository.findByName("ROLE_ACCOUNTANT").get(),
                        roleRepository.findByName("ROLE_ADMIN").get()
                )));
            } else {
                user.setRoles(new HashSet<>(Arrays.asList(
                        roleRepository.findByName("ROLE_USER").get(),
                        roleRepository.findByName("ROLE_ACCOUNTANT").get(),
                        roleRepository.findByName("ROLE_ADMIN").get()
                )));
            }
            users.add(user);
        }
        userRepository.saveAll(users);
    }

    private void createInvoices() {
        List<UserEntity> users = userRepository.findAll();
        List<Invoice> invoices = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Invoice invoice = new Invoice();
            invoice.setBuyer(users.get(i % users.size()).getName());
            invoice.setIssueDate(new Date());
            invoice.setDueDate(new Date());
            invoice.setItemName("Item " + i);
            invoice.setComment("Comment for Item " + i);
            invoice.setPrice(100.0 * (i + 1));
            invoices.add(invoice);
        }
        invoiceRepository.saveAll(invoices);
    }
}
