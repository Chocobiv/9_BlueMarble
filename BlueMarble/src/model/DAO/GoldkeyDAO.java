package model.DAO;

import java.sql.*;
import java.util.ArrayList;

import model.DTO.GoldkeyDTO;

public class GoldkeyDAO {
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;

	private static GoldkeyDAO dao = new GoldkeyDAO();

	private GoldkeyDAO() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team", "root", "123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static GoldkeyDAO getInstance() { return dao; }

	// 11. 플레이어가 소유하고 있는 황금열쇠 목록 가져오기 메소드

	public ArrayList<GoldkeyDTO> goldKeyList(int player) {
		String sql = "select c_num,c_name,c_coment from gold_key where p_no  = ?  and c_use = 0";
		ArrayList<GoldkeyDTO> list = new ArrayList<GoldkeyDTO>();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, player);
			rs = ps.executeQuery();
			while (rs.next()) { // rs에 결과값이 있을때 while문이 실행
				GoldkeyDTO dto = new GoldkeyDTO();
				dto.setC_num(rs.getInt(1));
				dto.setC_name(rs.getString(2));
				dto.setC_coment(rs.getString(3));
				list.add(dto);
			} // while end
			return list;
		} catch (Exception e) {
		}
		return list;
	}// goldKeyList end

	// ★비아추가★
	// 황금열쇠 소유자를 가져오는 메소드
	public int isPlayerGoldKey(int c_no) {
		String sql = "select p_no from gold_key where c_num = ?"; // SQL 작성
		int result = 0;
		try {
			ps = con.prepareStatement(sql); // SQL 연결/조작
			ps.setInt(1, c_no);
			rs = ps.executeQuery(); // SQL 실행/결과조작
			if (rs.next())
				result = rs.getInt(1);
			return result; // 반환
		} catch (Exception e) {
			System.out.println("경고) DAO:isPlayerGoldKey 실패 : " + e);
		}
		return result;
	}

	// 황금 열쇠 사용 가능 여부 확인 메소드
	// ★비아추가★
	public short isUsableGoldKey(int c_no) {
		String sql = "select c_use from gold_key where c_num = ?"; // SQL 작성
		short result = 0;
		try {
			ps = con.prepareStatement(sql); // SQL 연결/조작
			ps.setInt(1, c_no);
			rs = ps.executeQuery(); // SQL 실행/결과조작
			if (rs.next())
				result = rs.getShort(1);
			return result; // 반환
		} catch (Exception e) {
			System.out.println("경고) DAO:isUsableGoldKey 실패 : " + e);
		}

		return result;
	}

	// ★비아추가★
	// 황금 열쇠 사용 로직
	public boolean useGoldKey(int c_no) {
		// 황금 열쇠 사용여부 수정 로직 c_use를 1로
		String sql = "update gold_key set c_use = 1 where c_num = ?"; // SQL 작성
		try {
			ps = con.prepareStatement(sql); // SQL 연결/조작
			ps.setInt(1, c_no);
			ps.executeUpdate(); // SQL 실행/결과조작
			return true; // 반환
		} catch (Exception e) {
			System.out.println("경고) 황금열쇠 사용 실패 : " + e);
		}
		return false;
	}

}