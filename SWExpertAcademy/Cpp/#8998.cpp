#include<iostream>
#include<vector>
#include<algorithm>
 
using namespace std;
 
struct Task
{
    // di : 기간, ti : 데드라인
    int di, ti;
};
 
bool cmp(const Task &t1, const Task &t2)
{
    if(t1.di > t2.di)
        return true;
    return false;
}
 
Task task[1000000];
int main(int argc, char** argv)
{
    int T;
    scanf("%d", &T);
     
    for(int test_case = 1; test_case <= T; ++test_case)
    {
        int N;
        scanf("%d", &N);
         
        int Ti, Di;
        for(int i = 0; i < N; i++)
        {
            scanf("%d %d", &task[i].ti, &task[i].di);
        }
         
        sort(task, task + N, cmp);
        
        // 데드라인 - 필요기간
        int result = task[0].di - task[0].ti;
        for(int i = 1; i < N; i++)
        {
            if(task[i].di < result)
                result = task[i].di;
            result -= task[i].ti;
        }
         
        printf("#%d %d\n", test_case, result);
    }
    return 0;
}