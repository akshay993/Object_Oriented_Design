

//CSE 522: Homework 1, Part 2

//Created By: Akshay Chopra



  class BST_Part2 {

	  public static void main(String[] args) {
			AbsTree tr = new DupTree(100);
			 tr.insert(50);
			 tr.insert(125);
			 tr.insert(150);
			 tr.insert(25);
			 tr.insert(75);
			 tr.insert(20);
			 tr.insert(90);
			 tr.insert(50);
			 tr.insert(125);
			 tr.insert(150);
			 tr.insert(25);
			 tr.insert(75);
			 tr.insert(20);
			 tr.insert(90);

			 tr.delete(20);
			 tr.delete(20);
			 tr.delete(20);
			 tr.delete(150);
			 tr.delete(100);
			 tr.delete(150);
			 tr.delete(125);
			 tr.delete(125);
			 tr.delete(50);
			 tr.delete(50);
			 tr.delete(25);
			 tr.delete(50);
			 tr.delete(75);
			 tr.delete(90);
			 tr.delete(25);
			 tr.delete(50);
			 tr.delete(75);
			 tr.delete(90);
		}
  }

  abstract class AbsTree {

	public AbsTree(int n) {
		value = n;
		left = null;
		right = null;
	}

	public void insert(int n) {
		if (value == n)
			count_duplicates();
		else if (value < n)
			if (right == null)
				right = add_node(n);
			else
				right.insert(n);
		else if (left == null)
			left = add_node(n);
		else
			left.insert(n);
	}

	public void delete(int n) { // assume > 1 nodes in tree
		AbsTree t = find(n);

		// adapt Part 1 solution here

		//Added By Akshay Chopra

		int iCount; //Variable to Store the count of the node to be deleted



		//Printing Error message if the value to be deleted is not present in the tree
		if (t == null) {
			// print out error message and return
			System.out.println("Unable to delete "+ n+  " -- Element not present in the tree");
			return;

		}



		iCount=t.get_count();  //Retrieving the count of the node to be deleted in the variable iCount

		if(iCount>1)  //For count of the node greater than 1, we decrease the count by 1
		{
		   t.dec_count(); //Function to decrement count of node by 1
		}

		else
		{

			if (t.left == null && t.right == null) {
				// do case1 and return
				case1(t,this);
				return ;

			}
			if (t.left == null || t.right == null) {
				if (t != this) {
					// do case2 and return
					case2(t, this);
					return ;

				} else {
					// do case3 and return
					case3(t,"right");
					return;
				}
			}

			case3(t,"right");
			return;

			// do case3 and return
		}

	}

	protected void case1(AbsTree t, AbsTree root) {

		//Added By Akshay Chopra

		if(root==t)  //If only root is present in tree that is to be deleted, print error message
		 {
			 System.out.println("Unable to delete "+ root.value + " -- Tree will become empty");
		 }

		if(root.left!=null )
		{
			if( root.left.value==t.value )  //If Left of root is equal to the Element to be deleted
			{

				root.left=null;     //Set left of root as null
				return;
			}
		}

		if(root.right!=null )
		{
			if(root.right.value==t.value )  //If Right of root is equal to the Element to be deleted
			{
				root.right=null;   //Set right of root as null
				return;
			}
		}

		 if(root.value>t.value)    //If value of root is greater than element to be deleted
			  case1(t,root.left);  //Traverse to left of root

		 else if(root.value<t.value)  //If value of root is less than element to be deleted
			  case1(t,root.right);    //Traverse to right of root


	}

	protected void case2(AbsTree t, AbsTree root) {

		//Added By Akshay Chopra

		if(root.left!=null)
		{
			if(root.left.value==t.value)   //If value of Left of root is equal to element to be deleted
			{
				if(t.right==null)
				{
					root.left=t.left;   //Set left of root as left of element to be deleted
				    return;
				}
				else
				{
					root.left=t.right;   //Set left of root as right of element to be deleted
				    return;
				}
			}
		}

		if(root.right!=null)
		{
			if(root.right.value==t.value)   //If value of right of root is equal to element to be deleted
			{
				if(t.right==null)
				{
					root.right=t.left;  //set right of root as left of element to be deleted
					return;
				}
				else
				{
					root.right=t.right;   //Set right of root as right of element to be deleted
					return;
				}
			}
		}

		 if(root.value>t.value)    //If value of root greater than element to be deleted
			  case2(t,root.left);  //Traverse to left of root

		  else if(root.value<t.value)  //If value of root is less than element to be deleted
			  case2(t,root.right);     //Traverse to right of root


	}

	protected void case3(AbsTree t, String side) {

		//Added By Akshay Chopra


		if(this==t)     //If element to be deleted is same as root
		{
			if(this.left!=null)
			{

				//Find Max of Left subtree or Min of right subtree of left of root and apply case accordingly
				    AbsTree Tr;
					int val=0;
					Tr=this.left.max();
					val=this.left.max().value;
					if(Tr.left==null && Tr.right==null)  // If case 1 condition matches, apply case 1 deletion
						case1(Tr,this);
					else
						case2(Tr,this);   //else apply case 2
					this.value=val;
					this.set_count(Tr.get_count());

					return;


			}
			else
			{
					AbsTree Tr;
					int val=0;
					Tr=this.right.min();
					val=this.right.min().value;
					if(Tr.left==null && Tr.right==null)
						case1(Tr,this);
					else
						case2(Tr,this);
					this.value=val;
					this.set_count(Tr.get_count());
					return;


			}
		}


		if(this.left!=null)
		{
			if(this.left.value==t.value)    //If value of left of root is equal to element to be deleted
			{
				if(side=="right")
				{
					AbsTree Tr;
					int val=0;
					Tr=t.right.min();
					val=t.right.min().value;
					if(Tr.left==null && Tr.right==null)
						case1(Tr,t);
					else
						case2(Tr,t);
					this.left.value=val;
					this.left.set_count(Tr.get_count());
					return;
				}

				else if(side=="left")
				{
					AbsTree Tr;
					int val=0;
					Tr=t.left.max();
					val=t.left.max().value;
					if(Tr.left==null && Tr.right==null)
						case1(Tr,t);
					else
						case2(Tr,t);
					this.left.value=val;
					this.left.set_count(Tr.get_count());
					return;

				}
			}
		}

		if(this.right!=null)
		{
			if(this.right.value==t.value)   //If value of right of root is equal to element to be deleted
			{
				if(side=="right")
				{
					AbsTree Tr;
					int val=0;
					Tr=t.right.min();
					val=t.right.min().value;
					if(Tr.left==null && Tr.right==null)
						case1(Tr,t);
					else
						case2(Tr,t);
					this.right.value=val;
					this.right.set_count(Tr.get_count());

					return;

				}

				else if(side=="left")
				{
					AbsTree Tr;
					int val=0;
					Tr=t.left.max();
					val=t.left.max().value;
					if(Tr.left==null && Tr.right==null)
						case1(Tr,t);
					else
						case2(Tr,t);
					this.right.value=val;
					this.right.set_count(Tr.get_count());

					return;


				}
			}
		}


		 if(this.value>t.value)
			this.left.case3(t, side);

		  else if(this.value<t.value)
			  this.right.case3(t,side);





	}

	private AbsTree find(int n) {
		//Added By Akshay Chopra

		AbsTree t;
		if(this.value==n)
			return this;
		else if(this.value>n && this.left!= null)
		{
			t=left.find(n);
			return t;
		}
		else if(this.value<n && this.right!= null)
		{
			t=right.find(n);
		    return t;
		}
		else
			return null;



	}

	public AbsTree min() {
		//Added By Akshay Chopra

		if(left== null)
			return this;
		else
		 return	left.min();

	}

	public AbsTree max() {
		//Added By Akshay Chopra

		if(right== null)
			return this;
		else
			return right.max();


	}

	protected int value;
	protected AbsTree left;
	protected AbsTree right;

	// Protected Abstract Methods

	protected abstract AbsTree add_node(int n);
	protected abstract void count_duplicates();

	// additional protected abstract methods, as needed

	//Added By Akshay Chopra
	protected abstract int get_count();   //This method returns the count of a node

	protected abstract void dec_count();  //This method decrements the count of a node by 1

	protected abstract void set_count(int c); //This method is used to  set the count of a particular node to another node node at time of deletion



}


class Tree extends AbsTree {

	public Tree(int n) {
		super(n);
	}

	protected AbsTree add_node(int n) {
		return new Tree(n);
	}

	protected void count_duplicates() {
		;
	}

	// additional protected methods, as needed

	//Added By Akshay Chopra

	protected int get_count()
	{
		//Since for Tree class, a particular value can have only 1 node, so we return the count as 1

		return 1;
	}

	protected void set_count(int c)
	{
		return ;
	}

	protected void dec_count()
	{
		return;
	}

}

class DupTree extends AbsTree {

	public DupTree(int n) {
		super(n);
		count = 1;
	};

	protected AbsTree add_node(int n) {
		return new DupTree(n);
	}

	protected void count_duplicates() {
		count++;
	}

	// additional protected methods, as needed

	//Added By Akshay Chopra

	protected int get_count()
	{
		//Returning the count of the particular node
		return this.count;
	}

	protected void set_count(int c)
	{
		//Set count of a particular node as the count of another node which is sent as an argument to this function
		this.count = c;
	}

	protected void dec_count()
	{

		//Decrement the count of a particular node by 1
	   this.count--;
	}


	protected int count;
}
