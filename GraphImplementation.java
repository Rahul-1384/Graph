import java.util.*;

public class GraphImplementation{
    HashMap<Integer,HashMap<Integer,Integer>> graph;
    int vtx = 0;
    public GraphImplementation(int v){
        vtx = v;
        graph = new HashMap<>();
        for(int i=0;i<=v;i++){
            graph.put(i, new HashMap<Integer,Integer>());
        }
    }

    
    // Add Edges
    public void addEdges(int v1, int v2, int w){
        graph.get(v1).put(v2,w);
        // graph.get(v2).put(v1,w);
    }


    // Remove Edge
    public void removeEdge(int v1, int v2){
        graph.get(v1).remove(v2);
        graph.get(v2).remove(v1);
    }


    // Has Vertex
    public boolean hasVertex(int v){
        if(graph.containsKey(v)){
            return true;
        }
        return false;
    }


    // Has Edge
    public boolean hasEdge(int v1, int v2){
        return graph.get(v1).containsKey(v2) || graph.get(v2).containsKey(v1);
    }



    // remove vertex
    public void removeVertex(int v){
        for(int key : graph.get(v).keySet()){
            graph.get(key).remove(v);
        }
        graph.remove(v);
    }



    // Display
    public void display(){
        for(int k : graph.keySet()){
            for(int a : graph.get(k).keySet()){
                System.out.println(k+"<--"+graph.get(k).get(a)+"-->"+graph.get(k));
            }
        }
    }



    // Find Path
    public boolean findPath(int v1, int v2){
        HashSet<Integer> visited = new HashSet<>();
        return findPath(v1, v2, visited);
    }
    public boolean findPath(int v1, int v2, HashSet<Integer> visited){
        HashSet<Integer> set = new HashSet<>();
        if(v1 == v2){
            return true;
        }
        visited.add(v1);
        for(int key : graph.get(v1).keySet()){
            if(!set.contains(key)){
                boolean b = findPath(key, v2,visited);
                if(b){
                    return true;
                }
            }
        }
        set.remove(v1);
        return false;
    }




    // Print all path
    public void printAllPath(int v1, int v2){
        HashSet<Integer> visited = new HashSet<>();
        printAllPath(v1, v2, visited, v1+"");
    }
    public void printAllPath(int v1, int v2,HashSet<Integer> visited, String ans){
        HashSet<Integer> set = new HashSet<>();
        if(v1 == v2){
            System.out.println(ans);
            return;
        }
        set.add(v1);
        for(int key : graph.get(v1).keySet()){
            if(!set.contains(key)){
                printAllPath(key, v2, visited,ans+key);
            }
        }
        set.remove(v1);
    }
    



    // BFS in Graph
    public void BFS(int v){
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        while(!q.isEmpty()){
            int rv = q.poll();
            if(visited.contains(rv)){
                continue;
            }
            visited.add(rv);
            System.out.println(rv);
            for(int a : graph.get(rv).keySet()){
                q.add(a);
            }
        }
    }



    // DFS in Graph
    public void DFS(int v){
        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> st = new Stack<>();
        st.push(v);
        while(!st.isEmpty()){
            int rv = st.pop();
            if(visited.contains(rv)){
                continue;
            }
            visited.add(rv);
            System.out.println(rv);
            for(int a : graph.get(rv).keySet()){
                st.add(a);
            }
        }
    }


    // BFT ---> breadth first traversal
    public void BFT(int v){
        HashSet<Integer> visited = new HashSet<>();
        for(int k : graph.keySet()){
            if(!visited.contains(k)){
                // BFS(k,visited);
                Queue<Integer> q = new LinkedList<>();
                q.add(v);
                while(!q.isEmpty()){
                    int rv = q.poll();
                    if(visited.contains(rv)){
                        continue;
                    }
                    visited.add(rv);
                    System.out.println(rv);
                    for(int a : graph.get(rv).keySet()){
                        q.add(a);
                    }
                }
            }
        }
    }



    // No of components of a graph
    public void noOfComponents(int v,int count){
        HashSet<Integer> visited = new HashSet<>();
        for(int k : graph.keySet()){
            if(!visited.contains(k)){
                count++;
                // BFS(k,visited);
                Queue<Integer> q = new LinkedList<>();
                q.add(v);
                while(!q.isEmpty()){
                    int rv = q.poll();
                    if(visited.contains(rv)){
                        continue;
                    }
                    visited.add(rv);
                    for(int a : graph.get(rv).keySet()){
                        q.add(a);
                    }
                }
            }
        }
        System.out.println(count);
    }




