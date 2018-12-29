package Limitjumping;

import FunCode.JSystem;
import FunCode.JSprite;
import FunCode.JStaticSprite;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.Timer;

import Limitjumping.Controls;
import Limitjumping.Graphic;
import Limitjumping.Movement;
import Limitjumping.CGameMain;
import Limitjumping.Renderer;
import Limitjumping.CGameMain;
import Limitjumping.Music;
import FunCode.JEffect;
import FunCode.JSound;



@SuppressWarnings("unused")
public class CGameMain implements ActionListener
{
	public static CGameMain g_GameMain = null ;		//本类的唯一实例
	public int				m_iGameState =	2;		// 游戏状态，0 -- 游戏结束等待开始状态；1 -- 按下空格键开始，初始化游戏；2 -- 游戏进行中

	public final int WIDTH = 1600, HEIGHT = 900;	//最终宽度1600，高度1600

	public Renderer renderer;						//图像

	public Movement actions = new Movement(this);	//人物运动类

	public Graphic graphic = new Graphic(this);		//图像类

	public Controls controls = new Controls(this);	//控制类
	
	public Rectangle player;						//玩家
	//Rectangle 指定坐标空间中的一个区域，通过坐标空间中 Rectangle 对象左上方的点 (x,y)、宽度和高度可以定义这个区域。  
	public int ticks, yMotion, xMotion;				//鼠标单机，轴

	public int stoppingTicks = 100;					//运动
	public boolean isStoppingLeft = false;
	public boolean isStoppingRight = false;

	public boolean canClick = true;					//鼠标点击
	public int clickTicks;		
	public int clearTicks;		

	public int deathCount = 0;						//死亡计数
	public int levelCount = 0;				   		//关卡计数

	public int cur = 0;
	public int first;
	public boolean press = true;					

	public boolean canJump = true;					//判断跳跃

	public Rectangle floor;							//下边界
	public Rectangle wall;							//左边界
	public Rectangle roof;							//上边界

	public ArrayList<Rectangle> obstacle;			//数组底层结构的长方形的障碍物
	public ArrayList<Rectangle> spikes;				//钉子障碍物
	public ArrayList<Rectangle> redField;			//红色<长方形>范围

	public Rectangle block;							//蓝色块

	public int  animTick = 0;		

	public int bgTick = 0;		

	public int dir;									//direction方向

	public int howX = 1600;

	public boolean enter = false;					//0关进入游戏

	public int enterCount = 0;

	public Rectangle startGame;		

	public Rectangle howToPlay;

	public Rectangle blueScreen;

	public boolean spikesDown = true;

	public boolean howScreen = false;
	
	static int flag = 0;

	
	static
	{
		g_GameMain = null;
	}
	
	//=============================================================================
	//
	// 构造器
    public CGameMain() 
    {  	
    	JFrame jframe = new JFrame();							//新建一个窗体
		Timer timer = new Timer(15, this);

		renderer = new Renderer();

		jframe.add(renderer);									//将图像填加到窗体面板中
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//关闭退出 
		jframe.setSize(WIDTH, HEIGHT);							//窗体
		jframe.setTitle("极限跳跃者");
		jframe.setLocationRelativeTo(null);
		jframe.addKeyListener(controls.kl);
		jframe.addMouseListener(controls.ml);
		jframe.setResizable(false);								//窗体调整大小
		jframe.setVisible(true);
		
		player = new Rectangle(100, HEIGHT - 140, 40, 60);		//人物矩形区域

		floor = new Rectangle(WIDTH / 2 - 800, HEIGHT - 90, WIDTH, 100);

		wall = new Rectangle(0, 0, 5, HEIGHT - 90);			

		roof = new Rectangle(0, 0, WIDTH, 5);					//上边界区域

		blueScreen = new Rectangle(1600, 0, 1600, 900);			//蓝色填充

		obstacle = new ArrayList<Rectangle>();					//障碍物
		spikes = new ArrayList<Rectangle>();					//钉子
		redField = new ArrayList<Rectangle>();					//红色区域

		block = new Rectangle(WIDTH - 700, HEIGHT / 2 + 500, 50, 50);	//块

		startGame = new Rectangle(540, 200, 510, 100);			//游戏开始界面start位置边框
		howToPlay = new Rectangle(540, 350, 510, 100);			//游戏开始界面howToPlay位置边框

		timer.start();						// 启动 Timer，使它开始向其侦听器发送动作事件。
    }


	
    
	

