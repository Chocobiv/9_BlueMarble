package controller;

import model.DAO.NationDAO;
import model.DAO.PlayerDAO;
import model.DTO.PlayerDTO;

public class PlayerController {

	// 플레이어 등록 로직
	// **플레이어마다 등록하는 턴이 달라야해서 매개변수 추가**
	public boolean addPlayer(String name, int p_turn) {
		return PlayerDAO.getInstance().addPlayer(name, p_turn);
	}

	// 플레이어 정보 가져오는 로직
	PlayerDTO getPlayerInfo(int player) {
		return null;
	}

	// 누구 턴인지 가져오는 로직
	public int getWhoIsTurn() {
		return PlayerDAO.getInstance().getWhoIsTurn();
	}

	// **플레이어의 현재 위치 가져오는 로직 추가**
	public int getLocation(int player) {
		return PlayerDAO.getInstance().getLocation(player);
	}

	// 말 이동 로직
	// **반환 타입 변경**
	public boolean move(int player, int num) {
		int location = getLocation(player);
		if (location == 0) { // 만약에 플레이어의 현재 위치를 가져오는걸 실패했으면
			return false;
		}
		if ((location + num) > 20) // 이동한 목적지가 출발 지점을 지났으면
			return PlayerDAO.getInstance().move(player, (location + num) - 20);
		else
			return PlayerDAO.getInstance().move(player, location + num);
	}

	// **플레이어의 턴 수 교체 로직 추가**
	public boolean changeTurn(int player) {
		return PlayerDAO.getInstance().changeTurn(player);
	}

	// 통행료 내기 로직
	boolean payTollFee(int player, int land_no) {
		return false;
	}

	// 통행료 얻기 로직
	boolean takeTollFee(int player, int tollFee) {
		return false;
	}

	// 월급 및 상금 지급 로직
	boolean getPaid(int player, int pay) {
		// 출발 지점으로 돌아왔는지 확인해야함
		
		return false;
	}

	//수현 - 메소드 생성(9/29) - 플레이어 자산 가져오기
	public int getPlayerMoney(int player) {
		return 0;
	}
	
	
	// 전체 플레이어 삭제 로직
	public boolean deleteP() {
		return PlayerDAO.getInstance().deleteP();
	}

}
