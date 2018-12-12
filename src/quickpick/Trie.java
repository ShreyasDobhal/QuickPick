/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickpick;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Shreyas
 */
public class Trie {
    HashMap<Character,Trie> map;
    boolean isWord;
    
    public Trie() {
        map=new HashMap<>();
        isWord=false;
    }
    public void add(String s)
    {
        s=s.toLowerCase();
        int l=s.length();
        Trie root=this;
        for (int i=0;i<l;i++)
        {
            char c=s.charAt(i);
            if (root.map.get(c)==null)
                root.map.put(c,new Trie());
            root=root.map.get(c);
        }
        root.isWord=true;
    }
    public void remove(String s)
    {
        s=s.toLowerCase();
        int l=s.length();
        Trie root=this;
        for (int i=0;i<l;i++)
        {
            char c=s.charAt(i);
            if (root.map.get(c)==null)
                return ;
            root=root.map.get(c);
        }
        root.isWord=false;
    }
    public boolean contains(String s)
    {
        s=s.toLowerCase();
        int l=s.length();
        Trie root=this;
        for (int i=0;i<l;i++)
        {
            char c=s.charAt(i);
            if (root.map.get(c)==null)
                return false;
            root=root.map.get(c);
        }
        return root.isWord;
    }
    public boolean hasPrefix(String s)
    {
        s=s.toLowerCase();
        int l=s.length();
        Trie root=this;
        for (int i=0;i<l;i++)
        {
            char c=s.charAt(i);
            if (root.map.get(c)==null)
                return false;
            root=root.map.get(c);
        }
        return true;
    }
    public Queue<String> wordSuggest(String prefix)
    {
        prefix=prefix.toLowerCase();
        int l=prefix.length();
        Trie root=this;
        for (int i=0;i<l;i++)
        {
            char c=prefix.charAt(i);
            if (root.map.get(c)==null)
                return null;
            root=root.map.get(c);
        }
        
        Queue<Trie> tries=new LinkedList<>();
        Queue<String> queue=new LinkedList<>();
        Queue<String> output=new LinkedList<>();
        Queue<String> ans=new LinkedList<>();
        
        queue.add("");
        tries.add(root);
        while (!tries.isEmpty())
        {
            root=tries.remove();
            String str=queue.remove();
            for (Character ch:root.map.keySet())
            {
                queue.add(str+ch);
                tries.add(root.map.get(ch));
            }
            if (root.isWord)
                output.add(str);
        }
        
        for (String s:output)
            ans.add(prefix+s);
        return ans;
    }
}
