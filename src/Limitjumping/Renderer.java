package Limitjumping;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Renderer extends JPanel{

	private static final long serialVersionUID = 1L;
		//版本问题  自动生成
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		//在打印操作期间调用此方法。实现此方法以对组件调用 paintComponent。如果要在打印时添加特殊的绘制行为，可重写此方法。
			try {
				CGameMain.g_GameMain.repaint(g);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}