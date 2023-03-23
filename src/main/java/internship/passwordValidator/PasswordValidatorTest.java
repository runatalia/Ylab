package internship.passwordValidator;

import java.util.Scanner;

public class PasswordValidatorTest {
  public static void main(String[] args) throws WrongLoginException {
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println(passwordValidator.authentication(scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));
    }
  }
}
