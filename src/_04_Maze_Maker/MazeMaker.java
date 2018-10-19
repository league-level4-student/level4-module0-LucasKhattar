package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		Random rand = new Random();
		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.cell[rand.nextInt(w)][rand.nextInt(h)]);
		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		Random rand = new Random();
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		// C. if has unvisited neighbors,
		ArrayList<Cell> un = getUnvisitedNeighbors(currentCell);
		if (!un.isEmpty()) {
			// C1. select one at random.
			int randc = rand.nextInt(un.size());
			// C2. push it to the stack
			uncheckedCells.push(un.get(randc));
			// C3. remove the wall between the two cells
			removeWalls(currentCell, un.get(randc));
			// C4. make the new cell the current cell and mark it as visited
			selectNextPath(un.get(randc));

		}
		// D. if all neighbors are visited
		else {
			// D1. if the stack is not empty
			if (!uncheckedCells.isEmpty()) {
				// D1a. pop a cell from the stack
				Cell poop = uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = poop;
				selectNextPath(currentCell);
			}
		}
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() == c2.getX()) {
			if (c1.getY() > c2.getY()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			} else {
				c1.setSouthWall(false);
				c2.setNorthWall(false);
			}

		} else {
			if (c1.getX() < c2.getX()) {
				c1.setEastWall(false);
				c2.setWestWall(false);
			} else {
				c1.setWestWall(false);
				c2.setEastWall(false);
			}
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> getNeighbors = new ArrayList<Cell>();
		if (c.getX() - 1 != -1 && maze.cell[c.getX() - 1][c.getY()].hasBeenVisited() == false) {
			getNeighbors.add(maze.getCell(c.getX() - 1, c.getY()));
		}

//		if (c.getX() - 1 != -1 && c.getY() - 1 != -1
//				&& maze.cell[c.getX() - 1][c.getY() - 1].hasBeenVisited() == false) {
//			getNeighbors.add(maze.getCell(c.getX() - 1, c.getY() - 1));
//		}

		if (c.getY() - 1 != -1 && maze.cell[c.getX()][c.getY() - 1].hasBeenVisited() == false) {
			getNeighbors.add(maze.getCell(c.getX(), c.getY() - 1));
		}

		if (c.getX() + 1 != width  && maze.cell[c.getX() + 1][c.getY()].hasBeenVisited() == false) {
			getNeighbors.add(maze.getCell(c.getX() + 1, c.getY()));
		}

//		if (c.getX() + 1 != width  && c.getY() + 1 != height 
//				&& maze.cell[c.getX() + 1][c.getY() + 1].hasBeenVisited() == false) {
//			getNeighbors.add(maze.getCell(c.getX() + 1, c.getY() + 1));
//		}

		if (c.getY() + 1 != width  && maze.cell[c.getX()][c.getY() + 1].hasBeenVisited() == false) {
			getNeighbors.add(maze.getCell(c.getX(), c.getY() + 1));
		}

//		if (c.getX() - 1 != -1 && c.getY() + 1 != height 
//				&& maze.cell[c.getX() - 1][c.getY() + 1].hasBeenVisited() == false) {
//			getNeighbors.add(maze.getCell(c.getX() - 1, c.getY() + 1));
//		}

//		if (c.getX() + 1 != width  && c.getY() - 1 != -1
//				&& maze.cell[c.getX() + 1][c.getY() - 1].hasBeenVisited() == false) {
//			getNeighbors.add(maze.getCell(c.getX() + 1, c.getY() - 1));
//		}

		return getNeighbors;
	}
}