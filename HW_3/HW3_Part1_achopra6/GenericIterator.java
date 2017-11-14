//Created By Akshay Chopra
//Person No.: 50248989
//Email: achopra6@buffalo.edu

import java.util.*;

class Tree<T extends Comparable<T>> implements Iterable<T> {

	public Tree(T v) {
		value = v;
		left = null;
		right = null;
	}

	public void insert(T v) {
		if (value.compareTo(v) == 0)
			return;
		if (value.compareTo(v) > 0)
			if (left == null)
				left = new Tree<T>(v);
			else
				left.insert(v);
		else if (value.compareTo(v) < 0)
			if (right == null)
				right = new Tree<T>(v);
			else
				right.insert(v);
	}

	public Iterator<T> iterator() {
		return new TreeIterator<T>(this);
	}

	protected T value;
	protected Tree<T> left;
	protected Tree<T> right;
}


//Implementation of Iterator for BST
class TreeIterator<T extends Comparable<T>> implements Iterator<T>
{
	private Stack<Tree<T>> stack	= new Stack<Tree<T>>(); //Private stack of nodes
	
	
	public TreeIterator(Tree<T> node) 
	{
		// TODO Auto-generated constructor stub
		
		//Traversing the left spine of the BST and stack all nodes in the stack
		while(node!=null)
		{
			stack.push(node);
			node=node.left;
		}
	}

	@Override
	public boolean hasNext() 
	{	
		//Checks whether there is still value in stack or not
		return !stack.isEmpty();
	}

	@Override
	public T next() 
	{				
		Tree<T> node = stack.pop(); //Node has the value which is at the top of the stack
		stackpush(node.right);   //Function to insert values to stack if right subtree of popped node is not null
        
		return  node.value;   //Returning the value which is at the top of the stack
	}
	
	
	
	public void stackpush(Tree<T> node)
    {
       while (node != null) // If node is not null then we traverse left spine of node is traversed and all nodes are stacked
       {
    	      stack.push(node);
          node = node.left;
       }
    }


}


class Student implements Comparable<Student> {

	public String name;
	int marks;

	public Student(String n, int marks) {
		name = n;
		this.marks = marks;
	}

	public int compareTo(Student s) {
		return name.compareTo(s.name);
	}
}

class GenericIteratorDriver {

	public static void main(String[] args) {
		
		Student s0 = new Student("N", 75);
		Student s1 = new Student("A", 50);
		Student s2 = new Student("J", 35);
		Student s3 = new Student("H", 25);
		Student s4 = new Student("R", 85);
		Student s5 = new Student("K", 65);
		Student s6 = new Student("T", 95);
		Student s7 = new Student("U", 95);
		Student s8 = new Student("B", 95);

		Tree<Student> tree1 = new Tree<Student>(s0);
		tree1.insert(s1);
		tree1.insert(s2);
		tree1.insert(s3);
		tree1.insert(s4);
		tree1.insert(s5);
		tree1.insert(s6);
		tree1.insert(s7);
		tree1.insert(s8);

		for (Student s : tree1)
			System.out.println(s.name + " " + s.marks);	
     }	
}


 