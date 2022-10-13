import java.util.ArrayList;
import java.util.Collections;

import edu.du.dudraw.DUDraw;

public class Maze {
	enum CellValue {
		Wall, Open, Explored
	}

	CellValue[][] maze;

	public Maze(int h, int w) {
		//x is height and y 
		maze = new CellValue[h][w];
		for(int i=0; i<maze.length; i++) {
			for(int j=0; j<maze[0].length; j++) {
				maze[i][j] = CellValue.Wall;
			}	
		}
	}
	public void solveMazeBFS() {
		Cell start = new Cell(1,1);
		Cell goal = new Cell(maze.length-2, maze[0].length-2);
		Cell currentCell = start;

		this.maze[currentCell.getX()][currentCell.getY()] = CellValue.Explored;
		DLLQueue<Cell> myQueue = new DLLQueue<>();
		myQueue.enqueue(currentCell);
		while(!myQueue.isEmpty()) {
			currentCell = myQueue.dequeue();
			int x= currentCell.getX();
			int y= currentCell.getY();		
			if(currentCell.equals(goal)) {
				return;
			}
			//north
			if(maze[x][y+1] == CellValue.Open) {
				maze[x][y+1] = CellValue.Explored;
				myQueue.enqueue(new Cell(x, y+1));
			}
			//east
			if(maze[x+1][y] == CellValue.Open) {
				maze[x+1][y] = CellValue.Explored;
				myQueue.enqueue(new Cell(x+1, y));
			}
			//south
			if(maze[x][y-1] == CellValue.Open) {
				maze[x][y-1] = CellValue.Explored;
				myQueue.enqueue(new Cell(x, y-1));
			}
			//west
			if(maze[x-1][y] == CellValue.Open) {
				maze[x-1][y] = CellValue.Explored;
				myQueue.enqueue(new Cell(x-1, y));
			}
			draw();
			DUDraw.show();
			DUDraw.pause(500);
		}

	}
	public void solveMazeDFS() {
		Cell start = new Cell(1,1);
		Cell goal = new Cell(maze.length-2, maze[0].length-2);
		Cell currentCell = start;

		this.maze[currentCell.getX()][currentCell.getY()] = CellValue.Explored;
		DLLstack<Cell> stack = new DLLstack<>();
		stack.push(currentCell);
		while(!stack.isEmpty()) {
			currentCell = stack.pop();
			int x= currentCell.getX();
			int y= currentCell.getY();		
			if(currentCell.equals(goal)) {
				return;
			}
			//north
			if(maze[x][y+1] == CellValue.Open) {
				maze[x][y+1] = CellValue.Explored;
				stack.push(new Cell(x, y+1));
			}
			//east
			if(maze[x+1][y] == CellValue.Open) {
				maze[x+1][y] = CellValue.Explored;
				stack.push(new Cell(x+1, y));
			}
			//south
			if(maze[x][y-1] == CellValue.Open) {
				maze[x][y-1] = CellValue.Explored;
				stack.push(new Cell(x, y-1));
			}
			//west
			if(maze[x-1][y] == CellValue.Open) {
				maze[x-1][y] = CellValue.Explored;
				stack.push(new Cell(x-1, y));
			}
			draw();
			DUDraw.show();
			DUDraw.pause(500);
		}

	}
	public void generateMaze(int x, int y) {
		//		currentCell = (x_start,y_start)
		Cell current = new Cell(x, y);
		maze[x][y] = CellValue.Open;
		DLLstack<Cell> stack = new DLLstack<>();
		stack.push(current);
		while(!stack.isEmpty()) {
			current = stack.pop();
			ArrayList<Cell> neighbors = new ArrayList<Cell>();
			//check north
			if((current.getY()+2< maze[0].length-1) 
					&& this.maze[current.getX()][current.getY()+2] == CellValue.Wall) {

				Cell temp = new Cell(current.getX(),current.getY()+2);
				neighbors.add(temp);
				//				stack.push(temp);
				//System.out.println("north");
				this.maze[current.getX()][current.getY()+1] = CellValue.Open;
				this.maze[current.getX()][current.getY()+2] = CellValue.Open;
			}
			//check east
			if((current.getX()+2<maze.length-1 ) 
					&& this.maze[current.getX()+2][current.getY()] == CellValue.Wall) {
			//	System.out.println("east");
				Cell temp = new Cell(current.getX()+2,current.getY());
				neighbors.add(temp);
				//				stack.push(temp);
				this.maze[current.getX()+1][current.getY()] = CellValue.Open;
				this.maze[current.getX()+2][current.getY()] = CellValue.Open;
			}
			//south
			if((current.getY()-2> 0) 
					&& this.maze[current.getX()][current.getY()-2] == CellValue.Wall) {

				Cell temp = new Cell(current.getX(),current.getY()-2);
				neighbors.add(temp);
				//				stack.push(temp);
				//System.out.println("south");
				this.maze[current.getX()][current.getY()-1] = CellValue.Open;
				this.maze[current.getX()][current.getY()-2] = CellValue.Open;
			}
			//check west
			if((current.getX()-2>0 ) 
					&& this.maze[current.getX()-2][current.getY()] == CellValue.Wall) {
				//System.out.println("west");
				Cell temp = new Cell(current.getX()-2,current.getY());
				neighbors.add(temp);
				//				stack.push(temp);
				this.maze[current.getX()-1][current.getY()] = CellValue.Open;
				this.maze[current.getX()-2][current.getY()] = CellValue.Open;
			}
			Collections.shuffle(neighbors);
			for(int i=0; i<neighbors.size(); i++) {
				stack.push(neighbors.get(i));
			}
		}
	}
	public void draw() {
		for(int i=0; i<maze.length; i++) {
			for(int j=0; j<maze[0].length; j++) {
				if(maze[i][j] == CellValue.Wall) {
					DUDraw.setPenColor(DUDraw.BLACK);
					DUDraw.filledSquare(i+0.5, j+0.5, 0.5);
				}
				else if(maze[i][j] == CellValue.Open) {
					DUDraw.setPenColor(DUDraw.WHITE);
					DUDraw.filledSquare(i+0.5, j+0.5, 0.5);
				}
				else if(maze[i][j] == CellValue.Explored) {
					DUDraw.setPenColor(DUDraw.GREEN);
					DUDraw.filledSquare(i+0.5, j+0.5, 0.5);
				}
			}

		}



	}

	private class Cell {
		int xPos;
		int yPos;
		public Cell(int x, int y) {
			xPos = x;
			yPos = y;		
		}
		public void incX(int x) {
			xPos += x;
		}
		public void incY(int y) {
			xPos += y;
		}
		public int getX() {
			return xPos;
		}
		public int getY() {
			return yPos;
		}
		public boolean equals(Cell other) {
			if(other == null) {
				return false;
			}
			if(other.xPos == this.xPos && other.yPos == this.yPos) {
				return true;
			}
			else {
				return false;
			}
		}
	}

}
