package com.skjenco.hibernateSandbox.repository;

import com.skjenco.hibernateSandbox.model.Genre;
import com.skjenco.hibernateSandbox.model.Movie;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenreRepositoryTest {

    private static Logger logger = Logger.getLogger(GenreRepositoryTest.class);

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MovieRepository movieRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @Transactional
    public void test() {

        logger.info(TransactionSynchronizationManager.getCurrentTransactionName());
        Set<Movie> actionMovies = new HashSet<>();
        Set<Movie> dramaMovies = new HashSet<>();
        Set<Genre> dramaGenres = new HashSet<>();
        Set<Genre> actionGenres = new HashSet<>();
        Set<Genre> generes = new HashSet<>();

        Movie actionMovie = new Movie();
        actionMovie.setName("Batman");
        actionMovies.add(actionMovie);

        Movie dramaMovie = new Movie();
        dramaMovie.setName("Forest Gump");
        dramaMovies.add(dramaMovie);

        Genre actionGenre = new Genre();
        actionGenre.setName("Action");
        generes.add(actionGenre);
        actionGenres.add(actionGenre);

        Genre dramaGenre = new Genre();
        dramaGenre.setName("Drama");
        generes.add(dramaGenre);
        dramaGenres.add(dramaGenre);

        //Bidirectional sets
        actionGenre.setMovies(actionMovies);
        dramaGenre.setMovies(dramaMovies);
        actionMovie.setGenres(actionGenres);
        dramaMovie.setGenres(dramaGenres);

        genreRepository.saveAll(generes);

        System.out.println(actionMovie.getGenres().size());

        Query query = entityManager.createQuery("SELECT m from Movie m left join m.genres g where g.name = 'Action'");
        List<Movie> resultList = query.getResultList();
        assertEquals("Batman",resultList.get(0).getName());
        System.out.println(resultList.size());

    }

}