package com.example.concurrent.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Class DummyComments is responsible for
 *
 * @author Mykola.Matsishin <br> created on 04 October 2023
 * @since 5.8
 */
@Entity
@Table( name = "ticket_comments")
public class DummyComment implements Comparable<DummyComment> {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "comment")
	private String comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int compareTo(DummyComment o) {
		return String.CASE_INSENSITIVE_ORDER.compare(this.comment, o.comment);
	}
}
