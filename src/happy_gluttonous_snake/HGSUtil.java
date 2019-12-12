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
	// 音乐参数
	public static boolean musicsw = true;
	public static boolean soundsw = true;
	public static boolean mute = false;
	private static Clip clip1 = null;
	private static Clip clip2 = null;
	private static Clip clip3 = null;
	private static AudioInputStream audioIn = null;
	// 关卡数。
	// 排行榜分类：0.经典模式；8.无炸弹，有围墙；16.随机出现炸弹，有围墙；24.随机出现炸弹，有围墙和迷宫。
	public static int mode;
	// 冒险模式的三个关卡分别表示为：1.无炸弹，有围墙；2.随机出现炸弹，有围墙；3.随机出现炸弹，有围墙和迷宫。
	public static int level;
	// 字体。
	public static Font scorefont = new Font("幼圆", Font.BOLD, 30);
	public static Font listfont = new Font("华文行楷", 0, 25);
	public static Font rankingtitlefont = new Font("楷体", Font.BOLD, 18);
	public static Font rankingfont = new Font("楷体", 0, 18);
	public static Font abouttitlemenufont = new Font("幼圆", Font.BOLD, 35);
	public static Font aboutmenufont = new Font("幼圆", Font.BOLD, 25);
	// 图片。
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

	// 构造按钮的方法。
	public static JButton button(ImageIcon img, int x, int y, int width, int height, boolean rf) {
		// 生成一个原生的JButton对象。
		JButton button = new JButton(img);
		// 按钮对象显示的图片为参数中传递的值。
		button.setBounds(x, y, width, height);
		// 按钮背景色设置为不显示，即为透明背景色。
		button.setContentAreaFilled(rf);
		// 不绘制按钮的边框。
		button.setBorderPainted(rf);
		// 不绘制按钮在焦点状态下的效果。
		button.setFocusable(false);
		return button;
	}

	public static void dragFrame(final JFrame jf) {

		// 监听鼠标事件，重写按下事件的方法。
		jf.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				// 记录鼠标按下的位置。
				xp = e.getX();
				yp = e.getY();
			}
		});

		// 监听鼠标移动事件，重写拖拽的方法。
		jf.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				// 记录窗体的初始位置。
				x = jf.getX();
				y = jf.getY();
				// 记录鼠标移动后的即时位置。
				int x1 = e.getX();
				int y1 = e.getY();
				jf.setLocation(x + (x1 - xp), y + (y1 - yp));
			}
		});
	}

	// 获取排行榜文件内容并输出。
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

	// 更新文件中的信息。
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

	// 更新记录信息并判断是否是新纪录。
	public static void updateRecord(int time, int score) {
		// 先更新record数组。
		// 根据level从record中截取10个元素
		// 获取这10个元素的时间信息
		// 用当前的time值和这十个时间比较：从后往前比较如果比当前数据小，就替换当前位置。
		String[] record = getRecord();
		int i;
		int scores, times;
		String str;
		String[] strs;
		for (i = 0; i < 8; i++) {
			// 没有获取到字符串或者获取到“null”字符串，则停止循环。
			if (record[mode + i].equals("null") || record[mode + i] == null || record[mode + i].equals("")) {
				if (i == 0) {
					str = JOptionPane.showInputDialog("新纪录！请输入您的名称：");
					if (str == null || str.equals("")) {
						record[mode + i] = "无名氏" + "   " + score + "   " + time;
					} else if (str.length() == 2) {
						record[mode + i] = str + "     " + score + "   " + time;
					} else if (str.length() == 1) {
						record[mode + i] = str + "       " + score + "   " + time;
					} else {
						record[mode + i] = str + "   " + score + "   " + time;
					}
				} else {
					str = JOptionPane.showInputDialog("第" + (i + 1) + "名！请输入您的名称：");
					if (str == null || str.equals("")) {
						record[mode + i] = "无名氏" + "   " + score + "   " + time;
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
			// 拆分字符串为字符数组。
			strs = record[mode + i].split(" +");
			scores = Integer.parseInt(strs[strs.length - 2]);
			times = Integer.parseInt(strs[strs.length - 1]);
			if (score > scores || (score == scores && time < times)) {
				Move(i, record);
				if (i == 0) {
					str = JOptionPane.showInputDialog("新纪录！请输入您的名称：");
					if (str == null || str.equals("")) {
						record[mode + i] = "无名氏" + "   " + score + "   " + time;
					} else if (str.length() == 2) {
						record[mode + i] = str + "     " + score + "   " + time;
					} else if (str.length() == 1) {
						record[mode + i] = str + "       " + score + "   " + time;
					} else {
						record[mode + i] = str + "   " + score + "   " + time;
					}
				} else {
					str = JOptionPane.showInputDialog("第" + (i + 1) + "名！请输入您的名称：");
					if (str == null || str.equals("")) {
						record[mode + i] = "无名氏" + "   " + score + "   " + time;
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
					str = JOptionPane.showInputDialog("第" + (i + 1) + "名！请输入您的名称：");
					if (str == null || str.equals("")) {
						record[mode + i] = "无名氏" + "   " + score + "   " + time;
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

	// 将当前的位次上的信息以及后面的信息向后移动一位。
	private static void Move(int i, String[] record) {
		for (int x = 6; x > i; x--) {
			if (record[mode + x - 1].equals("null") || record[mode + x - 1] == null || record[mode + x - 1].equals(""))
				continue;
			record[mode + x + 1] = record[mode + x];
			record[mode + x] = record[mode + x - 1];
		}
		HGSUtil.setRecord(record);
	}

	// 播放音乐。
	public static void play(int musictype) {
		try {
			// 1,音频输入流加载数据
			audioIn = AudioSystem.getAudioInputStream(musicfile[musictype]);
			// 2、数据行
			clip2 = AudioSystem.getClip();
			// 3、开启数据行，接入输入流
			clip2.open(audioIn);
			// 4、开始播放声音---以后台线程的方式播放
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
			// 1,音频输入流加载数据
			audioIn = AudioSystem.getAudioInputStream(musicfile[musictype]);
			// 2、数据行
			clip3 = AudioSystem.getClip();
			// 3、开启数据行，接入输入流
			clip3.open(audioIn);
			// 4、开始播放声音---以后台线程的方式播放
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
			// 1,音频输入流加载数据
			audioIn = AudioSystem.getAudioInputStream(musicfile[musictype]);
			// 2、数据行
			clip1 = AudioSystem.getClip();
			// 3、开启数据行，接入输入流
			clip1.open(audioIn);
			// 4、循环播放声音---以后台线程的方式播放
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