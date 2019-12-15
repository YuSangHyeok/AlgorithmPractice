#include <iostream>

using namespace std;

int N;
int input[1000];
int dp[1000];

int main()
{
	scanf("%d", &N);

	for (int i = 0; i < N; i++)
		scanf("%d", &input[i]);

	fill(dp, dp + N, 1);

	for (int i = 1; i < N; i++)
		for (int j = 0; j < i; j++)
			if (input[i] > input[j] && dp[i] <= dp[j])
				dp[i] = dp[j] + 1;
		
	int max = 0;
	for (int i = 0; i < N; i++)
		if (max < dp[i])
			max = dp[i];
		
	printf("%d\n", max);
	return 0;
}