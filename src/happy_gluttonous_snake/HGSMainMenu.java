package happy_gluttonous_snake;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class HGSMainMenu extends JFrame {
	// ��Ϸ���⡣
	private JLabel menubackground;
	// ���忪ʼ���水ť������ģʽ��ð��ģʽ�����а񡢹�����Ϸ���˳���Ϸ�����֡���Ч��
	// ������Ϸ����������Ʒ��򣬿ո�����٣�P����ͣ������E���Զ��ӳ���
	private JButton classics, adventure, ranking, about, quit, music, sound;
//	private boolean unlock;

	public HGSMainMenu() {
		super();
//		unlock = false;
		HGSUtil.mode = 0;
		HGSUtil.level = 0;
		// ���˵�������
		menubackground = new JLabel(HGSUtil.menubackground);
		// ����ģʽ��ť��
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
		// ��Ӽ�������
		HGSListener listener = new HGSListener();
		classics.addMouseListener(listener);
		adventure.addMouseListener(listener);
		ranking.addMouseListener(listener);
		about.addMouseListener(listener);
		quit.addMouseListener(listener);
		music.addMouseListener(listener);
		sound.addMouseListener(listener);
		// ��ק��
		HGSUtil.dragFrame(this);
	}

	private class HGSListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if (e.getSource() == classics) {
				// ��������
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
				// �ر�����
				if (HGSUtil.musicsw) {
					HGSUtil.close();
					HGSUtil.musicsw = false;
					HGSUtil.mute = true;
					music.setIcon(HGSUtil.nomusic);
				} else {// ������
					HGSUtil.musicsw = true;
					HGSUtil.mute = false;
					HGSUtil.close();
					HGSUtil.loop(0);
					music.setIcon(HGSUtil.music);
				}

			} else if (e.getSource() == sound) {
				// �ر���Ч
				if (HGSUtil.soundsw) {
					HGSUtil.soundsw = false;
					sound.setIcon(HGSUtil.nosound);
				} else {// ������
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