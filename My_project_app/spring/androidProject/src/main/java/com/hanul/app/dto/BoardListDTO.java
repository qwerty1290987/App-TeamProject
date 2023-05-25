package com.hanul.app.dto;

public class BoardListDTO {

	String  id , board_title , board_body , board_count;
	
	public BoardListDTO() {	}

	public BoardListDTO(String id, String board_Title, String board_body, String board_count) {
		super();
		this.id = id;
		this.board_title = board_Title;
		this.board_body = board_body;
		this.board_count = board_count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_Title) {
		this.board_title = board_Title;
	}

	public String getBoard_body() {
		return board_body;
	}

	public void setBoard_body(String board_body) {
		this.board_body = board_body;
	}

	public String getBoard_count() {
		return board_count;
	}

	public void setBoard_count(String board_count) {
		this.board_count = board_count;
	}
	
	
	
}
