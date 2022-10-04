package controller;

import java.util.ArrayList;

import model.DAO.NationDAO;
import model.DAO.PlayerDAO;
import model.DTO.NationDTO;
import model.DTO.PlayerDTO;
import view.MainView;

public class PlayerController {

	// 비아 - 플레이어 등록 로직
	public boolean addPlayer(String name, int p_turn) {
		return PlayerDAO.getInstance().addPlayer(name, p_turn);
	}

	// 플레이어 정보 가져오는 로직
	PlayerDTO getPlayerInfo(int player) {
		return null;
	}

	// 비아 - 누구 턴인지 가져오는 로직
	public int getWhoIsTurn() {
		return PlayerDAO.getInstance().getWhoIsTurn();
	}

	// 비아 - 플레이어의 현재 위치 가져오는 로직 추가
	public int getLocation(int player) {
		return PlayerDAO.getInstance().getLocation(player);
	}

	// 비아 - 말 이동 로직
	public boolean move(int player, int num) {
		int location = getLocation(player);
		if (location == 0) { // 만약에 플레이어의 현재 위치를 가져오는걸 실패했으면
			return false;
		}
		if ((location + num) > 20) { // 이동한 목적지가 출발 지점을 지났으면
			new MainView().getPaid(player, 100000);
			return PlayerDAO.getInstance().move(player, (location + num) - 20);
		} else
			return PlayerDAO.getInstance().move(player, location + num);
	}

	// 비아 - 플레이어의 턴 수 교체 로직 추가
	public boolean changeTurn(int player) {
		return PlayerDAO.getInstance().changeTurn(player);
	}

	// 수현 - 통행료 내기 로직
	public boolean payTollFee(int player, int n_toll_fee) {//매개변수 통행료로 변경
		return PlayerDAO.getInstance().payTollFee(player, n_toll_fee);
	
	}

	// 수현 - 통행료 얻기 로직
	public boolean takeTollFee(int player, int n_toll_fee) {
		return PlayerDAO.getInstance().takeTollFee(player, n_toll_fee);
		
	}

	// 월급 및 상금 지급 로직 // 유정
	public boolean getPaid(int player, int pay) {
		// 출발 지점으로 돌아왔는지 확인해야함
		return PlayerDAO.getInstance().getPaid(player, pay);
	}

	// 현금 지불 // 유정
	public boolean payCash(int player, int cash) {
		return PlayerDAO.getInstance().payCash(player, cash);
	}

	// 수현 - 플레이어 자산 가져오기
	public int getPlayerMoney(int player) {
		return PlayerDAO.getInstance().getPlayerMoney(player);
	}
	
	// 수현 - 플레이어가 소유한 땅 정보 가져오기(매각위해)
	public ArrayList<NationDTO> getPlayerLand(int player) {
			return PlayerDAO.getInstance().getPlayerLand(player);
	}

	// 유정 - 무인도 탈출 성공
	public boolean escapeDesertIsland(int player) {
		return PlayerDAO.getInstance().escapeDesertIsland(player);
	}

	// 유정 - 무인도 탈출 실패
	public boolean escapeDesertIsland2(int player) {
		return PlayerDAO.getInstance().escapeDesertIsland2(player);
	}
	
	// 유정 - 무인도인지 확인
	public boolean Island(int player) {
		return PlayerDAO.getInstance().Island(player);
	}


	// 비아 - 전체 플레이어 삭제 로직
	public boolean deleteP() {
		return PlayerDAO.getInstance().deleteP();
	}

}
