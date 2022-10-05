package controller;

import java.util.ArrayList;
import java.util.Random;

import model.DAO.GoldkeyDAO;
import model.DTO.GoldkeyDTO;
import model.DTO.NationDTO;

public class GoldkeyController {
	PlayerController pCon = new PlayerController();
	NationController nCon = new NationController();

	// 황금 열쇠 뽑기 로직
	public boolean getGoldKey(int player, int c_num) {
		return GoldkeyDAO.getInstance().getGoldKey(player, c_num);
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
					System.out.println("\t\t\t황금열쇠) 땅 구매한것당 3만씩 지불");
					// 구매한 땅 카운트해와서
					int count1 = nCon.countPlayerLand(player);
					// 지불
					pCon.payCash(player, count1 * 30000);
					break;
				case 2: // 땅 구매한것당 1만씩 지불
					System.out.println("\t\t\t황금열쇠) 땅 구매한것당 1만씩 지불");
					// 구매한 땅 카운트해와서
					int count2 = nCon.countPlayerLand(player);
					// 지불
					pCon.payCash(player, count2 * 10000);
					break;
				case 3: // 1회 통행료 패스
					System.out.println("\t\t\t황금열쇠) 1회 통행료 패스");
					System.out.println("\t\t\t안내) 통행료 패스 완료!");
					break;
				case 4: // 뒤로 두칸 이동
					System.out.println("\t\t\t황금열쇠) 뒤로 두칸 이동");
					pCon.move(player, -2);
					break;
				case 5: // 출발지로 이동
					System.out.println("\t\t\t황금열쇠) 출발지로 이동");
					pCon.moveStart(player);
					break;
				case 6: // 20만원 당첨
					System.out.println("\t\t\t황금열쇠) 20만원 당첨");
					pCon.getPaid(player, 200000);
					break;
				case 7: // 다른 플레이어에게 10만원 받기
					System.out.println("\t\t\t황금열쇠) 다른 플레이어에게 10만원 받기");
					// 돈을 지불해야하는 플레이어가 돈이 없을 경우 그냥 종료
					int payPlayer = 0;
					if (player == 1)
						payPlayer = 2;
					else if (player == 2)
						payPlayer = 1;

					int p_money = pCon.getPlayerMoney(payPlayer);// 플레이어 자산 호출

					// 해당 플레이어 자산에서 10만원 이상인 경우에만 돈 지급됨
					if (p_money >= 100000) {
						pCon.payCash(payPlayer, 100000);
						pCon.getPaid(player, 100000);
					} else
						System.out.println("\t\t\t안내) 안타깝네요! 상대방의 돈이 10만원 미만이라 지급이 취소됩니다.");
					break;
				case 8: // 10만원 차감
					System.out.println("\t\t\t황금열쇠) 10만원 차감");
					pCon.payCash(player, 100000);
					break;
				case 9: // 상대방이 소유한 땅 무효화
					System.out.println("\t\t\t황금열쇠) 상대방이 소유한 땅 무효화");
					// 상대방이 소유한 땅 목록 가져와서
					int otherPlayer = 0;
					if (player == 1)
						otherPlayer = 2;
					else if (player == 2)
						otherPlayer = 1;
					ArrayList<NationDTO> list = pCon.getPlayerLand(otherPlayer);
					if (!list.isEmpty()) { // 상대방이 소유한 땅이 하나라도 있으면
						int[] n_no = new int[list.size() + 1]; // 상대방이 소유한 땅의 n_no 저장할 배열
						int i = 1; // 인덱스 1부터 시작
						for (NationDTO nation : list) {
							n_no[i] = nation.getN_no();
							i++;
						}
						Random random = new Random();
						int rd = random.nextInt(n_no.length) + 2; // 2부터
						// 땅의 소유자를 삭제시키고 매각 돈을 받지 못함
						nCon.deleteLandlord(rd);
						NationDTO nation = nCon.getNationInfo(rd);
						System.out.println("\t\t\t안내) " + nation.getN_name() + " 을 무효화했습니다.");
					} else
						System.out.println("\t\t\t안내) 안타깝네요! 상대방이 소유하고 있는 땅이 없어서 무효화가 취소됩니다."); // 상대방이 소유한 땅이 없으면
					break;
				case 10: // 무인도 탈출권
					System.out.println("\t\t\t황금열쇠) 무인도 탈출권");
					break;
				}
				return true;
			}
		}
		return false;
	}

	// 예은 황금열쇠 기능 추가
	public int getGoldkeyTF(int c_use) {
		return GoldkeyDAO.getInstance().getGoldkeyTF(c_use);
	}

	// 비아 - 황금 열쇠 사용 로직
	public boolean useGoldKey(int c_no) {// ★비아추가★
		return GoldkeyDAO.getInstance().useGoldKey(c_no);
	}

	// 비아 - 플레이어의 무인도 탈출권 소유 여부 확인 로직
	public int doesHaveIt() {
		return GoldkeyDAO.getInstance().doesHaveIt();
	}

	// 소유하고 있는 황금열쇠 목록 가져오기 로직
	public ArrayList<GoldkeyDTO> goldKeyList(int player) {
		return GoldkeyDAO.getInstance().goldKeyList(player);
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

	// 수현 - 황금열쇠 초기화
	public boolean resetGoldKey() {
		return GoldkeyDAO.getInstance().resetGoldKey();
	}

}
