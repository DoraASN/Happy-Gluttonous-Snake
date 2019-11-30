package happy_gluttonous_snake;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class HGSMainMenu extends JFrame {
	// 游戏标题。
	private JLabel menubackground;
	// 定义开始界面按钮，经典模式、冒险模式、排行榜、关于游戏、退出游戏、音乐、音效。
	// 关于游戏：方向键控制方向，空格键加速，P键暂停，长按E可自动加长。
	private JButton classics, adventure, ranking, about, quit, music, sound;
//	private boolean unlock;

	public HGSMainMenu() {
		super();
//		unlock = false;
		HGSUtil.mode = 0;
		HGSUtil.level = 0;
		// 主菜单背景。
		menubackground = new JLabel(HGSUtil.menubackground);
		// 经典模式按钮。
		classics = HGSUtil.button(HGSUtil.classics, 557 / 2 - 225 / 2, 280, 230, 72, false);
		menubackground.add(classics);
		adventure = HGSUtil.button(HGSUtil.adventure, 557 / 2 - 225 / 2, 360, 230, 72, false);
		menubackground.add(adventure);
		ranking = HGSUtil.button(HGSUtil.ranking, 557 / 2 - 225 / 2, 440, 230, 72, false);
		menubackground.add(ranking);
		about = HGSUtil.button(HGSUtil.about, 557 / 2 - 225 / 2, 520, 230, 72, false);
		menubackground.add(about);
		quit = HGSUtil.button(HGSUtil.quit, 557 / 2 - 225 / 2, 600, 230, 72, false);
		menubackground.add(quit);
		music = HGSUtil.button(HGSUtil.music, 399, 715, 75, 50, false);
		if (!HGSUtil.musicsw) {
			music.setIcon(HGSUtil.nomusic);
		}
		menubackground.add(music);
		sound = HGSUtil.button(HGSUtil.sound, 474, 715, 75, 50, false);
		if (!HGSUtil.soundsw) {
			sound.setIcon(HGSUtil.nosound);
		}
		menubackground.add(sound);
		super.add(menubackground);
		super.setSize(557, 773);
		super.setLocationRelativeTo(null);
		super.setIconImage(HGSUtil.tubiao.getImage());
		super.setUndecorated(true);
		super.setVisible(true);
		// 添加监听器。
		HGSListener listener = new HGSListener();
		classics.addMouseListener(listener);
		adventure.addMouseListener(listener);
		ranking.addMouseListener(listener);
		about.addMouseListener(listener);
		quit.addMouseListener(listener);
		music.addMouseListener(listener);
		sound.addMouseListener(listener);
		// 拖拽。
		HGSUtil.dragFrame(this);
	}

	private class HGSListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if (e.getSource() == classics) {
				// 播放音乐
				HGSUtil.close();
				HGSUtil.loop(1);

				HGSUtil.level = 0;
				HGSUtil.mode = 0;
				new HGSGameFrame();
				HGSMainMenu.this.dispose();
			} else if (e.getSource() == adventure) {
//				if (unlock) {
				new HGSAdventureMenu(HGSMainMenu.this);
//				}
				if (HGSUtil.level == 0) {
					HGSUtil.mode = 8;
					HGSUtil.level = 1;
				}
//				new HGSGameFrame();
			} else if (e.getSource() == ranking) {
				new HGSList();
			} else if (e.getSource() == about) {
				new HGSAboutMenu();
			} else if (e.getSource() == quit) {
				System.exit(0);
			} else if (e.getSource() == music) {
				// 关闭音乐
				if (HGSUtil.musicsw) {
					HGSUtil.close();
					HGSUtil.musicsw = false;
					HGSUtil.mute = true;
					music.setIcon(HGSUtil.nomusic);
				} else {// 打开音乐
					HGSUtil.musicsw = true;
					HGSUtil.mute = false;
					HGSUtil.close();
					HGSUtil.loop(0);
					music.setIcon(HGSUtil.music);
				}

			} else if (e.getSource() == sound) {
				// 关闭音效
				if (HGSUtil.soundsw) {
					HGSUtil.soundsw = false;
					sound.setIcon(HGSUtil.nosound);
				} else {// 打开音乐
					HGSUtil.soundsw = true;
					sound.setIcon(HGSUtil.sound);
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			if (e.getSource() == classics) {
				classics.setBounds(557 / 2 - 225 / 2, 285, 230, 72);
			} else if (e.getSource() == adventure) {
				adventure.setBounds(557 / 2 - 225 / 2, 365, 230, 72);
			} else if (e.getSource() == ranking) {
				ranking.setBounds(557 / 2 - 225 / 2, 445, 230, 72);
			} else if (e.getSource() == about) {
				about.setBounds(557 / 2 - 225 / 2, 525, 230, 72);
			} else if (e.getSource() == quit) {
				quit.setBounds(557 / 2 - 225 / 2, 605, 230, 72);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			if (e.getSource() == classics) {
				classics.setBounds(557 / 2 - 225 / 2, 280, 230, 72);
			} else if (e.getSource() == adventure) {
				adventure.setBounds(557 / 2 - 225 / 2, 360, 230, 72);
			} else if (e.getSource() == ranking) {
				ranking.setBounds(557 / 2 - 225 / 2, 440, 230, 72);
			} else if (e.getSource() == about) {
				about.setBounds(557 / 2 - 225 / 2, 520, 230, 72);
			} else if (e.getSource() == quit) {
				quit.setBounds(557 / 2 - 225 / 2, 600, 230, 72);
			}
		}
	}
}