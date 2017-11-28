import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Node {
	public Point coordinate;
	public Arrow color;
	public Discover discover;
	public Direction direction;
	public Node parent;
	public boolean isCircle;
	public ArrayList<Node> adjList;

	public Node() {
		parent = null;
		discover = Discover.WHITE;
	}
	public Node(Point coord, char color, char circle, String arrowDirection) {
		parent = null;
		discover = Discover.WHITE;
		this.coordinate = coord;

		if(color == 'R') {
			this.color = Arrow.RED;
		}
		else if(color == 'B'){
			this.color = Arrow.BLUE;
		}

		if(circle == 'N') {
			isCircle = false;
		}
		else if(circle == 'C') {
			isCircle = true;
		}

		if(arrowDirection.equals("N")) {this.direction = Direction.N;}
		else if(arrowDirection.equals("NW")){this.direction = Direction.NW;}
		else if(arrowDirection.equals("NE")){this.direction = Direction.NE;}
		else if(arrowDirection.equals("S")){this.direction = Direction.S;}
		else if(arrowDirection.equals("SW")){this.direction = Direction.SW;}
		else if(arrowDirection.equals("SE")){this.direction = Direction.SE;}
		else if(arrowDirection.equals("W")){this.direction = Direction.W;}
		else if(arrowDirection.equals("E")){this.direction = Direction.E;}
		else if(arrowDirection.equals("X")){this.direction = Direction.X;}


		adjList = new ArrayList<Node>();
	}

	public String toString() {
		return "Node: " + coordinate.getX() + ", " + coordinate.getY() + "| Arrow Color: " + color + "| Discovery: " + discover + "| Circled: " + isCircle + "| Direction: " + direction;
	}
	public void calcAdj(ArrayList<ArrayList<Node>> forwardGraph, ArrayList<ArrayList<Node>> oppositeGraph) {




		int count=1;
		int k=1;

		switch(direction) {
		case N:
			for(int i = (int) coordinate.getX(); i >0; i--) {
				if(!forwardGraph.get(i).get((int)coordinate.getY()).color.equals(color) && !forwardGraph.get(i).get((int)coordinate.getY()).coordinate.equals(coordinate)) {
					if(isCircle) {
						adjList.add(oppositeGraph.get(i).get((int)coordinate.getY()));
					}
					else {
						adjList.add(forwardGraph.get(i).get((int) coordinate.getY()));
					}
				}
			}
			break;
		case S:

			for(int i = (int)coordinate.getX(); i < (int) oppositeGraph.size(); ++i) {
				if(!forwardGraph.get(i).get((int)coordinate.getY()).color.equals(color) && !forwardGraph.get(i).get((int)coordinate.getY()).coordinate.equals(coordinate)) {
					if(isCircle) {
						adjList.add(oppositeGraph.get(i).get((int) coordinate.getY()));
					}
					else {
						adjList.add(forwardGraph.get(i).get((int) coordinate.getY()));
					}
				}
			}
			break;
		case W:

			for(int i = (int)coordinate.getY(); i >0; --i) {
				if(!forwardGraph.get((int)coordinate.getX()).get(i).color.equals(color) && !forwardGraph.get((int)coordinate.getX()).get(i).coordinate.equals(coordinate)) {
					if(isCircle) {
						adjList.add(oppositeGraph.get((int)coordinate.getX()).get(i));
					}
					else {
						adjList.add(forwardGraph.get((int)coordinate.getX()).get(i));
					}
				}
			}
			break;
		case E:
			for(int i = (int)coordinate.getY(); i < (int) oppositeGraph.size(); ++i) {
				if(!forwardGraph.get((int)coordinate.getX()).get(i).color.equals(color) && !forwardGraph.get((int)coordinate.getX()).get(i).coordinate.equals(coordinate)) {
					if(isCircle) {
						adjList.add(oppositeGraph.get((int)coordinate.getX()).get(i));
					}
					else {
						adjList.add(forwardGraph.get((int)coordinate.getX()).get(i));
					}
				}
			}
			break;
		case NW:
			count = (int) coordinate.getY();
			k = (int) coordinate.getX();
			while(count >1 && k >1) {
				if(!forwardGraph.get(k).get(count).color.equals(color) && !forwardGraph.get(k).get(count).coordinate.equals(coordinate)) {
					if(isCircle) {
						adjList.add(oppositeGraph.get(k).get(count));
					}
					else {
						adjList.add(forwardGraph.get(k).get(count));
					}
				}
				count--;
				k--;
			}
			break;
		case SW:
			count = (int) coordinate.getY();
			k = (int) coordinate.getX();
			while(count >1 && k < oppositeGraph.size()) {
				if(!forwardGraph.get(k).get(count).color.equals(color) && !forwardGraph.get(k).get(count).coordinate.equals(coordinate)) {
					if(isCircle) {
						adjList.add(oppositeGraph.get(k).get(count));
					}
					else {
						adjList.add(forwardGraph.get(k).get(count));
					}
				}
				count--;
				k++;
			}
			break;
		case NE:
			count = (int) coordinate.getY();
			k = (int) coordinate.getX();
			while(count < oppositeGraph.size() && k >1) {
				if(!forwardGraph.get(k).get(count).color.equals(color) && !forwardGraph.get(k).get(count).coordinate.equals(coordinate)) {
					if(isCircle) {
						adjList.add(oppositeGraph.get(k).get(count));
					}
					else {
						adjList.add(forwardGraph.get(k).get(count));
					}
				}
				count++;
				k--;
			}
			break;
		case SE:
			count = (int) coordinate.getY();
			k = (int) coordinate.getX();
			while(count < oppositeGraph.size() && k < oppositeGraph.size()) {

				if(!forwardGraph.get(k).get(count).color.equals(color) && !forwardGraph.get(k).get(count).coordinate.equals(coordinate)) {
					if(isCircle) {
						adjList.add(oppositeGraph.get(k).get(count));
					}
					else {
						adjList.add(forwardGraph.get(k).get(count));
					}

				}
				count++;
				k++;
			}
			break;
		default:
			break;
		}
	}

	public Direction flopChars(Node point) {
		Direction temp = null;
		if(point.direction.equals(Direction.N)) {temp = Direction.S;}
		else if(point.direction.equals(Direction.NW)){temp = Direction.SE;}
		else if(point.direction.equals(Direction.NE)){temp = Direction.SW;}
		else if(point.direction.equals(Direction.S)){temp = Direction.N;}
		else if(point.direction.equals(Direction.SW)){temp = Direction.NE;}
		else if(point.direction.equals(Direction.SE)){temp = Direction.NW;}
		else if(point.direction.equals(Direction.W)){temp = Direction.E;}
		else if(point.direction.equals(Direction.E)){temp = Direction.W;}
		else if(point.direction.equals(Direction.X)) {temp = Direction.X;}
		return temp;
	}

	public ArrayList<Node> getAdjList(){
		return adjList;
	}
}
