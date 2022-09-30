package model.DTO;

public class BoardDTO {
	
	private int b_no;
	private int n_no;
	
	public BoardDTO() {}

	
	
	public BoardDTO(int b_no, int n_no) {
		super();
		this.b_no = b_no;
		this.n_no = n_no;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public int getN_no() {
		return n_no;
	}

	public void setN_no(int n_no) {
		this.n_no = n_no;
	}
	
}
