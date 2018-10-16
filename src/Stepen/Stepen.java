package Stepen;
import java.util.*;
public class Stepen {
    public static void main(String[] args) {
        Scanner vr = new Scanner(System.in);
        System.out.print("Введите число для возведения в степень ");
        double vs=vr.nextDouble();
        System.out.println("В какую степень возвести?");
        double vd=vr.nextDouble();
        double x;
        x=Math.pow(vs, vd);
        System.out.print("Ответ=");
        System.out.print(x);
    }
}
