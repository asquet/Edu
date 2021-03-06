package ru.resolutionpoint.edu.animals.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import ru.resolutionpoint.edu.animals.model.Entity;
import ru.resolutionpoint.edu.animals.model.Environment;

/**
 * Class <code>EntitiesPanel</code> represents main panel.
 */
public class EntitiesPanel extends JPanel implements Observer {

	private int width;
	private int height;
	private List<EntityView> entities = new ArrayList<EntityView>();

    /**
     * Constructs new panel
     *
     * @param environment environment
     */
    public EntitiesPanel(Environment environment) {
		environment.addObserver(this);
		for (Entity entity : environment.getEntities()) {
			EntityView view = new EntityView(entity);
			entities.add(view);
		}
		width = Environment.WIDTH * EntityView.WIDTH;
		height = Environment.HEIGHT * EntityView.HEIGHT;
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 1; i <= Environment.WIDTH; i++) {
			int x = i * EntityView.WIDTH;
			g.drawLine(x, 0, x, height);
		}
		for (int j = 1; j <= Environment.HEIGHT; j++) {
			int y = j * EntityView.HEIGHT;
			g.drawLine(0, y, width, y);
		}
		for (EntityView view : entities) {
			view.paint(g);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
