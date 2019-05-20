import java.io.*;
import java.lang.*;
import java.util.*;

class TextSearch{
    
    
    
    public static int[] compute(char P[]){
        
        int k = 0;
        int m = P.length-1;
        int Pi[] = new int[m+1];
        
        for(int q = 2; q<=m ;q++){
            while(k>0 && P[k+1]!=P[q]){
                k=Pi[k];
            }
            if(P[k+1]==P[q]){
                k++;
            }
            Pi[q] = k;
        }
        
        return(Pi);
    }
    
    public static void KMP(String Pat, String Text){
        int m = Pat.length();
        int n = Text.length();
        char P[] = new char[m+1];
        char T[] = new char[n+1];
        
        for(int p = 0 ; p<m; p++){
            P[p+1] = Pat.charAt(p);
        }
        
        for(int p = 0 ; p<n; p++){
            T[p+1] = Text.charAt(p);
        }
        
        int Pi[] = compute(P);
        System.out.println();
        for(int i = 1; i<=m ; i++){
            System.out.print(Pi[i]+" ");
        }
        System.out.println();
        int q = 0;
        for(int i = 1; i<=n ; i++){
            while(q>0 && P[q+1]!=T[i]){
                q=Pi[q];
            }
            if(P[q+1]==T[i]){
                q++;
            }
            if(q==m){
                System.out.println("Found with shift "+(i-m));
                q = Pi[q];
            }
            
            
        }
        
        
    }
    
    public static void RKS(String P, String T, int q){
        
        int m = P.length();
        int n = T.length();
        if(m>n){
            System.out.println("No match");
        }
        int d = 10;
        int h = 1;
        
        //h = d^(m-1);
        for(int i=0;i<m-1;i++){
            h=(h*d)%q;
        }
        System.out.println("h: "+h);
        
        //Compute int representation of P and T[0..M]
        int p = 0;
        int t0 = 0;
        for(int i = 0; i<m; i++){
            p = (10*p+Integer.parseInt(""+P.charAt(i)))%q;
            
            t0 = (10*t0+Integer.parseInt(""+T.charAt(i)))%q;
        }
        
        
        for(int s=0;s<n-m+1;s++){
            System.out.println("Matching "+p+" to "+t0+" s "+s);
            if(p==t0){
                boolean match = true;
                for(int i = 0 ; i < m; i++){
                    System.out.println("Char "+T.charAt(i+s));
                    if(P.charAt(i)!=T.charAt(i+s)){
                        match = false;
                        break;
                    }
                }
                if(match){
                    System.out.println("Found at location: "+s);
                }
            }
            if(s<n-m)
                t0 =(d*(t0-h*Integer.parseInt(""+T.charAt(s)))+Integer.parseInt(""+T.charAt(s+m)))%q;
            
                
        }
        
        
    }
    
    
    public static void main(String argv[]){
        
        RKS("123","2312334123",100000);
        KMP("123121231","2312312412312123121231");
        
    }
}