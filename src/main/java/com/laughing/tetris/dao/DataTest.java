package com.laughing.tetris.dao;

import com.laughing.tetris.dto.Player;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class DataTest implements Data {

	public DataTest() {
		
	}
	
	@Override
	public List<Player> loadData() {
		List<Player> players = new ArrayList<>();
		players.add(new Player("shawn", 100));
		players.add(new Player("shawn", 1000));
		players.add(new Player("shawn", 2000));
		players.add(new Player("shawn", 4000));
		players.add(new Player("shawn", 8000));
		// 重新写入
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/data/recode.dat"));
			oos.writeObject(players);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return players;
	}

	@Override
	public void saveData(Player players) {
		System.out.println();
	}

	public static void main(String[] args) {
		DataTest test = new DataTest();
		test.loadData();
	}

}
