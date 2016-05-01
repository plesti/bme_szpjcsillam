package hu.gghf.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import hu.gghf.model.Map;
import hu.gghf.view.Window;

public class Controller {

	private Window w;
	/*private boolean jaffa_pressed_box = false;
	private boolean oneil_pressed_box = false;*/
	public Controller(Window w) {
		this.w = w;
		w.addKeyListener(new MoveEventer());
	}
	
	public class MoveEventer implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			String cmd;
			switch (arg0.getKeyChar()) {
			case 'w':
				cmd = "move 0 up";
				break;
			case 'a':
				cmd = "move 0 left";
				break;
			case 's':
				cmd = "move 0 down";
				break;
			case 'd':
				cmd = "move 0 right";
				break;
			case 'i':
				cmd = "move 1 up";
				break;
			case 'j':
				cmd = "move 1 left";
				break;
			case 'k':
				cmd = "move 1 down";
				break;
			case 'l':
				cmd = "move 1 right";
				break;
			case 'e':
				cmd = "shootportal 0 blue";
				break;
			case 'r':
				cmd = "shootportal 0 yellow"; //jol�
				break;
			case 'o':
				cmd = "shootportal 1 red";
				break;
			case 'p':
				cmd = "shootportal 1 green";
				break;
			case 'q':
				cmd = "pickbox 0";
				break;
			case 'u':
				cmd = "pickbox 1";
				break;
			default:
				cmd = "";
				break;
			}
			w.sendCommand(cmd);
			try {
				w.draw();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
