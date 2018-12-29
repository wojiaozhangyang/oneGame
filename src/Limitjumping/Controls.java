package Limitjumping;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controls implements KeyListener, MouseListener {

	CGameMain pm;
	KeyListener kl = this;
	MouseListener ml = this;

	public Controls(CGameMain platform) {

		pm = platform;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == ' ' || e.getKeyCode() == 'W' || e.getKeyCode() == 'A' || e.getKeyCode() == 'D') {
			if (e.getKeyCode() == ' ' || e.getKeyCode() == 'W') {
				pm.actions.jump();
				}
			else if (pm.press == true || e.getKeyCode() != pm.cur) {
				if (pm.cur == 0) {
					pm.first = e.getKeyCode();
				}
				pm.cur = e.getKeyCode();
				if (pm.cur == 'A') {
					pm.actions.left();
					pm.isStoppingLeft = false;
				} else if (pm.cur == 'D') {
					pm.actions.right();
					pm.isStoppingRight = false;
				}
				pm.press = false;
			}
			if (e.getKeyCode() == 'A' || e.getKeyCode() == 'D') {
				pm.dir = e.getKeyCode();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e){

		if (pm.first == 'A' && pm.cur == 'D' && e.getKeyCode() == 'D') {
			pm.cur = 'A';
			pm.dir = 'A';
			pm.press = true;
			pm.actions.left();
		} else if (pm.first == 'A' && pm.cur == 'A' && e.getKeyCode() == 'A') {
			pm.cur = 0;
			pm.dir = 'A';
			pm.stoppingTicks = 0;
			pm.isStoppingLeft = true;
			pm.actions.stopLeft();
			pm.press = true;
		} else if (pm.first == 'A' && pm.cur == 'D' && e.getKeyCode() == 'A') {
			pm.cur = 'D';
			pm.dir = 'D';
			pm.first = 'D';
			pm.press = true;
			pm.actions.right();
		}
		if (pm.first == 'D' && pm.cur == 'A' && e.getKeyCode() == 'A') {
			pm.cur = 'D';
			pm.dir = 'D';
			pm.press = true;
			pm.actions.right();
		} else if (pm.first == 'D' && pm.cur == 'D' && e.getKeyCode() == 'D') {
			pm.cur = 0;
			pm.dir = 'D';
			pm.stoppingTicks = 0;
			pm.isStoppingRight = true;
			pm.actions.stopRight();
			pm.press = true;
		} else if (pm.first == 'D' && pm.cur == 'A' && e.getKeyCode() == 'D') {
			pm.cur = 'A';
			pm.dir = 'A';
			pm.first = 'A';
			pm.press = true;
			pm.actions.left();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (pm.enter == false && pm.enterCount == 0 && e.getX() > pm.startGame.x
				&& e.getX() < pm.startGame.x + pm.startGame.width && e.getY() > pm.startGame.y + 37
				&& e.getY() < pm.startGame.y + pm.startGame.height + 37) {

			pm.enter = true;
		} else if (pm.enter == false && pm.enterCount == 0 && e.getX() > pm.howToPlay.x
				&& e.getX() < pm.howToPlay.x + pm.howToPlay.width && e.getY() > pm.howToPlay.y + 37
				&& e.getY() < pm.howToPlay.y + pm.howToPlay.height + 37) {

			pm.howX = 0;
		} else if (pm.enter == false && pm.enterCount == 0 && e.getY() > 700) {
			pm.howX = 1600;
		} else {
			if (pm.canClick == true) {
				pm.block.y = e.getY() - 50;
				pm.block.x = e.getX() - 25;
				pm.clickTicks = pm.ticks;
//				try {
//					new Music().WuKuaiPlay();
//				} catch (IOException e1) {
//				}
				pm.canClick = false;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
