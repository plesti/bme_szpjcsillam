package hu.gghf.view;

import javax.swing.*;

import hu.gghf.model.Application;
import hu.gghf.model.Map;

import java.awt.*;

public class Window extends JFrame {
	private Application a;
    private Label console;
    private Thread wthread;
    private MapPanel mapPanel;

	public Window(Application a, Map map) { 
		this.a = a;
        mapPanel = new MapPanel(map);
        mapPanel.setPreferredSize(new Dimension(map.maxsize*40, map.maxsize*40));
        console = new Label();
        console.setPreferredSize(new Dimension(map.maxsize*40, 20));

        this.setTitle("KFT in ekson");
        this.setLayout(new BorderLayout());
        this.add(mapPanel, BorderLayout.CENTER);
        this.add(console, BorderLayout.SOUTH);

		this.pack();
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		wthread = new Thread(new WindowThread());
		wthread.start();
	}

    public Label getConsole() {
        return console;
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

	public void sendCommand(String cmd) {
		a.sendCommand(cmd);
	}
	
	public class WindowThread implements Runnable {

		@Override
		public void run() {
			try {
				mapPanel.repaint();
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
