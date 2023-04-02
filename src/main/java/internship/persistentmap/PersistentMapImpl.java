package io.ylab.intensive.lesson04.persistentmap;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class PersistentMapImpl implements PersistentMap {

    private DataSource dataSource;
    private String mapName;

    public PersistentMapImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void init(String name) {
        mapName = name;
    }

    @Override
    public boolean containsKey(String key) throws SQLException {
        String sql = "select * from persistent_map where key = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {
            preparedStatement.setString(1, key);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            Boolean result = preparedStatement.getResultSet().next();
            return result;
        }
    }

    @Override
    public List<String> getKeys() throws SQLException {
        List<String> keys = new java.util.ArrayList<>();
        String sql = "select * from persistent_map";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                keys.add(resultSet.getString(2));
            }
            return keys;
        }
    }

    @Override
    public String get(String key) throws SQLException {
        String sql;
        String result = null;
        if (!containsKey(key)) {
            System.err.println("Ключ " + key + " не существует в БД");
            return null;
        } else {
            sql = "select * from persistent_map where key = ?";
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {
            preparedStatement.setString(1, key.trim());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                result = resultSet.getString(3);
            }
        }
        return result;
    }

    @Override
    public void remove(String key) throws SQLException {
        String sql = null;
        if (!containsKey(key)) {
            System.err.println("Ключ " + key + " не существует в БД");
        } else {
            sql = "delete from persistent_map where key = ?";
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {
            preparedStatement.setString(1, key.trim());
            preparedStatement.executeUpdate();
            System.out.println("Данные с ключом " + key + " удалены");
        }
    }

    @Override
    public void put(String key, String value) throws SQLException {
        String sql;
        if (!containsKey(key)) {
            sql = "insert into persistent_map (map_name, value,KEY) VALUES(?, ? ,? )";
        } else {
            sql = "update persistent_map set map_name = ?,  value = ? where key = ?";
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {
            preparedStatement.setString(1, mapName.trim());
            preparedStatement.setString(3, key.trim());
            preparedStatement.setString(2, value.trim());
            preparedStatement.executeUpdate();
            System.out.println("Данные: ключ - " + key + ", value - " + value + " добавлены в мапу " + mapName);
        }
    }

    @Override
    public void clear() throws SQLException {
        String sql = "delete from persistent_map where map_name = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {
            preparedStatement.setString(1, mapName);
            preparedStatement.executeUpdate();
            System.out.println("Все данные map " + mapName + " удалены");
        }
    }
}
