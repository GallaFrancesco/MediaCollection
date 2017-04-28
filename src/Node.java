import java.util.LinkedList;
import java.util.List;

// a class to represent a tree node 
// it points to its parent and its children
// children are inside a linkedlist
public class Node<T> {
	T data;
	MediaInfo info;
	Node<T> parent;
	List<Node<T>> children;
	
	public MediaInfo getInfo() {
		return info;
	}

	public void setInfo(MediaInfo info) {
		this.info = info;
	}
	
	public void printInfo(String[] properties) {
		if (this.info == null){
			return;
		}
		for (final String property : properties) {
			System.out.printf("[%s]: '%s''%n", property, this.getInfo().get("General", property));
		}
	}

	// constructor for each node 
	public Node (final T data){
		this.data = data;
		this.children = new LinkedList<Node<T>>();
	}
	
	// add a children to the node
	public Node<T> add_child (T childData){
		Node<T> childNode = new Node<T>(childData);
		childNode.parent = this;
		this.children.add(childNode);
		return childNode;
	}
	
	// get the depth of the node in the tree
	public int get_node_depth(){
		if(this.is_root()){
			return 0;
		}
		return parent.get_node_depth() + 1;
	}
	
	// check if a node is root / leaf 
	public boolean is_root(){
		return this.parent == null;
	}
	
	public boolean is_leaf(){
		return this.children.size() == 0;
	}
	
	// getters/setters
	public T getData (){
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
