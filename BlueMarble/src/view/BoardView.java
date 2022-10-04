package view;

import java.util.ArrayList;

import controller.NationController;
import controller.PlayerController;
import model.DTO.NationDTO;

//유정 - 부루마블 판 출력
public class BoardView {

	public void showBoard() {

		NationController nCon = new NationController();
		PlayerController pCon = new PlayerController();
		// DB에서 nation 가져와서 각 판에 출력 [R]
		// start 지점의 인덱스는 1, 총 20칸

		// 플레이어 게임 현황(자산, 땅 목록) 출력 [R]

		// 플레이어 말 표시

		String head = "╭─────────╮╭─────────╮╭─────────╮╭─────────╮╭─────────╮╭─────────╮\n";
		String foot = "╰─────────╯╰─────────╯╰─────────╯╰─────────╯╰─────────╯╰─────────╯\n";

		ArrayList<NationDTO> n = nCon.getNations();
		
		//비아 - 플레이어 위치에 따른 말 위치 변경 구현
		int player1_location = pCon.getLocation(1);
		int player2_location = pCon.getLocation(2);
		String[] player1_piece = new String[20];
		String[] player2_piece = new String[20];
		for(int i=0;i<20;i++) {
			if(i == player1_location-1) {
				player1_piece[i] = "🐶";
			}else {
				player1_piece[i] = " "; 
			}
		}
		for(int i=0;i<20;i++) {
			if(i == player2_location-1) {
				player2_piece[i] = "🐹";
			}else {
				player2_piece[i] = " "; 
			}
		}
		
		//비아 - 플레이어가 소유한 집 표시 구현
		ArrayList<NationDTO> p1_list = pCon.getPlayerLand(1);
		ArrayList<NationDTO> p2_list = pCon.getPlayerLand(2);
		String[] player1_land = {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "};
		String[] player2_land = {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "};
		for(NationDTO p1_land : p1_list) {
			for(int i=0;i<20;i++) {
				if(player1_land[i]==null) player1_land[i] = " ";
				if(i==p1_land.getN_no()-1) {
					player1_land[i] = "🏴";
					continue;
				}else if(player1_land[i]!="🏴" && i!=p1_land.getN_no()-1) {
					player1_land[i] = " ";
				}
			}
		}
		for(NationDTO p2_land : p2_list) {
			for(int i=0;i<20;i++) {
				if(i==p2_land.getN_no()-1) {
					player2_land[i] = "🏳";
					continue;
				}else if(player2_land[i]!="🏳" && i!=p2_land.getN_no()-1) {
					player2_land[i] = " ";
				}
			}
		}
		
		System.out.printf(head);
		int top = 10;
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n", n.get(top).getN_name(), n.get(top - 1).getN_name(),
				n.get(top - 2).getN_name(), n.get(top - 3).getN_name(), n.get(top - 4).getN_name(),
				n.get(top - 5).getN_name());
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n", player1_piece[top], player1_piece[top-1], 
				player1_piece[top-2], player1_piece[top-3], player1_piece[top-4], player1_piece[top-5]);
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n", player2_piece[top], player2_piece[top-1], 
				player2_piece[top-2], player2_piece[top-3], player2_piece[top-4], player2_piece[top-5]);
		
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n", player1_land[top], player1_land[top-1], 
				player1_land[top-2], player1_land[top-3], player1_land[top-4], player1_land[top-5]);
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n", player2_land[top], player2_land[top-1], 
				player2_land[top-2], player2_land[top-3], player2_land[top-4], player2_land[top-5]);
		
		System.out.printf(foot);

		// 14 , 7 , 15 , 6 , 16 , 5 , 17 , 4
		int[] center = { 11, 4, 12, 3, 13, 2, 14, 1 };
		for (int i = 0; i < center.length - 1; i += 2) {
			System.out.printf("╭─────────╮                                            ╭─────────╮\n");
			System.out.printf("%8s                                              %8s\n", n.get(center[i]).getN_name(),
					n.get(center[i + 1]).getN_name());
			System.out.printf("%8s                                              %8s\n", player1_piece[center[i]], 
					player1_piece[center[i+1]]);
			System.out.printf("%8s                                              %8s\n", player2_piece[center[i]], 
					player2_piece[center[i+1]]);
			
			System.out.printf("%8s                                              %8s\n", player1_land[center[i]], 
					player1_land[center[i+1]]);
			System.out.printf("%8s                                              %8s\n", player2_land[center[i]], 
					player2_land[center[i+1]]);
			
			System.out.printf("╰─────────╯                                            ╰─────────╯\n");
		}

		System.out.printf(head);
		int bottom = 17;
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n", // 5 / 4 / 3 / 2 / 1
				n.get(bottom - 2).getN_name(), n.get(bottom - 1).getN_name(), n.get(bottom).getN_name(),
				n.get(bottom + 1).getN_name(), n.get(bottom + 2).getN_name(), n.get(bottom - 17).getN_name());
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n", // 5 / 4 / 3 / 2 / 1
				player1_piece[bottom - 2], player1_piece[bottom - 1], player1_piece[bottom],
				player1_piece[bottom + 1], player1_piece[bottom + 2], player1_piece[bottom - 17]);
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n", // 5 / 4 / 3 / 2 / 1
				player2_piece[bottom - 2], player2_piece[bottom - 1], player2_piece[bottom],
				player2_piece[bottom + 1], player2_piece[bottom + 2], player2_piece[bottom - 17]);
		
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n", // 5 / 4 / 3 / 2 / 1
				player1_land[bottom - 2], player1_land[bottom - 1], player1_land[bottom],
				player1_land[bottom + 1], player1_land[bottom + 2], player1_land[bottom - 17]);
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n", // 5 / 4 / 3 / 2 / 1
				player2_land[bottom - 2], player2_land[bottom - 1], player2_land[bottom],
				player2_land[bottom + 1], player2_land[bottom + 2], player2_land[bottom - 17]);
		
		System.out.printf(foot);


	}

}
