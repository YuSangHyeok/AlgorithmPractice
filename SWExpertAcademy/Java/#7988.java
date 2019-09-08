import java.util.Scanner;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedList;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int K = sc.nextInt();
            int N = K*2;
            int nameIndex = 0;
             
            Graph game = new Graph(N);
            HashMap<String, Integer> nameMapping = new HashMap<>();
             
            for(int i = 0; i < K; i++)
            {
                String name_a = sc.next();
                String name_b = sc.next();
                int a, b;
                if(nameMapping.containsKey(name_a))
                    a = nameMapping.get(name_a);
                else
                {
                    nameMapping.put(name_a, nameIndex);
                    a = nameIndex;
                    nameIndex++;
                }
                if(nameMapping.containsKey(name_b))
                    b = nameMapping.get(name_b);
                else
                {
                    nameMapping.put(name_b, nameIndex);
                    b = nameIndex;
                    nameIndex++;
                }
                 
                game.addLink(a, b);
            }
             
            String res = game.checkSynergy()? "Yes" : "No";
            System.out.printf("#%d %s\n", test_case, res);
        }
    }
}
 
// 선수와 선수 간 시너지를 나타내는 그래프
class Graph
{
	// 선수
    class Node
    {
		// 선수의 이름 (HashMap을 이용하여 string을 int로 변환)
        int name;
		// 속한 팀. (0, 1) 어디에도 속하지 않는다면 -1
        int team;
		// 시너지를 받는 선수
        LinkedList<Integer> adjacent = new LinkedList<>();
         
        Node(int name)
        {
            this.name = name;
            team = -1;
            adjacent = new LinkedList<>();
        }
    }
     
    int N;
    Node[] player;
     
    Graph(int N)    
    {
        this.N = N;
        player = new Node[N];
        for(int i = 0; i < N; i++)
        {
            player[i] = new Node(i);
        }
    }
     
	// 시너지 추가
    void addLink(int a, int b)
    {
        if(!player[a].adjacent.contains(b))
            player[a].adjacent.add(b);
        if(!player[b].adjacent.contains(a))
            player[b].adjacent.add(a);
    }
    
	// 각 선수마다 팀을 짜준다
    boolean checkSynergy()
    {
        for(int i = 0; i < N; i++)
        {   
            if(player[i].team == -1)
            {
                player[i].team = 0;
                if(!bfs(i))
                {
                    return false;
                }
            }
        }
         
        return true;
    }
    
	// 시너지 정보를 바탕으로 팀 맺기 
    boolean bfs(int root)
    {
        LinkedList<Integer> qu = new LinkedList<>();
        qu.add(root);
         
        while(!qu.isEmpty())
        {
            int c = qu.poll();
            int t = (player[c].team + 1) % 2;
             
            for(int i : player[c].adjacent)
            {
				// 시너지를 받는 선수와 같은 팀을 맺을 수밖에 없다.
                if(player[i].team == player[c].team)
                {
                    return false;
                }
				// 그렇지 않다면 시너지를 받는 선수와 반대 팀으로 매칭
                if(player[i].team == -1)
                {
                    player[i].team = t;
                    qu.add(i);
                }
            }
        }
         
        return true;
    }
}