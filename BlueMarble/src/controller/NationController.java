package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.DAO.NationDAO;
import model.DTO.NationDTO;

public class NationController {
	PlayerController pCon = new PlayerController();
	
	
	// 전체 나라 가져오는 로직
	public ArrayList<NationDTO> getNations() {
		return NationDAO.getInstance().getNations();
	}

	// 수현 - 이동한 땅의 주인 존재 여부 확인 로직(1: 플레이어1 땅 / 2: 플레이어2 땅 / null: 땅 주인 없음)
	public int isExistLandlord(int whoIsTurn, int n_no) {
		return NationDAO.getInstance().isExistLandlord(n_no);
	}

	// 수현 - 땅 구매 로직
	public boolean buyLand(int player, int n_no, int n_price) {
		return NationDAO.getInstance().buyLand(player, n_no, n_price);
	}

	//수현 - 메소드 생성(9/29)  -> 땅 가격 전달 메소드
	public int getLandPrice(int n_no) {
		return NationDAO.getInstance().getLandPrice(n_no);
	}
	
	//수현 메소드생성(9/29) - 땅 이름 가져오기
	public String getLandName(int n_no) {
		return NationDAO.getInstance().getLandName(n_no);
		
	}
	

	// 특정 땅의 세금비용을 가져오는 로직
	int getTax(int n_no) {
		return 0;
	}

	// 올림픽 개최 로직
	boolean hostingOlympics(int land_no) {
		return false;
	}

	// 올림픽 폐막 로직
	boolean closingOlympics(int land_no) {
		return false;
	}

	boolean isMyland(int player, int land_no) {
		return false;
	}

	// 땅 매각 로직
	boolean saleLand(int player, int land_no) {
		return false;
	}
}
