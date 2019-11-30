package happy_gluttonous_snake;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HGSUtil {
	private static int x, y;
	private static int xp, yp;
	// ���ֲ���
	public static boolean musicsw = true;
	public static boolean soundsw = true;
	public static boolean mute = false;
	private static Clip clip1 = null;
	private static Clip clip2 = null;
	private static Clip clip3 = null;
	private static AudioInputStream audioIn = null;
	// �ؿ�����
	// ���а���ࣺ0.����ģʽ��8.��ը������Χǽ��16.�������ը������Χǽ��24.�������ը������Χǽ���Թ���
	public static int mode;
	// ð��ģʽ�������ؿ��ֱ��ʾΪ��1.��ը������Χǽ��2.�������ը������Χǽ��3.�������ը������Χǽ���Թ���
	public static int level;
	// ���塣
	public static Font scorefont = new Font("��Բ", Font.BOLD, 30);
	public static Font listfont = new Font("�����п�", 0, 25);
	public static Font rankingtitlefont = new Font("����", Font.BOLD, 18);
	public static Font rankingfont = new Font("����", 0, 18);
	public static Font abouttitlemenufont = new Font("��Բ", Font.BOLD, 35);
	public static Font aboutmenufont = new Font("��Բ", Font.BOLD, 25);
	// ͼƬ��
	public static ImageIcon menubackground = new ImageIcon("resource/image/menubackground.png");
	public static ImageIcon classics = new ImageIcon("resource/image/classics.png");
	public static ImageIcon adventure = new ImageIcon("resource/image/adventure.png");
	public static ImageIcon ranking = new ImageIcon("resource/image/ranking.png");
	public static ImageIcon about = new ImageIcon("resource/image/about.png");
	public static ImageIcon adventurebackground = new ImageIcon("resource/image/adventurebackground.png");
	public static ImageIcon firstpass = new ImageIcon("resource/image/firstpass.png");
	public static ImageIcon secondpass = new ImageIcon("resource/image/secondpass.png");
	public static ImageIcon thirdpass = new ImageIcon("resource/image/thirdpass.png");
	public static ImageIcon quit = new ImageIcon("resource/image/quit.png");
	public static ImageIcon music = new ImageIcon("resource/image/music.png");
	public static ImageIcon sound = new ImageIcon("resource/image/sound.png");
	public static ImageIcon nomusic = new ImageIcon("resource/image/nomusic.png");
	public static ImageIcon nosound = new ImageIcon("resource/image/nosound.png");
	public static ImageIcon tubiao = new ImageIcon("resource/image/tubiao.png");
	public static ImageIcon blankbutton = new ImageIcon("resource/image/blankbutton.png");
	public static ImageIcon listbackground = new ImageIcon("resource/image/listbackground.png");
	public static ImageIcon confirm = new ImageIcon("resource/image/confirm.png");
	public static ImageIcon aboutconfirm = new ImageIcon("resource/image/aboutconfirm.png");
	public static ImageIcon aboutbackground = new ImageIcon("resource/image/aboutbackground.png");
	public static ImageIcon name = new ImageIcon("resource/image/name.png");
	public static ImageIcon tomainmenu = new ImageIcon("resource/image/tomainmenu.png");
	public static ImageIcon lose = new ImageIcon("resource/image/lose.png");
	public static ImageIcon smallrestart = new ImageIcon("resource/image/smallrestart.png");
	public static ImageIcon smalltomainmenu = new ImageIcon("resource/image/smalltomainmenu.png");
	public static ImageIcon smallranking = new ImageIcon("resource/image/smallranking.png");
	public static ImageIcon smallquit = new ImageIcon("resource/image/smallquit.png");
	public static Image gamebackground = new ImageIcon("resource/image/gamebackground.png").getImage();
	public static Image restart = new ImageIcon("resource/image/restart.png").getImage();
	public static Image pause = new ImageIcon("resource/image/pause.png").getImage();
	public static Image gamepause = new ImageIcon("resource/image/gamepause.png").getImage();
	public static Image littlequit = new ImageIcon("resource/image/littlequit.png").getImage();
	public static Image next = new ImageIcon("resource/image/next.png").getImage();
	public static Image body = new ImageIcon("resource/image/body.png").getImage();
	public static Image bomb = new ImageIcon("resource/image/bomb.png").getImage();
	public static Image[] food = { new ImageIcon("resource/image/food1.png").getImage(),
			new ImageIcon("resource/image/food2.png").getImage(), new ImageIcon("resource/image/food3.png").getImage(),
			new ImageIcon("resource/image/food4.png").getImage(),
			new ImageIcon("resource/image/food5.png").getImage() };
	public static Image[] head = { new ImageIcon("resource/image/headup.png").getImage(),
			new ImageIcon("resource/image/headdown.png").getImage(),
			new ImageIcon("resource/image/headleft.png").getImage(),
			new ImageIcon("resource/image/headright.png").getImage() };
	public static Image wall = new ImageIcon("resource/image/wall.png").getImage();

	public static File[] musicfile = { new File("resource/music/mainmenubackgroundmusic.wav"),
			new File("resource/music/gamebackgroundmusic1.wav"), new File("resource/music/gamebackgroundmusic2.wav"),
			new File("resource/music/gamebackgroundmusic3.wav"), new File("resource/music/gamebackgroundmusic4.wav"),
			new File("resource/music/tobody.wav"), new File("resource/music/tobomb.wav"),
			new File("resource/music/towall.wav"), new File("resource/music/lose.wav"),
			new File("resource/music/eatsound1.wav"), new File("resource/music/eatsound2.wav"),
			new File("resource/music/eatsound3.wav"), new File("resource/music/eatsound4.wav"),
			new File("resource/music/eatsound5.wav") };

	// ���찴ť�ķ�����
	public static JButton button(ImageIcon img, int x, int y, int width, int height, boolean rf) {
		// ����һ��ԭ����JButton����
		JButton button = new JButton(img);
		// ��ť������ʾ��ͼƬΪ�����д��ݵ�ֵ��
		button.setBounds(x, y, width, height);
		// ��ť����ɫ����Ϊ����ʾ����Ϊ͸������ɫ��
		button.setContentAreaFilled(rf);
		// �����ư�ť�ı߿�
		button.setBorderPainted(rf);
		// �����ư�ť�ڽ���״̬�µ�Ч����
		button.setFocusable(false);
		return button;
	}

	public static void dragFrame(final JFrame jf) {

		// ��������¼�����д�����¼��ķ�����
		jf.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				// ��¼��갴�µ�λ�á�
				xp = e.getX();
				yp = e.getY();
			}
		});

		// ��������ƶ��¼�����д��ק�ķ�����
		jf.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				// ��¼����ĳ�ʼλ�á�
				x = jf.getX();
				y = jf.getY();
				// ��¼����ƶ���ļ�ʱλ�á�
				int x1 = e.getX();
				int y1 = e.getY();
				jf.setLocation(x + (x1 - xp), y + (y1 - yp));
			}
		});
	}

	// ��ȡ���а��ļ����ݲ������
	public static String[] getRecord() {
		BufferedReader br = null;
		String[] strs = new String[32];
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("resource/record.dat")), "GBK"));
			for (int i = 0; i < strs.length; i++) {
				strs[i] = br.readLine();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return strs;
	}

	// �����ļ��е���Ϣ��
	public static void setRecord(String[] strs) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("resource/record.dat"), "GBK");
			for (int i = 0; i < strs.length; i++) {
				pw.println(strs[i]);
			}
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
				pw = null;
			}
		}
	}

	// ���¼�¼��Ϣ���ж��Ƿ����¼�¼��
	public static void updateRecord(int time, int score) {
		// �ȸ���record���顣
		// ����level��record�н�ȡ10��Ԫ��
		// ��ȡ��10��Ԫ�ص�ʱ����Ϣ
		// �õ�ǰ��timeֵ����ʮ��ʱ��Ƚϣ��Ӻ���ǰ�Ƚ�����ȵ�ǰ����С�����滻��ǰλ�á�
		String[] record = getRecord();
		int i;
		int scores, times;
		String str;
		String[] strs;
		for (i = 0; i < 8; i++) {
			// û�л�ȡ���ַ������߻�ȡ����null���ַ�������ֹͣѭ����
			if (record[mode + i].equals("null") || record[mode + i] == null || record[mode + i].equals("")) {
				if (i == 0) {
					str = JOptionPane.showInputDialog("�¼�¼���������������ƣ�");
					if (str == null || str.equals("")) {
						record[mode + i] = "������" + "   " + score + "   " + time;
					} else if (str.length() == 2) {
						record[mode + i] = str + "     " + score + "   " + time;
					} else if (str.length() == 1) {
						record[mode + i] = str + "       " + score + "   " + time;
					} else {
						record[mode + i] = str + "   " + score + "   " + time;
					}
				} else {
					str = JOptionPane.showInputDialog("��" + (i + 1) + "�����������������ƣ�");
					if (str == null || str.equals("")) {
						record[mode + i] = "������" + "   " + score + "   " + time;
					} else if (str.length() == 2) {
						record[mode + i] = str + "     " + score + "   " + time;
					} else if (str.length() == 1) {
						record[mode + i] = str + "       " + score + "   " + time;
					} else {
						record[mode + i] = str + "   " + score + "   " + time;
					}
				}
				break;
			}
			// ����ַ���Ϊ�ַ����顣
			strs = record[mode + i].split(" +");
			scores = Integer.parseInt(strs[strs.length - 2]);
			times = Integer.parseInt(strs[strs.length - 1]);
			if (score > scores || (score == scores && time < times)) {
				Move(i, record);
				if (i == 0) {
					str = JOptionPane.showInputDialog("�¼�¼���������������ƣ�");
					if (str == null || str.equals("")) {
						record[mode + i] = "������" + "   " + score + "   " + time;
					} else if (str.length() == 2) {
						record[mode + i] = str + "     " + score + "   " + time;
					} else if (str.length() == 1) {
						record[mode + i] = str + "       " + score + "   " + time;
					} else {
						record[mode + i] = str + "   " + score + "   " + time;
					}
				} else {
					str = JOptionPane.showInputDialog("��" + (i + 1) + "�����������������ƣ�");
					if (str == null || str.equals("")) {
						record[mode + i] = "������" + "   " + score + "   " + time;
					} else if (str.length() == 2) {
						record[mode + i] = str + "     " + score + "   " + time;
					} else if (str.length() == 1) {
						record[mode + i] = str + "       " + score + "   " + time;
					} else {
						record[mode + i] = str + "   " + score + "   " + time;
					}
				}
				break;
			} else if (score == scores && time == times && i + 1 < 9 && !record[mode + i + 1].equals("null")
					&& record[mode + i + 1] != null && !record[mode + i + 1].equals("")) {
				strs = record[mode + i + 1].split("   ");
				scores = Integer.parseInt(strs[strs.length - 1]);
				if (score != scores) {
					Move(i + 1, record);
					str = JOptionPane.showInputDialog("��" + (i + 1) + "�����������������ƣ�");
					if (str == null || str.equals("")) {
						record[mode + i] = "������" + "   " + score + "   " + time;
					} else if (str.length() == 2) {
						record[mode + i] = str + "     " + score + "   " + time;
					} else if (str.length() == 1) {
						record[mode + i] = str + "       " + score + "   " + time;
					} else {
						record[mode + i] = str + "   " + score + "   " + time;
					}
					break;
				}
			}
		}
		HGSUtil.setRecord(record);
	}

	// ����ǰ��λ���ϵ���Ϣ�Լ��������Ϣ����ƶ�һλ��
	private static void Move(int i, String[] record) {
		for (int x = 6; x > i; x--) {
			if (record[mode + x - 1].equals("null") || record[mode + x - 1] == null || record[mode + x - 1].equals(""))
				continue;
			record[mode + x + 1] = record[mode + x];
			record[mode + x] = record[mode + x - 1];
		}
		HGSUtil.setRecord(record);
	}

	// �������֡�
	public static void play(int musictype) {
		try {
			// 1,��Ƶ��������������
			audioIn = AudioSystem.getAudioInputStream(musicfile[musictype]);
			// 2��������
			clip2 = AudioSystem.getClip();
			// 3�����������У�����������
			clip2.open(audioIn);
			// 4����ʼ��������---�Ժ�̨�̵߳ķ�ʽ����
			if (soundsw) {
				clip2.start();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void play(int musictype, int musictype1) {
		try {
			// 1,��Ƶ��������������
			audioIn = AudioSystem.getAudioInputStream(musicfile[musictype]);
			// 2��������
			clip3 = AudioSystem.getClip();
			// 3�����������У�����������
			clip3.open(audioIn);
			// 4����ʼ��������---�Ժ�̨�̵߳ķ�ʽ����
			if (soundsw) {
				clip3.start();
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void loop(int musictype) {
		try {
			// 1,��Ƶ��������������
			audioIn = AudioSystem.getAudioInputStream(musicfile[musictype]);
			// 2��������
			clip1 = AudioSystem.getClip();
			// 3�����������У�����������
			clip1.open(audioIn);
			// 4��ѭ����������---�Ժ�̨�̵߳ķ�ʽ����
			if (musicsw) {
				clip1.loop(Clip.LOOP_CONTINUOUSLY);
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void close() {
		clip1.close();
	}

//	private Image image(String str) {
//		return new ImageIcon(new Test().getClass().getResource(str)).getImage();
//	}
//
//	private ImageIcon imageicon(String str) {
//		return new ImageIcon(this.getClass().getResource(str));
//	}
//
//	private File file(String str) {
//		return new File((this.getClass().getResource(str)).getPath());
//	}
}