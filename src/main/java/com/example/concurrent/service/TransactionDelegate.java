package com.example.concurrent.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.concurrent.model.DummyTicket;
import com.example.concurrent.repository.TicketRepository;

/**
 * Class TransactionDelegate is responsible for
 *
 * @author Mykola.Matsishin <br> created on 03 October 2023
 * @since 5.8
 */
@Component
public class TransactionDelegate {
	@Autowired
	private TicketRepository repository;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
	public Optional<DummyTicket> findTicket(Integer id) {
		Optional<DummyTicket> ticket = repository.findById(id);
		DummyTicket dummyTicket = ticket.get();
		dummyTicket.setField1("panda");
		repository.save(dummyTicket);
		return ticket;
	}
}
