package com.example.concurrent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.example.concurrent.model.DummyTicket;
import jakarta.persistence.LockModeType;

/**
 * Interface TicketRepository is responsible for
 *
 * @author Mykola.Matsishin <br> created on 29 September 2023
 * @since 5.8
 */
public interface TicketRepository extends JpaRepository<DummyTicket, Integer> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT t.id from DummyTicket t WHERE t.id=:id")
	Optional<Long> acquirePessimisticLock(Integer id);
}
