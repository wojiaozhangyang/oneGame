package Limitjumping;

import java.io.IOException;


public class Collision {

	private CGameMain pm;

	public Collision(CGameMain platform) {

		pm = platform;
	}

	public void collision() throws IOException {

		// 钉子
		for (int i = 0; i < pm.spikes.size(); i++) {
			if (pm.player.intersects(pm.spikes.get(i))) {			//判断是否和钉子产生交集  boolean
				pm.player.setLocation(100, pm.HEIGHT - 140);		//回到初始位置
				new Music().DingZiPlay();  
				pm.deathCount += 1;
			}
		}

		// 障碍物
		for (int i = 0; i < pm.obstacle.size(); i++) {
			
			// 地面
			if (pm.player.y + pm.player.height >= pm.floor.y &&
					pm.player.x < pm.floor.x + pm.floor.width &&
						pm.player.x + pm.player.width > pm.floor.x) {
				
				pm.player.y = pm.floor.y - 1 - pm.player.height;
				pm.yMotion = 0;
				pm.canJump = true;
			}
			// 墙
			if (pm.player.x < pm.wall.x + pm.wall.width) {
				pm.player.x = pm.wall.x + pm.wall.width + 1;
			}
			// 屋顶
			if (pm.player.intersects(pm.roof)) {
				
				pm.player.y = pm.roof.y + pm.roof.height + 1;
				pm.yMotion = 0;
			}

			// 物块
			if (pm.player.y + pm.player.height < pm.block.y + 20 && pm.player.y + pm.player.height >= pm.block.y
					&& pm.player.x + pm.player.width > pm.block.x && pm.player.x < pm.block.x + pm.block.width
					&& pm.yMotion > 0) {

				pm.player.y = pm.block.y - pm.player.height + 1;
				pm.yMotion = 0;
				pm.canJump = true;
			}
			// top
			if (pm.player.y + pm.player.height < pm.obstacle.get(i).y + 20
					&& pm.player.y + pm.player.height >= pm.obstacle.get(i).y
					&& pm.player.x + pm.player.width > pm.obstacle.get(i).x
					&& pm.player.x < pm.obstacle.get(i).x + pm.obstacle.get(i).width) {

				if (pm.yMotion > 0 || pm.player.y + pm.player.height < pm.block.y + 20
						&& pm.player.y + pm.player.height >= pm.block.y && pm.player.x + pm.player.width > pm.block.x
						&& pm.player.x < pm.block.x + pm.block.width) {

					pm.player.y = pm.obstacle.get(i).y - pm.player.height - 1;
					pm.yMotion = 0;
					pm.canJump = true;
				}
			}
			// bottom
			if (pm.player.y < pm.obstacle.get(i).y + pm.obstacle.get(i).height
					&& pm.player.y > pm.obstacle.get(i).y + pm.obstacle.get(i).height - 20
					&& pm.player.x < pm.obstacle.get(i).x + pm.obstacle.get(i).width
					&& pm.player.x + pm.player.width > pm.obstacle.get(i).x) {

				pm.player.y = pm.obstacle.get(i).y + pm.obstacle.get(i).height;
				if (pm.yMotion < 0) {
					pm.yMotion = 0;
				}
			}
			// sides
			else if (pm.player.x < pm.obstacle.get(i).x + pm.obstacle.get(i).width
					&& pm.player.x + pm.player.width > pm.obstacle.get(i).x) {

				if (pm.player.y < pm.obstacle.get(i).y + pm.obstacle.get(i).height
						&& pm.player.y + pm.player.height > pm.obstacle.get(i).y + 10) {

					if (pm.xMotion > 0) {
						pm.player.x = pm.obstacle.get(i).x - 1 - pm.player.width;
						pm.xMotion = 0;
						pm.press = true;
					} else if (pm.xMotion < 0) {
						pm.player.x = pm.obstacle.get(i).x + pm.obstacle.get(i).width + 1;
						pm.xMotion = 0;
						pm.press = true;
					}
				}
			}
		}
		// redField
		for (int i = 0; i < pm.redField.size(); i++) {

			if (pm.block.intersects(pm.redField.get(i))) {

				if (pm.block.x + 25 < pm.redField.get(i).x) {

					pm.block.x = pm.redField.get(i).x - 51;
				} else if (pm.block.x + 25 > pm.redField.get(i).x + pm.redField.get(i).width) {

					pm.block.x = pm.redField.get(i).x + pm.redField.get(i).width + 1;
				} else
					pm.block.x = 1600;
			}
		}

	}
}
