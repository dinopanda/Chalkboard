import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main JFrame class to house all other drawable objects.
 * This class holds the logic to set window size.
 */
public class MainWindow {
	private static JFrame FRAME;

	/**
	 * Constructors.
	 */
	public MainWindow(String title, int width, int height) {
		setFrame(title, new Dimension(width, height));
	}

	public MainWindow(String title) {
		setFrame(title, calculateDimensions());
	}

	/**
	 * Setup JFrame defaults.
	 */
	private void setFrame(String title, Dimension dimension) {
		this.FRAME = new JFrame();
		this.FRAME.setTitle(title);
        this.FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.FRAME.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowOpened(WindowEvent e) {
        		System.out.println("Opening Chalkboard...");
        	}

        	@Override
        	public void windowClosing(WindowEvent e) {
        		System.out.println("Closing Chalkboard.");
        	}
        });
        this.FRAME.setPreferredSize(dimension);
        this.FRAME.pack();
        this.FRAME.setLocationRelativeTo(null);
        this.FRAME.setResizable(false);
        this.FRAME.setVisible(true);
	}

	/**
	 * Logic to set window size.
	 * 
	 * Currently set to create a square 1/4th the size of the screen
	 */
	private Dimension calculateDimensions() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		width = width / 4;
		height = height / 4;

		if (width > height) {
			height = width;
		} else {
			width = height;
		}

		return new Dimension(width, height);
	}

	/**
	 * Add JPanel to display. 
	 * Revalidate to ensure it's redrawn.
	 */
	public void addPanel(JPanel frame) {
		this.FRAME.add(frame);
		this.FRAME.revalidate();
	}

	/**
	 * Force redraw main window.
	 */
	public void revalidate() {
		this.FRAME.revalidate();
	}

	/**
	 * Getters.
	 */
	public JFrame getFrame() {
		return this.FRAME;
	}

	public Dimension getSize() {
		return this.FRAME.getSize();
	}

	public double getHeight() {
		return this.FRAME.getSize().getHeight();
	}

	public double getWidth() {
		return this.FRAME.getSize().getWidth();
	}
}
