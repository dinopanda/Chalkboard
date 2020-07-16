import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.JPanel;

/**
 * Basic drawable JPanel with a specified size and boarder.
 */
public class DrawablePanel extends JPanel {
	private Dimension setSize;

	public DrawablePanel(Dimension dimension) {
		this.setSize = dimension;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(new LineBorder(Color.black));
	}

	@Override
    public Dimension getPreferredSize() {             
        return setSize;
    }
}
