import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;

/**
 * Grid designed to hold a JLabels for ImageIcons to occupy.
 */
public class IconGrid extends Grid {
	private IconLabel iconGrid[][];

	/**
	 * Constructor.
	 *
	 * Populate Grid class panelGrid[][] with JLabels.
	 */
	public IconGrid(int rows, int cols, Dimension dimension) {
		super(rows, cols, dimension);
		iconGrid = new IconLabel[rows][cols];

		double width = dimension.getWidth() / cols;
		double height = dimension.getHeight() / rows;
		Dimension singleCellDimension = new Dimension();
		singleCellDimension.setSize(width, height);

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				IconLabel label = new IconLabel(singleCellDimension);
				iconGrid[y][x] = label;
				this.getGrid()[y][x].add(label, BorderLayout.CENTER);
			}
		}
	}

	/**
	 * Return entire iconGrid for manipulation.
	 */
	public JLabel[][] getIconGrid() {
		return this.iconGrid;
	}

	/**
	 * Get JLabel at a given location.
	 * This overrides the Grid get method.
	 */
	public JLabel getLabel(int row, int col) {
		return this.iconGrid[row][col];
	}

	/**
	 * Moves a given Icon on this IconGrid.
	 *
	 * Returns true if able to move, false otherwise.
	 */
	public boolean move(GridEntity gridEntity, Direction direction) {
		// Cast GridEntity up to icon.
		// not ideal
		Icon icon = (Icon) gridEntity;

		int newRow = -1;
		int newCol = -1;

		int rowLoc = icon.getRow();
		int colLoc = icon.getCol();

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

		if (newRow >= 0 && newRow < iconGrid.length 
			&& newCol >= 0 && newCol < iconGrid[0].length) {
			iconGrid[newRow][newCol].setIcon(
				icon.getIcon(iconGrid[newRow][newCol].getSize()));
			iconGrid[rowLoc][colLoc].setIcon(null);

			icon.setRow(newRow);
			icon.setCol(newCol);

			return true;
		}

		return false;
	}
}
