package io.ylab.intensive.lesson04.movie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;

public class MovieLoaderImpl implements MovieLoader {
  private final DataSource dataSource;

  public MovieLoaderImpl(DataSource dataSource) {

    this.dataSource = dataSource;
  }

  @Override
  public void loadData(File file) {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      Movie movie;
      String[] readArray;
      reader.readLine();
      reader.readLine();
      while ((line = reader.readLine()) != null) {
        readArray = line.split(";");
        movie = setValuesInMovie(readArray);
        insertIntoDataBase(movie);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Movie setValuesInMovie(String[] line) {
    Movie movie = new Movie();
    try {
      movie.setYear(Integer.parseInt(line[0].trim()));
    } catch (NumberFormatException e) {
      movie.setYear(null);
    }
    try {
      movie.setLength(Integer.parseInt(line[1].trim()));
    } catch (NumberFormatException e) {
      movie.setLength(null);
    }
    movie.setTitle(line[2].trim());
    movie.setSubject(line[3].trim());
    movie.setActors(line[4].trim());
    movie.setActress(line[5].trim());
    movie.setDirector(line[6].trim());
    try {
      movie.setPopularity(Integer.parseInt(line[7].trim()));
    } catch (NumberFormatException e) {
      movie.setPopularity(null);
    }
    String awards = line[1].trim().toUpperCase();
    if (!awards.equals("NO")) {
      movie.setAwards(true);
    } else {
      movie.setAwards(false);
    }
    return movie;
  }

  private void insertIntoDataBase(Movie movie) {
    String sql = "insert into movie (year,length,title,subject,actors,actress,director,popularity,awards) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection connection = dataSource.getConnection();
      PreparedStatement preparedStatement =
            connection.prepareStatement(sql);) {

      insertInTable(1, movie.getYear(), preparedStatement);
      insertInTable(2, movie.getLength(), preparedStatement);
      insertInTable(3, movie.getTitle(), preparedStatement);
      insertInTable(4, movie.getSubject(), preparedStatement);
      insertInTable(5, movie.getActors(), preparedStatement);
      insertInTable(6, movie.getActress(), preparedStatement);
      insertInTable(7, movie.getDirector(), preparedStatement);
      insertInTable(8, movie.getPopularity(), preparedStatement);
      insertInTable(9, movie.getAwards(), preparedStatement);

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void insertInTable(int index, Integer data,  PreparedStatement preparedStatement) throws SQLException {
    if (data != null) {
      preparedStatement.setInt(index, data);
    } else {
      preparedStatement.setNull(index, Types.INTEGER);
    }
  }

  private void insertInTable(int index, String data, PreparedStatement preparedStatement) throws SQLException {
    if (data != null) {
      preparedStatement.setString(index, data);
    } else {
      preparedStatement.setNull(index, Types.VARCHAR);
    }
  }

  private void insertInTable(int index, Boolean data, PreparedStatement preparedStatement) throws SQLException {
    if (data != null) {
      preparedStatement.setBoolean(index, data);
    } else {
      preparedStatement.setNull(index, Types.BOOLEAN);
    }
  }
}
