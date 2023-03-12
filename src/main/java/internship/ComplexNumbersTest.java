package internship;

import java.util.Scanner;

public class ComplexNumbersTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Первое комплексное число: \nВведите действительное число");
        double realNumber1 = sc.nextDouble();
        System.out.println("Введите мнимое число");
        double imaginaryNumberNumber1 = sc.nextDouble();
        System.out.println("Второе комплексное число: \nВведите действительное число");
        double realNumber2 = sc.nextDouble();
        System.out.println("Введите мнимое число");
        double imaginaryNumberNumber2 = sc.nextDouble();
        ComplexNumbersImpl complexNumbers1 = new ComplexNumbersImpl(realNumber1, imaginaryNumberNumber1);
        ComplexNumbersImpl complexNumbers2 = new ComplexNumbersImpl(realNumber2, imaginaryNumberNumber2);
        System.out.println("Sum: " + complexNumbers1.sum(complexNumbers2).toString());
        System.out.println("Minus: " + complexNumbers1.minus(complexNumbers2).toString());
        System.out.println("Multiply: " + complexNumbers1.multiply(complexNumbers2).toString());
        System.out.println("Modul: "+complexNumbers1.abs());
    }
}
