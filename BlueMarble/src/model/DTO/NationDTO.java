package model.DTO;
//요기  teamProject 이 부분은 각자 패키지명으로 바꾸면 될것같아요!

public class NationDTO {

	private int n_no;
	private String n_name;
	private int n_price;
	private int n_toll_fee;
	private int p_no;
	private int b_no;		//플레이어 현재 위치
	
	public NationDTO() {}

	public NationDTO(int n_no, String n_name, int n_price, int n_toll_fee, int p_no) {
		super();
		this.n_no = n_no;
		this.n_name = n_name;
		this.n_price = n_price;
		this.n_toll_fee = n_toll_fee;
		this.p_no = p_no;
	}
	
	//전체 필드를 포함한 생성자
	public NationDTO(int n_no, String n_name, int n_price, int n_toll_fee, int p_no, int b_no) {
		super();
		this.n_no = n_no;
		this.n_name = n_name;
		this.n_price = n_price;
		this.n_toll_fee = n_toll_fee;
		this.p_no = p_no;
		this.b_no = b_no;
	}

	public int getN_no() {
		return n_no;
	}

	public void setN_no(int n_no) {
		this.n_no = n_no;
	}

	public String getN_name() {
		return n_name;
	}

	public void setN_name(String n_name) {
		this.n_name = n_name;
	}

	public int getN_price() {
		return n_price;
	}

	public void setN_price(int n_price) {
		this.n_price = n_price;
	}

	public int getN_toll_fee() {
		return n_toll_fee;
	}

	public void setN_toll_fee(int n_toll_fee) {
		this.n_toll_fee = n_toll_fee;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	
}
