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

		// 플레이어 말(흑말,백말) 표시
		System.out.println("------------------------------------------------------------------");

		String head = "╭─────────╮╭─────────╮╭─────────╮╭─────────╮╭─────────╮╭─────────╮\n\n";
		String foot = "╰─────────╯╰─────────╯╰─────────╯╰─────────╯╰─────────╯╰─────────╯\n";

		ArrayList<NationDTO> n = nCon.getNations();

		System.out.printf(head);
		int top = 10;
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n", n.get(top).getN_name(), n.get(top - 1).getN_name(),
				n.get(top - 2).getN_name(), n.get(top - 3).getN_name(), n.get(top - 4).getN_name(),
				n.get(top - 5).getN_name());
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n\n", "●", "●", "●", "●", "●", "●");
		System.out.printf(foot);

		// 14 , 7 , 15 , 6 , 16 , 5 , 17 , 4
		int[] center = { 11, 4, 12, 3, 13, 2, 14, 1 };
		for (int i = 0; i < center.length - 1; i += 2) {
			System.out.printf("╭─────────╮                                            ╭─────────╮\n\n");
			System.out.printf("%8s                                              %8s\n", n.get(center[i]).getN_name(),
					n.get(center[i + 1]).getN_name());
			System.out.printf("%8s                                              %8s\n\n", "●", "●");
			System.out.printf("╰─────────╯                                            ╰─────────╯\n");
		}

		System.out.printf(head);
		int bottom = 17;
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n", // 5 / 4 / 3 / 2 / 1
				n.get(bottom - 2).getN_name(), n.get(bottom - 1).getN_name(), n.get(bottom).getN_name(),
				n.get(bottom + 1).getN_name(), n.get(bottom + 2).getN_name(), n.get(bottom - 17).getN_name());
		System.out.printf("%8s  %8s  %8s  %8s  %8s  %8s  \n\n", "●", "●", "●", "●", "●", "●");
		System.out.printf(foot);

		System.out.println("------------------------------------------------------------------");

	}

}
