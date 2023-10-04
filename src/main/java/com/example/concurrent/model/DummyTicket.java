package com.example.concurrent.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Class DummyTicket is responsible for
 *
 * @author Mykola.Matsishin <br> created on 29 September 2023
 * @since 5.8
 */
@Entity
@Table(name = "tickets")
public class DummyTicket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "field1")
	private String field1;
	@Column(name = "field2")
	private String field2;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = DummyComment.class, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "ticket_id", referencedColumnName = "id", nullable = false, updatable = false)
	private List<DummyComment> dummyComments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public List<DummyComment> getDummyComments() {
		return dummyComments;
	}

	public void setDummyComments(List<DummyComment> dummyComments) {
		this.dummyComments = dummyComments;
	}
}
