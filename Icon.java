import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Icon class currently extends a GridEntity.
 *
 * Icons have a default Enum Direction of DOWN.
 *
 * Potential Exercise:
 *   - Save all 4 Direction Scaled ImageIcons during constructor
 *     call to reduce the computational overhead to rotate them
 *     each time
 */
public class Icon extends GridEntity {
	private String path;
	private URL pathUrl;
	private Direction direction;

	/**
	 * Constructors. 
	 *
	 * Default Icon:
	 *   - Direction is DOWN
	 *   - movable is false
	 */
	public Icon(Grid grid, int row, int col, String path) {
		this(grid, row, col, path, Direction.DOWN, false);
	}

	public Icon(String name, Grid grid, int row, int col, String path, 
		Direction direction, boolean movable) {
		super(name, grid, row, col, movable);
		this.path = path;
		this.direction = direction;
		this.pathUrl = getClass().getClassLoader().getResource(path);
	}

	public Icon(Grid grid, int row, int col, String path, 
		Direction direction, boolean movable) {
		super(grid, row, col, movable);
		this.path = path;
		this.direction = direction;
		this.pathUrl = getClass().getClassLoader().getResource(path);
	}

	/**
	 * Set Icon Direction.
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Return path to icon.
	 */
	public String getPath() {
		return this.path;
	}

	/** 
	 * Get full size icon as ImageIcon.
	 */
	public ImageIcon getIcon() {
		return new ImageIcon(pathUrl);
	}

	/** 
	 * Get scaled icon as ImageIcon.
	 */
	public ImageIcon getIcon(Dimension dimension) {
		return getIcon(dimension.width, dimension.height);
	}

	/** 
	 * Get scaled icon as ImageIcon.
	 */
	public ImageIcon getIcon(int width, int height) {
		return rotateIcon(width, height);
	}

	/**
	 * Logic to handle rotating arbitrary Icon in the various Directions.
	 * 
	 * Default DOWN direction is checked first to reduce computation.
	 * Backup case also returns the default DOWN direction icon scaled.
	 */
	private ImageIcon rotateIcon(int width, int height) {
		// Skip transformations and return scaled image in default case
		if (Direction.DOWN == this.direction) {
			return new ImageIcon(new ImageIcon(pathUrl)
				.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		}

		try {
			// Read icon image
			BufferedImage image = ImageIO.read(pathUrl);
			// Create new BufferedImage size to draw on
			BufferedImage transformedImage = new BufferedImage(
            	image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

			// Get the corresponding AffineTransformation for a given Direction
			AffineTransform transform = null;
			switch (this.direction) {
				case UP:
					transform = rotateUp(image.getWidth(), image.getHeight());
					break;
				case LEFT:
					transform = rotateLeft(image.getWidth(), image.getHeight());
					break;
				case RIGHT:
					transform = rotateRight(image.getWidth(), image.getHeight());
					break;
			}

			// Draw the rotated image
	        Graphics2D g2d = transformedImage.createGraphics();
	        g2d.drawImage(image, transform, null);
	        g2d.dispose();

	        // Convert BufferedImage to ImageIcon and then scale.
	        // We scale here because the Image.SCALE_SMOOTH produces
	        // a much much smoother image than any RenderingHints in
	        // Graphics2D could produce.
	        return new ImageIcon(new ImageIcon(transformedImage)
	        	.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			System.out.println(e);
		}

		// Backup case, not rotated scaled image
		return new ImageIcon(new ImageIcon(pathUrl)
			.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}

	/**
	 * Create AffineTransform for Up rotation.
	 */
	private AffineTransform rotateUp(int width, int height) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.PI, width / 2, height / 2);
        double offset = (width - height)/2;
        transform.translate(offset, offset);
        return transform;
	}

	/**
	 * Create AffineTransform for Left rotation.
	 */
	private AffineTransform rotateLeft(int width, int height) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.PI/2, width/2, height/2);
        double offset = (width - height)/2;
        transform.translate(offset, offset);
        return transform;
	}

	/**
	 * Create AffineTransform for Right rotation.
	 */
	private AffineTransform rotateRight(int width, int height) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(-Math.PI / 2, width / 2, height / 2);
        double offset = (width - height) / 2;
        transform.translate(-offset, -offset);
        return transform;
	}
}
