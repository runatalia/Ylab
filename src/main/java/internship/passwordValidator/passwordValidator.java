package internship.passwordValidator;

import java.util.regex.Pattern;
public class passwordValidator {
  public static boolean authentication(String login, String password, String confirmPassword) {
    boolean authentication = true;
    try {
      if (!Pattern.matches("\\w+", login)) {
        throw new WrongLoginException("Логин содержит недопустимые символы");
      }
      if (login.length() > 20) {
        throw new WrongLoginException("Логин слишком длинный");
      }
      if (!Pattern.matches("\\w+", password)) {
        throw new WrongPasswordException("Пароль содержит недопустимые символы");
      }
      if (password.length() > 20) {
        throw new WrongPasswordException("Пароль слишком длинный");
      }
      if (!password.equals(confirmPassword)) {
        throw new WrongPasswordException("Пароль и подтверждение не совпадают");
      }
    } catch (WrongLoginException | WrongPasswordException e) {
      authentication = false;
      System.out.println(e.getMessage());
      } finally {
      return authentication;
    }
  }
}