	//=============================================================================
    // 游戏主循环，此函数将被不停的调用，引擎每刷新一次屏幕，此函数即被调用一次
	// 用以处理游戏的开始、进行中、结束等各种状态. 
	// 函数参数fDeltaTime : 上次调用本函数到此次调用本函数的时间间隔，单位：秒
    public void GameMainLoop( float fDeltaTime )
    {
    	switch( m_iGameState )
		{
    		// 初始化游戏，清空上一局相关数据
		case 1:
			{
				GameInit();
				m_iGameState	=	2;// 初始化之后，将游戏状态设置为进行中
			}
			break;
	
			// 游戏进行中，处理各种游戏逻辑
		case 2:
			{
				// 修改此处游戏循环条件，完成正确游戏逻辑
				if( true )
				{
					GameRun( fDeltaTime );
				}
				else
				{
					// 游戏结束。调用游戏结算函数，并把游戏状态修改为结束状态
					m_iGameState	=	0;
					GameEnd();
				}
			}
			break;
	
			// 游戏结束/等待按空格键开始
		case 0:
		default:
			break;
		};
    }
    
	//==============================================================================
	//
    // 每局开始前进行初始化，清空上一局相关数据
	public void	GameInit()
	{
	}
	
	//==============================================================================
	//
	// 每局游戏进行中
	public void	GameRun( float fDeltaTime )
	{
	}
	
	//==============================================================================
	//
	// 本局游戏结束
	public void	GameEnd()
	{
	}
    
	// dOnMouseMove：鼠标移动后将被调用的方法
	// 参数 fMouseX, fMouseY：为鼠标当前坐标
	//
    public void	OnMouseMove( float fMouseX, float fMouseY )
	{
	}
	
    // dOnMouseClick：鼠标按下后将被调用的方法
 	// 参数 iMouseType：鼠标按键值，见 enum MouseTypes 定义
 	// 参数 fMouseX, fMouseY：为鼠标当前坐标
	//	
	public void	OnMouseClick( int iMouseType, float fMouseX, float fMouseY )
	{	
	}

	// dOnMouseUp：鼠标弹起后将被调用的方法
	// 参数 iMouseType：鼠标按键值，见 enum MouseTypes 定义
	// 参数 fMouseX, fMouseY：为鼠标当前坐标
	//		
	public void	OnMouseUp( int iMouseType, float fMouseX, float fMouseY )
	{		
	}
    
	// dOnKeyDown：键盘被按下后将被调用的方法
	// 参数 iKey：被按下的键，值见 enum KeyCodes 宏定义
	// 参数 bAltPress, bShiftPress，bCtrlPress：键盘上的功能键Alt，Ctrl，Shift当前是否也处于按下状态
	//    
    public void	OnKeyDown( int iKey, boolean bAltPress, boolean bShiftPress, boolean bCtrlPress )
    {
    }
    
	// dOnKeyUp：键盘按键弹起后将被调用的方法
	// 参数 iKey：弹起的键，值见 enum KeyCodes 宏定义
	//  
	public void	OnKeyUp( int iKey )
	{
	}
	
	// dOnSpriteColSprite：精灵与精灵碰撞后将被调用的方法
	// 精灵之间要产生碰撞，必须在编辑器或者代码里设置精灵发送及接受碰撞
	// 参数 szSrcName：发起碰撞的精灵名字
	// 参数 szTarName：被碰撞的精灵名字
	//
	public void	OnSpriteColSprite( String szSrcName, String szTarName )
	{
	}
	
	// dOnSpriteColWorldLimit：精灵与世界边界碰撞后将被调用的方法
	// 精灵之间要产生碰撞，必须在编辑器或者代码里设置精灵的世界边界限制
	// 参数 szName：碰撞到边界的精灵名字
	// 参数 iColSide：碰撞到的边界 0 左边，1 右边，2 上边，3 下边
	//		
	public void	OnSpriteColWorldLimit( String szName, int iColSide )
	{
	}


	public void actionPerformed(ActionEvent e) {			//鼠标单击方法监听事件

		try {
			actions.movement();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	}

	public void repaint(Graphics g) throws Exception {			//游戏图形

		graphic.graphics(g);
	}

	public static void main(String[] args) throws IOException {

		g_GameMain = new CGameMain();     //控制台主程序入口
		Music player = new Music(".\\GameWav\\BeiJing.wav");   //创建音乐播放器  
        player.start(true);                    		//以开始以循环的形式播放，player(false)为不循环播放  
          
	}

}

