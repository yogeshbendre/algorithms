/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
import java.lang.*;

class UnionFind {
    
    HashMap<Integer, Integer> mysets;
    HashMap<Integer,Integer> ranks;
    
    public UnionFind(){
        mysets = new HashMap<Integer,Integer>();
        ranks = new HashMap<Integer,Integer>();

    }
    
    public int getRank(int e){
        return ranks.get(e);
    }
    
    public void addNewElement(int e){
        
        mysets.put(e,e);
        ranks.put(e,1);
    }
    
    public Integer findRoot(int e){
        Integer r = -1;
        System.out.println(e);
        if(mysets.containsKey(e)){
            r = mysets.get(e);
            while(r!=-1 && e!=r){
                e = r;
                r = mysets.get(e);
            }
        
            return e;
        }
        else{
            return null;
        }
        
    }
    
    //Path Compression
    public Integer find(int e){
        Integer root = findRoot(e);
        if(root!=null && root!=e)
            mysets.put(e,root);
        return root;
    }


    public void unionRank(int e1, int e2){
        
        Integer r1 = find(e1);
        Integer r2 = find(e2);
        
        if(r1!=null && r2!=null){
            if(ranks.get(r1)<ranks.get(r2)){
                Union(r1,r2);
                ranks.put(r2,ranks.get(r1)+ranks.get(r2));
            }
            else{
                Union(r2,r1);
                ranks.put(r1,ranks.get(r1)+ranks.get(r2));
            }
        }
        
        
    }
    
    public void Union(int e1, int e2){
        //set root of e1 as e2
        mysets.put(e1,e2);        
    }
    
    
	public static void main (String[] args) {
		System.out.println("GfG!");
		
		UnionFind uf = new UnionFind();
		
		uf.addNewElement(1);
		uf.addNewElement(2);
		uf.addNewElement(3);
		uf.addNewElement(4);
		uf.addNewElement(5);
		
		uf.unionRank(1,2);
		uf.unionRank(3,4);
		uf.unionRank(4,1);
		
		
		
		for(Integer e : uf.mysets.keySet()){
		    System.out.println("For e:"+e+" Root : "+uf.find(e)+" Rank: "+uf.getRank(uf.find(e)));
		}
		
		System.out.println("Graph Cycle: ");
		int nodes[] = {0,1,2};
		String edges[] = {"0_1","1_2","2_0"};
		
		
		uf = new UnionFind();
		for(int e : nodes){
		    System.out.println("Add node: "+e);
		    uf.addNewElement(e);
		}
		
		System.out.println("Process Edges: ");
		for(String s: edges){
		    int v1 = Integer.parseInt(s.split("_")[0]);
		    int v2 = Integer.parseInt(s.split("_")[1]);
		    
		    if(uf.find(v1)==uf.find(v2)){
		        System.out.println("Cycle Detected !!");
		    }
		    else{
		        System.out.println("Safe edge");
		        uf.unionRank(v1,v2);
		    }
		}
		
		
	}

    
    
    
    
}