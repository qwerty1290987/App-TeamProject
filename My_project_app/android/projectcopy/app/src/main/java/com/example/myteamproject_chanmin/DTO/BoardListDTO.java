package com.example.myteamproject_chanmin.DTO;

public class BoardListDTO {

    String id, board_title , board_body , board_num;

    public BoardListDTO(){}

    public BoardListDTO(String id, String board_title, String board_body, String board_num) {
        this.id = id;
        this.board_title = board_title;
        this.board_body = board_body;
        this.board_num = board_num;
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

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_body() {
        return board_body;
    }

    public void setBoard_body(String board_body) {
        this.board_body = board_body;
    }

    public String getBoard_num() {
        return board_num;
    }

    public void setBoard_num(String board_num) {
        this.board_num = board_num;
    }
}
