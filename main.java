import java.util.ArrayList;
import java.util.List;

public class main {
	private static ArrayList<ArrayList<Node>> forwardGraph;
	private static ArrayList<ArrayList<Node>> oppositeGraph;
	public static int row;
	public static int col;
	
	public static void main(String[] args) {
		
		FileInput input = new FileInput();
		forwardGraph = input.legend(forwardGraph);
		oppositeGraph = input.legend(oppositeGraph);
		row = input.row-1;
		col = input.col-1;
		
		//Reverses direction of nodes for oppositeGraph
		reverseDirection();
		//Creates the forward and reverse adjacency list of nodes
		createAdjacencyMap();
		
		
	}
	
	public static void reverseDirection() {
		for(int i = 1; i <= row; i++) {
			for(int j = 1; j <= col;j++) {
				oppositeGraph.get(i).get(j).direction = oppositeGraph.get(i).get(j).flopChars(oppositeGraph.get(i).get(j));
			}
		}
	}
	public static void createAdjacencyMap() {
		for(int i = 1; i <= row; ++i) {
			for(int j = 1; j <= col; ++j) {
				//calculate adjacency lists for forward and opposite graphs on every node
				forwardGraph.get(i).get(j).calcAdj(forwardGraph,oppositeGraph);
				
				System.out.println("forwardNode: " + forwardGraph.get(i).get(j).toString());
				for(Node node: forwardGraph.get(i).get(j).getAdjList()) {
					
					System.out.println(node.toString());
				}
				oppositeGraph.get(i).get(j).calcAdj(oppositeGraph,forwardGraph);
			}
		}
	}
}
