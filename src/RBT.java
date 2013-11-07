import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RBT<E extends Comparable<E>> {
	
	private static final int RED = 1;
	private static final int BLACK = 0;
	
	Node<E> root;
	private int size;
	private List<E> list;
	
	/**
	 * This method process a binary search until it reaches external node
	 * this node then
	 * 
	 * @param data
	 */
	public void add (E data){
		//start building tree if root is empty
		if (isEmpty()) {
			root = new Node<E>(data, BLACK);
		//add to pre-existing tree 
		}else {
			Node <E> node = add(root, data);
			//two cases that need to reconstruct the tree
			if(node.parent().isRed()){
				//case 1: siblings are different colors
				if (node.parent().right().color() != node.parent().left().color())
					trinodeRestructing(node);
				//case 2: siblings are both Red
				else
					recoloring(node);
			}
		}
		size++;
	}
	
	private void recoloring(Node<E> node) {
		// TODO Auto-generated method stub
		
	}

	private void trinodeRestructing(Node<E> node) {
		// TODO Auto-generated method stub
		
	}

	private Node<E> add(Node<E> node, E data) {
		//base case: find the position to add
		if(node.left() == null){
			node.setLeft(new Node<E>(data));
			makeLeaf(node.left());
			return node.left();	
		}else if(node.right() == null){
			node.setRight(new Node<E>(data));
			makeLeaf(node.right());
			return node.right();
		}
		//if data smaller than node.data
		//keep traverse to the left side of the tree
		else if(node.data().compareTo(data) > 0)	
			node = add(node.left(), data);	
		//if data bigger than node.data
		//keep traverse to the right side of the tree
		else
			node = add(node.right(), data);
		return node;
	}

	/**
	 * Leaf node is always BLACK and doesn't store any thing
	 * A last node that stores data is passed in, this method will create
	 * leaf children for it
	 * @param data the last node that has data
	 */
	private void makeLeaf(Node<E> node) {
		node.setLeft(new Node<E>());
		node.setRight(new Node<E>());
	}

	/**
	 * This method add a collection to a tree or build a RBT from
	 * @param c A collection of elements
	 */
	public void addAll(Collection<? extends E> c){
		for (E data : c){
			add(data);
		}
	}
	
	/**
	 * This will be freaking complicated
	 * @param data
	 * @return the Element itself if it exists, null otherwise
	 */
	public E remove (E data){
		return null;
	}
	
	/**
	 * If the element can be gotten, it must be in the tree 
	 * @param data Element need to find
	 * @return True if element is in the tree, False otherwise
	 */
	public boolean contains (E data){
		return get(data) != null;
	}
	
	/**
	 * Recurse through the tree to find the element is asked for
	 * return null if that element doesn't exist
	 * @param data Element need to find
	 * @return the Element if it is in the tree, null otherwise
	 */
	public E get (E data){
		if(isEmpty()) return null;
		Node<E> node = get(root, data);
		return (node == null) ? null : node.data();
	}
	
	private Node<E> get(Node<E> node, E data) {
		//base case 1: find the node
		if(node.data().compareTo(data) == 0)
			return node;
		//base case 2: node is not in the tree
		else if (node.left() == null || node.right() == null)
			return null;
		else {
			if (node.data().compareTo(data) > 0)
				node = get(node.left(), data);
			else
				node = get(node.right(), data);
		}
		return node;
	}

	/**
	 * Test if the tree is empty
	 * @return True if the tree is empty, False otherwise
	 */
	public boolean isEmpty(){
		return root == null;
	}
	
	/**
	 * Return the number of element in the tree
	 * @return 
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Remove all elements in the tree
	 */
	public void clear(){
		root = null;
		size = 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<E> preOrder(){
		if (isEmpty()) return null;
		list = new ArrayList<E>();
		return preOrder (root, list);
	}

	private List<E> preOrder(Node<E> node, List<E> list) {
		list.add(node.data());
		//base case
		if (node.left() != null){
			preOrder(node.left(), list);
		} 
		if (node.right() != null) {
			preOrder(node.right(), list);
		}
		return list;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<E> inOrder() {
		if (isEmpty()) return null;
		list = new ArrayList<E>();
		return inOrder(root, list);
	}
	
	private List<E> inOrder(Node<E> node, List<E> list){
		//base case
		if(node.left() == null && node.right() == null){
			list.add(node.data());
		} else {
			if(node.left() != null){
				inOrder(node.left(),list);
			}
			list.add(node.data());
			if(node.right() != null){
				inOrder(node.right(),list);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<E> postOrder() {
		if (isEmpty()) return null;
		list = new ArrayList<E>();
		return postOrder(root, list);
	}
	
	private List<E> postOrder(Node<E> node, List<E> list){

		if(node.left() != null){
			postOrder(node.left(),list);
		}
		if(node.right() != null){
			postOrder(node.right(),list);
		}
		list.add(node.data());
		
		return list;
	}
	
	/**
	 * 
	 * @param preOrder
	 */
	public void reconstruct(List<? extends E> preOrder) {
		this.clear();
		for (E item : preOrder){
			add(item);
		}
	}
}
