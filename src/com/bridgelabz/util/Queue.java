/******************************************************************************
 *  Compilation:  javac -d . com/bridgelabz/util/Queue.java
 *  
 *  Purpose: To give functionalities for object oriented programs
 *  			
 *  		
 *  			
 *  			
 *  @author  Shritej
 *  @version 1.0
 *  @since   12-10-2017
 *
 ******************************************************************************/
package com.bridgelabz.util;


/**
*	NodeQueue Class to create the object reference 
*/
class NodeQueue<E> {
    E data;
    NodeQueue link;
    NodeQueue() {
        //data=0;
        link=null;
    }
    NodeQueue(E value,NodeQueue l) {
        data=value;
        link=l;
    }
}
/**
*	Generic type Queue  
*/
public class Queue<E> {
    NodeQueue front,rear;
    public int size;
    public Queue() {
        front=rear=null;
        size=0;
    }
  	/* Insertion of element in Queue*/
    public void Enqueue(E value) {
        NodeQueue<E> reference=new NodeQueue<E>(value,null);
         size++;
        if(front==null) {
            front=reference;
            return;
        }
        else {
            NodeQueue travel=front;
            //travel=travel.link;
            while(travel.link!=null) {
                travel=travel.link;
            }
            
            travel.link=reference;
        }
    }
    /* Display elements of Queue */
    public void display() {
        System.out.println("queue display");
        NodeQueue nptr=front;
        if(size==0) {
            System.out.println("empty");
            return;
        }
        if(front.link==null) {
            System.out.print(front.data+" \n");
            return;
        }
        System.out.print(front.data+" <- ");
        nptr=nptr.link;
        while(nptr.link!=null) {
            System.out.print(nptr.data+" <- ");
            nptr=nptr.link;
        }
        System.out.print(nptr.data+" \n");
        
    }
    /* Remove elements from the Queue */
    public Object Dequee() {
	if(front==null) {
		System.out.println("queue is empty");
		return null;
	}
		NodeQueue temporary=front;
        front=front.link;
        size--;
        return temporary.data;
    }
    public boolean isEmpty() {
    	if(front==null) {
    		return true;
    	}
    	return false;
    }
    
}
