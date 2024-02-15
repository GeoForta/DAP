

public class ex2 {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5};
        int s = 0;
        int cnt = 0;

        for (int i = 0; i < arr.length; ++i)
        {
            s += arr[i];
            cnt++;
        }

        int rez = s / cnt;
        System.out.println(rez);
    }

}
