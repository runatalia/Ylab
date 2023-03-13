package internship;

import java.util.Scanner;

public class SnilsValidatorTest {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String snils = sc.nextLine();
        SnilsValidator snilsValidator = new SnilsValidatorImpl();
        System.out.println(snilsValidator.validate(snils));
        sc.close();
    }
}
