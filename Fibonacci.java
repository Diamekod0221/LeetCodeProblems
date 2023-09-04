public class Fibonacci {

    public static int iterate(int n){
        validate(n);

        if( n == 0 || n == 1){
            return 1;
        }

        return getNthIteration(n);

    }

    private static void validate(int n){
        if( n < 0){
            throw new IllegalArgumentException("You want a negative sequence moment: " + n);
        }
    }

    private static int getNthIteration(int n){
        int fib0 = 1;
        int fib1 = 1;
        int fibswap = 0;

        int i = 1;
        while(i < n){
            fibswap = fib1;
            fib1 = fib0 + fib1;
            fib0 = fibswap;

            i++;
        }

        return fib1;
    }

    public static int recursive(int n){
        validate(n);

        if( n == 0 || n == 1){
            return 1;
        }

        return recursive(n-1) + recursive(n - 2 );

    }

    //brzydka iteracja
    public static int tailRecursive(int n, int a, int b){
        if( n == 0){
            return a;
        }
        if( n == 1){
            return b;
        }

        return tailRecursive(n-1, b, a + b);

    }

}