    // Is Cycle or not
    public boolean isCycle(int v){
        HashSet<Integer> visited = new HashSet<>();
        for(int k : graph.keySet()){
            if(!visited.contains(k)){
                // BFS(k,visited);
                Queue<Integer> q = new LinkedList<>();
                q.add(v);
                while(!q.isEmpty()){
                    int rv = q.poll();
                    if(visited.contains(rv)){
                        return true;
                    }
                    visited.add(rv);
                    for(int a : graph.get(rv).keySet()){
                        q.add(a);
                    }
                }
            }
        }
        return false;
    }







// Spanning Tree
    class pair{

        int vtx;
        int parent;
        int cost;
        pair(int vtx, int parent, int cost){
            this.vtx = vtx;
            this.parent = parent;
            this.cost = cost;
        }
    }
    public void MST(int v){
        PriorityQueue<pair> pq = new PriorityQueue<pair>(new Comparator<pair>() {
            public int compare(pair o1, pair o2){
                return o1.cost - o2.cost;
            }
        });
        
        int ans = 0;
        HashSet<Integer> visited = new HashSet<>();
        pq.add(new pair(v, v, 0));
        while(!pq.isEmpty()){
            pair rv = pq.poll();
            int vtx = rv.vtx;
            int parent = rv.parent;
            int cost = rv.cost;
            if(visited.contains(vtx)){
                continue;
            }
            visited.add(vtx);
            System.out.println(vtx+" "+parent+" "+cost);
            ans += cost;

            for(int k : graph.get(vtx).keySet()){
                pq.add(new pair(k, vtx, graph.get(vtx).get(k)));
            }
        }
        System.out.println("Spanning Tree: "+ans);
    }


// Shortest Path
// BalmanFord
    class Pair{
        int v1;
        int v2;
        int cost;

        Pair(int v1, int v2, int cost){
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }
        public String toString(){
            return v1+" "+v2+" "+cost;
        }
    }
    public List<Pair> pairList(){
        List<Pair> list = new ArrayList<>();
        for(int k : graph.keySet()){
            for(int j : graph.get(k).keySet()){
                int cost = graph.get(k).get(j);
                Pair p = new Pair(k, j, cost);
                list.add(p);
            }
        }
        return list;
    }

    public void belmanFord(){
        int[] cost = new int[vtx];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = 0;
        List<Pair> list = pairList();

        for(int i=0;i<=vtx;i++){
            for(Pair k : list){
                int v1 = k.v1;
                int v2 = k.v2;
                int wt = k.cost;
    
                int oldCost = cost[v2];
                int newCost = cost[v1] + wt;
    
                if(i == vtx && oldCost > newCost){
                    System.out.println("-ve edge");
                    return;
                }
    
                if(newCost < oldCost){
                    cost[v2] = newCost;
                }
            }
        }
        System.out.println(Arrays.toString(cost));
        
    }




    public static void main(String[] args) {
        GraphImplementation graph = new GraphImplementation(5);
        // graph.addEdges(1, 2, 10);
        // graph.addEdges(1, 4, 20);
        // graph.addEdges(2, 1, 10);
        // graph.addEdges(2, 3, 30);
        // graph.addEdges(3, 2, 30);
        // graph.addEdges(3, 4, 40);
        // graph.addEdges(4, 1, 20);
        // graph.addEdges(4, 3, 40);
        // graph.addEdges(4, 5, 50);
        // graph.addEdges(5, 4, 50);
        // graph.addEdges(5, 6, 60);
        // graph.addEdges(5, 7, 70);
        // graph.addEdges(6, 5, 60);
        // graph.addEdges(6, 7, 80);
        // graph.addEdges(7, 5, 70);
        // graph.addEdges(7, 6, 80);


        graph.addEdges(0, 1, -10);
        graph.addEdges(0, 2, 10);
        graph.addEdges(1, 3, 10);
        graph.addEdges(1, 4, 25);
        graph.addEdges(2, 3, 20);
        graph.addEdges(3, 4, -5);


        graph.display();
        graph.BFS(1);
        
        System.out.println("\nDFS starting from node 1:");
        graph.DFS(1);
        
        // System.out.println("\nPath between 1 and 7 exists: " + graph.findPath(1, 7));
        
        // System.out.println("\nAll paths from 1 to 7:");
        // graph.printAllPath(1, 7);

        System.out.println("\nDFS search:");
        graph.DFS(1);

        System.out.println("\nBFT:");
        graph.BFT(1);

        System.out.print("\nNo of Components:");
        graph.noOfComponents(1, 0);

        System.out.print("\nIs Cycle: ");
        System.out.print(graph.isCycle(1));

        System.out.println();
        // graph.MST(1);

        System.out.println();
        graph.pairList();

        System.out.println();
        graph.belmanFord();
    }
}