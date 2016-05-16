package hu.gghf.view;

import javax.swing.*;

import hu.gghf.entities.Jaffa;
import hu.gghf.entities.Player;
import hu.gghf.entities.Replicator;
import hu.gghf.interfaces.GameEventListener;
import hu.gghf.model.Map;

import java.awt.*;

public class Window extends JFrame implements GameEventListener {
    private Label console;
    private MapPanel mapPanel;

	public Window() {
        this.setTitle("KFT in ekson");
        this.setLayout(new BorderLayout());

        console = new Label();
        this.add(console, BorderLayout.SOUTH);

		this.pack();
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

    public void setMap(Map map) {
        mapPanel = new MapPanel(map);
        mapPanel.setPreferredSize(new Dimension(map.maxsize*40, map.maxsize*40));
        console.setPreferredSize(new Dimension(map.maxsize*40, 20));
        this.add(mapPanel, BorderLayout.CENTER);
        this.pack();
    }

    public void updateMap() {
        if (mapPanel != null)
            mapPanel.repaint();
    }

    @Override
    public void printCall(String msg) {
        if (console != null) {
            console.setText(msg);
        }
    }

    @Override
    public void playerDied(Player player) {
        if (player.getClass() != Jaffa.class) {
            JOptionPane.showMessageDialog(this,
                    "Jaffa nyert!",
                    "Jatek vege",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void playerWins(Player player) {
        if (player.getClass() == Jaffa.class) {
            JOptionPane.showMessageDialog(this, "Jaffa nyert!", "Jatek vege", JOptionPane.PLAIN_MESSAGE);
        } else if (player.getClass() == Player.class) {
            JOptionPane.showMessageDialog(this, "Oneil nyert!", "Jatek vege", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void replicatorDied(Replicator replicator) {
        this.printCall("Replikator meghalt!");
    }
}
