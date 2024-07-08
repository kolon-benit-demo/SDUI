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
@Table(name = "page_layout")
@NoArgsConstructor
@Getter
public class PageLayout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, columnDefinition = "LONGTEXT")
	private String contents;

	public PageLayout(String name, String contents) {
		this.name = name;
		this.contents = contents;
	}
}
