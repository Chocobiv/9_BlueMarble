package view;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import controller.GoldkeyController;
import controller.NationController;
import controller.PlayerController;
import model.DTO.GoldkeyDTO;
import model.DTO.NationDTO;

//메인 뷰 클래스
public class MainView {
	PlayerController pCon = new PlayerController();
	NationController nCon = new NationController();
	GoldkeyController gCon = new GoldkeyController();
	NationDTO nationdto = new NationDTO(); // 땅 정보 출력 객체! 여기저기서 계속 객체만들길래 전역으로 선언

	Scanner sc = new Scanner(System.in); // 입력 객체
	// **플레이어1부터 시작**
	int whoIsTurn = 1; // p_turn이 0인 p_no을 저장하는 변수 = p_no = player
	int count = 0; // 전체 턴 수(30 초과시 게임 종료)
	int player1Pause = 0; // 플레이어1의 무인도에 갖힌 턴
	int player2Pause = 0; // 플레이어2의 무인도에 갖힌 턴
	int move_b_no = 1; // 나온 주사위만큼 이동한 현재 위치
	int OlympicsLand=0; // 수현 변수 추가!! - DB에 올림픽개최지를 저장할게 없어서...올림픽개최지 나라 번호 저장할 변수

	// 1. 게임 실행 메소드
	public void play() {
		// goldKeyList(whoIsTurn);
		// 플레이어 등록
		addPlayer();
		while (count != 31) { // count에 따라서 무한반복 종료
			whoIsTurn = pCon.getWhoIsTurn();
			// 부루마블 판 출력
			new BoardView().showBoard();
			// 주사위
			int dice = rollDice();
			// 말 이동
			move(whoIsTurn, dice); // 턴인 플레이어를 주사위만큼 이동

			isExistLandlord(whoIsTurn, move_b_no);

			count++;
			if (whoIsTurn == 1) // **임시 조치**
				whoIsTurn = 2;
			else if (whoIsTurn == 2)
				whoIsTurn = 1;

			// 황금열쇠 사용
			useGoldKey(whoIsTurn, 4);

			// **테스트 편의를 위해서 만듦, 나중에 수정할 것**
			System.out.print("안내) 게임 끝낼까요?");
			String answer = sc.next();
			if (answer.equals("Y") || answer.equals("y")) {
				// 플레이어 삭제
				deleteP();
				System.out.println();
				count = 31; // **무한 반복 종료를 위한 임시 조치**
			} else if (answer.equals("N") || answer.equals("n"))
				System.out.println("안내) 게임을 계속합니다.\n");
			else
				System.out.println("안내) 알 수 없는 입력입니다.\n");
		}
	}

	// 비아(9/29) - 2. 플레이어 등록 메소드 - 플레이어 이름 입력받아서 DB에 저장 [C]
	void addPlayer() {
		// 처음 시작할 때 60만원 지급
		System.out.print("플레이어 1 이름 : ");
		String name1 = sc.next();
		System.out.print("플레이어 2 이름 : ");
		String name2 = sc.next();
		boolean result1 = pCon.addPlayer(name1, 0); // 항상 플레이어1이 먼저 시작
		boolean result2 = pCon.addPlayer(name2, 1); // 플레이어2는 자기 차례까지 남은 턴: 1
		if (result1 && result2)
			System.out.println("안내) 플레이어 등록 성공\n");
		else
			System.out.println("안내) 플레이어 등록 실패\n");

		System.out.println("안내) 부루마블 게임을 시작합니다!\n");
	}

	// 유정 - 3. 부루마블 판 출력 메소드

