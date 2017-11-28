import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileInput {
public int col;
public int row;
private int x;
private int y;
private char circle;
private char color;
private String direction;

public ArrayList<ArrayList<Node>> legend(ArrayList<ArrayList<Node>> nodeGraph){
	File input = new File("input.txt");
	
	try {
		Scanner scan = new Scanner(input);
		String newLine = scan.nextLine();
		String[] lineData = newLine.split(" ");
		
		row = Integer.parseInt(lineData[0].trim());
		col = Integer.parseInt(lineData[1].trim());
		row++;
	
		
		nodeGraph = new ArrayList<ArrayList<Node>>(row);
		
		for(int i = 0; i <= col; ++i) {
			nodeGraph.add(new ArrayList<Node>());
		}
		
	
		while(scan.hasNextLine()) {
			newLine = scan.nextLine();
			lineData = newLine.split(" ");
			
			x = Integer.parseInt(lineData[0].trim());
			y = Integer.parseInt(lineData[1].trim());
			color = lineData[2].trim().charAt(0);
			circle = lineData[3].trim().charAt(0);
			direction = lineData[4].trim();
			
			if(y==1) {
				nodeGraph.get(x).add(null);
			}
			nodeGraph.get(x).add(new Node(new Point(x,y), color,circle,direction));
			
			
		}
	
	scan.close();	
	}catch(IOException e) {
		e.printStackTrace();
	}
	
	return nodeGraph;
}
}
