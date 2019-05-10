import java.util.*;
import java.io.*;
import java.lang.*;

class Sorter{
    
    
    
    
    public static int partition(int a[], int low, int high){
        
        //Last element as pivot
        int pivot = a[high];
        int i = low;
        int j = high-1;
        System.out.println("LOW, HIGH, I, J "+low+" "+high+" "+i+" "+j);
        while(i<j){
            
            while(i<=high-1 && a[i]<pivot){
                i++;
            }
            while(j>=i && a[j]>=pivot){
                j--;
            }
            if(i<j){
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
            
        }
        System.out.println("i: low: high: "+i+" "+low+" "+high);
        System.out.println("Pivot Data: "+ a[high]);
        System.out.println("Preswap: ");
        for(int m=0;m<a.length;m++){
            System.out.print(a[m]+" ");
        }
        System.out.println();
        
        
            int temp = a[i];
            a[i] = a[high];
            a[high] = temp;
        System.out.println("Post pivot: ");
        for(int m=0;m<a.length;m++){
            System.out.print(a[m]+" ");
        }
        System.out.println();
        return(i);
    }
    
    public static void quicksort(int a[], int low, int high){
        if(low<high){
            
            int part = partition(a,low,high);
			System.out.println("Now location fixed for: "+part+" "+a[part]);
			System.out.println("Calling for "+low+" to "+(part-1));
            quicksort(a, low, part-1);
            System.out.println("Calling for "+(part+1)+" to "+high);
			quicksort(a, part+1, high);
                
        }
        
    }
    
    public static void qiks(int a[]){
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]+" ");
        }

        quicksort(a, 0, a.length-1);
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]+" ");
        }
    }
    
    
    public static void merge(int a[], int l, int mid, int r){
        
        int a1[] = new int[mid-l+1];
        int a2[] = new int[r-mid];
        
        for(int i = l, j = 0 ; i<=mid ; i++){
            a1[j++] = a[i];
        }
        
        for(int i = mid+1, j = 0 ; i<=r ; i++){
            a2[j++] = a[i];
        }
        
        int i = 0, j = 0, k=l;
        
        while(i<a1.length && j<a2.length){
            if(a1[i]<a2[j]){
                a[k++] = a1[i++];
            }
            else{
                a[k++] = a2[j++];
            }
                
        }
        
        while(i<a1.length){
            a[k++] = a1[i++];
        }
            
        while(j<a2.length){
            a[k++] = a2[j++];
        }

        

    }
    
    public static int[] mergesort(int a[], int l, int r){
        
        if(r>l){
            int mid = (l+r)/2;
            
            mergesort(a, l, mid);
            mergesort(a, mid+1, r);
            
            merge(a,l,mid,r);
            
            return(a);
        }
        
        return(a);
        
        
    }
    
    public static int[] mrgs(int a[]){
        
        int n = a.length;
        a = mergesort(a,0,n-1);
        return(a);
        
    }
    
    public static int[] inss(int a[]){
        int n = a.length;
        
        for(int i=0;i<n-1;i++){
            int k = a[i+1];
            int j = i;
            while(j>=0 && k<a[j]){
                
                a[j+1] = a[j--];
            }
            a[j+1] = k;
        }
        
        return(a);
    }
    
    public static int[] sels(int a[]){
        int n = a.length;
        for(int i=0; i<n;i++){
                System.out.print(a[i]+" ");
        }
        System.out.println();
            
        for(int i=0;i<n-1;i++){
            int minloc = i;
            for(int j=i+1;j<n;j++){
                if(a[j]<a[minloc]){
                    minloc = j;
                }
                
            }
            
            int temp = a[i];
            a[i] = a[minloc];
            a[minloc] = temp;
            
            //a[i] = a[i]+a[minloc];
            //a[minloc] = a[i]-a[minloc];
            //a[i] = a[i]-a[minloc];
            //System.out.println(new ArrayList<Integer>(a).toString());
            for(int k=0; k<n;k++){
                System.out.print(a[k]+" ");
            }
            System.out.println();
            
        }
        
        for(int i=0;i<n;i++){
            System.out.println(a[i]);
        }
        
        return(a);
    }
    
    public static int[] bbls(int a[]){
            if(a.length<2){
                return(a);
            }
            
            for(int i = 0 ; i<a.length-1; i++){
                boolean flag = false;
                for(int j = 0;j<a.length-i-1;j++){
                    if(a[j]>a[j+1]){
                        flag = true;
                        a[j]=a[j]^a[j+1];
                        a[j+1]=a[j]^a[j+1];
                        a[j]=a[j]^a[j+1];
                    }
                }
                if(!flag){
                    //No swapping means array is sorted at this point.
                    break;
                }
            }
            return(a);
            
    }
    
    public static void main(String argv[]){
        
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for(int t = 0; t<T; t++){
            
            int N = sc.nextInt();
            int a[] = new int[N];
            
            for(int i=0; i<N; i++){
                a[i] = sc.nextInt();
            }
            
            //int a1[] = bbls(a);
            //int a1[] = sels(a);
            
            //int a1[] = inss(a);
            qiks(a);
            for(int i=0; i<N;i++){
                System.out.print(a[i]+" ");
            }
            System.out.println();
            
        }
    }
}