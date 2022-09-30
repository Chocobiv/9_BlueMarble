package view;

import java.util.ArrayList;

import controller.NationController;
import controller.PlayerController;
import model.DTO.NationDTO;


//부루마블 판 출력
public class BoardView {
	public void showBoard() {
		NationController nCon = new NationController();
		PlayerController pCon = new PlayerController();
		// DB에서 nation 가져와서 각 판에 출력 [R]
		// start 지점의 인덱스는 1, 총 20칸
		
		// 플레이어 게임 현황(자산, 땅 목록) 출력 [R]
		
		// 플레이어 말(흑말,백말) 표시
		System.out.println("----------------------------------매옹 시작----------------------------------");
        // 될거같은데.. 왜 안될까 \r \n 소용없는듯ㅠ
	    String 나라 = "네글자임";
	    String 머리 =   "╭─────────╮";            String 막줄머리 = "╭─────────╮\n";
	    String 원 =    "           ";                String 막줄원 = "           \n";
	    String 투 =    " "+나라+"  ";                 String 막줄투 = "    "+나라+" \n";
	    String 쓰리 =   " arrays[n] ";              String 막줄쓰리 = " arrays[n] \n";
	    String 포 =    "     ●     ";                String 막줄포 = "     ●     \n";
	    String 파이브 = "           ";              String 막줄파이브 = "           \n";
	    String 발 =   "╰─────────╯";                String 막줄발 = "╰─────────╯\n";
        
	    ArrayList<NationDTO> nation_list = nCon.getNations();
	    String[] nation_names = new String[20];
	    for(int i=0;i<nation_list.size();i++) {
	    	nation_names[i] = nation_list.get(i).getN_name();
	    }
	    
	    
		for (int i = 10; i > 4; i--) {
			System.out.print("╭─────────╮   ");
		}
		System.out.println();
		//System.out.print("   ");
		for (int i = 10; i > 4; i--) {
			System.out.print("     "+nation_names[i]+"    ");
		}
		System.out.println();
		for (int i = 10; i > 4; i--) {
			if(i == pCon.getLocation(1))		//플레이어1의 b_no과 i가 같으면
				System.out.printf("♣");

			//else
				//System.out.printf("%5s");  칸 맞추려면 printf 써야함 %5s 

		}
		System.out.println();
		for (int i = 10; i > 4; i--) {
			if(i == pCon.getLocation(2))		//플레이어2의 b_no과 i가 같으면
				System.out.print("★");
			else
				System.out.print("\t");
		}
		System.out.println();
		for (int i = 10; i > 4; i--) {
			System.out.print("╰─────────╯   ");
		}
		System.out.println();
	    
	    
        // 만약 이차원배열 6/6이 아니라 42/6 이면 가능하지 않나? [][6] 만 /n 쓰고...
        String[][] 사삼 = new String[42][6];
        for (int i = 0; i < 사삼.length; i++) {
            for (int k = 0; k < 사삼[i].length; k++) {
               // [][5] 제외한 모든 칸
               if( i == 0 && k <= 4 || i % 7 == 0 && k <= 4 ) {
                   사삼[i][k] = 머리;   
               }if( i % 7 == 1 && k <= 4 ) {
                  사삼[i][k] = 원;
               }if( i % 7 == 2 && k <= 4 ) {
                  사삼[i][k] = "  "+투;
               }if( i == 1 && k <= 4 && k >= 1 ||  i > 6 && i <= 34 && k <= 4 && k >= 1  ) {
                  사삼[i][k] = 원;            // 투부분 공백으로
               }if( i % 7 == 3 && k <= 4 ) {
                  사삼[i][k] = 쓰리;
               }if( i > 6 && i <= 34 && k <= 4 && k >= 1) {
                  사삼[i][k] = 원;            // 쓰리부분 공백으로
               }if( i % 7 == 4 && k <= 4 ) {
                  사삼[i][k] = 포;
               }if( i > 6 && i <= 34 && k <= 4 && k >= 1) {
                  사삼[i][k] = 원;            // 포부분 공백으로
               }if( i % 7 == 5 && k <= 4 ) {
                  사삼[i][k] = 파이브;
               }if( i % 7 == 6 && k <= 4 ) {
                  사삼[i][k] = 발;
               }if( i > 6 && i <= 34  && k <= 4 && k >= 1 ) {
                      사삼[i][k] = 원;      // 발부분 공백으로
                   
                
                  // [][5] 줄
               }if( k == 5 && i % 7 == 0 ) {
                  사삼[i][k] = 막줄머리;
               }if( k == 5 && i % 7 == 1 ) {
                  사삼[i][k] = 막줄원;
               }if( k == 5 && i % 7 == 2 ) {
                  사삼[i][k] = 막줄투;
               }if( k == 5 && i % 7 == 3 ) {
                  사삼[i][k] = 막줄쓰리;
               }if( k == 5 && i % 7 == 4 ) {
                  사삼[i][k] = 막줄포;
               }if( k == 5 && i % 7 == 5 ) {
                  사삼[i][k] = 막줄파이브;
               }if( k == 5 && i % 7 == 6 ) {
                  사삼[i][k] = 막줄발;
               }

               System.out.print(사삼[i][k]);
            }
        }
        System.out.println("----------------------------------매옹 끝----------------------------------");
	}
}
