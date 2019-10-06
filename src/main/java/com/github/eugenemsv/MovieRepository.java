package com.github.eugenemsv;

import io.agroal.api.AgroalDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.*;

@ApplicationScoped
public class MovieRepository {

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE movie (id BIGINT, name VARCHAR(16))";
    private static final String INSERT_MOVIE_QUERY = "INSERT INTO movie (id , name) VALUES (?,?)";
    private static final String SELECT_MOVIE_QUERY = "SELECT name from movie where id=?";

    @Inject
    private AgroalDataSource dataSource;

    public void createTable() {
        try (Connection connection = dataSource.getConnection()) {

            try (Statement statement = connection.createStatement()) {
                statement.execute(CREATE_TABLE_QUERY);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create movie table", e);
        }
    }

    public void add(long id, String name) {
        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(INSERT_MOVIE_QUERY)) {
                statement.setLong(0, id);
                statement.setString(1, name);
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert new movie", e);
        }
    }

    public String findById(long id) {
        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(INSERT_MOVIE_QUERY)) {
                statement.setLong(0, id);
                try (ResultSet resultSet = statement.getResultSet()) {
                    return resultSet.getString(0);
                }
            }

        } catch (
                SQLException e) {
            throw new RuntimeException("Failed to insert new movie", e);
        }
    }

}
