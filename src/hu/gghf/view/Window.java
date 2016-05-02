package hu.gghf.view;

import javax.swing.*;

import hu.gghf.entities.Jaffa;
import hu.gghf.model.Application;
import hu.gghf.model.Map;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Window extends JFrame {

	//private JPanel panel_fields;
	private Application a;
	private Map map;
	private Graphics2D g_main, g_canvas;
	private BufferedImage canvas;
	
	public Window(Application a, Map map) { 
		this.a = a;
		this.map = map;
		//panel_fields = new JPanel();
		//this.add(panel_fields);
		this.setTitle("KFT in eksün");
		this.setPreferredSize(new Dimension(map.maxsize*40,map.maxsize*40+25));
		this.setMinimumSize(new Dimension(100,100));
		this.setMaximumSize(new Dimension(map.maxsize*40,map.maxsize*40+25));
		this.pack();
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		canvas = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
		this.setVisible(true);
		
		
	}
	
	public void draw() throws InterruptedException {
		try {
		AffineTransform transform = new AffineTransform();
		g_main = (Graphics2D)this.getGraphics();
		int canvas_size_x = this.getWidth();
		int canvas_size_y = this.getHeight();
		canvas = new BufferedImage(canvas_size_x,canvas_size_y,BufferedImage.TYPE_INT_ARGB);
		g_canvas = (Graphics2D)canvas.getGraphics();
		double height_ratio = (double)canvas_size_y / (double)this.getPreferredSize().height;
		double width_ratio = (double)canvas_size_x / (double)this.getPreferredSize().width;
		g_canvas.setColor(Color.BLACK);
		g_canvas.fillRect(0, 0, canvas_size_x, canvas_size_y-25);
		for (int i = 0; i < map.maxsize; i++) {
			for (int j = 0; j < map.maxsize; j++) {
				drawObject(g_canvas, map.getMapObject(new Point(i,j)).getImage(), width_ratio, height_ratio, width_ratio*i*40.0, height_ratio*j*40.0+25, transform);
				hu.gghf.entities.Box b;
				if ((b = map.getBox(new Point(i,j))) != null) {
					drawObject(g_canvas, b.getImage(), width_ratio, height_ratio, width_ratio*b.getPosition().x*40.0, height_ratio*b.getPosition().y*40.0+25, transform);
					
				}
			}
		}
		if (map.getReplicator() != null)
		drawObject(g_canvas, map.getReplicator().getImage(), width_ratio, height_ratio, width_ratio*map.getReplicator().getPosition().x*40.0, height_ratio*map.getReplicator().getPosition().y*40.0+25, transform);
		if (map.getPlayer("1") != null)
		drawObject(g_canvas, map.getPlayer("1").getImage(), width_ratio, height_ratio, width_ratio*map.getPlayer("1").getPosition().x*40.0, height_ratio*map.getPlayer("1").getPosition().y*40.0+25, transform);
		if (map.getPlayer("0") != null)
		drawObject(g_canvas, map.getPlayer("0").getImage(), width_ratio, height_ratio, width_ratio*map.getPlayer("0").getPosition().x*40.0, height_ratio*map.getPlayer("0").getPosition().y*40.0+25, transform);
		g_main.drawImage(canvas,0,0,this.getWidth(),this.getHeight(),null);
		Thread.sleep(100);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void drawObject(Graphics2D g, BufferedImage i, double w_ratio, double h_ratio, double x, double y, AffineTransform a) {
		a.translate(x, y);
		a.scale(w_ratio, h_ratio);
		g.drawImage(i, a, null);
		a.scale(1.0/w_ratio, 1.0/h_ratio);
		a.translate(-x, -y);
	}

	public void sendCommand(String cmd) {
		a.sendCommand(cmd);
	}

}
