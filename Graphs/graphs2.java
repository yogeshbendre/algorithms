import java.util.*;
import java.io.*;
import java.lang.*;


class Graph{
    
    ArrayList<ArrayList<Integer>> nodes;
    HashMap<String,Integer> weights;
    int V;
    int E;
    public Graph(){
        nodes = new ArrayList<ArrayList<Integer>>();
        V=0;
        E=0;
        weights = new HashMap<String, Integer>();
    }
    
    public void addVNodes(int V){
        for(int i = 0  ; i<V; i++){
            addNode(i);
        }
        
    }
    
    public void addNode(int n){
        ArrayList<Integer> new_node = new ArrayList<Integer>(); 
        nodes.add(new_node);
        weights.put(n+"_"+n,0);
        V++;
    }
    
    public void addEdge(int src, int dest){
        addEdge(src,dest,1);
    }
    
    public void addEdge(int src, int dest, int weight){
        nodes.get(src).add(dest);
        E++;
        weights.put(src+"_"+dest,weight);
    }
    
    public void printGraph(){
        System.out.println("Your Graph");
        System.out.println();
        for(int i=0;i<V;i++){
            System.out.print(""+i+" -> ");
            for(Integer u: nodes.get(i)){
                System.out.print(" "+u+"("+weights.get(i+"_"+u)+")");
            }
            System.out.println();
        }
        
    }
    
    public void BFS(int src, Queue<Integer> q, boolean visited[], int parent[]){
        
        
        if(!visited[src]){
                q.add(src);
                visited[src] = true;
                parent[src] = -1;
        }
        
        while(!q.isEmpty()){
            
            int u = q.remove();
            System.out.println("Visited: "+u+" Parent: "+parent[u]);
            for(int v : nodes.get(u)){
                
                if(!visited[v]){
                    visited[v] = true;
                    q.add(v);
                    parent[v] = u;
                }
                
            }
        }
    }
    
    
    public void BFSAll(){
        boolean visited[] = new boolean[V];
        int parent[] = new int[V];
        for(int i = 0 ; i<V;i++){
            visited[i] = false;
            parent[i] = -1;
        }
        
        Queue<Integer> q = new LinkedList<Integer>();
        
        for(int n=0; n<V; n++){
            BFS(n,q,visited,parent);
        }
    }
    
    public void DFS(int node,boolean visited[], int parent[], Stack<Integer>topSort){
        
        visited[node] = true;
        System.out.println("Visited: "+node+" Parent: "+parent[node]);
        for(int v: nodes.get(node)){
            if(!visited[v]){
                parent[v]=node;
                DFS(v,visited,parent,topSort);
            }
        }
        
        topSort.push(node);
    }
    
    
    public void DFSAll(){
        boolean visited[] = new boolean[V];
        int parent[] = new int[V];
        Stack<Integer> topSort = new Stack<Integer>();
        for(int i =0; i<V;i++){
            visited[i]=false;
            parent[i]=-1;
        }
        
        for(int i=0;i<V;i++){
            if(!visited[i]){
                parent[i] = -1;
                DFS(i,visited,parent,topSort);
            }
        }
        
        System.out.println("Topological Sort Order: ");
        while(!topSort.isEmpty()){
            int n = topSort.pop();
            System.out.print(" "+n);
        }
        
    }
    
    public void initialize_single_src(int s,int dist[], int parent[]){
        
        for(int i=0;i<V;i++){
            dist[i]=Integer.MAX_VALUE;
            parent[i]=-1;
        }
        
        dist[s] = 0;
        
    }
    
    public boolean relax(int u, int v, int dist[], int parent[]){
        boolean relaxed = false;
        if(dist[u]!=Integer.MAX_VALUE){
            if(dist[v] >(dist[u]+weights.get(u+"_"+v))){
                dist[v] = dist[u]+weights.get(u+"_"+v);
                parent[v] = u;
                relaxed=true;
            }
        }
        return(relaxed);
    }
    
    public void BellMan_Ford(int src){
        
        System.out.println();
        System.out.println("Bellman-Ford: ");
        
        int dist[] = new int[V];
        int parent[] = new int[V];
        //If we can relax more than V, it means cycle exists.
        boolean relaxed = false;
        initialize_single_src(src,dist,parent);
        for(int i = 0 ; i < (V+1); i++){
            relaxed = false;
            for(int u = 0 ; u<V; u++){
                for(int v : nodes.get(u)){
                    
                    relaxed = relaxed | (relax(u,v,dist,parent));
                    
                }
            }
            
        }
        if(relaxed){
            System.out.println("Negative cycle exists...!!!");
        }
        else{
            for(int i=0;i<V;i++){
                System.out.println("Dist to : "+i+" : "+dist[i]+" via "+parent[i]);
            }
        }
        
    }
    
    public static void main(String args[]){
        
        Graph g = new Graph();
        g.addVNodes(9);
        g.addEdge(0,1,5); 
        g.addEdge(1,2,-3);
        g.addEdge(2,3,-4);
        g.addEdge(1,4,0);
        g.addEdge(0,5,2);
        g.addEdge(5,1,1);
        g.addEdge(6,7,11);
        g.addEdge(7,8,-20);
        g.addEdge(8,2,2);
        
        g.printGraph();
        System.out.println("BFS: ");
        g.BFSAll();
        System.out.println("DFS: ");
        g.DFSAll();
        g.BellMan_Ford(0);
    }
    
}