import java.util.Scanner;

public class Ex1{
    public static void main(String[] args) {
        float c;
        Scanner scan = new Scanner(System.in);

        c = scan.nextFloat();

        if (c < 0)
        {
            System.out.println("este negativ");
        }
        else
        {
            System.out.println("este pozitiv");
        }
    }
}
