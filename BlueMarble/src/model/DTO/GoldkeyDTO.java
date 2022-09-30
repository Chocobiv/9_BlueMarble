package model.DTO;
//요기  teamProject 이 부분은 각자 패키지명으로 바꾸면 될것같아요!

public class GoldkeyDTO {

	private int c_num;
	private String c_name;
	private String c_coment;
	private boolean c_use;
	private int p_no;
	
	
	public GoldkeyDTO() {}


	public GoldkeyDTO(int c_num, String c_name, String c_coment, boolean c_use, int p_no) {
		super();
		this.c_num = c_num;
		this.c_name = c_name;
		this.c_coment = c_coment;
		this.c_use = c_use;
		this.p_no = p_no;
	}


	public int getC_num() {
		return c_num;
	}


	public void setC_num(int c_num) {
		this.c_num = c_num;
	}


	public String getC_name() {
		return c_name;
	}


	public void setC_name(String c_name) {
		this.c_name = c_name;
	}


	public String getC_coment() {
		return c_coment;
	}


	public void setC_coment(String c_coment) {
		this.c_coment = c_coment;
	}


	public boolean isC_use() {
		return c_use;
	}


	public void setC_use(boolean c_use) {
		this.c_use = c_use;
	}


	public int getP_no() {
		return p_no;
	}


	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	
}
