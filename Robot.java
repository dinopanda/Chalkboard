/**
 * Robot class controllable by:
 * - code
 * - input
 *   - keyboard
 *   - mouse
 */
public class Robot extends Icon {
	private static final String STITCH_ROBOT_ICON_PATH = 
		"images/robotStitch.png";

	/**
	 * Constructors.
	 */
	public Robot(String name, Grid grid, int row, int col) {
		super(name, grid, row, col, 
			STITCH_ROBOT_ICON_PATH, Direction.DOWN, true);
	}

	public Robot(Grid grid, int row, int col) {
		super(grid, row, col, 
			STITCH_ROBOT_ICON_PATH, Direction.DOWN, true);
	}
}