	// 비아(9/29) - 4. 주사위(1~6) 메소드
	int rollDice() {
		// 1~6의 숫자 중 랜덤한 정수 반환
		// 숫자만 보여주기
		System.out.println("안내) 플레이어" + whoIsTurn + "님의 차례입니다.");
		System.out.print("안내) 주사위 굴립니다");

		int count = 0;
		while (count < 2) {
			System.out.print("..");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			count++;
		}
		System.out.println();

		Random random = new Random(); // Random 이용
		int dice = random.nextInt(6) + 1; // 1~6사이의 주사위
		switch (dice) {
		case 1:
			System.out.println("주사위 : ⚀ " + dice);
			break;
		case 2:
			System.out.println("주사위 : ⚁ " + dice);
			break;
		case 3:
			System.out.println("주사위 : ⚂ " + dice);
			break;
		case 4:
			System.out.println("주사위 : ⚃ " + dice);
			break;
		case 5:
			System.out.println("주사위 : ⚄ " + dice);
			break;
		case 6:
			System.out.println("주사위 : ⚅ " + dice);
			break;
		}
		return dice;
	}

	// 비아(9/29) - 5. 말 이동 메소드 [무조건 플레이어1부터 시작] [U]
	void move(int player, int num) {
		// num만큼 말 이동(+ or -)
		// 단, 이동한 목적지가 출발 지점을 지났으면 그에 맞는 행동 필요
		boolean result = pCon.move(player, num);
		if (result) {
			System.out.println("안내) 말 이동 성공\n");
			// 플레이어의 다음 자기 차례까지 남은 턴 수(p_turn) 교체
			boolean result2 = pCon.changeTurn(player);
			if (result2)
				System.out.println("안내) 플레이어 턴 수 교체 성공\n");
			else
				System.out.println("안내) 플레이어 턴 수 교체 실패\n");
		} else
			System.out.println("안내) 말 이동 실패\n");

		// 주사위 나온만큼 이동한 현재 위치
		move_b_no = pCon.getLocation(player);
		// 현재 말 위치 출력(**나중에 판 구현되면 삭제해야함**)
		System.out.println("플레이어" + player + "의 현재위치 : " + move_b_no);
	}

	// 수현 - 6. 이동한 땅의 주인 존재 여부 확인 메소드 [R]
	// 출발점, 황금열쇠, 무인도, 올림픽은 아예 못들어가게 1, 6, 11, 16
	// 올림픽에 도착하면 메소드 실행하게 여기서 설정해도 되나??
	void isExistLandlord(int whoIsTurn, int n_no) {
		if(n_no==11) {hostingOlympics(whoIsTurn); return;}
		// whoIsTurn : 누구 턴? (DB : p_no)
		if(n_no==1 || n_no==6 || n_no==16){System.out.println("안내) 구매불가능한 땅입니다.");return;}
		// 1: 플레이어1 땅 / 2: 플레이어2 땅 / null: 땅 주인 없음
		int p_no = nCon.isExistLandlord(whoIsTurn, n_no); // 소유자여부 가져오기
		nationdto = nCon.getNationInfo(n_no);// 땅 정보 호출
		int p_money = pCon.getPlayerMoney(whoIsTurn);// 플레이어 자산 호출
		if (p_no == 0) {// 빈 땅일때
			System.out.println("안내) 빈땅을 구입할 수 있습니다.\n");
			System.out.println("안내) "+nationdto.getN_name() + "의 가격은 " + nationdto.getN_price() + "입니다.");
			System.out.println("안내) 현재 자산은 " +p_money+" 원 입니다.\n");
			try{//try 안에 어디까지 넣어야되는걸까...
				System.out.print("안내) 땅을 구입하시겠습니까? [구입 : 1][포기 : 2]");
				int ch = sc.nextInt();
				if (ch == 1) {buyLand(whoIsTurn, n_no);} // 사겠다고 하면 땅구매 메소드 이동
				else if(ch==2){return;} // no 하면 턴종료
			}catch(Exception e){System.out.println("숫자로 입력해주세요"); sc=new Scanner(System.in);}
			
		} else if (p_no == whoIsTurn) {//내 소유 땅일때
			System.out.println("안내) 이미 소유한 땅입니다.");
			return;
		} else if (p_no != whoIsTurn) {//상대방 땅일때
			System.out.println("안내) 안타깝게도 상대방 땅입니다. 통행료를 지불해주세요.\n");
			payTollFee(whoIsTurn, n_no); // 통행료지불 메소드로 이동
		}
	}

