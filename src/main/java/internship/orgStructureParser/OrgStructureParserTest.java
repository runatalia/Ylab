package internship.orgStructureParser;

import java.io.File;
import java.io.IOException;

public class OrgStructureParserTest {
  public static void main(String[]args)  {
    File file = new File("src/main/resources/csvFile.txt");
    OrgStructureParser orgStructureParser = new OrgStructureParserImpl();
    Employee headEmployee;
    try {
      headEmployee = orgStructureParser.parseStructure(file);
      System.out.println("id: " + headEmployee.getId());
      System.out.println("Position: " + headEmployee.getPosition());
      System.out.println("Name: " + headEmployee.getName());
      headEmployee.subordinateToString();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
