/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickpick;

import database.Database;
import java.util.ArrayList;

/**
 *
 * @author Shreyas
 */
public class BestMatch {
    public static String find(String str) {
        str=str.toLowerCase();
        if ("".equals(str))
            return "";
        Database database=Database.getInstance();
        double best=0;
        boolean flag=false;
        String bestTag="";
        
        //ArrayList<Pair> list=new ArrayList<>();
        
        for (String tag:database.tags) {
            int edits=editDistance(str, tag);
            double norm=edits*1.0/(str.length()+tag.length());
            
            //list.add(new Pair(tag,norm));
            
            if (norm<best || flag==false)
            {
                flag=true;
                best=norm;
                bestTag=tag;
            }
        }
//        list.sort((a,b)->{
//            return Double.compare(a.score,b.score);
//        });
        //System.out.println(list);
        //bestTag=list.get(0).tag;
        return bestTag;
    }
    private static int editDistance(String a,String b)
    {
        int n=a.length();
        int m=b.length();
        int arr[][]=new int[n+1][m+1];
        for (int i=0;i<=n;i++)
            arr[i][0]=i;
        for (int i=0;i<=m;i++)
            arr[0][i]=i;
            
        for (int i=1;i<=n;i++)
        {
            for (int j=1;j<=m;j++)
            {
                if (a.charAt(i-1)==b.charAt(j-1))
                    arr[i][j]=arr[i-1][j-1];
                else
                    arr[i][j]=Math.min(arr[i-1][j],Math.min(arr[i][j-1],arr[i-1][j-1]))+1;
            }
        }
        return arr[n][m];
    }
    static class Pair {
        double score;
        String tag;
        public Pair(String tag,double score) {
            this.tag=tag;
            this.score=score;
        }
        public String toString() {
            return tag+" : "+score;
        }
    }
    
}