	// 수현 - 7. 땅 구매 메소드 [U] (누구 턴인지, 구매할 땅 번호) [U]
	void buyLand(int player, int n_no) {
		nationdto = nCon.getNationInfo(n_no);// 땅 정보 호출
		int p_money = pCon.getPlayerMoney(player);// 플레이어 자산 호출
		if (nationdto.getN_price() <= p_money) {
			// 내 자산이 더 많으면 구매 실행
			boolean result = nCon.buyLand(player, n_no, nationdto.getN_price());
			p_money = pCon.getPlayerMoney(player); // 구매 후 자산 업데이트 하고 한번 더 호출
			if (result) {//제대로 업데이트완료했으면
				System.out.println("안내) 땅 구매 완료했습니다.\n");
				
				System.out.println("현재 남은 자산은 " + p_money + "원 입니다.");
			}
		} else {
			System.out.println("안내) 자산부족으로 실패했습니다.");
		}
	}

	// 수현- 8. 통행료내기 메소드 [U] (누구 턴인지, 통행료 내야하는 땅 번호) [U]
	void payTollFee(int player, int n_no) {
		// 요기 매개변수 land_no 이었는데 n_no으로 통일시키기 위해 변경!!
		nationdto = nCon.getNationInfo(n_no);// 땅 정보 호출
		int p_money = pCon.getPlayerMoney(player);// 플레이어 자산 호출 // 메소드 3개에서 반복되고있음 물어보고 전역으로 사용하던가 해야할듯!
		System.out.println("안내) 현재 보유한 자산은 "+p_money+"원 입니다.");
		System.out.println("안내) "+nationdto.getN_name() + "  통행료는 " + nationdto.getN_toll_fee() + "원 입니다.\n");

		// 해당 플레이어 자산에서 통행료를 지불했을때 0 이하가 되면 매각 메소드 실행
		if ((p_money - nationdto.getN_toll_fee()) < 0) {
			System.out.println("안내) 현재 자산이 부족해 통행료를 지불할 수 없습니다.\n");
			saleLand(player, n_no);
		}
		else {
			boolean result = pCon.payTollFee(player, nationdto.getN_toll_fee());//수현(10/1) - 매개변수 n_no에서 통행료로 변경!
			p_money = pCon.getPlayerMoney(player);
			if(result) {
				System.out.println("안내) 통행료 지불이 완료됐습니다. 현재 자산은 " +p_money+ "원 입니다.");
				boolean result2=pCon.takeTollFee(player, nationdto.getN_toll_fee());
				if(result2) {System.out.println("안내) 상대방에게 통행료 지급이 완료됐습니다.");}
			}
		}
	}

	// 유정 - 9. 월급 및 상금 지급 메소드 [U] 10만원+
	public void getPaid(int player, int pay) {
		boolean result = pCon.getPaid(player, pay);
		if (result) {
			System.out.println("안내) 월급이 입금됐습니다.");
		} else {
			System.out.println("안내) 월급 입금 실패~!");
		}
	}

	// 유정 - 10. 현금 지불 메소드 [U]
	void payCash(int player, int cash) {
		boolean result = pCon.payCash(player, cash);
		if (result) {
			System.out.println("안내) 금액이 차감되었습니다.");
		} else {
			System.out.println("안내) 금액 차감 실패~!");
		}
	}

	// 예은 - 11. 플레이어가 소유하고 있는 황금열쇠 목록 가져오기 메소드
	void goldKeyList(int player) {
		ArrayList<GoldkeyDTO> result = gCon.goldKeyList(player);
		if (!result.isEmpty()) {
			System.out.println("번호 \t 이름 \t 내용");
			for (GoldkeyDTO dto : result) {
				System.out.println(dto.getC_num() + "\t" + dto.getC_name() + "\t" + dto.getC_coment());
			} // for end
		} // if
	}// goldKeyList

