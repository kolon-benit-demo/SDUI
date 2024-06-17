package com.example.sdui.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sdui")
@NoArgsConstructor
@Getter
public class Sdui {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true, columnDefinition = "LONGTEXT")
	private String json;

	public Sdui(final String json) {
		this.json = json;
	}
}
