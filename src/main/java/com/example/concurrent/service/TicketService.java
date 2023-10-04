package com.example.concurrent.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.concurrent.model.DummyComment;
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

	@Transactional
	public void executeTicketSubmission(DummyTicket dummyTicket, String fieldUpdate) {
		logger.info("starting update {} {}", Thread.currentThread().getName(), fieldUpdate);
		try {
			repository.blockingLock(dummyTicket.getId());
			Optional<DummyTicket> ticketById = repository.findById(1);
			String ticketUUID = UUID.randomUUID().toString();
			List<DummyComment> oldComments = ticketById.map(DummyTicket::getDummyComments)
					.orElse(new ArrayList<>())
					.stream()
					.sorted()
					.limit(20)
					.collect(Collectors.toList());
			dummyTicket.setField1(fieldUpdate);
			for (int i = 0; i < 30; i++) {
				DummyComment dummyComment = new DummyComment();
				dummyComment.setComment(i+" "+ticketUUID);
				oldComments.add(dummyComment);
			}
			dummyTicket.setDummyComments(oldComments);
			repository.save(dummyTicket);
			logger.info("finished update {} {}", Thread.currentThread().getName(), fieldUpdate);
		} catch (Exception e) {
			logger.error("failed update {} {} {}", Thread.currentThread().getName(), fieldUpdate, ExceptionUtils.getRootCauseMessage(e));
		}
	}
}
