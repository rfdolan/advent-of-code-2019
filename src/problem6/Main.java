package problem6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

	static long problem6part1() {
		System.out.println("<=== Problem 6 part 1 ===>");
		Node center = getTree();

		int result = sum(center, 0);
		
		return result/2;
	}
	
	static long problem6part2() {
		System.out.println("<=== Problem 6 part 2 ===>");
		
		Node current = getTree();
		ArrayList<Node> path = getPath(current, "SAN");
		for(Node n: path) {
			System.out.println(n.getId());
		}

		Node parent = getCommonParent(current, "YOU", "SAN");
		int result = getDistBetween(parent, "YOU", "SAN");
		
		return result;
	}

	static Node getCommonParent(Node start, String targ1, String targ2) {
		ArrayList<Node> pathToTarg1 = getPath(start, targ1);
		ArrayList<Node> pathToTarg2 = getPath(start, targ2);

		for(Node n: pathToTarg1) {
			for(Node n2: pathToTarg2) {
				if(n.getId().contentEquals(n2.getId())){
					return n;
				}
			}
			
		}
		return null;
		
	}
	
	static ArrayList<Node> getPath(Node start, String targ) {
		ArrayList<Node> result = new ArrayList<Node>();
		if(start.getId().contentEquals(targ)) {
			result.add(start);

		}
		else {
			for(Node n: start.getChildren()) {
				ArrayList<Node> res = getPath(n, targ);
				if(res.size() > 0) {
					///System.out.println("hfds;");
					result.addAll(res);
					result.add(start);
					return result;
				}
			}
		}
		return result;
	}
	
	static int getDistBetween(Node start, String targ1, String targ2) {
		ArrayList<Node> pathTo1 = getPath(start, targ1);
		ArrayList<Node> pathTo2 = getPath(start, targ2);

		int result = (pathTo1.size() + pathTo2.size()) - 4;
		
		return result;
	}
	
	
	static Node searchFor(String target, Node curr) {
		Node result = null;
		if(curr.getId().contentEquals(target)) {
			result = curr;
		}
		else {
			for(Node n: curr.getChildren()) {
				result = searchFor(target, n);
				if(result != null) {
					return result;
				}
			}
		}

		return result;
		
	}
	
	static int sum(Node curr, int level) {
		int total =level ;

		for(Node n: curr.getChildren()) {
			total += sum(n, level+1);
		}
		return total + level;
	}
	
	static Node getTree() {
		
		String data = "";
		ArrayList<Node> trees = new ArrayList<Node>();

		try {
	      File myObj = new File("problem6.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {

			data = myReader.nextLine();
			//System.out.println(data);
			String[] content = data.split("\\)");
			String parent = content[0];
			String child = content[1];
			Node result = null;
			Node childResult = null;
			boolean found = false;
			for(int i=0; i<trees.size() && !found; i++) {
				Node n = trees.get(i);
				result = searchFor(parent, n);
				if(result != null) {
					found = true;
				}
			}
			found = false;
			for(int i=0; i<trees.size() && !found; i++) {
				Node n = trees.get(i);
				childResult = searchFor(child, n);
				if(childResult != null) {
					found = true;
				}
			}
			if(result != null) {
				// Both exist, add child to parent
				if(childResult != null) {
					result.addChild(childResult);
				}
				// Parent exists, child does not, so create kid and add to parent
				else {
					Node newKid = new Node(child);
					result.addChild(newKid);
				}
			}
			// Child exists but parent doesn't, so add to new parent
			else if(childResult != null) {
				Node newDad = new Node(parent);
				newDad.addChild(childResult);
				trees.add(newDad);
			}
		
			// Neither exists yet, so create it and assign child, keeping track of this node
			else {
				Node newParent = new Node(parent);
				Node newKid = new Node(child);
				newParent.addChild(newKid);
				trees.add(newParent);
			}
		  }
	      myReader.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		Node center = null;
		boolean found = false;
		for(int i=0; i<trees.size() && !found; i++){
			Node n = trees.get(i);
			center = searchFor("COM", n);
			if(center != null) {
				found = true;
			}
		}
		return center;
	}

	public static void main(String[] args) {
		System.out.println(problem6part1());
		System.out.println(problem6part2());

	}

}
