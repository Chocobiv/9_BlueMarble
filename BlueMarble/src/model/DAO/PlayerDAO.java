package model.DAO;

import java.sql.*;
import java.util.ArrayList;

import model.DTO.NationDTO;
import model.DTO.PlayerDTO;

public class PlayerDAO {
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;

	private static PlayerDAO dao = new PlayerDAO();

	private PlayerDAO() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team", "root", "1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static PlayerDAO getInstance() {
		return dao;
	}

	// 비아(9/29) - 플레이어 등록 메소드
	public boolean addPlayer(String name, int p_turn) {
		String sql = "insert into player values(null,?,600000,?,1)"; // SQL 작성
		try {
			ps = con.prepareStatement(sql); // SQL 연결/조작
			ps.setString(1, name);
			ps.setInt(2, p_turn);
			ps.executeUpdate(); // SQL 실행/결과조작
			return true; // 반환
		} catch (Exception e) {
			System.out.println("경고) 플레이어 추가 실패 : " + e);
		}
		return false;
	}

	// 비아(10/04) - 플레이어 정보 가져오는 메소드
	public PlayerDTO getPlayerInfo(int player) {
		String sql = "select * from player where p_no = ?";
		PlayerDTO dto = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, player);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto = new PlayerDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
			}
			return dto;
		} catch (Exception e) {
			System.out.println("경고) 플레이어 정보 가져오기 오류" + e);
		}
		return dto;
	}

	// 비아(9/29) - 누구 턴인지 가져오는 로직
	public int getWhoIsTurn() {
		String sql = "select p_no from player where p_turn = 0";
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			return result;
		} catch (Exception e) {
			System.out.println("경고) 턴 출력 오류" + e);
		}
		return result;
	}

	// 비아(9/29) - 플레이어의 현재 위치 가져오는 메소드
	public int getLocation(int player) {
		String sql = "select b_no from player where p_no = ?"; // SQL 작성
		int location = 0;
		try {
			ps = con.prepareStatement(sql); // SQL 연결/조작
			ps.setInt(1, player);
			rs = ps.executeQuery(); // SQL 실행/결과조작
			if (rs.next())
				location = rs.getInt(1);
			return location; // 반환
		} catch (Exception e) {
			System.out.println("경고) 플레이어 위치 가져오기 실패 : " + e);
		}
		return location;
	}

	// 비아(9/29) - 말 이동 메소드
	public boolean move(int player, int num) {
		String sql = "update player set b_no = ? where p_no = ?"; // SQL 작성
		try {
			ps = con.prepareStatement(sql); // SQL 연결/조작
			ps.setInt(1, num);
			ps.setInt(2, player);
			ps.executeUpdate(); // SQL 실행/결과조작
			return true; // 반환
		} catch (Exception e) {
			System.out.println("경고) 말 이동 실패 : " + e);
		}
		return false;
	}

	// 비아(9/29) - 플레이어의 턴 수 교체 로직
	public boolean changeTurn(int player) {
		String sql1 = "update player set p_turn = 0 where p_turn = 1"; // SQL 작성
		String sql2 = "update player set p_turn = 1 where p_no = ?"; // SQL 작성
		try {
			// 남은 턴 수가 1인 플레이어의 남은 턴 수를 0으로 수정
			ps = con.prepareStatement(sql1); // SQL 연결/조작
			ps.executeUpdate(); // SQL 실행/결과조작
			ps.executeUpdate(); // SQL 실행/결과조작

			// 주사위를 던진 플레이어의 남은 턴 수를 1으로 수정
			ps = con.prepareStatement(sql2); // SQL 연결/조작
			ps.setInt(1, player);
			ps.executeUpdate(); // SQL 실행/결과조작
			return true; // 반환
		} catch (Exception e) {
			System.out.println("경고) 말 이동 실패 : " + e);
		}
		return false;
	}

	// 유정
	public boolean donotmove(int player) {
		String sql = "update player set b_no = 16 where p_no = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, player);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("경고) 무인도에 갇혀있기 실패 : " + e);
		}
		return false;
	}

	// 유정 - 9. 월급 및 상금 지급 메소드 [U] 10만원+
	public boolean getPaid(int player, int pay) {
		String sql = "update player set p_money = p_money+? where p_no = ? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, pay);
			ps.setInt(2, player);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("월급 업데이트 오류" + e);
		}
		return false;
	}

	// 유정 - 10. 현금 지불
	public boolean payCash(int player, int cash) {
		String sql = "update player set p_money = p_money-? where p_no = ? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, cash);
			ps.setInt(2, player);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("돈 지불 업데이트 오류" + e);
		}
		return false;
	}

	// 수현 - 플레이어 자산 가져오기
	public int getPlayerMoney(int player) {
		String sql = "select p_money from player where p_no=?";
		int p_money = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, player);
			rs = ps.executeQuery();
			if (rs.next()) {
				p_money = rs.getInt(1);
			}
			return p_money;
		} catch (Exception e) {
			System.out.println("플레이어 자산 확인 오류 " + e);
		}
		return 0;
	}

	// 수현 - 통행료 내기 메소드
	public boolean payTollFee(int player, int n_toll_fee) {
		String sql = "update player set p_money= p_money-? where p_no=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, n_toll_fee);
			ps.setInt(2, player);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("통행료 지불 오류 " + e);
		}
		return false;
	}

	// 수현 - 통행료 얻기 메소드
	public boolean takeTollFee(int player, int n_toll_fee) {
		String sql = "update player set p_money= p_money+? where p_no != ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, n_toll_fee);
			ps.setInt(2, player);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("통행료 입금 오류 " + e);
		}
		return false;
	}

	// 수현 - 플레이어가 소유한 땅 정보 출력 메소드
	public ArrayList<NationDTO> getPlayerLand(int player) {
		String sql = "select * from nation where p_no=?";
		ArrayList<NationDTO> list = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, player);
			rs = ps.executeQuery();
			while (rs.next()) {
				NationDTO dto = new NationDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			System.out.println("플레이어 소유한 땅 정보 출력 오류 " + e);
		}
		return list;
	}

	// 유정, 수현
	// 무인도에 도착하면 무인도테이블에 업데이트
	public boolean moveDesertIsland(int player) {
		String sql = "insert into DesertLand values(3, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, player);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("무인도에서 두턴 기다리기 오류" + e);
		}
		return false;
	}

	// false면 애초에 무인도에 없는 플레이어,
	// 무인도에 갇혔는지 확인하면서 w_turn-1
	public boolean Island(int player) {
		String sql = "update DesertLand set w_turn= w_turn- 1 where p_no = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, player);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("무인도에갇힌사람 오류" + e);
		}
		return false;
	}

	// 무인도에 몇턴 갇혔는지 확인
	public int getwatingturn(int player) {
		String sql = "select w_turn from DesertLand where p_no = ?";
		int w_turn = -1;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, player);
			rs = ps.executeQuery();
			if (rs.next()) {
				w_turn = rs.getInt(1);
			}
			return w_turn;
		} catch (Exception e) {
			System.out.println("웨이팅 턴 밖 출력 오류 " + e);
		}
		return w_turn;
	}

	// 무인도탈출 - 테이블에서 제거
	public boolean escapeDesertIsland(int player) {
		String sql = "delete from DesertLand where p_no=?";
		try {

			ps = con.prepareStatement(sql);
			ps.setInt(1, player);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("무인도 탈출 오류 " + e);
		}
		return false;
	}

	// 비아(9/29) - 전체 플레이어 삭제 메소드
	public boolean deleteP() {
		String sql1 = "delete from player"; // SQL 작성
		String sql2 = "ALTER TABLE player AUTO_INCREMENT = 1"; // auto_increment 초기화
		try {
			ps = con.prepareStatement(sql1); // SQL 연결/조작
			ps.executeUpdate(); // SQL 실행/결과조작
			ps = con.prepareStatement(sql2); // SQL 연결/조작
			ps.executeUpdate(); // SQL 실행/결과조작
			return true; // 반환
		} catch (Exception e) {
			System.out.println("경고) 플레이어 삭제 실패 : " + e);
		}
		return false;
	}
}
