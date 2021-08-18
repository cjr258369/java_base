/**
 * 快速幂
 */
public class quickPow {
    public static void main(String[] args) {
        System.out.println(quickPow(3,2));
        System.out.println(Math.pow(3,-2));
    }

    public static double quickPow(double x,int n){
        int N = n;
//        return N>=0? quickMul(x,N) : 1/quickMul(x,-N);
        return N>=0? quickMul2(x,N) : 1/quickMul2(x,-N);
    }

    /**
     * 递归版本
     * @param a
     * @param n
     * @return
     */
    public static double quickMul(double a,int n){
        if(n==0){
            return 1.0;
        }
        double y = quickMul(a,n/2);
        return n%2==0? y*y : y*y*a;
    }

    /**
     * 迭代版本
     * @param a
     * @param n
     * @return
     */
    public static double quickMul2(double a,int n){
        int N = n;
        double x_contribute = a;
        double result = 1.0;
        while(N>0){
            if(N%2==1){
                result *= x_contribute;
            }
            N /= 2;
            x_contribute *= x_contribute;
        }

        return result;
    }

}
