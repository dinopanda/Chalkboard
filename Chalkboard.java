import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

/**
 * Main class for Chalkboard.
 * 
 * This application is intended to be used to
 * teach the basics of computer science.
 */
public class Chalkboard {
	private static int GRID_ROWS = 7;
	private static int GRID_COLS = 7;

	public static void main(String args[]) {
		System.out.println("Chalkboard Starting...");

		// Create default window
		MainWindow mainWindow = new MainWindow("Chalkboard V1");

		// Call setup function
		List<GridEntity> entities = setup(mainWindow);
		ScratchPad scratchPad = new ScratchPad(entities);

		scratchPad.run();

		System.out.println("Waiting for Chalkboard to close...");
	}

	/**
	 * Handle any custom setup here.
	 *
	 * Store all entities into gridEntities List to be used for
	 * ScratchPad access later. 
	 */
	private static List<GridEntity> setup(MainWindow mainWindow) {
		List<GridEntity> gridEntities = new ArrayList<GridEntity>();

		IconGrid iconGrid = new IconGrid(GRID_ROWS, GRID_COLS, mainWindow.getSize());
		mainWindow.addPanel(iconGrid);
		iconGrid.revalidate();

		JLabel label = iconGrid.getLabel(4, 3);
		Robot robot = new Robot("stitch", iconGrid, 4, 3);
		label.setIcon(robot.getIcon(label.getSize()));

		mainWindow.revalidate();

		gridEntities.add(robot);

		return gridEntities;
	}
}
