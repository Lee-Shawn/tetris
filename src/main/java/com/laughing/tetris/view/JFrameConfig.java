package com.laughing.tetris.view;

import com.laughing.tetris.controller.GameControl;
import com.laughing.tetris.ui.Img;
import com.laughing.tetris.util.FrameUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;


public class JFrameConfig extends JFrame {
	
	private final JButton btnOk = new JButton(" 确定");
	
	private final JButton btnCancel = new JButton("取消");
	
	private final JButton btnUser = new JButton("应用");
	
	private final TextCtrl[] keyText = new TextCtrl[8];
	
	private final JLabel errorMsg = new JLabel();
	
	private JList<String> skinList = null;
	
	private JPanel skinView = null;
	
	private final DefaultListModel<String> skinData = new DefaultListModel<>();
	
	private final GameControl gameControl;
	
	private static final Image IMG_PSP = new ImageIcon("src/main/resources/data/psp.jpg").getImage();
	
	private Image[] skinViewList = null;
	
	private static final String[] METHOD_NAMES = {
		"keyRight","keyUp","keyLeft","keyDown",
		"keyFunLeft","keyFunUp","keyFunRight","keyFunDown"
	};
	
	private final static String PATH = "src/main/resources/data/control.dat";
	
	public JFrameConfig(GameControl gameControl) {
		// 获得游戏控制器对象
		this.gameControl = gameControl;
		// 设置布局管理器为“边界布局”
		this.setLayout(new BorderLayout());
		// 初始化按键输入框
		this.initKeyText();
		// 添加主面板
		this.add(this.createMainPanel(), BorderLayout.CENTER);
		// 添加按钮面板
		this.add(this.createButtonPanel(), BorderLayout.SOUTH);
		// 设置标题
		this.setTitle("设置");
		// 设置窗口大小不能改变
		this.setResizable(false);
		// 设置窗口大小
		this.setSize(700, 500);
		// 设置窗口居中
		FrameUtil.setFrameCenter(this);
	}

	/**
	 * 初始化按键输入框
	 */
	private void initKeyText() {
		int x = 0;
		int y = 20;
		int w = 64;
		int h = 20;
		for (int i = 0; i < 4; i++) {
			keyText[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
			y += 32;
		}
		x = 574;
		y = 20;
		for (int i = 4; i < 8; i++) {
			keyText[i] = new TextCtrl(x, y, w, h, METHOD_NAMES[i]);
			y += 32;
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH));
			HashMap<Integer, String> cfgSet = (HashMap<Integer, String>)ois.readObject();
			ois.close();
			Set<Entry<Integer, String>> entrySet = cfgSet.entrySet();
			for (Entry<Integer, String> e: entrySet) {
				for (TextCtrl tc : keyText) {
					if (tc.getMethodName().equals(e.getValue())) {
						tc.setKeyCode(e.getKey());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建按钮面板
	 */
	private JPanel createButtonPanel() {
		// 创建按钮面板流式布局
		JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// 给确定按钮添加事件监听
		this.btnOk.addActionListener(e -> {
			if(writeConfig()) {
				setVisible(false);
				gameControl.setOver();
			}
		});
		// 设置错误信息前景色
		this.errorMsg.setForeground(Color.RED);
		// 增加错误信息到面板
		jp.add(errorMsg);
		// 增加确定按钮到面板
		jp.add(this.btnOk);
		// 给取消按钮添加事件监听
		this.btnCancel.addActionListener(e -> {
			setVisible(false);
			gameControl.setOver();
		});
		// 增加取消按钮到面板
		jp.add(this.btnCancel);
		// 给应用按钮添加事件监听
		this.btnUser.addActionListener(e -> {
			writeConfig();
			gameControl.repaint();
		});
		// 增加应用按钮到面板
		jp.add(this.btnUser);
		return jp;
	}

	/**
	 * 创建主面板(选项卡面板)
	 */
	private JTabbedPane createMainPanel() {
		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab("控制设置", this.createControlPanel());
		jtp.addTab("皮肤设置", this.createSkinPanel());
		return jtp;
	}
	
	/**
	 * 玩家皮肤面板
	 */
	private Component createSkinPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		File dir = new File(Img.GRAPHICS_PATH);
		File[] files = dir.listFiles();
		if (files == null) {
			throw new RuntimeException("文件不存在！");
		}
		this.skinViewList = new Image[files.length];
		for (int i = 0; i < files.length; i++) {
			// 增加选项
			this.skinData.addElement(files[i].getName());
			// 增加预览图
			this.skinViewList[i] =new ImageIcon(files[i].getPath() + "\\view.jpg").getImage(); 
		}
		// 添加列表数据到列表
		this.skinList = new JList<>(this.skinData);
		// 默认选择第一个
		this.skinList.setSelectedIndex(0);
		this.skinList.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				repaint();
			}
		});
		this.skinView = new JPanel() {
			public void paintComponent(Graphics g) {
				Image showImg = skinViewList[skinList.getSelectedIndex()];
				int x = this.getWidth() - showImg.getWidth(null) >> 1;
				int y = this.getHeight() - showImg.getHeight(null) >> 1;
				g.drawImage(showImg, x, y, null);
			}
		};
		panel.add(new JScrollPane(this.skinList), BorderLayout.WEST);
		panel.add(this.skinView, BorderLayout.CENTER);
		return panel;
	}

	/**
	 * 玩家控制设置面板
	 */
	private JPanel createControlPanel() {
		JPanel jp = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(IMG_PSP, 0, 0, null);
			}
		};
		// 设置布局管理器
		jp.setLayout(null);
		for (TextCtrl textCtrl : keyText) {
			jp.add(textCtrl);
		}
		
		return jp;
	}
	
	/**
	 * 写游戏配置
	 */
	public boolean writeConfig() {
		HashMap<Integer, String> keySet = new HashMap<>();
		for (TextCtrl textCtrl : keyText) {
			int keyCode = textCtrl.getKeyCode();
			if (keyCode == 0) {
				this.errorMsg.setText("错误按键！");
				return false;
			}
			keySet.put(keyCode, textCtrl.getMethodName());
		}
		if (keySet.size() != 8) {
			this.errorMsg.setText("重复按键！");
			return false;
		}
		try {
			// 切换皮肤
			Img.setSkin(this.skinData.get(this.skinList.getSelectedIndex()) + "/");
			// 写入控制配置
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
			oos.writeObject(keySet);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
			this.errorMsg.setText(e.getMessage());
			return false;
		}
		this.errorMsg.setText(null);
		return true;
	}

}
