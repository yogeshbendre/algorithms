import java.io.*;
import java.lang.*;
import java.util.*;



class Node{
    Set<String> adj;
    String name;
    public Node(String name){
        this.adj = new HashSet<String>();
        this.name = name;
    }
}
    

class Graph{
    
    private HashMap<String,Node> nodes;
    private boolean undirected;
    
    public Graph(boolean isUndirected){
        
        nodes = new HashMap<String, Node>();
        undirected = isUndirected;
        
    }
    
    public void addNode(String name){
        nodes.put(name, new Node(name));
        
    }
    
    public void addEdge(String srcName, String destName){
        
        Node src = nodes.get(srcName);
        src.adj.add(destName);
        nodes.put(srcName,src);
        if(undirected){
            Node dest = nodes.get(destName);
            dest.adj.add(srcName);
            nodes.put(destName,dest);
        }
        
    }
    
    public void printGraph(){
        System.out.println("Your Graph");
        for(Map.Entry<String,Node> e: nodes.entrySet()){
            
            System.out.print(e.getKey()+" -> ");
            for(String n : e.getValue().adj){
                System.out.print(" "+n);
            }
            System.out.println();
        }
        
    }
    
    
    public void DFS(){
        
        HashSet<String> visited = new HashSet<String>();
        Stack<String> mystack = new Stack<String>();
        
        for(Map.Entry<String,Node>e : nodes.entrySet()){
            
            if(visited.contains(e.getKey())){
                continue;
            }
            
            mystack.push(e.getKey());
            visited.add(e.getKey());
            
            while(!mystack.isEmpty()){
                
                String nextNode = mystack.pop();
                System.out.println("Visit: "+nextNode);
                for(String n : nodes.get(nextNode).adj){
                    if(visited.contains(n)){
                        continue;
                    }
                    
                    mystack.push(n);
                    visited.add(n);
                    
                    
                }
                
                
                
                
            }
            
            
        }
        
        
        
    }
    
    public void BFS(){
        
        HashSet<String> visited = new HashSet<String>();
        Queue<String> myqueue = new LinkedList<String>();
        for(Map.Entry<String,Node>e : nodes.entrySet()){
            if(visited.contains(e.getKey())){
                continue;
            }
            //System.out.println("Unvisited Source: "+e.getKey());
            visited.add(e.getKey());
            myqueue.add(e.getKey());
            
            //System.out.println("Visited: "+visited.toString());
            //System.out.println("Queue: "+myqueue.toString());
            while(!myqueue.isEmpty()){
                
                String nextnode = myqueue.remove();
                System.out.println("From queue: "+nextnode);
                //System.out.println("Visited: "+visited.toString());
                //System.out.println("Queue: "+myqueue.toString());
                
                
                Set<String> neighbors = nodes.get(nextnode).adj;
                for(String n : neighbors){
                    if(visited.contains(n)){
                        continue;
                    }
                    //System.out.println("Unvisited Neighbor: "+n);
                    myqueue.add(n);
                    visited.add(n);
                    //System.out.println("Visited: "+visited.toString());
                    //System.out.println("Queue: "+myqueue.toString());
                }
            
            }
            
        }
        
    }
    
    public static void main(String argv[]){
        Graph g = new Graph(true);
        g.addNode("A");
        g.addNode("B");
        g.addNode("C");
        g.addNode("D");
        g.addNode("E");
        g.addNode("F");
        g.addNode("G");
        g.addEdge("A","B");
        g.addEdge("A","C");
        g.addEdge("B","D");
        g.addEdge("B","C");
        g.addEdge("E","F");
        g.printGraph();
        g.BFS();
        g.DFS();
    }
}