package com.example.concurrent.repository;


import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

/**
 * Class CustomTicketRepositoryImpl is responsible for
 *
 * @author Mykola.Matsishin <br> created on 04 October 2023
 * @since 5.8
 */
@Repository
public class CustomTicketRepositoryImpl implements CustomTicketRepository {
	private static final String BLOCKING_QUERY_TXT = "SELECT count(*) FROM pg_advisory_xact_lock( :lockId )";
	@PersistenceContext
	private EntityManager em;

	@Transactional( value = TxType.MANDATORY )
	public void blockingLock(long lockId) {
		// using transaction-level lock, which is released automatically at the end of the transaction
		Query query = em.createNativeQuery(BLOCKING_QUERY_TXT);
		query.setParameter("lockId", lockId);
		query.getSingleResult();
	}
}
