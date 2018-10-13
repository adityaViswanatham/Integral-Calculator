//Name: Aditya Viswanatham
//Class: CS 2336.003
//Project 4

package Paradox;

//Node Class.
public class Node <E> {
	//Class Attributes.
	public E gen_var;
	//Left Pointer.
	public Node <E> left;
	//Right Pointer.
	public Node <E> right;
	//Class Constructor.
	public Node() {};
	//Overloaded Constructor.
	public Node(E e)
	{
		gen_var = e;
		left = null;
		right = null;
	}
	//Setters.
	public void setLeft(Node <E> left)
	{
		this.left = left;
	}
	public void setRight(Node <E> right)
	{
		this.right = right;
	}
	//Getters.
	public Node <E> getLeft()
	{
		return left;
	}
	public Node <E> getRight()
	{
		return right;
	}
	//GetObject() returns the payload object.
	public E getObject()
	{
		return gen_var;
	}
}
