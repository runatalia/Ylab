package internship.datedMap;

import static java.lang.Thread.sleep;

public class DatedMapTest {
  public static void main(String[]args) throws InterruptedException {
    DatedMap dateMap = new DatedMapImpl();
    dateMap.put("Natalia", "Hi");
    dateMap.put("Vasiliy", "Hello");
    Thread.sleep(1000);
    System.out.println("Значения для ключа Vasiliy: " + dateMap.get("Vasiliy"));
    System.out.println("Время добавления значения с ключом Vasiliy: "
            + dateMap.getKeyLastInsertionDate("Vasiliy"));
    dateMap.put("Olya", "Good morning");
    dateMap.put("Ekaterina", "Good evening");
    System.out.println("Весь set ключей: " + dateMap.keySet());
    Thread.sleep(1000);
    dateMap.put("Vasiliy", "Hi");
    System.out.println("Значения для ключа Vasiliy: " + dateMap.get("Vasiliy"));
    System.out.println("Время изменения значения с ключом Vasiliy: "
            + dateMap.getKeyLastInsertionDate("Vasiliy"));
    dateMap.remove("Vasiliy");
    System.out.println("Значения для ключа Vasiliy после удаления: " + dateMap.get("Vasiliy"));
    System.out.println("Значения для ключа Olya: " + dateMap.get("Olya"));
    System.out.println("Весь set ключей: " + dateMap.keySet());
    System.out.println("Время добавления значения с ключом Olya: "
            + dateMap.getKeyLastInsertionDate("Olya"));
  }
}
