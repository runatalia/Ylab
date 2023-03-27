package io.ylab.intensive.lesson04.eventsourcing.db;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import io.ylab.intensive.lesson04.DbUtil;
import io.ylab.intensive.lesson04.RabbitMQUtil;
import io.ylab.intensive.lesson04.eventsourcing.Person;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DbApp {
    private static final String EXCHANGE_NAME = "exc";

    public static void main(String[] args) throws Exception {
        DataSource dataSource = initDb();
        ConnectionFactory connectionFactory = initMQ();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            executeRequest(message, dataSource);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }

    private static ConnectionFactory initMQ() throws Exception {
        return RabbitMQUtil.buildConnectionFactoryctionFactory();
    }

    private static DataSource initDb() throws SQLException {
        String ddl = ""
                + "drop table if exists person;"
                + "CREATE TABLE if not exists person (\n"
                + "person_id bigint primary key,\n"
                + "first_name varchar,\n"
                + "last_name varchar,\n"
                + "middle_name varchar\n"
                + ")";
        DataSource dataSource = DbUtil.buildDataSource();
        DbUtil.applyDdl(ddl, dataSource);
        return dataSource;
    }

    private static void executeRequest(String message, DataSource dataSource) {

        if (message.startsWith("delete")) {
            delete(message, dataSource);
        } else {
            try {
                saveOrUpdate(message, dataSource);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void delete(String message, DataSource dataSource) {
        try (java.sql.Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(message);) {
            int checkDeleteRow = preparedStatement.executeUpdate();
            if (checkDeleteRow < 1) {
                throw new SQLException();
            }
            System.out.println("Данные удалены");
        } catch (SQLException e) {
            System.err.println("Данного id не существует");
        }
    }

    private static void saveOrUpdate(String message, DataSource dataSource) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(message, Person.class);
        if (checkPersonInDatabase(person, dataSource) == true) {
            update(dataSource, person);
        } else {
            save(dataSource, person);
        }
    }

    private static boolean checkPersonInDatabase(Person person, DataSource dataSource) {
        try (java.sql.Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("select * from person where person_id = ?")) {
            preparedStatement.setLong(1, person.getId());
            preparedStatement.execute();
            Boolean result = preparedStatement.getResultSet().next();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void save(DataSource dataSource, Person person) {
        try (java.sql.Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("insert into person values(?,?,?,?)")) {
            preparedStatement.setLong(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setString(4, person.getMiddleName());
            preparedStatement.executeUpdate();
            System.out.println("Данные сохранены " + person);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private static void update(DataSource dataSource, Person person) {
        try (java.sql.Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "update person set first_name = ?, last_name =?, middle_name =? where person_id = ?")) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getMiddleName());
            preparedStatement.setLong(4, person.getId());
            preparedStatement.executeUpdate();
            System.out.println("Данные person обновлены: " + person);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
