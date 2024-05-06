package org.quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.quarkus.entity.Film;
import org.quarkus.repository.FIlmRepository;

import java.util.List;

@Path("/")
public class FIleResource {

    @Inject
    FIlmRepository filmRepository;

    @GET
    @Path("/helloWorld")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld() {
        return "Hello World!";
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("id") short id) {
        return filmRepository.getFilmById(id)
                .orElseThrow(
                        () -> new RuntimeException("asd")
                ).getDescription();
    }

    @GET
    @Path("paged/{pages}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public List<Film> listOfPagedFilms(@PathParam("pages") long page, @PathParam("minLength") short minLength) {
        return filmRepository.paged(page, minLength)
                .toList();
    }


}
