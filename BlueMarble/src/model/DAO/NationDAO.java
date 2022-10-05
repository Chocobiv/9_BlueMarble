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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team", "root", "1234");
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
	
	// 수현 - 땅 매각 정보 업데이트
	public boolean saleLand(int player ,int n_no, int n_price) {
		String sql="update nation set p_no=null where n_no=? ";
		String sql2="update player set p_money=p_money+? where p_no=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, n_no);
			ps.executeUpdate();
	
			ps=con.prepareStatement(sql2);
			ps.setInt(1, n_price);
			ps.setInt(2, player);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("땅 매각 과정 오류 " +e);}
		return false;
	}
	
	// 비아 - [황금열쇠] 땅 소유자 삭제 로직
	public boolean deleteLandlord(int n_no) {
		String sql="update nation set p_no=null where n_no=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, n_no);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("경고) 땅 소유자 삭제 오류 " +e);}
		return false;
	}
	
	// 수현 - 올림픽 개최 로직
	public boolean hostingOlympics(int n_no) {
		String sql="update nation set n_toll_fee=n_toll_fee*2 where n_no=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, n_no);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("올림픽 개막 오류 " +e);}
		return false;
	}

	// 수현 - 올림픽 폐막 로직
	public boolean closingOlympics(int n_no) {
		String sql="update nation set n_toll_fee=n_toll_fee/2 where n_no=? ";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, n_no);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("올림픽 폐막 오류 " +e);}
		return false;
	}
	
	// 비아 - 플레이어가 소유한 땅의 개수 카운트 메소드
	public int countPlayerLand(int player) {
		String sql = "select count(*) from nation where p_no=?";
		int count = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, player);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (Exception e) { System.out.println("경고) 소유한 땅 개수 카운트 실패 " + e); }
		return count;
	}
	
}