	// 비아(9/30) - 12. 플레이어가 소유하고 있는 황금열쇠 사용 메소드
	void useGoldKey(int player, int c_no) {// ★비아추가★
		// 황금열쇠 번호 받아와서 사용
		// 1. 황금 열쇠 사용 가능 여부 확인
		boolean usable_result = gCon.isUsableGoldKey(player, c_no);
		// 2. 1번이 true이면 황금 열쇠 사용
		if (usable_result) {
			boolean result = gCon.useGoldKey(c_no);
			if (result) {
				System.out.println("안내) 황금열쇠 사용 완료했습니다.");
			} else {
				System.out.println("안내) 황금열쇠 사용 실패했습니다.");
			}
		} else {
			System.out.println("안내) 사용 가능한 황금열쇠 번호가 아닙니다.");
		}
	}

	// 예은 - 13. 황금 열쇠 뽑기 메소드 [R,U]
	void getGoldKey(int player) {
		// 황금 열쇠는 랜덤 - 범위는 황금 열쇠 개수
		Random random = new Random();
		int goldrandom = random.nextInt(10) + 1;
		
		
	}

	// 예은 - 14. 무인도 메소드 - 2턴 쉼 [U]
	void moveDesertIsland(int player) {

	}

	// 유정 - 15. 무인도 탈출 시도 메소드 - 주사위가 6이 나오면 탈출, 아니면 쉬는 턴 -1 [U]
	void escapeDesertIsland(int player) {
		boolean result = pCon.escapeDesertIsland(player);
		System.out.println("안내) 무인도에서 탈출합니다.");
	}

	// 유정 - 15-1 무인도인지 확인 메소드
	void Island(int player) {
		boolean result = pCon.Island(player);
		if (result == true && move_b_no == 16) {
			System.out.println("안내) 무인도에 도착했습니다.");
			System.out.println("안내) 지금부터 2턴을 쉬어야하며, 주사위가 6이 나올 경우 탈출 가능합니다.");

			Random random = new Random(); // Random 이용
			int dice2 = random.nextInt(6) + 1; // 1~6사이의 주사위
			System.out.println("주사위 : ⚀ " + dice2); // 실행해보니 주사위 굴리고 3 나왔는데 한칸 이동.. 어떻게 막는지 모르겠습니다..
			if (move_b_no == 16) {
				if (dice2 == 6) {
					escapeDesertIsland(player);
				} // 6이면 탈출
				else if (dice2 < 6) {
					System.out.println("안내) 무인도 탈출 실패");
				}
			}

		}
	}

	// 수현 - 16. 올림픽 개최 메소드 [U]-
	void hostingOlympics(int player) {
		// 이미 개최 중인 올림픽 개최지가 있으면 폐막하고
		// 새로 개최할 나라(land_no)를 올림픽 개최 중으로 수정
		// 만약에 플레이어가 소유하지 않은 땅에 개최하지 못하도록 조건\
		System.out.println("안내) 올림픽 개최 이벤트장소에 도착했습니다.\n");
		System.out.println("안내) 소유중인 땅중에서 하나를 선택해 통행료를 2배로 받을 수 있습니다.");
		System.out.println("안내) 상대방이 올림픽을 개최한다면 선택한 땅의 통행료는 다시 기존으로 돌아옵니다.\n");
		
		ArrayList<NationDTO> list= pCon.getPlayerLand(player); // 플레이어가 소유한 땅 정보
		// 소유한 땅 없으면 올림픽 개최못하게!
		if(list.size()==0) {System.out.println("안내) 아직 소유한 땅이 없어 올림픽을 개최할 수 없습니다.");return;}
		if(OlympicsLand!=0) {//이미 다른땅에 올림픽이 개최된 상태라면
			//기존 올림픽 개최지 통행료 제자리!
			boolean result=nCon.closingOlympics(OlympicsLand);
			if(result) {//제대로 처리됐으면 다시 0으로 바꿔주기!
				System.out.println("안내) 기존 개최됐던 올림픽은 폐막됐음을 알립니다.\n");
				OlympicsLand=0;
			}
		}
		System.out.println("번호\t나라명\t현재 통행료");
		for(int i=0; i<list.size();i++) {
			System.out.println((i+1)+"\t"+list.get(i).getN_name()+"\t"+list.get(i).getN_toll_fee());
		}
		System.out.print("안내) 올림픽을 개최할 나라의 번호를 입력해주세요 : ");int ch=sc.nextInt();
		boolean result= nCon.hostingOlympics(list.get(ch-1).getN_no());
		if(result) {
			System.out.println("안내) 올림픽이 정상적으로 개최됐습니다.\n");
			OlympicsLand=list.get(ch-1).getN_no();
		}
		
		
	}

