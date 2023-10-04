package com.example.concurrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.concurrent.model.DummyTicket;

/**
 * Interface TicketRepository is responsible for
 *
 * @author Mykola.Matsishin <br> created on 29 September 2023
 * @since 5.8
 */
public interface TicketRepository extends JpaRepository<DummyTicket, Integer>, CustomTicketRepository {
}
