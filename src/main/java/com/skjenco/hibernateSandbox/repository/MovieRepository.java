package com.skjenco.hibernateSandbox.repository;

import com.skjenco.hibernateSandbox.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie,Long> {
}
