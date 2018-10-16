package PloshadKvadrata;

import java.util.*;
public class Kvadrat {
    public static void main(String[] args) {
        Scanner vd = new Scanner(System.in);
        System.out.print("Введите длинну грани квадрата H=");
        double cH = vd.nextDouble();
        double x=(double)Math.pow(cH, 2);
        System.out.print("Ответ=");
        System.out.print(x);
    }
}
