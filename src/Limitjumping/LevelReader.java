package Limitjumping;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelReader {

	CGameMain pm;
	Scanner scan;

	public LevelReader(CGameMain platform) {

		pm = platform;

	}

	//IO读取文本文件的内部数据，并迭代关卡
	public void getLevel(int level) {

		try {

			if (level == 1) {
				scan = new Scanner(new File(".\\Levels.txt"));
				scan.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String result;

		if (scan.hasNextLine())
			result = scan.nextLine();
		else
			result = "";

		while (!result.equals("[Level " + (level + 1) + "]") && !result.equals("")) {

			String[] parts = result.split(",");
			//Integer整数Int型，parseInt()是这个类的一个静态方法，作用是把括号里面的参数（args[0])转为int型的值
			if (parts[0].equals("obstacle")) {

				pm.obstacle.add(new Rectangle(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
						Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
			} else if (parts[0].equals("spikes")) {

				pm.spikes.add(new Rectangle(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
						Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
			} else if (parts[0].equals("redField")) {

				pm.redField.add(new Rectangle(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
						Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
			}

			if (scan.hasNextLine())
				result = scan.nextLine();
			else
				result = "";
		}
	}
}
