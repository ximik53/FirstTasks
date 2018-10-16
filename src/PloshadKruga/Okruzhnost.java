package PloshadKruga;
import java.util.*;
public class Okruzhnost {
    public static void main(String[] args) {
        double cq;
        Scanner vd = new Scanner(System.in);
        System.out.print("Введите длинну окружности C=");
        cq=vd.nextDouble();
        double x;
        x=2*cq*Math.PI;
        System.out.print("Площадь круга=");
        System.out.print(x);
    }
}

