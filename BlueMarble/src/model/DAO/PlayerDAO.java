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

	// **NationDAO로 이동 필요**
	// 전체 나라 가져오는 메소드
	public ArrayList<NationDTO> getNations() {
		return null;
	}

	// 플레이어 등록 메소드
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

	// 누구 턴인지 가져오는 로직
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
			System.out.println("턴 출력 오류" + e);
		}
		return result;
	}

	// **플레이어의 현재 위치 가져오는 메소드**
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

	// 말 이동 메소드
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

	// **플레이어의 턴 수 교체 로직**
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
	   //수현 - 플레이어 자산 가져오기 
	   public int getPlayerMoney(int player) {
	      String sql="select p_money from player where p_no=?";
	      int p_money=0;
	      try {
	         ps=con.prepareStatement(sql);
	         ps.setInt(1, player);
	         rs=ps.executeQuery();
	         if(rs.next()) {
	            p_money=rs.getInt(1);
	         }
	         return p_money;
	      } catch (Exception e) {System.out.println("플레이어 자산 확인 오류 " +e);}
	      return 0;
	   }
	   
	   
	// 통행료 얻기 메소드
	boolean takeTollFee(int player, int tollFee) {
		return false;
	}

	// 전체 플레이어 삭제 메소드
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
