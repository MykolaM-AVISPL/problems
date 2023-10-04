package com.example.concurrent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.concurrent.model.DummyComment;

/**
 * Class DummyCommentRepository is responsible for
 *
 * @author Mykola.Matsishin <br> created on 04 October 2023
 * @since 5.8
 */
public interface DummyCommentRepository extends JpaRepository<DummyComment, Integer> {
}
