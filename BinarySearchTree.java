/** @author: Aditya Viswanatham
*/

package Paradox;
import java.util.*;
import java.text.DecimalFormat;

// Binary Search Tree Class
public class BinarySearchTree {

	//Class Attributes.
	private Node <Payload> root;
	ArrayList <String> eq_list;
	ArrayList <String> left_list;
	Node <Payload> parent = null;
	double upper_bound;
	double lower_bound;

	// Class constructor.
	public BinarySearchTree() {
		root = null;
	}
	// Overloaded Constructor.
	public BinarySearchTree(Node <Payload> root) {
		this.root = root;
	}
	//Setter
	public void setRoot(Node <Payload> root) {
		this.root = root;
	}
	//Getter
	public Node <Payload> getRoot() {
		return root;
	}

	//Insert function to insert the elements.
	public void insert(int exp, int coeff) {
		root = insert(root, exp, coeff);
	}
	private Node <Payload> insert(Node <Payload> root, int exp, int coeff) {
		if(root == null) {
			root = new Node <Payload>(new Payload(coeff, exp));
			return root;
		}
		if(root.getObject().getexpo() > exp) {
			if(root.getLeft() == null)
				root.setLeft(new Node <Payload>(new Payload(coeff, exp)));
			else
				insert(root.getLeft(), exp, coeff);
		}
		else if(root.getObject().getexpo() == exp) {
			root.getObject().setcoeff(root.getObject().getcoeff() + coeff);
		}
		else {
			if(root.getRight() == null)
				root.setRight(new Node <Payload>(new Payload(coeff, exp)));
			else
				insert(root.getRight(), exp, coeff);
		}
		return root;
	}

	//Print function to print the tree if necessary.
	public void print() {
		print(root);
	}
	private void print(Node <Payload> root) {
		if(root == null)
			return;
		System.out.println(root.getObject().toString());
		print(root.getLeft());
		print(root.getRight());
	}

	//Search function to search for nodes in the tree.
	public void search(int exp) {
		search(root, exp);
		if(search(root, exp) == true) {
			System.out.println("The value is found.");
		}
		else {
			System.out.println("The value is not found.");
		}
	}
	private boolean search(Node <Payload> root, int exp) {
		Node <Payload> temp_root = root;
		if(temp_root == null) {
			return false;
		}
		else {
			if(temp_root.getObject().expo == exp) {
				return true;
			}
			else if(exp - temp_root.getObject().expo < 0) {
				temp_root = temp_root.getLeft();
				search(temp_root, exp);
			}
			else {
				temp_root = temp_root.getRight();
				search(temp_root, exp);
			}
			return false;
		}
	}

	//Delete function to delete nodes.
		public boolean delete(Node <Payload> node, int value) {
	        if (node == null) {
	            return false;
	        }
	        if (node.getObject().expo == value) {

	            if ((node.getLeft() == null) && (node.getRight() == null)) {
	                node = null;
	                return true;
	            }
	            if ((node.getLeft() != null) && (node.getRight() != null)) {
	                node.getObject().setexpo(findMin(node.getRight()));
	                return true;
	            }
	            if (node.getLeft() != null) {
	                parent.setLeft(node.getLeft());
	                node = null;
	                return true;
	            }
	            if (node.getRight() != null) {
	                parent.setRight(node.getRight());
	                node = null;
	                return true;
	            }
	        }
	        parent = node;
	        if (node.getObject().getexpo() > value) {
	            return delete(node.getLeft(), value);
	        }
	        else {
	            return delete(node.getRight(), value);
	        }
	    }

	// Finding minimum value in the tree.
	public int findMin(Node <Payload> node) {
	  if (node.getLeft() == null) {
	    int v = node.getObject().getexpo();
	    node = null;
	    return v;
	  }
	  return findMin(node.getLeft());
	}

	//Post Order Traversal function.
	public void postOrderTraverse() {
		postOrderTraverse(root);
	}
	private void postOrderTraverse(Node <Payload> root) {
		if(root == null)
			return;
		postOrderTraverse(root.getLeft());
		postOrderTraverse(root.getRight());
		System.out.println(root.getObject().expo);
	}

	//Pre-Order Traversal function.
	public void preOrderTraverse() {
		preOrderTraverse(root);
	}
	private void preOrderTraverse(Node <Payload> root) {
		if(root == null)
			return;
		System.out.println(root.getObject().expo);
		postOrderTraverse(root.getLeft());
		postOrderTraverse(root.getRight());
	}

	// InOrder traversal used for the program.
	public ArrayList<String> traverse() {
		eq_list = new ArrayList<String>();
		traverse(root);
		return eq_list;
	}
	private void traverse(Node <Payload> root) {
		if (root == null)
			return;

		traverse(root.getRight());
		eq_list.add(root.getObject().toString());
		traverse(root.getLeft());
	}
	// Overloading Functions. Traverse function to calculate the definite integrals.
	public String traverse(int upper, int lower) {
		DecimalFormat df = new DecimalFormat("0.000");
		left_list = traverse();
		String left_eqn = "";
		for(int i=0; i < left_list.size(); i++) {
			if(i==0)
				left_eqn += left_list.get(i);
			else {
				if(left_list.get(i).charAt(0) == ('-'))
					left_eqn += left_list.get(i);
				else
					left_eqn += "+" + left_list.get(i);
			}
		}
		left_eqn += " , " + upper + "|" + lower;
		double upper_bound = traverse(root, upper);
		double lower_bound = traverse(root, lower);
		double result = Double.parseDouble(df.format(upper_bound - lower_bound));
		result = result * -1;
		String res = Double.toString(result);
		if(res.indexOf(".") == res.length() - 2)
			left_eqn += " = " + res + "00";
		else
			left_eqn += " = " + res;
		return left_eqn;
	}
	private double traverse(Node <Payload> root, int bound) {
		if (root == null)
			return 0;
		double value = 0;
		value +=traverse(root.getRight(), bound);
		value +=traverse(root.getLeft(), bound);
		value += root.getObject().evaluate(bound);
		return value;
	}
	//Destroy function to destroy the tree.
	public void destroy() {
		destroy(root);
	}
	private void destroy(Node <Payload> root) {
		root = null;
	}
	//Delete function to delete the nodes in the tree.
	public void delete() {
		delete(root);
	}
	private void delete(Node <Payload> root) {
	   while (root != null) {
		   Node <Payload> left = root.left;
		   if (left == null) {
			   Node <Payload> right = root.right;
		       root.right = null;
		       root = right;
		   }
		   else {
		       root.left = left.right;
		       left.right = root;
		       root = left;
		   }
	  }
	}
}
