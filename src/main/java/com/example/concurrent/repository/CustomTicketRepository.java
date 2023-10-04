package com.example.concurrent.repository;

/**
 * Interface CustomTicketRepository is responsible for
 *
 * @author Mykola.Matsishin <br> created on 04 October 2023
 * @since 5.8
 */
public interface CustomTicketRepository {
	public void blockingLock(long lockId);

}
