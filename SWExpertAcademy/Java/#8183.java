import java.util.Scanner;
 
class Solution
{
    static int N, A, B;
    static int[] letter;
     
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
         
        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = sc.nextInt();
            A = sc.nextInt();
            B = sc.nextInt();
             
            letter = new int[N+1];
            for(int i = 0; i < N; i++)
            {
                letter[i] = sc.nextInt();
            }
            letter[N] = 100000000;
             
            int bottom = 0;
            int top = 0;
            int t = letter[bottom];
             
            System.out.printf("#%d", test_case);

            // 마지막 편지를 가져갈 때 까지 (0~N-1)
            while(bottom < N)
            {
                // 시간이 되면 위에 편지 1통 추가
                if(letter[top+1] == t)
                {
                    top++;
                }
                 
                // 조건을 만족하면 정해진 수만큼 편지를 꺼낸다
                if(top - bottom + 1 >= A || t - letter[bottom] >= B)
                {
                    int num = (top - bottom) / 2 +1;
                    bottom += num;
                    for(int i = 0; i < num; i++)
                        System.out.printf(" %d", t);
                }
                // 시간 1 증가
                t++;
            }
            System.out.println();
        }
    }
}