import java.util.ArrayList;
import java.util.List;

/**
 * ScratchPad where "Scratch" like input is placed.
 *
 * Full control over the entities on the board are present here.
 */
 public class ScratchPad {
 	private List<GridEntity> entities;

 	/**
 	 * Constructor.
 	 */
 	public ScratchPad(List<GridEntity> entities) {
 		this.entities = entities;
 	}

 	/**
 	 * Method called where user code is put.
 	 * This is the "Scratch" like section. 
 	 */
 	public void run() {
 		// Find a specific entity. 
 		GridEntity stitch = null;
 		for(int i = 0; i < entities.size(); i++) {
 			if ("stitch".equals(entities.get(i).getName())) {
 				stitch = entities.get(i);
 				break;
 			}
 		}

 		// Example Motion. 
 		Direction dir = Direction.UP;

 		int steps = 0;
		while(steps++ < 100) {
			if (!stitch.move(dir)) {
				if (Direction.UP == dir) {
					dir = Direction.RIGHT;
				} else if (Direction.RIGHT == dir) {
					dir = Direction.DOWN;
				} else if (Direction.DOWN == dir) {
					dir = Direction.LEFT;
				} else if (Direction.LEFT == dir) {
					dir = Direction.UP;
				}
			}

			try {
				Thread.sleep(30);
			} catch (Exception e) {

			}
		}
 	}
 }
