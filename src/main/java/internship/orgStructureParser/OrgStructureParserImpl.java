package internship.orgStructureParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrgStructureParserImpl implements OrgStructureParser {
  private List<Employee> employees = new ArrayList<>();

  @Override
  public Employee parseStructure(File csvFile) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
      String line;
      String [] readArray;
      reader.readLine();
      while (!(line = reader.readLine()).isEmpty()) {
        readArray = line.split(";");
        setValuesInEmployee(readArray);
      }
      setSubordinate();
      return employees.stream().filter(x -> x.getBossId() == null).findFirst().orElseThrow();
    }
  }

  private void setValuesInEmployee(String[] line) {
    Employee employee = new Employee();
    employee.setId(Long.parseLong(line[0].trim()));
    try {
      employee.setBossId(Long.parseLong(line[1].trim()));
    } catch (NumberFormatException e) {
      employee.setBossId(null);
    }
    employee.setName(line[2].trim());
    employee.setPosition(line[3].trim());
    employees.add(employee);
  }

  private void setSubordinate() {
    for (int i = 0;i < employees.size(); i++) {
      for (int j = 0;j < employees.size(); j++) {
        if (employees.get(i).getId() == employees.get(j).getBossId()) {
        employees.get(i).getSubordinate().add(employees.get(j));
        }
      }
    }
  }
}
