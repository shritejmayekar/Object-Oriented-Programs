
package com.bridgelabz.util;


/**
 *	NodeQueue Class to create the object reference 
 */
class NodeQueue<E> {
<<<<<<< HEAD
	public E data;
	public NodeQueue link;
=======
	E data;
	NodeQueue link;
>>>>>>> 45c374d8e4e6167f3ece8285e3fff1e38e8f1453
	NodeQueue() {
		//data=0;
		link=null;
	}
	NodeQueue(E value,NodeQueue l) {
		data=value;
		link=l;
	}
}
/******************************************************************************
 *
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
 *
 ******************************************************************************/
/**
 * 
 *Generic type Queue  
 *
 * @param <E>
 */

public class Queue<E> {
<<<<<<< HEAD
	public NodeQueue front,rear;
=======
	NodeQueue front,rear;
>>>>>>> 45c374d8e4e6167f3ece8285e3fff1e38e8f1453
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
<<<<<<< HEAD
		return (front==null);
=======
		if(front==null) {
			return true;
		}
		return false;
>>>>>>> 45c374d8e4e6167f3ece8285e3fff1e38e8f1453
	}

}
