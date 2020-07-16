import java.awt.Dimension;

import javax.swing.JLabel;

/**
 * Basic drawable JPanel with a specified size and boarder.
 */
public class IconLabel extends JLabel {
	private Dimension setSize;
	private Icon iconEntity;

	/**
	 * Constructors.
	 */
	public IconLabel(Dimension dimension) {
		this.setSize = dimension;
	}

	public IconLabel(Icon iconEntity, Dimension dimension) {
		this(dimension);
		this.iconEntity = iconEntity;
	}

	/**
	 * Setting IconEntity.
	 */
	public void setIconEntity(Icon iconEntity) {
		this.iconEntity = iconEntity;
	}

	/**
	 * Getting IconEntity.
	 */
	public Icon getIconEntity() {
		return this.iconEntity;
	}

	/**
	 * JLabel overrides.
	 *
	 * Overriding all size getting methods.
	 */
	@Override
    public Dimension getPreferredSize() {             
        return setSize;
    }

    @Override
    public Dimension getMaximumSize() {             
        return setSize;
    }

    @Override
    public Dimension getMinimumSize() {             
        return setSize;
    }
}
