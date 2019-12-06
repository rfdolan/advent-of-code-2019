package problem6;

import java.util.ArrayList;

public class Node {
	public ArrayList<Node> children;
	public String id;

	
	Node(String id) {
		this.id = id;
		this.children = new ArrayList<Node>();
	}
	
	void addChild(Node kid) {
		children.add(kid);
	}
	
	String getId() {
		return this.id;
	}
	
	ArrayList<Node> getChildren() {
		return this.children;
	}
	
	

}
