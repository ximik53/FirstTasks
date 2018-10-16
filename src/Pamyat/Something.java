package Pamyat;

public class Something {
    int x;
    int y;
}
class Something2 {
    public static void main() {
        Something n1=new Something();
        n1.x=0;
        n1.y=0;
        Something n2=n1;
        n2.x=1;
        n2.x=1;
        System.out.println(n1);
        System.out.println(n2);
    }
}
