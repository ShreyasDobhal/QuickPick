/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Shreyas
 */
public class Heap {
    
    public ArrayList<Product> heap;
    private int index;
    private SortBy sortType;
    private int sortMethod;
    
    public static final int SORT_BY_DEFAULT=0;
    public static final int SORT_BY_COST_INC=1;
    public static final int SORT_BY_COST_DEC=2;
    public static final int SORT_BY_DOU_INC=3;
    public static final int SORT_BY_DOU_DEC=4;
    
    public Heap() {
        heap=new ArrayList<>();
        index=0;
        sortMethod=SORT_BY_DEFAULT;
        getImplementation(sortMethod);
    }
    // TODO: Give implementations for newest and oldest
    private void getImplementation(int type) {
        switch (type) {
            case SORT_BY_COST_INC:
                sortType = new SortBYCostInc();
                break;
            case SORT_BY_COST_DEC:
                sortType = new SortBYCostDec();
                break;
            case SORT_BY_DOU_INC:
                break;
            case SORT_BY_DOU_DEC:
                break;
            case SORT_BY_DEFAULT:
            default:
                sortType = new SortBYCostInc();
                break;
        }
    }
    public void buildHeap(int type) {
        sortMethod=type;
        getImplementation(sortMethod);
        buildHeap();
    }
    public void buildHeap() {
        int index=heap.size()/2;
        for (int i=index;i>=0;i--)
            heapify(heap.size(),i);
    }
    private void heapify(int n, int i)  {
        while (i<n) {
            int left=2*i+1;
            int right=2*i+2;
            int index=i;
            if (left<n && sortType.compare(heap.get(left),heap.get(index)))
                index=left;
            if (right<n && sortType.compare(heap.get(right),heap.get(index)))
                index=right;
            Product tmp=heap.get(index);
            heap.set(index,heap.get(i));
            heap.set(i,tmp);
            if (i==index)
                break;
            i=index;
        }
    }
    private void heapifyUp(int n,int i) {
        while (i>0) {
            int index=i;
            int parent=(i-1)/2;
            if (parent>=0 && sortType.compare(heap.get(index),heap.get(parent)))
            {
                Product tmp=heap.get(index);
                heap.set(index,heap.get(parent));
                heap.set(parent,tmp);
            }
            i=parent;
        }
    }
    private void heapifyDown(int n,int i) {
        while (i<n) {
            int index=i;
            int left=2*i+1;
            int right=2*i+2;
            int val=index;
            if (left<n && sortType.compare(heap.get(left),heap.get(index)))
                val=left;
            if (right<n && sortType.compare(heap.get(right),heap.get(val)))
                val=right;
            
            Product tmp=heap.get(index);
            heap.set(index,heap.get(val));
            heap.set(val,tmp);
            
            if (index==val)
                break;
            i=val;
        }
    }
    
    public void insert(Product pro) {
       heap.add(pro);
       heapifyUp(heap.size(),heap.size()-1);
    }
    
    public Product removeTop() {
        if (heap.size()<=0)
            return null;
        Product tmp=heap.get(0);
        heap.set(0,heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        heapifyDown(heap.size(),0);
        return tmp;
    }
    
    public List<Product> getList() {
        List<Product> list=new LinkedList<>();
        Stack<Product> sav=new Stack<>();
        
        while (!heap.isEmpty()) {
            Product product=removeTop();
            sav.push(product);
            list.add(product);
        }
        while (!sav.isEmpty()) {
            Product product=sav.pop();
            insert(product);
        }
        return list;
    }
    
    @Override
    public String toString() {
        return heap.toString();
    }
}
// cost inc / dec
// dateofupload inc / dec



class SortBYCostInc implements SortBy
{
    @Override
    public boolean compare(Product a,Product b) {
        if (a.cost<b.cost)
            return true;
        return false;
    }
}
class SortBYCostDec implements SortBy
{
    @Override
    public boolean compare(Product a,Product b) {
        if (a.cost>b.cost)
            return true;
        return false;
    }
}

interface SortBy
{
    public boolean compare(Product a,Product b);    
}
