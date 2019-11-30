package happy_gluttonous_snake;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class HGSAboutMenu extends JFrame {
	private JButton jb;
	private JLabel bg, jl;

	public HGSAboutMenu() {
		super();
		super.setIconImage(HGSUtil.tubiao.getImage());
		bg = new JLabel(HGSUtil.aboutbackground);
		jb = HGSUtil.button(HGSUtil.aboutconfirm, 671 / 2 - 50, 750, 100, 46, false);
		bg.add(jb);

		jl = new JLabel("<html>游戏介绍</html>");
		jl.setFont(HGSUtil.abouttitlemenufont);
		jl.setBounds(671 / 2 - 470 / 2, 60, 470, 37);
		bg.add(jl);
		jl = new JLabel(
				"<html>本游戏源于经典小游戏《贪吃蛇》，并在此基础上进行了一定的规则改变，使得玩法更加丰富。具体如下：<br><U>经典模式</U>中基础速度会随着时间的推移越来越快，加速速度不变化；<br><U>冒险模式</U>中的基础速度和加速速度每个关卡不同，其中关卡一最慢，关卡三最快。<br></html>");
		jl.setFont(HGSUtil.aboutmenufont);
		jl.setBounds(671 / 2 - 470 / 2, 110, 470, 195);
		bg.add(jl);

		jl = new JLabel("<html>游戏说明</html>");
		jl.setFont(HGSUtil.abouttitlemenufont);
		jl.setBounds(671 / 2 - 470 / 2, 330, 470, 37);
		bg.add(jl);
		jl = new JLabel("<html><U>游戏操作</U>：方向键控制蛇移动的方向，空格键加速，P键暂停（游戏界面的“暂停游戏”同样可以暂停游戏）。</html>");
		jl.setFont(HGSUtil.aboutmenufont);
		jl.setBounds(671 / 2 - 470 / 2, 380, 460, 80);
		bg.add(jl);
		jl = new JLabel("<html><U>游戏设置</U>：主界面右下角可分别进行游戏背景音乐和音效的开关操作。</html>");
		jl.setFont(HGSUtil.aboutmenufont);
		jl.setBounds(671 / 2 - 470 / 2, 470, 460, 55);
		bg.add(jl);

		jl = new JLabel("<html>开发人员</html>");
		jl.setFont(HGSUtil.abouttitlemenufont);
		jl.setBounds(671 / 2 - 470 / 2, 555, 470, 37);
		bg.add(jl);
		jl = new JLabel(HGSUtil.name);
		jl.setFont(HGSUtil.aboutmenufont);
		jl.setBounds(671 / 2 - 470 / 2, 610, 454, 123);
		bg.add(jl);

		jl = new JLabel("2019 @捉虫小队");
		jl.setFont(HGSUtil.rankingtitlefont);
		jl.setBounds(671 - 130 - 60, 810, 130, 37);
		bg.add(jl);

		super.add(jb);
		super.add(bg);
		super.setSize(671, 881);
		super.setUndecorated(true);
		super.setBackground(new Color(0, 0, 0, 0));
		super.setLocationRelativeTo(null);
		super.setVisible(true);
		// 监听器。
		HGSListener listener = new HGSListener();
		jb.addMouseListener(listener);
		HGSUtil.dragFrame(this);
	}

	// 鼠标监听器。
	private class HGSListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			HGSAboutMenu.this.dispose();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			jb.setBounds(671 / 2 - 50, 755, 100, 46);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			jb.setBounds(671 / 2 - 50, 750, 100, 46);
		}
	}
}