package com.skjenco.hibernateSandbox.repository;

import com.skjenco.hibernateSandbox.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
