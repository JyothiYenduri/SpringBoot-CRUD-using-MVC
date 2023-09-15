package com.example.invoiceprocessapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.invoiceprocessapplication.exception.InvoiceNotFoundException;
import com.example.invoiceprocessapplication.model.Invoice;
import com.example.invoiceprocessapplication.repository.InvoiceRepository;
@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository repo;
	@Override
	public Invoice saveInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		return repo.save(invoice);
	}

	@Override
	public List<Invoice> getAllInvoices() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Invoice getInvoiceById(Long id) {
		// TODO Auto-generated method stub
		Optional<Invoice> opt=repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			throw new InvoiceNotFoundException("Invoice with ID: " +id+" not found ");
		}
		
	}

	@Override
	public void deleteInvoiceById(Long id) {
		// TODO Auto-generated method stub
		repo.delete(getInvoiceById(id));
	}

	@Override
	public void updateInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		repo.save(invoice);
	}

}
