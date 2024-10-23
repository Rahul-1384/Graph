import java.util.*;

public class GraphImplementation{
    HashMap<Integer,HashMap<Integer,Integer>> graph;
    public GraphImplementation(int v){
        graph = new HashMap<>();
        for(int i=1;i<=v;i++){
            graph.put(i, new HashMap<Integer,Integer>());
        }
    }

    // Add Edges
    public void addEdges(int v1, int v2, int w){
        graph.get(v1).put(v2,w);
        graph.get(v2).put(v1,w);
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
    public static void main(String[] args) {
        GraphImplementation graph = new GraphImplementation(7);
        graph.addEdges(1, 2, 10);
        graph.addEdges(1, 4, 20);
        graph.addEdges(2, 1, 10);
        graph.addEdges(2, 3, 30);
        graph.addEdges(3, 2, 30);
        graph.addEdges(3, 4, 40);
        graph.addEdges(4, 1, 20);
        graph.addEdges(4, 3, 40);
        graph.addEdges(4, 5, 50);
        graph.addEdges(5, 4, 50);
        graph.addEdges(5, 6, 60);
        graph.addEdges(5, 7, 70);
        graph.addEdges(6, 5, 60);
        graph.addEdges(6, 6, 80);
        graph.addEdges(7, 5, 70);
        graph.addEdges(7, 6, 80);
        graph.display();
        graph.BFS(1);
        
        System.out.println("\nDFS starting from node 1:");
        graph.DFS(1);
        
        System.out.println("\nPath between 1 and 7 exists: " + graph.findPath(1, 7));
        
        System.out.println("\nAll paths from 1 to 7:");
        graph.printAllPath(1, 7);
    }
}