package com.example.concurrent.service;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.concurrent.model.DummyTicket;
import jakarta.annotation.PostConstruct;

/**
 * Class UpdateRunner is responsible for
 *
 * @author Mykola.Matsishin <br> created on 29 September 2023
 * @since 5.8
 */
@Component
public class UpdateRunner {
	@Autowired
	private TicketService ticketService;

	private ExecutorService executorService = Executors.newFixedThreadPool(100);

	@PostConstruct
	public void runConcurrentUpdate() {
		DummyTicket dummyTicket = new DummyTicket();
		dummyTicket.setId(1);
		dummyTicket.setField1("initial");
		for (int i = 0; i < 100; i++) {
			executorService.submit(() -> ticketService.executeTicketSubmission(dummyTicket, UUID.randomUUID().toString()));
		}
	}

}
