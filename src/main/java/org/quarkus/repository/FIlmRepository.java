package org.quarkus.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.quarkus.entity.Film;
import org.quarkus.entity.Film$;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class FIlmRepository  {

    @Inject
    JPAStreamer jpaStreamer;

    public static final int PAGE_SIZE = 20;

    public Optional<Film> getFilmById(short id) {
        return jpaStreamer
                .stream(Film.class)
                .filter(Film$.id.equal(id))
                .findFirst();
    }

    public Stream<Film> paged(long page, short minLength) {
        return jpaStreamer
                .stream(Film.class)
                .filter(Film$.length.greaterThan(minLength))
                .sorted(Film$.length)
                .skip(page * PAGE_SIZE)
                .limit(PAGE_SIZE);
    }
}










