package model.DAO;

import java.sql.*;
import java.util.ArrayList;

import model.DTO.NationDTO;

public class NationDAO {
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;

	private static NationDAO dao = new NationDAO();

	private NationDAO() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team", "root", "123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static NationDAO getInstance() { return dao; }

	// 전체 나라 가져오는 메소드
	public ArrayList<NationDTO> getNations() {
		String sql = "select * from nation";
		NationDTO dto = null;
		ArrayList<NationDTO> list = new ArrayList<>();

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto = new NationDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			System.out.println("나라 정보 출력 오류 " + e);
		}
		return list;
	}

	// 수현(9/30) n_no맞는 땅정보 한번에 가져오기 위한 메소드 생성 // 소유자존재 여부는 따로!
	public NationDTO getNationInfo(int n_no) {
		String sql = "select * from nation where n_no=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, n_no);
			rs = ps.executeQuery();
			if (rs.next()) {
				NationDTO dto = new NationDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
				return dto;
			}
		} catch (Exception e) {
			System.out.println("땅 정보 출력 오류 " + e);
		}
		return null;
	}

	// 수현 - 이동한 땅의 주인 존재 여부 확인 로직
	public int isExistLandlord(int n_no) {
		String sql = "select p_no from nation where n_no=?";
		int p_no = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, n_no);
			rs = ps.executeQuery();
			if (rs.next()) {
				p_no = rs.getInt(1);
			}
			return p_no;
		} catch (Exception e) {
			System.out.println("땅 주인 확인 오류 " + e);
		}
		return p_no;
	}

	// 수현 - 땅 구매 로직 // 플레이어 자산 업데이트까지
	public boolean buyLand(int player, int n_no, int n_price) {
		String sql = "update nation set p_no=? where n_no=?";
		String sql1 = "update player set p_money= p_money-? where p_no=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, player);
			ps.setInt(2, n_no);
			ps.executeUpdate();

			ps = con.prepareStatement(sql1);
			ps.setInt(1, n_price);
			ps.setInt(2, player);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("땅 구매 오류 " + e);
		}
		return false;
	}
}
