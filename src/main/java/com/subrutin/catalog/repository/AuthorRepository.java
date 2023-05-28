package com.subrutin.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subrutin.catalog.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	//sql = select * from Author a where a.id=author.id
	public Optional<Author> findById(Long id);
	
	public List<Author> findBySecureIdIn(List<String> authorIdList);
	
	public Optional<Author> findBySecureId(String id);
	
	public Optional<Author> findByIdAndDeletedFalse(Long id);

	//sql = select * from author a where a.name LIKE authorName
	public List<Author> findByNameLike(String authorName);
}
