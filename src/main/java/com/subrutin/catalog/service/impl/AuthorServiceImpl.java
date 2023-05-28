package com.subrutin.catalog.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.dto.AuthorCreateRequestDTO;
import com.subrutin.catalog.dto.AuthorResponseDTO;
import com.subrutin.catalog.dto.AuthorUpdateRequestDTO;
import com.subrutin.catalog.exception.BadRequestException;
import com.subrutin.catalog.repository.AuthorRepository;
import com.subrutin.catalog.service.AuthorService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;

	@Override
	public AuthorResponseDTO findAuthorById(String id) {
		// TODO Auto-generated method stub
		// 1. Fetch data from database
		Author author = authorRepository.findBySecureId(id)
				.orElseThrow(() -> new BadRequestException("Invalid authorId"));
		// 2. author -> authorResponseDTO
		AuthorResponseDTO dto = new AuthorResponseDTO();
		dto.setAuthorName(author.getName());
		dto.setBirthDate(author.getBirthDate().toEpochDay());
		return dto;
	}

	@Override
	public void createNewAuthor(AuthorCreateRequestDTO dto) {
		Author author = new Author();
		author.setName(dto.getAuthorName());
		author.setBirthDate(LocalDate.ofEpochDay(dto.getBirthDate()));

		authorRepository.save(author);
	}

	@Override
	public void updateAuthor(String authorId, AuthorUpdateRequestDTO dto) {
		Author author = authorRepository.findBySecureId(authorId)
				.orElseThrow(() -> new BadRequestException("Invalid authorId"));

		author.setName(dto.getAuthorName() == null ? author.getName() : dto.getAuthorName());
		author.setBirthDate(
				dto.getBirthDate() == null ? author.getBirthDate() : LocalDate.ofEpochDay(dto.getBirthDate()));

		authorRepository.save(author);
	}

	@Override
	public void deleteAuthor(String authorId) {
		// 1. select data
		// 2. delete
		// or
		// 1. delete (hard delete)
//		authorRepository.deleteById(authorId);
		Author author = authorRepository.findBySecureId(authorId)
				.orElseThrow(() -> new BadRequestException("Invalid authorId"));

		authorRepository.delete(author);
		// soft delete
		// 1. select data deleted = false
//		Author author = authorRepository.findByIdAndDeletedFalse(authorId).orElseThrow(()->new BadRequestException("Invalid.authorId"));
//		// 2. update deleted = true
//		author.setDeleted(Boolean.TRUE);
//		authorRepository.save(author);

	}

	@Override
	public List<Author> findAuthors(List<String> authorIdList) {
		List<Author> authors = authorRepository.findBySecureIdIn(authorIdList);
		if (authors.isEmpty())
			throw new BadRequestException("author can't empty");
		return authors;
	}

	@Override
	public List<AuthorResponseDTO> constructDTO(List<Author> authors) {
		
		return authors.stream().map((a)->{
			AuthorResponseDTO dto = new AuthorResponseDTO();
			dto.setAuthorName(a.getName());
			dto.setBirthDate(a.getBirthDate().toEpochDay());
			return dto;
		}).collect(Collectors.toList());
	}

}