	// 수현 - 17. 땅 매각 메소드 [U]
	void saleLand(int player, int n_no) {
		// 황금열쇠때문에 금액을 지급해야할 때, 통행료 지불해야할 때 현재 보유 자산에서 지불금액을 차감했을 때 그 금액이 0미만이면
		
		System.out.println("안내) 자산이 부족해 보유한 땅을 매각해야합니다.\n");
		ArrayList<NationDTO> list= pCon.getPlayerLand(player); // 플레이어가 소유한 땅 정보
		
		// 소유한 땅 없음 -> 게임 종료 메소드 넣어야함!!
		if(list.size()==0) {
			System.out.println("안내) 매각 가능한 땅이 없습니다. 패배하셨습니다."); 
			return; // 게임 종료 메소드 deleteP() 요건가??
		}
		try {//요거 어떻게 써야 번호를 다시 선택하게 할수있을까...//while문을 써야하나...
			System.out.println("번호\t나라명\t매각가격");
			for(int i=0; i<list.size();i++) {
				System.out.println((i+1)+"\t"+list.get(i).getN_name()+"\t"+list.get(i).getN_price());
			}
			System.out.print("안내) 매각할 나라의 번호를 입력해주세요 : ");int ch=sc.nextInt(); // ch=나라 번호! , 나라 통행료도 넘겨줘야함!
			//매각 업데이트
			boolean result=nCon.saleLand(player,list.get(ch-1).getN_no(),list.get(ch-1).getN_price());
			if(result) {System.out.println("안내) 땅 매각 업데이트 완료했습니다. \n"); payTollFee(player,n_no);}
			
		}catch(Exception e) {System.out.println("번호로 선택해주세요 "); sc=new Scanner(System.in);}
	}

	// 비아(9/29) - 18. 전체 플레이어 삭제 메소드 [D]
	void deleteP() {
		// 게임 종료 후 '게임이 종료되었습니다. 승자는 ~님입니다.' 플레이어 삭제
		System.out.println("게임이 종료되었습니다. 승자는 ~님입니다.");
		boolean result = pCon.deleteP();
		if (result)
			System.out.println("안내) 플레이어 삭제 성공\n");
		else
			System.out.println("안내) 플레이어 삭제 실패\n");
	}

}
//** DB **
// player(player_no[PK],name,money,location,turn) (2명 or +bank)
// nation(nation_no[PK],name,price,tax,olympic,player_no[FK]) -> 14개
// goldkey(goldkey_no[PK],name,comment,player_no[FK])
// [규칙] 황금키 뽑은 후 바로 사용하는게 아니라 보유 가능함!
// [정리] 한 플레이어가 여러개의 황금키를 가지고 있을 수 있고 각 카드는 1장씩만 존재
// [정리] 각 모서리 : 출발지점/ 황금키/ 올림픽(개최비:무료/ 통행료2배)/ 무인도
// [논의 필요] 넣을 나라 정하기
// [논의 필요] 자산이 0이하일때만 매각가능 / 이때 땅없으면 파산
// 턴 횟수 제한 추가 : 모든 플레이어 한번씩 하는 것 기준 1턴 - 30턴 제한. 30턴 초과 시 자산이 많은 사람이 승자
// 땅 매각 시 가격은 땅가격과 동일
