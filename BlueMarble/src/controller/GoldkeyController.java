package controller;

import java.util.ArrayList;

import model.DAO.GoldkeyDAO;
import model.DTO.GoldkeyDTO;

public class GoldkeyController {
	PlayerController pCon = new PlayerController();

	// 황금 열쇠 뽑기 로직
	GoldkeyDTO getGoldKey(int player, int goldkey_no) {
		return null;
	}

	// 비아 - 황금 열쇠 사용 가능 여부 확인 로직
	public boolean isUsableGoldKey(int player, int c_no) {
		// 황금열쇠 번호 받아와서 사용
		// 황금열쇠 소유자가 현재 턴의 플레이어이고,
		int isPlayerGoldKey = GoldkeyDAO.getInstance().isPlayerGoldKey(c_no);
		if (isPlayerGoldKey == player) {
			// 황금열쇠 사용여부가 0이면 사용
			short isUsableGoldKey = GoldkeyDAO.getInstance().isUsableGoldKey(c_no);
			if (isUsableGoldKey == 0) {
				switch (c_no) {
				case 1: // 땅 구매한것당 3만씩 지불
					System.out.println("황금열쇠) 땅 구매한것당 3만씩 지불");
					// 구매한 땅 카운트해와서
					// 유정님꺼로 하면됨
					break;
				case 2: // 땅 구매한것당 1만씩 지불
					System.out.println("황금열쇠) 땅 구매한것당 1만씩 지불");
					// 구매한 땅 카운트해와서
					// 유정님꺼로 하면됨
					break;
				case 3: // 1회 통행료 패스
					System.out.println("황금열쇠) 1회 통행료 패스");
					// 새로운 메소드

					break;
				case 4: // 뒤로 두칸 이동
					System.out.println("황금열쇠) 뒤로 두칸 이동");
					pCon.move(player, -2);
					break;
				case 5: // 출발지로 이동
					System.out.println("황금열쇠) 출발지로 이동");
					// 새로운 메소드

					break;
				case 6: // 20만원 당첨
					System.out.println("황금열쇠) 20만원 당첨");
					// 월급 받는 메소드 - 유정님
					break;
				case 7: // 다른 플레이어에게 10만원 받기
					System.out.println("황금열쇠) 다른 플레이어에게 10만원 받기");
					// 돈을 지불해야하는 플레이어가 돈이 없을 경우 그냥 종료

					break;
				case 8: // 10만원 차감
					System.out.println("황금열쇠) 10만원 차감");
					// 유정님꺼로 하면됨

					break;
				case 9: // 상대방이 소유한 땅 무효화
					System.out.println("황금열쇠) 상대방이 소유한 땅 무효화");
					// 상대방이 소유한 땅 목록 보여주기
					// 땅의 소유자를 삭제시키고
					// 상대방은 매각 돈을 받지 못함

					break;
				case 10: // 무인도 탈출권
					System.out.println("황금열쇠) 무인도 탈출권");
					// 새로운 메소드
					// 플레이어가 무인도에 있지 않으면 사용하면 안됨
					break;
				}
				return true;
			}
		}
		return false;
	}

	// 비아 - 황금 열쇠 사용 로직
	public boolean useGoldKey(int c_no) {// ★비아추가★
		return GoldkeyDAO.getInstance().useGoldKey(c_no);
	}

	// 비아 - 소유하고 있는 황금열쇠 목록 가져오기 로직
	public ArrayList<GoldkeyDTO> goldKeyList(int player) {
		return GoldkeyDAO.getInstance().goldKeyList(player);
	}

	// 현금 지불 로직
	boolean payCash(int player, int cash) {
		return false;
	}

	// 무인도 - 2턴 쉼 로직
	boolean moveDesertIsland(int player) {
		return false;
	}

	// 무인도 탈출 시도 성공 로직
	boolean escapeSuccess(int player) {
		return false;
	}

	// 무인도 탈출 시도 실패 로직
	boolean escapeFail(int player) {
		return false;
	}
}
