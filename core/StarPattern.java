package core;

public class StarPattern {
    public static void main(String[] args) {
        int n = 10;
        System.out.print("* \n");
        for (int i = 0; i < n; i++) {

            for (int j = 0; j <= i; j++) {
                if (i == 1 || i%2 != 0) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}
