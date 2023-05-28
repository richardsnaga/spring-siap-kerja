package com.subrutin.catalog.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Index;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Data;

@MappedSuperclass
@Table(indexes = {
		@Index(name = "uk_secure_id", columnList = "secure_id")
})
@Data
public abstract class AbtractBaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9088197462256219582L;

	@Column(name = "secure_id", nullable = false, unique = true)
	private String secureId=UUID.randomUUID().toString();
	
	@Column(name = "deleted", columnDefinition = "boolean default false")
	private Boolean deleted;
}
