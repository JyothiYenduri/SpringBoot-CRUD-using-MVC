package com.example.invoiceprocessapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.invoiceprocessapplication.exception.InvoiceNotFoundException;
import com.example.invoiceprocessapplication.model.Invoice;
import com.example.invoiceprocessapplication.service.InvoiceService;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceservice;

	//Create a new invoice
	@PostMapping
	public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice){
		Invoice savedInvoice=invoiceservice.saveInvoice(invoice);
		return new ResponseEntity<>(savedInvoice, HttpStatus.CREATED);
		
	}
	
	//Get all invoices
	@GetMapping
	public ResponseEntity<List<Invoice>> getAllInvoices(){
		List<Invoice> invoices=invoiceservice.getAllInvoices();
		return new ResponseEntity<>(invoices,HttpStatus.OK);
		
	}
	
	//Get an invoice by ID
	@GetMapping("/{id}")
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id){
		try {
			Invoice invoice=invoiceservice.getInvoiceById(id);
			return new ResponseEntity<>(invoice,HttpStatus.OK);
		} catch (InvoiceNotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
				
	}
	
	//Update an existing invoice
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
		try {
			invoice.setId(id);
			invoiceservice.updateInvoice(invoice);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (InvoiceNotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	//Delete an invoice by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
		try {
			invoiceservice.deleteInvoiceById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (InvoiceNotFoundException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
}
