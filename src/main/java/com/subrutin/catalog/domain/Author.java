package com.subrutin.catalog.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")
//@DynamicUpdate
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE author SET deleted = true WHERE id = ?")
public class Author extends AbtractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4415917570527208430L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
	@SequenceGenerator(name = "author_generator", sequenceName = "author_id_sequence")
//	@SequenceGenerator(name = "author_generator", sequenceName = "author_id_seq")
	private Long id;
	
	@Column(name = "name", nullable = false, columnDefinition = "varchar(300)")
	private String name;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;


}
