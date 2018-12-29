package Limitjumping;

public class ObjectMovement {

	private CGameMain pm;

	public ObjectMovement(CGameMain platform) {

		pm = platform;
	}

	public void movement() {

		// level 5
		if (pm.levelCount == 5) {

			if (pm.spikes.get(0).y < 700 && pm.spikesDown) {
				pm.spikes.get(0).y += 3;
			} else {

				pm.spikes.get(0).y -= 3;
				pm.spikesDown = false;
				if (pm.spikes.get(0).y < 402) {

					pm.spikesDown = true;
				}
			}
		}

		// level 6
		if (pm.levelCount == 6) {

			if (pm.player.x >= pm.obstacle.get(2).x) {

				pm.obstacle.get(2).y += -5;
			}
			if (pm.obstacle.get(2).y < 0) {

				pm.obstacle.get(2).y = -20;
			}
			if (pm.player.x <= 100) {

				pm.obstacle.get(2).y = 750;
			}
			for (int i = 4; i < 7; i++) {

				if (pm.player.x + pm.player.width > pm.spikes.get(i).x || pm.spikes.get(i).y > 15) {

					pm.spikes.get(i).y += 5;
				}
				if (pm.player.x <= 100) {

					pm.spikes.get(i).y = 5;
				}
			}
		}

		// level 7
		if (pm.levelCount == 7) {

			if (pm.player.x >= pm.obstacle.get(0).x) {

				pm.obstacle.get(0).y += -3;
			}
			if (pm.obstacle.get(0).y < 0) {

				pm.obstacle.get(0).y = -20;
			}
			if (pm.player.x <= 100) {

				pm.obstacle.get(0).y = 750;
			}
			if (pm.player.x + pm.player.width > pm.spikes.get(1).x || pm.spikes.get(1).y > 15) {

				pm.spikes.get(1).y += 15;
				pm.spikes.get(2).x -= 15;
			}
			if (pm.player.x <= 100) {

				pm.spikes.get(1).y = 15;
				pm.spikes.get(2).x = 2000;
			}
		}

		// level 8
		if (pm.levelCount == 8) {

			pm.blueScreen.x = 0;
		}

		// level 9
		if (pm.levelCount == 9) {

			pm.blueScreen.x = 1600;

			pm.roof.y = -300;

			if (pm.obstacle.get(0).x <= 900 && pm.obstacle.get(0).y % 2 == 0) {

				pm.obstacle.get(0).x += 6;
				pm.obstacle.get(1).x += 6;

				if (pm.obstacle.get(0).x > 900) {

					pm.obstacle.get(0).y = -19;
				}
			} else {

				pm.obstacle.get(0).x += -6;
				pm.obstacle.get(1).x += -6;

				if (pm.obstacle.get(0).x < 100) {

					pm.obstacle.get(0).y = -20;
				}
			}
			pm.spikes.get(0).x = pm.obstacle.get(0).x + 50;
			pm.spikes.get(1).x = pm.obstacle.get(1).x - 30;

			if (pm.player.x <= 100) {

				pm.obstacle.get(0).x = -100;
				pm.obstacle.get(1).x = 500;
				pm.obstacle.get(0).y = -20;
			}
		}

		// level 10
		if (pm.levelCount == 10) {

			pm.roof.y = 0;
		}

		// level 11
		if (pm.levelCount == 11) {

			pm.blueScreen.x = 0;

			if (pm.spikes.get(1).x <= 1200 && pm.spikes.get(1).y % 2 == 0) {

				pm.spikes.get(1).x += 6;
				pm.spikes.get(2).x = pm.spikes.get(1).x - 50;

				if (pm.spikes.get(1).x > 1200) {

					pm.spikes.get(1).y = 499;
				}
			} else {

				pm.spikes.get(1).x += -6;
				pm.spikes.get(2).x = pm.spikes.get(1).x - 50;

				if (pm.spikes.get(1).x < 300) {

					pm.spikes.get(1).y = 500;
				}
			}
		}
		
		// level 12
		if(pm.levelCount == 12){
			
			pm.blueScreen.x = 1600;
		}
		
		// level 13
		if(pm.levelCount == 13){
			
			pm.enter = false;
			pm.enterCount = 0;
			pm.levelCount = 0;
		}
	}
}
