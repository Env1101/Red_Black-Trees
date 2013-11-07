
public class Node<E> {

	private static final int RED = 1;
	private static final int BLACK = 0;
	
	private E data;
	private Node<E> left, right, parent;
	private int color; //Red is 1, Black is 0
	
	//constructor for leaf node
	public Node () {
		this (null, BLACK);
	}
	
	//all the new actual node (that stores data)
	//are RED when added to tree
	public Node (E data){
		this (data, RED);
	}
	
	public Node (E data, int color){
		this.data = data;
		this.left = null;
		this.right = null;
		this.color = color;
	}
	
	public E data() {
		return data;
	}
	
	public void setData(E data) {
		this.data = data;
	}
	
	public Node<E> right() {
		return right;
	}
	
	public void setRight(Node<E> right) {
		this.right = right;
		right.parent = this;
	}
	
	public Node<E> left() {
		return left;
	}
	
	public void setLeft(Node<E> left) {
		this.left = left;
		left.parent = this;
	}
	
	public int color(){
		return color;
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	public Node<E> parent(){
		return parent;
	}
	public void setParent (Node<E> parent){
		this.parent = parent;
	}

	public boolean isRed() {
		return this.color() == RED;
	}
}
