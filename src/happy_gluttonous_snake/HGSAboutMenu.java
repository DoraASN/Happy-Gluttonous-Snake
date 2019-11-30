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

		jl = new JLabel("<html>��Ϸ����</html>");
		jl.setFont(HGSUtil.abouttitlemenufont);
		jl.setBounds(671 / 2 - 470 / 2, 60, 470, 37);
		bg.add(jl);
		jl = new JLabel(
				"<html>����ϷԴ�ھ���С��Ϸ��̰���ߡ������ڴ˻����Ͻ�����һ���Ĺ���ı䣬ʹ���淨���ӷḻ���������£�<br><U>����ģʽ</U>�л����ٶȻ�����ʱ�������Խ��Խ�죬�����ٶȲ��仯��<br><U>ð��ģʽ</U>�еĻ����ٶȺͼ����ٶ�ÿ���ؿ���ͬ�����йؿ�һ�������ؿ�����졣<br></html>");
		jl.setFont(HGSUtil.aboutmenufont);
		jl.setBounds(671 / 2 - 470 / 2, 110, 470, 195);
		bg.add(jl);

		jl = new JLabel("<html>��Ϸ˵��</html>");
		jl.setFont(HGSUtil.abouttitlemenufont);
		jl.setBounds(671 / 2 - 470 / 2, 330, 470, 37);
		bg.add(jl);
		jl = new JLabel("<html><U>��Ϸ����</U>��������������ƶ��ķ��򣬿ո�����٣�P����ͣ����Ϸ����ġ���ͣ��Ϸ��ͬ��������ͣ��Ϸ����</html>");
		jl.setFont(HGSUtil.aboutmenufont);
		jl.setBounds(671 / 2 - 470 / 2, 380, 460, 80);
		bg.add(jl);
		jl = new JLabel("<html><U>��Ϸ����</U>�����������½ǿɷֱ������Ϸ�������ֺ���Ч�Ŀ��ز�����</html>");
		jl.setFont(HGSUtil.aboutmenufont);
		jl.setBounds(671 / 2 - 470 / 2, 470, 460, 55);
		bg.add(jl);

		jl = new JLabel("<html>������Ա</html>");
		jl.setFont(HGSUtil.abouttitlemenufont);
		jl.setBounds(671 / 2 - 470 / 2, 555, 470, 37);
		bg.add(jl);
		jl = new JLabel(HGSUtil.name);
		jl.setFont(HGSUtil.aboutmenufont);
		jl.setBounds(671 / 2 - 470 / 2, 610, 454, 123);
		bg.add(jl);

		jl = new JLabel("2019 @׽��С��");
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
		// ��������
		HGSListener listener = new HGSListener();
		jb.addMouseListener(listener);
		HGSUtil.dragFrame(this);
	}

	// ����������
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