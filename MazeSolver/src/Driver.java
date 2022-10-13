import java.util.Scanner;

import edu.du.dudraw.DUDraw;

public class Driver {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);  
		System.out.println("enter a y: ");
		int y = sc.nextInt();
		System.out.println("enter a x: ");
		int x = sc.nextInt();
		Maze myMaze = new Maze(x, y);
		DUDraw.setCanvasSize(500, 500);
		DUDraw.setXscale(0,x);
		DUDraw.setYscale(0,y);
		DUDraw.enableDoubleBuffering();
		myMaze.draw();
		int startx = (int)(Math.random()*x);
		int starty = (int)(Math.random()*y);
		while(startx%2 !=1) {
			startx = (int)(Math.random()*x);
		}
		while(starty%2 !=1) {
			starty = (int)(Math.random()*y);
		}
		myMaze.generateMaze(startx,starty);
		myMaze.draw();
		DUDraw.show();
		DUDraw.pause(100);
		myMaze.solveMazeBFS();
		myMaze.draw();
	}

}
