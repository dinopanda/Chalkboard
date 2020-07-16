import java.util.concurrent.atomic.AtomicInteger;

/**
 * Abstract class to hold a Grid-ed entity.
 */
public abstract class GridEntity {
	private static final AtomicInteger INSTANCE_COUNTER = new AtomicInteger();

	private String name;
	private int rowLoc;
	private int colLoc;
	private boolean movable;
	private Grid grid;

	/**
	 * Constructors.
	 */
	public GridEntity(String name, Grid grid, int rowLoc, int colLoc, boolean movable) {
		this.name = name;
		this.grid = grid;
		this.rowLoc = rowLoc;
		this.colLoc = colLoc;
		this.movable = movable;
	}

	public GridEntity(Grid grid, int rowLoc, int colLoc, boolean movable) {
		this(getDefaultName(), grid, rowLoc, colLoc, movable);
	}

	public GridEntity(String name, Grid grid, int rowLoc, int colLoc) {
		this(name, grid, rowLoc, colLoc, false);
	}

	public GridEntity(Grid grid, int rowLoc, int colLoc) {
		this(getDefaultName(), grid, rowLoc, colLoc, false);
	}

	/**
	 * Create default name with AtomicInteger.
	 */
	private static String getDefaultName() {
		int instance = INSTANCE_COUNTER.incrementAndGet();
		return "GridEntity_" + instance;
	}

	/**
	 * Move Function.
	 */
	public boolean move(Direction direction) {
		return this.getMovable() ? this.getGrid().move(this, direction) : false;
	}

	/** 
	 * Getter and Setters.
	 */
	public String getName() {
		return this.name;
	}

	public Grid getGrid() {
		return this.grid;
	}

	public int getRow() {
		return this.rowLoc;
	}

	public int getCol() {
		return this.colLoc;
	}

	public boolean getMovable() {
		return this.movable;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public void setRow(int rowLoc) {
		this.rowLoc = rowLoc;
	}

	public void setCol(int colLoc) {
		this.colLoc = colLoc;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}
}
