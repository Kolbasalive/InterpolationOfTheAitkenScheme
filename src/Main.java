// y = ln(x + sqrt(x^2 + 1))  [a,b] = [-5, 5]

public class Main {
    public static double[][] matrix = new double[10][10];
    public static int a = -5, b = 5;
    public static double[][] array12 = init(a,b,12);
    public static double[][] array50 = init(a,b,50);
    public static double x = -2.4;

    public static void main(String[] args) {

        printMatrix(array12);
        printNumberedKnot(array12);
        printMatrix(array50);
        System.out.println(" X       Y(x)    L1      L3      L6");
        for (int f = 0; f < array50.length-1; f++) {
            x = array50[f][0];
            eitken(3,9);
            System.out.printf("%.3f ", x);
            System.out.printf("%.3f ", getYk(x));
            System.out.printf("%.3f ", matrix[3][4]);
            System.out.printf("%.3f ", matrix[3][6]);
            System.out.printf("%.3f ", matrix[3][9]);
            System.out.println();
        }
    }

    public static double[][] init(int a, int b, int n){
        double[][] array = new double[n+1][2];
        double h = getStep(a, b, n);
        double x = a;
        int i = 0;
        while (x <= b){
            array[i][0] = x;
            array[i][1] = getYk(x);
            i++;
            x += h;
        }

        return array;
    }
    public static double getStep(int a, int b, int n){
        return (double) (b - a) / n;
    }

    public static void printMatrix(double[][] array){
        System.out.println("Xk       Yk");
        for (double[] doubles : array) {
            StringBuilder sb = new StringBuilder();
            for (double aDouble : doubles) {
                if (aDouble < 0){
                    sb.append(String.format("%.3f ", aDouble));
                }else{
                    sb.append(String.format("%.3f ", aDouble));
                }
            }
            System.out.println(sb.toString().trim());
        }
        System.out.println();
    }

    public static void printNumberedKnot(double[][] array){
        var l = new int[] {4,6,9};
        for (int i = 0; i < 3; i++) {
            var j = 3;
            System.out.println("Xk       Yk");
            while(j <= l[i]){
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%.3f  ", array[j][0]));
                if (array[j][1] <= 0){
                    sb.append(String.format("%.3f    ", array[j][1]));
                }else{
                    sb.append(String.format("%.3f  ", array[j][1]));
                }
                j++;
                System.out.println(sb.toString().trim());
            }
            System.out.println();
        }
        System.out.println();
    }

    public static double getYk(double x){
        return Math.log(x + Math.sqrt(x*x + 1));
    }

    public static double eitken(int i, int j){
        if (j - i == 1){
            matrix[i][j] = ((array12[i][1] * (array12[j][0] - x)) -
                    (array12[j][1] * (array12[i][0] - x)))
                    / (array12[j][0] - array12[j-1][0]);
        }else{
            matrix[i][j] = ((eitken(i,j-1) * (array12[j][0] - x)) -
                    (eitken(i+1,j) * (array12[i][0] - x)))
                    /(array12[j][0] - array12[i][0]);
        }

        return matrix[i][j];
    }

}
