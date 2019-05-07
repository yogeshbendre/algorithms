import java.io.*;
import java.lang.*;
import java.util.*;


class MaxHeap{
    
    int h[];
    int hlen, hsize;
    
    public MaxHeap(int arr[]){
        
        h = new int[arr.length*2];
        for(int i=0;i<arr.length;i++){
            h[i+1]=arr[i];
        }
        hlen = arr.length*2;
        hsize = arr.length+1;
        h[0]=-1;
        
    }
    public void increaseKey(int ind, int val){
        if(h[ind]>val){
            return;
        }
        h[ind] = val;
        while(h[parent(ind)]<h[ind] && ind>1){
            int temp = h[parent(ind)];
            h[parent(ind)] = h[ind];
            h[ind] = temp;
            ind = parent(ind);
        }
        
    }
    public void insertToHeap(int val){
        hsize = hsize+1;
        h[hsize-1] = -1;
        increaseKey(hsize-1,val);
        
    }
    public int parent(int i){
        return(i >> 1);
    }
    
    public int left(int i){
        int l = i << 1;
        if(l>=hsize){
            l=0;
            
        }
        return(l);
        
    }
    
    public int right(int i){
        int r = (i << 1)|1;
        if(r>=hsize){
            r=0;
        }
        return(r);
        
    }
    
    
    public void maxHeapify(int ind){
        
        
        
        int l = left(ind);
        int r = right(ind);
        int largest = (h[ind]>h[l] ? ind : l);
        //System.out.println();
        largest = (h[largest]>h[r]? largest : r);
        if(ind!=largest){
            h[ind] = h[ind]^h[largest];
            h[largest] = h[ind]^h[largest];
            h[ind] = h[ind]^h[largest];
            maxHeapify(largest);
        }
            
    }
    
    public void buildHeap(int arr[]){
        
        for(int i=((hsize)>>1);i>0;i--){
            maxHeapify(i);
        }
        
    }
    public void heapSort(){
        
        int mylen = hsize;
        for(int i=hsize-1;i>1;i--){
            int temp = h[1];
            h[1] = h[i];
            h[i] = temp;
            
            hsize--;
            maxHeapify(1);
        }
        System.out.println("Sorted Arr: ");
        for(int i = 1;i<mylen;i++){
            System.out.println(h[i]);
        }
        
    }
    public void printArr(){
        System.out.println("Full Arr:");
        for(int i = 1;i<hlen;i++){
            System.out.println(h[i]);
        }
    }
    public void printHeap(){
        System.out.println();
        System.out.println("Your Heap:");
        int k = 1, d=0;
        for(int i = 1;i<hsize;i++){
            System.out.print(h[i]+" ");
            d++;
            if(d==k){
                System.out.println();
                d=0;
                k = k << 1;
            }
            
        }
        
    }
    
    public static void main(String argv[]){
        
        int arr[]={3,2,4,5,1,10};
        
        MaxHeap mh = new MaxHeap(arr);
        mh.buildHeap(arr);
        mh.printHeap();
        mh.insertToHeap(12);
        mh.printHeap();
        mh.heapSort();
        mh.printArr();
        
    }
    
}