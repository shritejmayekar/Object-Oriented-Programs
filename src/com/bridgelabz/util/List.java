package com.bridgelabz.util;


import java.util.Scanner;

/***********************************************************************
 *	Purpose:To perform operation on linked list
 *
 *
 * 	@author Shritej
 *	@version 1.0
 *	@since	2-10-2017
 *
 ************************************************************************/
class NodeList<T>{
	public T data;
	public NodeList link;
	NodeList() {
		//data=0;
		link=null;
	}
	NodeList(T value,NodeList link) {
		data=value;
		this.link=link;
	}
}
public class List<T>{
	NodeList start;
	NodeList end;
	public int size;

	public List() {
		start=null;
		size=0;
	}
	public boolean search(T value) {
		NodeList traverse=start;
		int position=0;
		if(start==null) {
			System.out.print("empty list");
			return false;
		}
		if(start.data.equals(value)) {

			deletByPosition(position);
			return true;
		}
		traverse=traverse.link;
		while((traverse.link!=null)) {
			position++;
			if(traverse.data.equals(value))
			{	deletByPosition(position);
			return true;
			}
			traverse=traverse.link;

		}
		if(traverse.data.equals(value)) {
			position++;
			deletByPosition(position);
			return true;
		}
		return false;
	}

	public void insertAtFirst(T value) {
		NodeList reference=new NodeList(value,null);
		size++;
		if(start==null) {
			start=reference;

		}
		else {
			reference.link=start;
			start=reference;
		}

	}
	public void display() {
		System.out.println("linked list");
		NodeList reference=start;
		if(size==0) {
			System.out.println("empty");
			return;
		}
		if(start.link==null) {
			System.out.print(start.data+" ");
			return;
		}
		System.out.print(start.data+" -> ");
		reference=reference.link;
		while(reference.link!=null) {
			System.out.print(reference.data+" -> ");
			reference=reference.link;
		}
		System.out.print(reference.data+" \n");
	}
	public void insertAtEnd(T value) {
		NodeList nnptr=new NodeList(value,null);
		size++;
		if(start==null) {
			start=nnptr;
			return;
		}
		else {
			NodeList travel=start;
			//travel=travel.link;
			while(travel.link!=null) {
				travel=travel.link;
			}

			travel.link=nnptr;
		}
	}
	public void deleteAtFirst() {
		if(start==null) {
			System.out.println("empty list");
			return;
		}
		start=start.link;
		size--;
		return;
	}
	public void deleteAtEnd() {
		if(start==null) {
			System.out.println("empty list");
			return;
		}
		NodeList delete=start;
		while(delete.link!=null) {
			end=delete;
			delete=delete.link;
		}
		size--;
		end.link=null;
	}
	public void deletByPosition(int pos) {

		if(pos==0) {
			deleteAtFirst();

			return;
		}
		if(pos==size) {
			deleteAtEnd();
			return;
		}
		NodeList deletePos=start;

		for (int j = 1; j <= size; j++) {

			if(j==pos) {
				NodeList temp=deletePos.link;
				temp=temp.link;
				deletePos.link=temp;
				break;
			}
			deletePos=deletePos.link;
		}
		size--;
	}
	public void insertAtPos(T value,int pos) {
		if(pos>size) {
			System.out.println("impossible");
			return;
		}
		if(pos==0) {
			insertAtFirst(value);
			return;
		}
		if(pos==size) {
			insertAtEnd(value);
			return;
		}
		NodeList reference=start;
		NodeList newReference=new NodeList(value,null);
		for(int i=1;i<=size;i++) {
			if(i==pos) {

				NodeList temp=reference.link;
				reference.link=newReference;
				newReference.link=temp;
				break;

			}
			reference=reference.link;

		}
		size++;

	}
	public boolean isEmpty() {
		if(start==null) {
			return true;
		}
		return false;
	}
}

