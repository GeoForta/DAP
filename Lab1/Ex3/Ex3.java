
public class Ex3{

    public static boolean Prim(int nr)
    {
        if (nr < 2) return false;
        if (nr == 2) return true;
        if (nr % 2 == 0) return false;

        for (int i = 1; i * i <= nr; ++i)
        {
            if (nr % i == 0)
                return false;
        }
        return true;
    }
    public static void main(String args[])
    {
        int j;
        for (int i = 1; i <= 100; ++i)
        {
            j = i + 2;
            if (Prim(i) && Prim(j))
            {
                System.out.println("i " + i + " " + "j" + " " + (j));
            }
        }
       
    }
}