package happy_gluttonous_snake;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class HGSAdventureMenu extends JFrame {
	private JLabel adventurebackground;
	private JButton firstpass, secondpass, thirdpass, tomainmenu;
	private HGSMainMenu hgsMainMenu;

	public HGSAdventureMenu(HGSMainMenu hgsMainMenu) {
		super();
		this.hgsMainMenu = hgsMainMenu;
		// 主菜单背景。
		adventurebackground = new JLabel(HGSUtil.adventurebackground);
		// 按钮。
		firstpass = HGSUtil.button(HGSUtil.firstpass, 400 / 2 - 141 / 2, 100, 141, 65, false);
		adventurebackground.add(firstpass);
		secondpass = HGSUtil.button(HGSUtil.secondpass, 400 / 2 - 141 / 2, 220, 141, 65, false);
		adventurebackground.add(secondpass);
		thirdpass = HGSUtil.button(HGSUtil.thirdpass, 400 / 2 - 141 / 2, 340, 141, 65, false);
		adventurebackground.add(thirdpass);
		tomainmenu = HGSUtil.button(HGSUtil.tomainmenu, 400 / 2 - 163 / 2, 500, 163, 50, false);
		adventurebackground.add(tomainmenu);
		// 设置框架格式。
		super.add(adventurebackground);
		super.setSize(400, 600);
		super.setLocationRelativeTo(null);
		super.setIconImage(HGSUtil.tubiao.getImage());
		super.setUndecorated(true);
		super.setVisible(true);
		// 添加监听器。
		HGSListener listener = new HGSListener();
		firstpass.addMouseListener(listener);
		secondpass.addMouseListener(listener);
		thirdpass.addMouseListener(listener);
		tomainmenu.addMouseListener(listener);
		// 增加拖拽功能。
		HGSUtil.dragFrame(this);
	}

	private class HGSListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if (e.getSource() == firstpass) {
				HGSUtil.close();
				HGSUtil.loop(2);
				HGSUtil.mode = 8;
				HGSUtil.level = 1;
				new HGSGameFrame();
				HGSAdventureMenu.this.dispose();
				hgsMainMenu.dispose();
			} else if (e.getSource() == secondpass) {
				HGSUtil.close();
				HGSUtil.loop(3);
				HGSUtil.mode = 16;
				HGSUtil.level = 2;
				new HGSGameFrame();
				HGSAdventureMenu.this.dispose();
				hgsMainMenu.dispose();
			} else if (e.getSource() == thirdpass) {
				HGSUtil.close();
				HGSUtil.loop(4);
				HGSUtil.mode = 24;
				HGSUtil.level = 3;
				new HGSGameFrame();
				HGSAdventureMenu.this.dispose();
				hgsMainMenu.dispose();
			} else if (e.getSource() == tomainmenu) {
				new HGSMainMenu();
				HGSAdventureMenu.this.dispose();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			if (e.getSource() == firstpass) {
				firstpass.setBounds(400 / 2 - 141 / 2, 105, 141, 65);
			} else if (e.getSource() == secondpass) {
				secondpass.setBounds(400 / 2 - 141 / 2, 225, 141, 65);
			} else if (e.getSource() == thirdpass) {
				thirdpass.setBounds(400 / 2 - 141 / 2, 345, 141, 65);
			} else if (e.getSource() == tomainmenu) {
				tomainmenu.setBounds(400 / 2 - 163 / 2, 505, 163, 50);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			if (e.getSource() == firstpass) {
				firstpass.setBounds(400 / 2 - 141 / 2, 100, 141, 65);
			} else if (e.getSource() == secondpass) {
				secondpass.setBounds(400 / 2 - 141 / 2, 220, 141, 65);
			} else if (e.getSource() == thirdpass) {
				thirdpass.setBounds(400 / 2 - 141 / 2, 340, 141, 65);
			} else if (e.getSource() == tomainmenu) {
				tomainmenu.setBounds(400 / 2 - 163 / 2, 500, 163, 50);
			}
		}
	}
}