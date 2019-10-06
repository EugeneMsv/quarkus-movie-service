package com.github.eugenemsv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/movie")
public class MovieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Inject
    private MovieRepository movieRepository;

    @Path("/table")
    @POST
    public void createTable() {
        LOGGER.info("Creating movie table");
        movieRepository.createTable();
    }

    @Path("/{id}")
    @POST
    public void add(@PathParam("id") Long id, String name) {
        LOGGER.info("Creating movie record id={}, name={}", id, name);
        movieRepository.add(id, name);
    }

    @Path("/{id}")
    @GET
    public String findById(@PathParam("id") Long id) {
        LOGGER.info("Movie find: id={}", id);
        return movieRepository.findById(id);
    }

}