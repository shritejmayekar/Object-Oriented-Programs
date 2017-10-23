
package com.bridgelabz.util;
/**
 *	Node class to create the reference of Object
 */
class Node<E>
{
	E data;
	Node link;

	/*  Constructor  */
	public Node()
	{
		link = null;
		// data = 0; as E data =null or 0
	}    
	/*  Constructor  */
	public Node(E d,Node n)
	{
		data = d;
		link = n;
	}    

}
/**
 *	Stack class of Generic type
 */
public class Stack<E> {
	Node top;
	public int size;
	/*  Constructor  */
	public Stack() {
		top=null;
		size=0;
	}
	/* stack push operation */
	public void push(E value) {
		Node<E> reference=new Node<E>(value,null);
		size++;
		if(top==null) {
			top=reference;
		}
		else {
			reference.link=top;
			top=reference;

		}

	}
	/* stack pop operation */
	public Object pop() {
		Object element;
		if(top==null) {
			System.out.println("underflow");
			return null;
		}
		element=top.data;
		top=top.link;
		size--;
		return element;

	}
	/* stack top element */
	public Object peek() {	
		return top.data;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public int size() {
		return size;
	}
	/* stack display */
	public void display() {
		System.out.println("stack display");
		if(size==0) {
			System.out.println("empty");
			return;
		}

		if(top.link==null){
			System.out.println(" "+top.data +"");
			return;

		}
		Node newReferenece=top;
		System.out.print(" "+top.data+" ");
		newReferenece=top.link;
		while(newReferenece.link!=null) {
			System.out.print("->"+newReferenece.data+"");
			newReferenece=newReferenece.link;
		}
		System.out.print("->"+newReferenece.data+"\n ");

	}






}




