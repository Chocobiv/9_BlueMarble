package model.DTO;
//요기  teamProject 이 부분은 각자 패키지명으로 바꾸면 될것같아요!

public class PlayerDTO {

	private int p_no;
	private String p_name;
	private int p_money;
	private int p_turn;
	private int b_no;
	private int p_switch;
	
	public PlayerDTO() {}

	public PlayerDTO(int p_no, String p_name, int p_money, int p_turn, int b_no) {
		super();
		this.p_no = p_no;
		this.p_name = p_name;
		this.p_money = p_money;
		this.p_turn = p_turn;
		this.b_no = b_no;
	}
	
	public PlayerDTO(int p_no, String p_name, int p_money, int p_turn, int b_no, int p_switch) {
		super();
		this.p_no = p_no;
		this.p_name = p_name;
		this.p_money = p_money;
		this.p_turn = p_turn;
		this.b_no = b_no;
		this.p_switch = p_switch;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_money() {
		return p_money;
	}

	public void setP_money(int p_money) {
		this.p_money = p_money;
	}

	public int getP_turn() {
		return p_turn;
	}

	public void setP_turn(int p_turn) {
		this.p_turn = p_turn;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public int getP_switch() {
		return p_switch;
	}

	public void setP_switch(int p_switch) {
		this.p_switch = p_switch;
	}
	
	
	
}
