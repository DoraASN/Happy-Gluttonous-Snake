package happy_gluttonous_snake;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class HGSList extends JFrame {
	private JLabel jl = new JLabel();
	JLabel listbackground;
	private JButton confirm;

	public HGSList() {
		super();
		listbackground = new JLabel(HGSUtil.listbackground);
		jl = new JLabel();
		jl.setText("————排行榜————");
		jl.setFont(HGSUtil.listfont);
		jl.setBounds(25, 20, 500, 35);
		listbackground.add(jl);
		jl = new JLabel();
		jl.setText("——————————————————————");
		jl.setBounds(25, 1 * 16 + 55, 300, 4);
		listbackground.add(jl);
		jl = new JLabel();
		jl.setText("模式 排名  姓名   分数  时间");
		jl.setFont(HGSUtil.rankingtitlefont);
		jl.setBounds(25, 2 * 16 + 45, 300, 22);
		listbackground.add(jl);
		jl = new JLabel();
		jl.setText("——————————————————————");
		jl.setBounds(25, 3 * 16 + 50, 300, 4);
		listbackground.add(jl);
		String[] strs = new String[32];
		strs = HGSUtil.getRecord();
		for (int i = 0; i < 8; i++) {
			jl = new JLabel();
			jl.setText("经典   " + (i + 1) + "  " + strs[i]);
			jl.setFont(HGSUtil.rankingfont);
			jl.setBounds(25, (i + 4) * 16 + 40, 300, 22);
			listbackground.add(jl);
		}
		jl = new JLabel();
		jl.setText("——————————————————————");
		jl.setBounds(25, 11 * 16 + 60, 300, 4);
		listbackground.add(jl);
		for (int i = 0; i < 8; i++) {
			jl = new JLabel();
			jl.setText("冒险1  " + (i + 1) + "  " + strs[8 + i]);
			jl.setFont(HGSUtil.rankingfont);
			jl.setBounds(25, (i + 11) * 16 + 65, 300, 22);
			listbackground.add(jl);
		}
		jl = new JLabel();
		jl.setText("——————————————————————");
		jl.setBounds(25, 19 * 16 + 70, 300, 4);
		listbackground.add(jl);
		for (int i = 0; i < 8; i++) {
			jl = new JLabel();
			jl.setText("冒险2  " + (i + 1) + "  " + strs[16 + i]);
			jl.setFont(HGSUtil.rankingfont);
			jl.setBounds(25, (i + 19) * 16 + 75, 300, 22);
			listbackground.add(jl);
		}
		jl = new JLabel();
		jl.setText("——————————————————————");
		jl.setBounds(25, 27 * 16 + 80, 300, 4);
		listbackground.add(jl);
		for (int i = 0; i < 8; i++) {
			jl = new JLabel();
			jl.setText("冒险3  " + (i + 1) + "  " + strs[24 + i]);
			jl.setFont(HGSUtil.rankingfont);
			jl.setBounds(25, (i + 27) * 16 + 85, 300, 22);
			listbackground.add(jl);
		}
		jl = new JLabel();
		jl.setText("——————————————————————");
		jl.setBounds(25, 36 * 16 + 75, 300, 4);
		listbackground.add(jl);
		jl = new JLabel();
		listbackground.add(jl);
		super.add(listbackground);
		super.setUndecorated(true);
		super.setSize(328, 757);
		super.setLocationRelativeTo(null);
		super.setIconImage(HGSUtil.tubiao.getImage());
		super.setVisible(true);
		confirm = HGSUtil.button(HGSUtil.confirm, 328 / 2 - 90, 690, 180, 52, false);
		listbackground.add(confirm);
		super.setResizable(false);
		confirm.addMouseListener(new HGSListener());
		// 拖拽。
		HGSUtil.dragFrame(this);
	}

	private class HGSListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if (e.getSource() == confirm) {
				HGSList.this.dispose();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			if (e.getSource() == confirm) {
				confirm.setBounds(328 / 2 - 90, 693, 180, 52);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			if (e.getSource() == confirm) {
				confirm.setBounds(328 / 2 - 90, 690, 180, 52);
			}
		}
	}
}