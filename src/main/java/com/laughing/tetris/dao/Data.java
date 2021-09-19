package com.laughing.tetris.dao;

import com.laughing.tetris.dto.Player;

import java.util.List;


/**
 * 数据持久层接口
 * @author Tomorrow
 *
 */
public interface Data {

	/**
	 * 读取数据
	 */
	List<Player> loadData();
	
	/**
	 * 存储数据
	 */
	void saveData(Player players);
	
}
