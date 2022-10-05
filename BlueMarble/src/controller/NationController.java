package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.DAO.NationDAO;
import model.DAO.PlayerDAO;
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

	// 수현(9/30) - n_no맞는 땅정보 한번에 가져오기 위한 메소드 생성
	public NationDTO getNationInfo(int n_no) {
		return NationDAO.getInstance().getNationInfo(n_no);
	}

	// 수현 - 땅 구매 로직
	public boolean buyLand(int player, int n_no, int n_price) {
		return NationDAO.getInstance().buyLand(player, n_no, n_price);
	}

	// 특정 땅의 세금비용을 가져오는 로직 수현 -> 땅 정보 한번에가져올수있어서 삭제함! 주석도 삭제할예정!


	// 수현 - 올림픽 개최 로직
	public boolean hostingOlympics(int n_no) {
		return NationDAO.getInstance().hostingOlympics(n_no);
		
	}

	// 수현 - 올림픽 폐막 로직
	public boolean closingOlympics(int n_no) {
		return NationDAO.getInstance().closingOlympics(n_no);
	}

	boolean isMyland(int player, int land_no) {
		return false;
	}

	// 수현 - 땅 매각 로직 -> 땅 p_no=null  // 플레이어 자산 업데이트 한번에
	public boolean saleLand(int player, int n_no , int n_price) {
		return NationDAO.getInstance().saleLand(player,n_no, n_price);
	}
	
	// 비아 - [황금열쇠] 땅 소유자 삭제 로직
	public boolean deleteLandlord(int n_no) {
		return NationDAO.getInstance().deleteLandlord(n_no);
	}
	
	// 비아 - 플레이어가 소유한 땅의 개수 카운트 로직
	public int countPlayerLand(int player) {
		return NationDAO.getInstance().countPlayerLand(player);
	}
	
	// 비아 - 플레이어가 소유한 땅의 땅가격 총합 로직
	public int sumPlayerLand(int player) {
		return NationDAO.getInstance().sumPlayerLand(player);
	}
		
	// 수현- 땅 소유자 초기화
	public boolean resetLand() {
		return NationDAO.getInstance().resetLand();
	}
	
}
