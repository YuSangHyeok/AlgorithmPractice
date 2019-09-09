import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    // i^2 배열
    static long[] pow = new long[1000001];
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        
        for(long i = 1; i < 1000001; i++)
            pow[(int) i] = i * i;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			long N = sc.nextLong();
            long res = 0;
            while(N != 2)
            {
                long next = find(N);
                res += pow[(int)next] - N + 1;
                N = next;
            }
            System.out.printf("#%d %d\n", test_case, res);
		}
	}
    
    // i^2 배열에서의 2진 탐색
    static long find(long p)
    {
        int s = 2, e = 1000000;
        while(s <= e)
        {
            int mid = (s + e) / 2;
            if(p > pow[mid])
                s = mid+1;
            else if(p < pow[mid])
                e = mid-1 ;
            else
                return (long) mid;
        }
        
        return (long) s;
    }
}