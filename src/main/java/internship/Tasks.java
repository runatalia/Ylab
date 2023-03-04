package internship;

import java.util.Random;
import java.util.Scanner;

public class Tasks {
    public static void stars(){
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            String template = scanner.next();
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++) {
                    System.out.print((j==0)?template:(" "+template));
                }
                System.out.println();
            }
        }
    }
    public static void  pellNumbers(){
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            int result0 = 0;
            int result1 = 1;
            int result = 2;
            switch (n) {
                case 0:
                    System.out.println(result0);
                    break;
                case 1:
                    System.out.println(result1);
                    break;
                default:
                    for (int i = 2; i <= n; i++) {
                        result = 2 * result1 + result0;
                        result0 = result1;
                        result1 = result;
                    }
                    System.out.println(result);
            }
        }
    }
    public static void multTable(){
            for(int i = 1; i <= 9; i++){
                for(int j = 1; j <= 9; j++){
                    System.out.println(i+" * "+j + " = " + i*j);
                }  System.out.println();
            }
        }
    public static void guess(){
        Scanner sc = new Scanner(System.in);
        int number = new Random().nextInt(99)+1;
        int maxAttempts = 10;
        int attempt = 1;
        int remainingAttempts = maxAttempts;
        int guess;
        System.out.println("Я загадал число. У тебя " + maxAttempts + " попыток угадать.");
        while (remainingAttempts != 0){
            guess = sc.nextInt();
            if (guess == number) {
                System.out.println("Ты угадал с "+ attempt +" попытки!");
                return;
            }
            remainingAttempts = maxAttempts - attempt;
            System.out.println("Мое число "+(guess > number?"меньше":"больше")+"!Осталось "+ remainingAttempts
                    +(remainingAttempts>4||remainingAttempts==0?" попыток!":(remainingAttempts>1?" попытки!": " попытка!")));
            attempt++;
        }
        System.out.print("Ты не угадал");
    }
    }




