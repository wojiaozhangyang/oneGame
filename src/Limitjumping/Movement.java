package Limitjumping;

import java.io.IOException;

public class Movement {

	private CGameMain pm;
	private ObjectMovement om;
	private Collision col;
	private LevelReader lv;

	public Movement(CGameMain platform) {

		pm = platform;
		om = new ObjectMovement(pm);
		col = new Collision(pm);
		lv = new LevelReader(pm);
	}

	public void movement() throws IOException {

		// start menu
		if (pm.enter == false && pm.enterCount == 0) {
		}

		else {

			pm.ticks++;
			if (!pm.canClick && pm.ticks - pm.clickTicks > 20) {
				pm.canClick = true;
			}
			if (pm.ticks % 5 == 0) {
				pm.animTick += 1;
			}
			if (pm.ticks % 40 == 0) {
				pm.bgTick += 1;
			}

			// 每局开始前进行初始化，清空上一局相关数据
			if (pm.player.x + pm.player.width > pm.WIDTH || pm.levelCount == 0) {

				pm.levelCount++;
				pm.clearTicks = pm.ticks;
				pm.obstacle.clear();
				pm.spikes.clear();
				pm.redField.clear();
				pm.block.x = -100;
				// 加载关卡
				lv.getLevel(pm.levelCount);

				// 恢复玩家到初始位置
				pm.player.setLocation(100, pm.HEIGHT - 140);
			}

			if (pm.yMotion < 20 && pm.player.y + pm.player.height < pm.floor.y) {

				pm.yMotion += 1;
			}

			if (pm.isStoppingLeft) {

				stopLeft();
			}
			if (pm.isStoppingRight) {

				stopRight();
			}

			// 物体运动的方法
			om.movement();

			// player Movement
			pm.player.x += pm.xMotion;
			pm.player.y += pm.yMotion;

			// 碰撞class 方法
			col.collision();

			if (pm.press == true && pm.cur == 'A') {
				left();
			} else if (pm.press == true && pm.cur == 'D') {
				right();
			}

			// 物块移动速度
			pm.block.y += 2;
		}
		pm.renderer.repaint();
	}

	public void jump() {

		if (pm.canJump == true && pm.yMotion == 0) {
			pm.yMotion -= 20;
			pm.canJump = false;
		}
		try {
			new Music().TiaoYuePlay();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void left() {

		pm.xMotion = -7;
	}

	public void right() {

		pm.xMotion = 7;
	}

	public void stopLeft() {

		if (pm.stoppingTicks < 20 && pm.xMotion < 0) {
			if (pm.ticks % 2 == 0) {
				pm.xMotion += 1;
			}
			pm.stoppingTicks++;
		} else if (pm.xMotion >= 0) {
			pm.isStoppingLeft = false;
		}
	}

	public void stopRight() {

		if (pm.stoppingTicks < 20 && pm.xMotion > 0) {
			if (pm.ticks % 2 == 0) {
				pm.xMotion -= 1;
			}
			pm.stoppingTicks++;
		} else if (pm.xMotion <= 0) {
			pm.isStoppingRight = false;
		}
	}
}
