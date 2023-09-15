package com.example.invoiceprocessapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invoiceprocessapplication.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
