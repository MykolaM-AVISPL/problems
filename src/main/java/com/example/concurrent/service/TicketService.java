package com.example.concurrent.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.example.concurrent.model.DummyTicket;
import com.example.concurrent.repository.TicketRepository;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class TicketService is responsible for
 *
 * @author Mykola.Matsishin <br> created on 29 September 2023
 * @since 5.8
 */
@Service
public class TicketService {
	Logger logger = LoggerFactory.getLogger(TicketService.class);
	@Autowired
	private TicketRepository repository;
	@Autowired
	private TransactionDelegate transactionDelegate;

	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public void executeTicketSubmission(DummyTicket dummyTicket, String fieldUpdate) {
		logger.info("starting update {} {}", Thread.currentThread().getName(), fieldUpdate);
		try {
			repository.acquirePessimisticLock(dummyTicket.getId());
			dummyTicket.setField1(fieldUpdate);
			repository.save(dummyTicket);
			logger.info("finished update {} {}", Thread.currentThread().getName(), fieldUpdate);
		} catch (Exception e) {
			logger.error("failed update {} {} {}", Thread.currentThread().getName(), fieldUpdate,ExceptionUtils.getRootCauseMessage(e));
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void complexTransaction() {
		Optional<DummyTicket> ticket = transactionDelegate.findTicket(1);
		if (ticket.isPresent()) {
			DummyTicket entity = ticket.get();
			entity.setField2("success");
			repository.save(entity);
		}
	}
}
