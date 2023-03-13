package internship;

import java.util.Scanner;

public class SequencesTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Sequences sequences = new SequencesImpl();
        System.out.print("A: " );
        sequences.a(n);
        System.out.print("\nB: " );
        sequences.b(n);
        System.out.print("\nC: " );
        sequences.c(n);
        System.out.print("\nD: " );
        sequences.d(n);
        System.out.print("\nE: " );
        sequences.e(n);
        System.out.print("\nF: " );
        sequences.f(n);
        System.out.print("\nG: " );
        sequences.g(n);
        System.out.print("\nH: " );
        sequences.h(n);
        System.out.print("\nI: " );
        sequences.i(n);
        System.out.print("\nG: " );
        sequences.j(n);
        sc.close();
    }
}