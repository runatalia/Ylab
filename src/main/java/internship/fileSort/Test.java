package internship.fileSort;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
  public static void main(String[] args) throws IOException {
    File dataFile = new Generator().generate("data.txt", 3750_000); //за 12 минут  375_000_000 записей
    System.out.println(new Validator(dataFile).isSorted()); // false
    long time = System.currentTimeMillis();
    File sortedFile = new Sorter().sortFile(dataFile);
    System.out.println("Время сортировки: "+ (System.currentTimeMillis()- time)*0.001 + " секунд");
    System.out.println(new Validator(sortedFile).isSorted()); // true
  }
}
