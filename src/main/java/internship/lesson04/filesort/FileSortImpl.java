package io.ylab.intensive.lesson04.filesort;



import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.sql.DataSource;


public class FileSortImpl implements FileSorter {
  private DataSource dataSource;
  private Connection connection;

  public FileSortImpl(DataSource dataSource) {

    this.dataSource = dataSource;
  }

  @Override
  public File sort(File data) {
    try {
      connection = dataSource.getConnection();
      readFile(data);
      return writeFile();
    } catch (IOException | SQLException  e) {
      throw new RuntimeException(e);
    } finally {
      try {
        connection.close();
      } catch (SQLException e) {
        System.err.println(e.getMessage());
      }
    }
  }

  private void readFile(File data) throws SQLException, FileNotFoundException {
    List<Long> digits = new ArrayList<>();
    int limit = 0;
    Scanner reader = new Scanner(new FileInputStream(data));
    while (reader.hasNextLong()) {
      digits.add(reader.nextLong());
      limit++;
      if (limit >= 100) {
        insertIntoDataBase(digits);
        digits.clear();
        limit = 0;
      }
    }
  }

  private void insertIntoDataBase(List<Long> digits) throws SQLException {
    String sql = "insert into numbers (val) "
            + "VALUES(?)";
    try (PreparedStatement preparedStatement =
                 connection.prepareStatement(sql)) {
      for (Long digit : digits) {
        preparedStatement.setLong(1, digit);
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();
    }
  }

  private File writeFile() throws IOException, SQLException {
    File file = new File("dataSort");
    String sql = "select * from numbers ORDER BY val DESC";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        writer.write(resultSet.getString(1));
        writer.newLine();
      }
    }
    return file;
  }
}
