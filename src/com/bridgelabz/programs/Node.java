package com.bridgelabz.programs;
;

public class Node {
	int value;
	Node ref;
	Node() {
		value=0;
		ref=null;
	}
	Node(int value,Node ref) {
		this.value=value;
		this.ref=ref;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List list=new List();
		list.add(6);
		list.display();
		list.add(10);
		list.display();
	}

}
class List {
	Node start;
	List() {
	
		start=null;
		// |5| ->|6|->null
	}
	
	void add(int value) {
		Node temp=start;
	Node reference=new Node(value,null);
	if(start==null) {
		start=reference;
	}
	else {
		if(temp.ref==null) 
			temp.ref=reference;
		while(temp.ref!=null) {
			temp=temp.ref;
		}
		temp.ref=reference;
		
	}
	
	
	}
	
	
	void display() {
	Node temp=start;
	if(start==null) {
		System.out.println("empty list");
	return;	
	}
	if(temp.ref==null) {
		System.out.print(start.value+" ->");
	return;	
	}
	
	while(temp.ref!=null) {
		temp=temp.ref;
		System.out.print(temp.value+" ->");
	}
	}
	
}
