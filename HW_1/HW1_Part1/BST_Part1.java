
// CSE 522: Homework 1, Part 1

//Created By: Akshay Chopra


class BST_Part1 {
	public static void main(String args[]) {
		 Tree tr;
		 tr = new Tree(100);
		 tr.insert(50);
		 tr.insert(125);
		 tr.insert(150);
		 tr.insert(25);
		 tr.insert(75);
		 tr.insert(20);
		 tr.insert(90);

		 tr.delete(20);
		 tr.delete(20);
		 tr.delete(125);
		 tr.delete(150);
		 tr.delete(100);
		 tr.delete(50);
		 tr.delete(75);
		 tr.delete(25);
		 tr.delete(90);

	}
}

class Tree { // Defines one node of a binary search tree

	public Tree(int n) {
		value = n;
		left = null;
		right = null;
	}

	public void insert(int n) {
		if (value == n)
			return;
		if (value < n)
			if (right == null)
				right = new Tree(n);
			else
				right.insert(n);
		else if (left == null)
			left = new Tree(n);
		else
			left.insert(n);
	}


	public Tree min() {
		// returns the Tree node with the minimum value
		// you should write the code
		if(left== null)
			return this;
		else
		 return	left.min();


	}

	public Tree max() {
		// returns the Tree node with the maximum value
		// you should write the code
		if(right== null)
			return this;
		else
			return right.max();

	}


	public Tree find(int n) {
		// returns the Tree node with value n
		// returns null if n is not present in the tree
		// you should write the code
		Tree t;
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

	public void delete(int n) {
		Tree t = find(n);
		if (t == null) {
			// print out error message and return
			System.out.println("Unable to delete "+ n+  " -- Element not present in the tree");
			return;

		}
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
				case3(t,"right");   //I am "right" as which_side value, can be changed to "left"
				return;
			}
		}

		case3(t,"right");
		return;
		// do case3 and return
	}

	private void case1(Tree t, Tree root) {

		 if(root==t)   //If only root is present in tree that is to be deleted, print error message
		 {
			 System.out.println("Unable to delete "+ root.value + " -- Tree will become empty");
		 }

		if(root.left!=null )
		{
			if( root.left.value==t.value )  //If Left of root is equal to the Element to be deleted
			{
				root.left=null;            //Set left of root as null
				return;
			}
		}

		if(root.right!=null )
		{
			if(root.right.value==t.value ) //If Right of root is equal to the Element to be deleted
			{
				root.right=null;           //Set right of root as null
				return;
			}
		}

		 if(root.value>t.value)   //If value of root is greater than element to be deleted
			  case1(t,root.left);  //Traverse to left of root

		 else if(root.value<t.value) //If value of root is less than element to be deleted
			  case1(t,root.right);   //Traverse to right of root

	}

	private void case2(Tree t, Tree root) {

		if(root.left!=null)
		{
			if(root.left.value==t.value)     //If value of Left of root is equal to element to be deleted
			{
				if(t.right==null)
				{
					root.left=t.left;       //Set left of root as left of element to be deleted
				    return;
				}
				else
				{
					root.left=t.right;    //Set left of root as right of element to be deleted
				    return;
				}
			}
		}

		if(root.right!=null)
		{
			if(root.right.value==t.value)  //If value of right of root is equal to element to be deleted
			{
				if(t.right==null)
				{
					root.right=t.left;      //set right of root as left of element to be deleted
					return;
				}
				else
				{
					root.right=t.right;   //Set right of root as right of element to be deleted
					return;
				}
			}
		}

		 if(root.value>t.value)     //If value of root greater than element to be deleted
			  case2(t,root.left);   //Traverse to left of root

		  else if(root.value<t.value)  //If value of root is less than element to be deleted
			  case2(t,root.right);    //Traverse to right of root


	}

	private void case3(Tree t, String which_side) {
		// which == "left" or which == "right"

		if(this==t)      //If element to be deleted is same as root
		{
			if(this.left!=null)
			{
				   //Find Max of Left subtree or Min of right subtree of left of root and apply case accordingly

				    Tree Tr;
					int val=0;
					Tr=this.left.max();
					val=this.left.max().value;
					if(Tr.left==null && Tr.right==null) // If case 1 condition matches, apply case 1 deletion
						case1(Tr,this);
					else
						case2(Tr,this); //else apply case 2
					this.value=val;
					return;


			}
			else
			{
				//Find Max of Left subtree or Min of right subtree of right of root and apply case accordingly

					Tree Tr;
					int val=0;
					Tr=this.right.min();
					val=this.right.min().value;
					if(Tr.left==null && Tr.right==null)  //If case 1 condition matches, apply case 1 deletion
						case1(Tr,this);
					else
						case2(Tr,this);  //else apply case 2
					this.value=val;
					return;


			}
		}


		if(this.left!=null)
		{
			if(this.left.value==t.value)   //If value of left of root is equal to element to be deleted
			{
				if(which_side=="right")
				{
					Tree Tr;
					int val=0;
					Tr=t.right.min();
					val=t.right.min().value;
					if(Tr.left==null && Tr.right==null)
						case1(Tr,t);
					else
						case2(Tr,t);
					this.left.value=val;
					return;
				}

				else if(which_side=="left")
				{
					Tree Tr;
					int val=0;
					Tr=t.left.max();
					val=t.left.max().value;
					if(Tr.left==null && Tr.right==null)
						case1(Tr,t);
					else
						case2(Tr,t);
					this.left.value=val;
					return;

				}
			}
		}

		if(this.right!=null)
		{
			if(this.right.value==t.value)  //If value of right of root is equal to element to be deleted
			{
				if(which_side=="right")
				{
					Tree Tr;
					int val=0;
					Tr=t.right.min();
					val=t.right.min().value;
					if(Tr.left==null && Tr.right==null)
						case1(Tr,t);
					else
						case2(Tr,t);
					this.right.value=val;

					return;

				}

				else if(which_side=="left")
				{
					Tree Tr;
					int val=0;
					Tr=t.left.max();
					val=t.left.max().value;
					if(Tr.left==null && Tr.right==null)
						case1(Tr,t);
					else
						case2(Tr,t);
					this.right.value=val;

					return;


				}
			}
		}


		 if(this.value>t.value)
			this.left.case3(t, which_side);

		  else if(this.value<t.value)
			  this.right.case3(t,which_side);



 	}

	protected int value;
	protected Tree left;
	protected Tree right;
}
