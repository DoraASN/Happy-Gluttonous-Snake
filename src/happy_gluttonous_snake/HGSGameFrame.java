package happy_gluttonous_snake;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HGSGameFrame extends JFrame {
	private HGSGamePanel gamepanel;

	public HGSGameFrame() {
		super();
		gamepanel = new HGSGamePanel(true, this);
		super.add(gamepanel);
		super.setSize(1100, 800);
		super.setLocationRelativeTo(null);
		super.setIconImage(HGSUtil.tubiao.getImage());
		super.setUndecorated(true);
		super.setVisible(true);
		super.addKeyListener(new HGSKeyListener());
		HGSListener hgslistener = new HGSListener();
		// 点击功能。
		this.addMouseListener(hgslistener);
		// 按钮跳动功能。
		this.addMouseMotionListener(hgslistener);
		HGSUtil.dragFrame(this);
	}

	public static class HGSGamePanel extends JPanel {
		// 游戏面板。20行，36列。
		private static ArrayList<String> body, wall;
		private static int x, y;
		// 0、1、2、3：上、下、左、右。
		private static int direction, directionlock;
		private static int speed;
		private static int time;
		private static int score;
		private int foodx;
		private int foody;
		private int foodtype;
		private int bombx;
		private int bomby;
		private static boolean k, eat, lose, start, quit, pause;
		private static int button, timer;
		private HGSGameFrame hgsGameFrame;
		private DecimalFormat speedform = new DecimalFormat("###.00");

		public HGSGamePanel(boolean quit, HGSGameFrame hgsGameFrame) {
			super();
			this.hgsGameFrame = hgsGameFrame;
			HGSGamePanel.quit = quit;
			body = new ArrayList<String>();
			wall = new ArrayList<String>();
			pause = false;
			start = true;
			k = false;
			eat = false;
			lose = false;
			speed = 400;
			x = 100;
			y = 41;
			wall();
			bomb();
			food();
			button = 0;
			direction = 3;
			directionlock = 2;
			body.add("40:41");
			body.add("70:41");
			body.add("100:41");

			// 蛇移动，计时开始、投食。
			move();
			time();
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			// 背景。
			g.drawImage(HGSUtil.gamebackground, 0, 0, 1100, 800, null);
			// 按钮。
			g.drawImage(HGSUtil.restart, 80, 650, 163, 50, null);
			g.drawImage(HGSUtil.tomainmenu.getImage(), 80, 710, 163, 50, null);
			g.drawImage(HGSUtil.pause, 850, 650, 163, 50, null);
			g.drawImage(HGSUtil.littlequit, 850, 710, 163, 50, null);
			if (button == 1) {
				g.drawImage(HGSUtil.restart, 80, 655, 163, 50, null);
			} else if (button == 2) {
				g.drawImage(HGSUtil.tomainmenu.getImage(), 80, 715, 163, 50, null);
			} else if (button == 3) {
				g.drawImage(HGSUtil.pause, 850, 655, 163, 50, null);
			} else if (button == 4) {
				g.drawImage(HGSUtil.littlequit, 850, 715, 163, 50, null);
			}
			// 时间、分数、速度。
			g.setFont(HGSUtil.scorefont);
			g.drawString("分数：     " + score + "分", 385, 675);
			g.drawString("时间：     " + time + "秒", 385, 718);
			if (HGSUtil.level == 0) {
				g.drawString("速度：     " + speedform.format(1000.0 / speed) + "格/秒", 385, 760);
			} else {
				g.drawString("速度：     " + speedform.format(1000.0 / (speed - HGSUtil.level * 75)) + "格/秒", 385, 760);
			}
			// 根据关卡画墙。
			if (HGSUtil.level > 0) {
				// 添加墙。
				wall();
				for (int i = 0; i < wall.size() - 1; i++) {
					int wallx, wally;
					String[] strs = wall.get(i).split(":");
					wallx = Integer.parseInt(strs[strs.length - 2]);
					wally = Integer.parseInt(strs[strs.length - 1]);
					g.drawImage(HGSUtil.wall, wallx, wally, 30, 30, null);
				}
			}
			g.drawImage(HGSUtil.food[foodtype], foodx, foody, 30, 30, null);
			// 画炸弹。
			if (HGSUtil.level > 1) {
				g.drawImage(HGSUtil.bomb, bombx, bomby, 30, 30, null);
			}
			String[] strs;
			if (start) {
				g.drawImage(HGSUtil.body, 40, 41, 30, 30, null);
				g.drawImage(HGSUtil.body, 70, 41, 30, 30, null);
				g.drawImage(HGSUtil.head[3], 100, 41, 30, 30, null);
			} else {
				// 画蛇头。
				// 画蛇身体。
				for (int i = 0; i < body.size() - 1; i++) {
					int bodyx, bodyy;
					strs = body.get(i).split(":");
					bodyx = Integer.parseInt(strs[strs.length - 2]);
					bodyy = Integer.parseInt(strs[strs.length - 1]);
					g.drawImage(HGSUtil.body, bodyx, bodyy, 30, 30, null);
					// 判断是否碰到自己身体。
					if ((x == bodyx && y == bodyy && !lose)) {
						HGSUtil.play(5);
						lose();
					}
					g.drawImage(HGSUtil.head[direction], x, y, 30, 30, null);
				}
			}
			if (pause) {
				g.drawImage(HGSUtil.gamepause, 550 - 793 / 2, 200, 793, 278, null);
			}
		}

		private void move() {
			new Thread() {
				public void run() {
					while (quit) {
						if (k) {
							if (direction == 0 && directionlock != 0) {
								y = (y - 30 + 600) % 600;
								directionlock = 1;
							} else if (direction == 1 && directionlock != 1) {
								y = (y + 30 + 600) % 600;
								directionlock = 0;
							} else if (direction == 2 && directionlock != 2) {
								x = (x - 30 + 1080) % 1080;
								directionlock = 3;
							} else if (direction == 3 && directionlock != 3) {
								x = (x + 30 + 1080) % 1080;
								directionlock = 2;
							}
							body.add(x + ":" + y);
							eat = false;
							eat();
						}
						try {
							if (HGSUtil.level == 0) {
//								if (speed > 100) {
//									speed = 400 - time * 3;
//									sleep(speed);
//								} else {
								sleep(speed);
//								}
							} else
								// 1：75，2：150，3；225。
								sleep(speed - HGSUtil.level * 75);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						repaint();
					}
				}
			}.start();
		}

		private void time() {
			new Thread() {
				public void run() {
					while (quit) {
						if (k) {
							// 计时，当5秒时增加炸弹。
							if (HGSUtil.level >= 2) {
								timer++;
								if (HGSUtil.level == 2 && timer == 5) {
									bomb();
									timer = 0;
								} else if (HGSUtil.level == 3 && timer == 3) {
									bomb();
									timer = 0;
								}
							}
							time++;
						}
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		}

		// 根据不同的关卡生成墙的集合。
		private void wall() {
			// 判断关卡。
			// 第三关添加迷宫。
			if (HGSUtil.level == 3) {
				if (HGSUtil.level == 3) {
					wall.add((2 * 30 + 10) + ":" + (11 + 2 * 30));
					wall.add((2 * 30 + 10) + ":" + (11 + 3 * 30));
					wall.add((3 * 30 + 10) + ":" + (11 + 2 * 30));
					wall.add((3 * 30 + 10) + ":" + (11 + 3 * 30));
					wall.add((2 * 30 + 10) + ":" + (11 + 16 * 30));
					wall.add((2 * 30 + 10) + ":" + (11 + 17 * 30));
					wall.add((3 * 30 + 10) + ":" + (11 + 16 * 30));
					wall.add((3 * 30 + 10) + ":" + (11 + 17 * 30));
					wall.add((32 * 30 + 10) + ":" + (11 + 2 * 30));
					wall.add((32 * 30 + 10) + ":" + (11 + 3 * 30));
					wall.add((33 * 30 + 10) + ":" + (11 + 2 * 30));
					wall.add((33 * 30 + 10) + ":" + (11 + 3 * 30));
					wall.add((32 * 30 + 10) + ":" + (11 + 16 * 30));
					wall.add((32 * 30 + 10) + ":" + (11 + 17 * 30));
					wall.add((33 * 30 + 10) + ":" + (11 + 16 * 30));
					wall.add((33 * 30 + 10) + ":" + (11 + 17 * 30));
					for (int i = 4; i < 16; i++) {
						for (int j = 9; j < 11; j++) {
							wall.add((i * 30 + 10) + ":" + (11 + j * 30));
						}
					}
					for (int i = 20; i < 32; i++) {
						for (int j = 9; j < 11; j++) {
							wall.add((i * 30 + 10) + ":" + (11 + j * 30));
						}
					}
					for (int i = 17; i < 19; i++) {
						for (int j = 3; j < 8; j++) {
							wall.add((i * 30 + 10) + ":" + (11 + j * 30));
						}
					}
					for (int i = 17; i < 19; i++) {
						for (int j = 12; j < 17; j++) {
							wall.add((i * 30 + 10) + ":" + (11 + j * 30));
						}
					}
					for (int i = 0; i < 5; i++) {
						wall.add(((11 + i) * 30 + 10) + ":" + (11 + (3 + i) * 30));
						wall.add(((20 + i) * 30 + 10) + ":" + (11 + (12 + i) * 30));
						wall.add(((11 + i) * 30 + 10) + ":" + (11 + (16 - i) * 30));
						wall.add(((20 + i) * 30 + 10) + ":" + (11 + (7 - i) * 30));
					}
				}
			} else if (HGSUtil.level > 0) {
				// 添加横向的墙。
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 36; j++) {
						wall.add((j * 30 + 10) + ":" + (11 + i * 570));
					}
				}
				// 添加竖向的墙。
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 20; j++) {
						wall.add((10 + i * 1050) + ":" + (j * 30 + 11));
					}
				}
			}
		}

		// 随机投食功能。
		private void food() {
			foodtype = (int) (Math.random() * HGSUtil.food.length);
			if (HGSUtil.level == 0) {
				do {
					foodx = 10 + (int) (Math.random() * 36) * 30;
					foody = 11 + (int) (Math.random() * 20) * 30;
				} while (wall.contains(foodx + ":" + foody) || body.contains(foodx + ":" + foody));
			} else if (HGSUtil.level > 0) {
				do {
					foodx = 40 + (int) (Math.random() * 34) * 30;
					foody = 41 + (int) (Math.random() * 18) * 30;
				} while (wall.contains(foodx + ":" + foody) || body.contains(foodx + ":" + foody));
			}
		}

		// 初始化炸弹。
		private void bomb() {
			do {
				bombx = 10 + (int) (Math.random() * 36) * 30;
				bomby = 11 + (int) (Math.random() * 20) * 30;
			} while ((foodx == bombx && foody == bomby) || body.contains(bombx + ":" + bomby)
					|| wall.contains(bombx + ":" + bomby));
		}

		// 判断是否吃到食物并控制身体是否增加长度，是否吃到炸弹，是否吃到墙。
		private void eat() {
			// 判断是否吃到食物。
			if (x == foodx && y == foody) {
				HGSUtil.play(foodtype + 9);
				score++;
				eat = true;
				food();
			}
			// 判断是否增加身体长度。
			if (!eat && !start) {
				body.remove(0);
			}
			// 判断是否吃到炸弹。
			if (HGSUtil.level > 1 && x == bombx && y == bomby && !lose) {
				HGSUtil.play(6);
				lose();
			}
			// 判断是否吃到墙。
			if (HGSUtil.level > 0) {
				for (int i = 0; i < wall.size() - 1; i++) {
					int wallx, wally;
					String[] strs = wall.get(i).split(":");
					wallx = Integer.parseInt(strs[strs.length - 2]);
					wally = Integer.parseInt(strs[strs.length - 1]);
					if (wallx == x && wally == y) {
						HGSUtil.play(7);
						lose();
						return;
					}
				}
			}
		}

		// 判断失败。
		private void lose() {
			k = false;
			lose = true;
			HGSUtil.close();
			HGSUtil.play(8, 8);
			new HGSLoseFrame(hgsGameFrame).setVisible(true);
			HGSUtil.updateRecord(time, score);
		}

		// 重新开始。
		public static void restart() {
			pause = false;
			direction = 3;
			directionlock = 2;
			timer = 0;
			time = 0;
			score = 0;
			eat = false;
			button = 0;
			speed = 400;
			x = 100;
			y = 41;
			direction = 3;
			wall.clear();
			body.clear();
			body.add("40:41");
			body.add("70:41");
			body.add("100:41");
			start = true;
			lose = false;
			k = false;
		}

		public static void setQuit(boolean Quit) {
			quit = Quit;
		}
	}

	// 鼠标监听器。
	public class HGSListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if (e.getX() >= 80 && e.getX() <= 243 && e.getY() >= 650 && e.getY() <= 700) {
				HGSUtil.close();
				HGSUtil.loop(HGSUtil.level + 1);
				HGSGamePanel.restart();
			} else if (e.getX() >= 80 && e.getX() <= 243 && e.getY() >= 710 && e.getY() <= 760) {
				HGSUtil.close();
				HGSUtil.loop(0);
				HGSGamePanel.setQuit(false);
				new HGSMainMenu();
				HGSGamePanel.restart();
				HGSGameFrame.this.dispose();
			} else if (e.getX() >= 850 && e.getX() <= 1013 && e.getY() >= 650 && e.getY() <= 700) {
				HGSGamePanel.k = false;
				if (HGSUtil.musicsw && !HGSUtil.mute) {
					HGSUtil.close();
					HGSUtil.musicsw = false;
				}
				if (!HGSGamePanel.lose) {
					HGSGamePanel.pause = true;
				}
			} else if (e.getX() >= 850 && e.getX() <= 1013 && e.getY() >= 710 && e.getY() <= 760) {
				System.exit(0);
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			super.mouseMoved(e);
			if (e.getX() >= 80 && e.getX() <= 243 && e.getY() >= 650 && e.getY() <= 700) {
				HGSGamePanel.button = 1;
			} else if (e.getX() >= 80 && e.getX() <= 243 && e.getY() >= 710 && e.getY() <= 760) {
				HGSGamePanel.button = 2;
			} else if (e.getX() >= 850 && e.getX() <= 1013 && e.getY() >= 650 && e.getY() <= 700) {
				HGSGamePanel.button = 3;
			} else if (e.getX() >= 850 && e.getX() <= 1013 && e.getY() >= 710 && e.getY() <= 760) {
				HGSGamePanel.button = 4;
			} else {
				HGSGamePanel.button = 0;
			}
		}
	}

	// 按键监听器。
	private class HGSKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			HGSGamePanel.start = false;
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if (HGSUtil.level == 0) {
//					 1：75，2：150，3；225。
					HGSGamePanel.speed = 50;
				} else if (HGSUtil.level == 1) {
					HGSGamePanel.speed = 115;
				} else if (HGSUtil.level == 2) {
					HGSGamePanel.speed = 180;
				} else if (HGSUtil.level == 3) {
					HGSGamePanel.speed = 245;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_UP && HGSGamePanel.directionlock != 0) {
				HGSGamePanel.direction = 0;
				if (!HGSGamePanel.lose) {
					HGSGamePanel.k = true;
					HGSGamePanel.pause = false;
				}
				if (!HGSUtil.musicsw && !HGSUtil.mute) {
					HGSUtil.musicsw = true;
					HGSUtil.loop(HGSUtil.level + 1);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN && HGSGamePanel.directionlock != 1) {
				HGSGamePanel.direction = 1;
				if (!HGSGamePanel.lose) {
					HGSGamePanel.k = true;
					HGSGamePanel.pause = false;
				}
				if (!HGSUtil.musicsw && !HGSUtil.mute) {
					HGSUtil.musicsw = true;
					HGSUtil.loop(HGSUtil.level + 1);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT && HGSGamePanel.directionlock != 2) {
				HGSGamePanel.direction = 2;
				if (!HGSGamePanel.lose) {
					HGSGamePanel.k = true;
					HGSGamePanel.pause = false;
				}
				if (!HGSUtil.musicsw && !HGSUtil.mute) {
					HGSUtil.musicsw = true;
					HGSUtil.loop(HGSUtil.level + 1);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && HGSGamePanel.directionlock != 3) {
				HGSGamePanel.direction = 3;
				if (!HGSGamePanel.lose) {
					HGSGamePanel.k = true;
					HGSGamePanel.pause = false;
				}
				if (!HGSUtil.musicsw && !HGSUtil.mute) {
					HGSUtil.musicsw = true;
					HGSUtil.loop(HGSUtil.level + 1);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_P) {
				if (!HGSGamePanel.lose) {
					HGSGamePanel.pause = true;
				}
				HGSGamePanel.k = false;
				if (HGSUtil.musicsw && !HGSUtil.mute) {
					HGSUtil.close();
					HGSUtil.musicsw = false;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_E) {
				HGSGamePanel.eat = false;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			super.keyReleased(e);
//			if (e.getKeyCode() == KeyEvent.VK_SPACE && HGSUtil.level == 0) {
//				if (HGSGamePanel.time > 100) {
//					HGSGamePanel.speed = 100;
//				} else {
//					HGSGamePanel.speed = 400 - HGSGamePanel.time * 3;
//				}
			/* } else */if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				HGSGamePanel.speed = 400;
			}
		}
	}
}