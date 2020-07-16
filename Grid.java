import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * JPanel designed to hold a grid of DrawablePanels.
 *
 * Potential Exercise:
 *   - Create GridUtils class to remove common code.
 */
public class Grid extends JPanel implements IGrid {
	private DrawablePanel panelGrid[][];

	/**
	 * Constructor.
	 *
	 * This sets up row, columns, and all the DrawablePanels
	 * The expected result is for the grid to be the size of
	 * the dimensions passed in. This means each DrawablePanel 
	 * could be a square or a rectangle.
	 */
	public Grid(int rows, int cols, Dimension dimension) {
		this.setLayout(new GridLayout(rows, cols));
		panelGrid = new DrawablePanel[rows][cols];

		double width = dimension.getWidth() / cols;
		double height = dimension.getHeight() / rows;

		Dimension singleCellDimension = new Dimension();
		singleCellDimension.setSize(width, height);

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				DrawablePanel panel = new DrawablePanel(singleCellDimension);
				panelGrid[y][x] = panel;
				this.add(panel);
			}
		}
	}

	/**
	 * move Method implementation from IGrid interface.
	 */
	public boolean move(GridEntity gridEntity, Direction direction) {
		int newRow = -1;
		int newCol = -1;

		int rowLoc = gridEntity.getRow();
		int colLoc = gridEntity.getCol();

		if (Direction.UP == direction) {
			newRow = rowLoc - 1;
			newCol = colLoc;
		} else if (Direction.DOWN == direction) {
			newRow = rowLoc + 1;
			newCol = colLoc;
		} else if (Direction.LEFT == direction) {
			newRow = rowLoc;
			newCol = colLoc - 1;
		} else if (Direction.RIGHT == direction) {
			newRow = rowLoc;
			newCol = colLoc + 1;	
		}

		if (newRow >= 0 && newRow < panelGrid.length 
			&& newCol >= 0 && newCol < panelGrid[0].length) {
			gridEntity.setRow(newRow);
			gridEntity.setCol(newCol);

			return true;
		}

		return false;
	}

	/**
	 * Return entire panelGrid for manipulation. 
	 */
	public DrawablePanel[][] getGrid() {
		return this.panelGrid;
	}

	/**
	 * Get DrawablePanel and a given location. 
	 */
	public DrawablePanel getCell(int row, int col) {
		return panelGrid[row][col];
	}

	/**
	 * Get DrawablePanel at a given location. 
	 */
	public DrawablePanel get(int row, int col) {
		return panelGrid[row][col];
	}
}
