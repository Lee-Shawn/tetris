package com.laughing.tetris.view;

import com.laughing.tetris.controller.GameControl;
import com.laughing.tetris.util.FrameUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class JFrameSavePoint extends JFrame {
	
	private JLabel lbPoint = null;
	
	private JTextField txName = null;
	
	private JButton btnOK = null;
	
	private JLabel errMsg = null;
	
	private final GameControl gameControl;

	public JFrameSavePoint(GameControl gameControl) {
		this.gameControl = gameControl;
		this.setTitle("保存记录");
		this.setSize(256, 192);
		FrameUtil.setFrameCenter(this);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.createCom();
		this.createAction();
	}

	/**
	 * 显示窗口
	 */
	public void show(int point) {
		this.lbPoint.setText("您的得分：" + point);
		this.setVisible(true);
	}
	/**
	 * 创建事件监听
	 */
	private void createAction() {
		this.btnOK.addActionListener(e -> {
			String name = txName.getText();
			if (name.length() > 16 || "".equals(name)) {
				errMsg.setText("名字输入错误！");
			} else {
				setVisible(false);
				gameControl.savePoint(name);
			}
		});
	}

	/**
	 * 初始化控件
	 */
	private void createCom() {
		// 创建北部面板（流式布局）
		JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// 显示分数
		this.lbPoint = new JLabel();
		// 添加分数到北部面板
		north.add(this.lbPoint);
		// 创建错误信息控件
		this.errMsg = new JLabel();
		// 设置前景色
		this.errMsg.setForeground(Color.RED);
		// 添加错误信息到北部面板
		north.add(errMsg);
		// 北部面板添加到主面板
		this.add(north, BorderLayout.NORTH);
		
		// 创建创建中间面板（流式布局）
		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// 创建文本框
		this.txName = new JTextField(10);
		// 显示名字
		center.add(new JLabel("请输入您的名字："));
		// 添加名字到中间面板
		center.add(this.txName);
		// 中间面板添加到主面板
		this.add(center, BorderLayout.CENTER);
		
		// 创建确定按钮
		this.btnOK = new JButton("确定");
		// 创建南部面板（流式布局）
		JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// 按钮添加到南部面板
		south.add(btnOK);
		// 南部面板添加到主面板
		this.add(south, BorderLayout.SOUTH);
	}
	
}
