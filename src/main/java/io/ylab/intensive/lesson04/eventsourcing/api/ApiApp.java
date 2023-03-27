package io.ylab.intensive.lesson04.eventsourcing.api;


import com.rabbitmq.client.ConnectionFactory;
import io.ylab.intensive.lesson04.DbUtil;
import io.ylab.intensive.lesson04.RabbitMQUtil;
import io.ylab.intensive.lesson04.eventsourcing.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApiApp {
  public static void main(String[] args) throws Exception {
    PersonApiImpl personApi = new PersonApiImpl(initMQ(), DbUtil.buildDataSource());
    personApi.savePerson(1L, "Natalia", "Voronina", "Aleksandrovna");
    personApi.savePerson(2L, "Volodya", "Sergeev", "Vasilevich");
    personApi.savePerson(3L, "Vasya", "Kosicina", "Alekseevna");
    personApi.savePerson(3L, "Olya", "Morojkina", "Andreevna");
    personApi.savePerson(4L, "Masha", "Divanova", "Vladimirovna");
    personApi.savePerson(5L, "Andrey", "Kisa", "Timurovna");
    personApi.deletePerson(1L);
    personApi.deletePerson(15L);
    Person person1 = personApi.findPerson(2L);
    System.out.println("Найден person c id = 2: " + person1);
    Person person2 = personApi.findPerson(15L);
    System.out.println("Найден person c id = 15: " + person2);
    List<Person> persons = personApi.findAll();
    System.out.println("Все найденные persons: ");
    for (Person p : persons) {
      System.out.println(p);
    }
  }

  private static ConnectionFactory initMQ() throws Exception {
    return RabbitMQUtil.buildConnectionFactoryctionFactory();
  }
}
