package happy_gluttonous_snake;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class HGSLoseFrame extends JFrame {
	private JButton but1, but2, but3, but4;
	private JLabel background;
	private HGSListener listener;
	private HGSGameFrame hgsGameFrame;

	public HGSLoseFrame(HGSGameFrame hgsGameFrame) {
		super();
		this.hgsGameFrame = hgsGameFrame;
		super.setSize(492, 408);
		super.setLocationRelativeTo(null);
		background = new JLabel(HGSUtil.lose);
		but1 = HGSUtil.button(HGSUtil.smallrestart, 31, 246, 202, 56, false);
		but2 = HGSUtil.button(HGSUtil.smalltomainmenu, 31, 316, 202, 56, false);
		but3 = HGSUtil.button(HGSUtil.smallranking, 252, 246, 202, 56, false);
		but4 = HGSUtil.button(HGSUtil.smallquit, 252, 316, 202, 56, false);
		background.add(but1);
		background.add(but2);
		background.add(but3);
		background.add(but4);
		super.setIconImage(HGSUtil.tubiao.getImage());
		super.add(background);
		super.setResizable(false);
		super.setUndecorated(true);
		// 添加监听器。
		listener = new HGSListener();
		but1.addMouseListener(listener);
		but2.addMouseListener(listener);
		but3.addMouseListener(listener);
		but4.addMouseListener(listener);
		// 增加拖拽功能。
		HGSUtil.dragFrame(this);
	}

	private class HGSListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if (e.getSource() == but1) {
				HGSUtil.close();
				HGSUtil.loop(HGSUtil.level + 1);
				HGSGameFrame.HGSGamePanel.restart();
				HGSLoseFrame.this.dispose();
			} else if (e.getSource() == but2) {
				new HGSMainMenu();
				HGSUtil.close();
				HGSUtil.loop(0);
				HGSGameFrame.HGSGamePanel.setQuit(false);
				HGSGameFrame.HGSGamePanel.restart();
				HGSLoseFrame.this.dispose();
				hgsGameFrame.dispose();
			} else if (e.getSource() == but3) {
				new HGSList();
			} else if (e.getSource() == but4) {
				System.exit(0);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			if (e.getSource() == but1) {
				but1.setBounds(32, 247, 202, 56);
			} else if (e.getSource() == but2) {
				but2.setBounds(32, 317, 202, 56);
			} else if (e.getSource() == but3) {
				but3.setBounds(253, 247, 202, 56);
			} else if (e.getSource() == but4) {
				but4.setBounds(253, 317, 202, 56);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			if (e.getSource() == but1) {
				but1.setBounds(31, 246, 202, 56);
			} else if (e.getSource() == but2) {
				but2.setBounds(31, 316, 202, 56);
			} else if (e.getSource() == but3) {
				but3.setBounds(252, 246, 202, 56);
			} else if (e.getSource() == but4) {
				but4.setBounds(252, 316, 202, 56);
			}
		}
	}
}