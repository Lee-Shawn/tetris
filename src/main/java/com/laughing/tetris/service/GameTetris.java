package com.laughing.tetris.service;


import com.laughing.tetris.config.GameConfig;
import com.laughing.tetris.dto.GameDto;
import com.laughing.tetris.entity.GameAct;
import com.laughing.tetris.ui.Music;

import java.awt.Point;
import java.util.Map;
import java.util.Random;


public class GameTetris implements GameService {

	/**
	 * 游戏数据对象
	 */
	private final GameDto dto;
	
	/**
	 * 随机数生成器
	 */
	private final Random random = new Random();
	
	/**
	 * 方块种类个数
	 */
	private static final int MAX_TYPE = GameConfig.getSystemConfig().getTypeConfig().size()-1;
	
	/**
	 * 升级行数
	 */
	private static final int LEVEL_UP = GameConfig.getSystemConfig().getLevelUp();
	
	/**
	 * 加分
	 */
	private static final Map<Integer, Integer> PLUS_POINT = GameConfig.getSystemConfig().getPlusPoint();
	
	public GameTetris(GameDto dto) {
		this.dto = dto;
	}
	
	/**
	 *  控制器方向键（上）
	 */
	public boolean keyUp() {
		if (this.dto.isPause()) {
			return true;
		}
		synchronized (this.dto) {
			if (this.dto.isStart()) {
				this.dto.getGameAct().round(this.dto.getGameMap());
			}
		}
		return true;
	}

	/**
	 * 控制器方向键（下）
	 */
	public boolean keyDown() {
		if (this.dto.isPause()) {
			return true;
		}
		// 锁住线程
		synchronized (this.dto) {
			if (this.dto.isStart()) {
				// 方块向下移动，并判断是否移动成功
				if (this.dto.getGameAct().move(0, 1, this.dto.getGameMap())) {
					return false;
				}
				// 获得游戏地图对象
				boolean[][] map = this.dto.getGameMap();
				// 获得方块对象
				Point[] act = this.dto.getGameAct().getActPoints();
				// 将方块堆积到地图数组
				for (Point point : act) {
					map[point.x][point.y] = true;
				}
				// 判断是否可以消行,并计算获得经验值
				int plusExp = this.plusExp();
				// 如果消行
				if (plusExp > 0) {
					// 增加经验值
					this.plusPoint(plusExp);
				}
				// 创建下一个方块
				this.dto.getGameAct().init(this.dto.getNext());
				// 随机生成再下一个方块
				this.dto.setNext(random.nextInt(MAX_TYPE));
				// 检查游戏是否失败
				if (this.isLose()) {
					// 结束游戏
					this.dto.setStart(false);
				}
			}
		}
		return true;
	}

	/**
	 * 检查游戏是否失败
	 */
	private boolean isLose() {
		// 获得现在的活动方块
		Point[] actPoints = this.dto.getGameAct().getActPoints();
		// 获得现在的游戏地图
		boolean[][] map = this.dto.getGameMap();
		for (Point actPoint : actPoints) {
			if (map[actPoint.x][actPoint.y]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 升级操作
	 */
	private void plusPoint(int plusExp) {
		int level = this.dto.getNowLevel();
		int rmLine = this.dto.getNowRemoveLine();
		int point = this.dto.getNowPoint();
		if (rmLine % LEVEL_UP + plusExp >= LEVEL_UP) {
			this.dto.setNowLevel(++level);
		}
		this.dto.setNowRemoveLine(rmLine + plusExp);
		this.dto.setNowPoint(point + PLUS_POINT.get(plusExp));
	}

	/**
	 * 控制器方向键（左）
	 */
	public boolean keyLeft() {
		if (this.dto.isPause()) {
			return true;
		}
		synchronized (this.dto) {
			if (this.dto.isStart()) {
				this.dto.getGameAct().move(-1, 0, this.dto.getGameMap());
			}
		}
		return true;
	}

	/**
	 * 控制器方向键（右）
	 */
	public boolean keyRight() {
		if (this.dto.isPause()) {
			return true;
		}
		synchronized (this.dto) {
			if (this.dto.isStart()) {
				this.dto.getGameAct().move(1, 0, this.dto.getGameMap());
			}
		}
		return true;
	}

	/**
	 * 消行操作
	 */
	private int plusExp() {
		// 获得游戏地图
		boolean[][] map = this.dto.getGameMap();
		int exp = 0;
		// 扫描游戏地图，查看是否可消行
		for (int y = 0; y < GameDto.GAMEZONE_H; y++) {
			// 判断是否可消行
			if (this.canRemoveLine(y, map)) {
				// 消行
				this.removeLine(y, map);
				// 增加经验
				exp++;
			}
		}
		return exp;
	}
	
	/**
	 * 消行处理
	 */
	private void removeLine(int rowNumber, boolean[][] map) {
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			for (int y = rowNumber; y > 0; y--) {
				map[x][y] = map[x][y-1];
			}
			map[x][0] = false;
		}
	}

	/**
	 * 判断某一行是否可消除
	 */
	private boolean canRemoveLine(int y, boolean[][] map) {
		// 单行内对每一个单元格进行扫描
		for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
			if (!map[x][y]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 背景音乐
	 */
	public void setBGM() {
		if (this.dto.isPause()) {
			System.out.println("暂停");
			Music.stopBGM();
		} else {
			System.out.println("开始");
			Music.playBGM();
		}
	}

	@Override
	public boolean keyFunUp() {
		// 作弊
		this.dto.setCheat(true);
		this.plusPoint(4);
		return true;
	}

	@Override
	public boolean keyFunDown() {
		if (this.dto.isPause()) {
			return true;
		}
		// 瞬间下落
		if (this.dto.isStart()) {
			while(!this.keyDown());
		}
		return true;
	}

	@Override
	public boolean keyFunLeft() {
		// 阴影开关
		this.dto.changeShowShadow();
		return true;
	}

	@Override
	public boolean keyFunRight() {
		// 暂停
		if (this.dto.isStart()) {
			this.dto.changePause();
			this.setBGM();
		}
		return true;
	}

	@Override
	public void startGame() {
		// 随机生成下一个方块
		this.dto.setNext(random.nextInt(MAX_TYPE));
		// 随机生成画面方块
		dto.setGameAct(new GameAct(random.nextInt(MAX_TYPE)));
		// 把游戏状态设为开始
		this.dto.setStart(true);
		// dto初始化
		this.dto.dtoInit();
	}

	@Override
	public void mainAction() {
		this.keyDown();
	}
	
}
