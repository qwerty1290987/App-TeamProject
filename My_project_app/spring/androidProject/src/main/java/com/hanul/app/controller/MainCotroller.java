package com.hanul.app.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.hanul.app.dao.AnDAO;
import com.hanul.app.dto.BoardListDTO;
import com.hanul.app.dto.GymDTO;
import com.hanul.app.dto.MemberDTO;
import com.hanul.app.dto.TimerListDTO;
import com.hanul.app.dto.TimerListIComDTO;
import com.hanul.app.dto.TrainerDTO;

@Controller
public class MainCotroller {
	
	Gson gson = new Gson();
	

	@RequestMapping(value = "/updateBoard" , method = {RequestMethod.GET , RequestMethod.POST} )
	public void updateBoard(HttpServletRequest req , Model model) {
		
		System.out.println("updateBoard() 실행됨 ");
		
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		
		
		
		AnDAO dao = new AnDAO();
		dao.updateBoard(id, title, body);
		
	
	}
	
	
	@RequestMapping(value = "/boardList" , method = {RequestMethod.GET , RequestMethod.POST} )
	public String boardList(HttpServletRequest req , Model model) {
		
		System.out.println("boardList() 실행됨 ");
		
		String id = req.getParameter("id");
		
		AnDAO dao = new AnDAO();
		ArrayList<BoardListDTO> dtos = dao.boardList(id);
		
		model.addAttribute("boardList" , dtos);
		
		return "boardList";
		
	}
	
	
	@RequestMapping(value = "/boardMyList" , method = {RequestMethod.GET , RequestMethod.POST} )
	public String boardMyList(HttpServletRequest req , Model model) {
		
		System.out.println("boardMyList() 실행됨 ");
		
		String id = req.getParameter("id");
		
		AnDAO dao = new AnDAO();
		ArrayList<BoardListDTO> dtos = dao.boardMyList(id);
		
		model.addAttribute("boardList" , dtos);
		
		return "boardList";
		
	}
	
	
	@RequestMapping(value = "/timerDelete" , method = {RequestMethod.GET , RequestMethod.POST} )
	public void timerDelete(HttpServletRequest req , Model model) {
		
		System.out.println("timerDelete() 실행됨 ");
		
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		
		AnDAO dao = new AnDAO();
		dao.timerDelete(id , title);
		
		
	}
	
	
	@RequestMapping(value = "/timerSave", method = {RequestMethod.GET, RequestMethod.POST} )
	public void timerSave(HttpServletRequest req, Model model) {
		System.out.println("timerSave() 실행됨");
		
		
		String hourString = req.getParameter("hour");	
		String minuitString = req.getParameter("minuit");	
		String secondString = req.getParameter("second");
		String title = req.getParameter("title"); 
		String id = req.getParameter("id");
		
		int hour = 0;
		if (hourString.equals("")) {
			hour = 0;
		}else if (true ) {
			hour = Integer.parseInt(hourString);
		}
		
		
		int minuit = 0;
		if (minuitString.equals("")) {
			minuit = 0;
		}else if (true ) {
			minuit = Integer.parseInt(minuitString);
		}
		
		
		int second = 0;
		if (secondString.equals("")) {
			second = 0;
		}else if (true ) {
			second = Integer.parseInt(secondString);
		}
		
		
		if ( second >= 60 ) { 
			minuit = minuit + 1;
			second = second - 60;
		}
		if ( minuit >= 60 ) { 
			hour = hour + 1;
			minuit = minuit - 60;
		}
		
		
		System.out.println(second);
		System.out.println(minuit);
		System.out.println(hour);
		System.out.println(id);
		
		AnDAO dao = new AnDAO();
		dao.timerSave(id , title , hour , minuit , second);
		
	//	model.addAttribute("trainerList", dtos); 
		
		
	}	
	@RequestMapping(value = "/timerList", method = {RequestMethod.GET, RequestMethod.POST} )
	public String timerList(HttpServletRequest req, Model model) {
		System.out.println("timerList() 실행됨");
		
		
		String id = req.getParameter("id");

		System.out.println("파라미터 잘왔냐? " + id);
		
		AnDAO dao = new AnDAO();
	    ArrayList<TimerListDTO> dtos = dao.timerList(id);
	    
		
	    model.addAttribute("timerList", dtos); 
		
		return "timerList";
	}	
	@RequestMapping(value = "/timerComReadeList", method = {RequestMethod.GET, RequestMethod.POST} )
	public String timerComReadeList(HttpServletRequest req, Model model) {
		System.out.println("timerComReadeList() 실행됨");
		
		
		String id = req.getParameter("id");
		
		System.out.println("파라미터 잘왔냐? " + id);
		
		AnDAO dao = new AnDAO();
		ArrayList<TimerListIComDTO> dtos = dao.timerComReadeList(id);
		
		
		model.addAttribute("timerComReadeList", dtos); 
		
		return "timerComReadeList";
	}	
	@RequestMapping(value = "/timerUpdate", method = {RequestMethod.GET, RequestMethod.POST} )
	public void timerUpdate(HttpServletRequest req, Model model) {
		System.out.println("timerUpdate() 실행됨");
		
		
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		
		System.out.println("파라미터 잘왔냐? " + id);
		
		AnDAO dao = new AnDAO();
		dao.timerUpdate(id , title);
		
		
	}	
	
	
	@RequestMapping(value = "/anJoin", method = {RequestMethod.GET, RequestMethod.POST} )
	public void anJoin(HttpServletRequest req, Model model) {
		System.out.println("anJoin() 실행됨");
		
		// 서버<->클라이언트(안드)
		// 통신을할때 데이터 이동을 String으로 함 
		
		// 데이터 받는 부분
		String id =  req.getParameter("id");
		String pw =  req.getParameter("pw");
		String name =  req.getParameter("name");
		String phone =  req.getParameter("phone");
		String email =  req.getParameter("email");

//		System.out.println(id + ", " +pw + ", " +name + ", " + email + ", " + phone );
		
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setEmail(email);
		dto.setPhone(phone);
		
		//2. 한번 찍어본다
		System.out.println(dto.getId());
		System.out.println(dto.getPw());
		System.out.println(dto.getName());
		System.out.println(dto.getEmail());	
		System.out.println(dto.getPhone());
		/*
		// 사진 받는 부분
		MultipartFile file = mReq.getFile("file");
		String fileName = "";
		if(file != null) {
			fileName = file.getOriginalFilename();
			System.out.println(fileName);
			fileName = "My" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
			System.out.println(fileName);
			// 디렉토리 존재하지 않으면 생성
			makeDir(req);	
				
			if(file.getSize() > 0){			
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/");
				
				System.out.println( fileName + " : " + realImgPath);
				System.out.println( "fileSize : " + file.getSize());					
												
			 	try {
			 		// 이미지파일 저장
					file.transferTo(new File(realImgPath, fileName));										
				} catch (Exception e) {
					e.getMessage();
				} 
									
			}else{
				// 이미지파일 실패시
				fileName = "FileFail.jpg";
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/" + fileName);
				System.out.println(fileName + " : " + realImgPath);
						
			}		
		}
		
		// dto의 profile값을 이미지 파일 이름으로 변경한다
		dto.setProfile(fileName);
		*/
		// 데이터베이스에 저장한다
		AnDAO dao = new AnDAO();
		dao.anJoin(dto);
		
	}	
	
	//로그인 기능
	@RequestMapping(value = "/anLogin", method = {RequestMethod.GET, RequestMethod.POST} )
	public String anLogin(HttpServletRequest req, Model model) {
		System.out.println("anLogin() 실행됨");
		
		// 안드로이드에서 받은 데이터 저장하기 
		String id = (String) req.getParameter("id");
		String pw = (String) req.getParameter("pw");
		System.out.println("id : " + id + ", pw : " + pw);
		
		// 로그인 메소드 실행 
		AnDAO dao = new AnDAO();
		MemberDTO dto = dao.anLogin(id, pw);
		
		
		model.addAttribute("anLogin", dto); 
		
	
		
		return "anLogin";
	}	
	
	@RequestMapping(value = "/selectMembers", method = {RequestMethod.GET, RequestMethod.POST} )
	public String selectMembers(HttpServletRequest req, Model model) {
		System.out.println("selectMembers() 실행됨");
		
		
		
		String id = (String) req.getParameter("1");		
		System.out.println("성공했냐?" + id );
		
		
		AnDAO dao = new AnDAO();
		ArrayList<GymDTO> dtos = dao.selectMembers();
		
		model.addAttribute("selectMembers", dtos); 
		
		return "selectMembers";
	}	
	
	@RequestMapping(value = "/trainerList", method = {RequestMethod.GET, RequestMethod.POST} )
	public String trainerList(HttpServletRequest req, Model model) {
		System.out.println("trainerList() 실행됨");
		
		
		
		String id = (String) req.getParameter("1");		
		System.out.println("성공했냐?" + id );
		
		
		AnDAO dao = new AnDAO();
		ArrayList<TrainerDTO> dtos = dao.trainerList();
		
		model.addAttribute("trainerList", dtos); 
		
		return "trainerList";
	}	
	@RequestMapping(value = "/trainerofgym", method = {RequestMethod.GET, RequestMethod.POST} )
	public String trainerofgym(HttpServletRequest req, Model model) {
		System.out.println("trainerofgym() 실행됨");
		
		
		String param = req.getParameter("gym_id");	
		int id = Integer.parseInt(param);
		System.out.println("성공했냐?" + id );
		
		
		AnDAO dao = new AnDAO();
		ArrayList<TrainerDTO> dtos = dao.trainerofgym(id);
		
		model.addAttribute("trainerList", dtos); 
		
		return "trainerList";
	}	
	@RequestMapping(value = "/trainersgym", method = {RequestMethod.GET, RequestMethod.POST} )
	public String trainersgym(HttpServletRequest req, Model model) {
		System.out.println("trainerofgym() 실행됨");
		
		
		String gym_name = req.getParameter("gym_name");	
		String gym = req.getParameter("gym");
		System.out.println("성공했냐?" + gym_name + gym );
		
		
		AnDAO dao = new AnDAO();
		ArrayList<GymDTO> dtos = dao.trainersgym(gym_name);
		
		model.addAttribute("selectMembers", dtos); 
		
		return "selectMembers";
	}	
	
	public void makeDir(HttpServletRequest req){
		File f = new File(req.getSession().getServletContext()
				.getRealPath("/resources"));
		if(!f.isDirectory()){
			f.mkdir();
		}	
	}
	
	@RequestMapping(value = "/file.f", produces = "text/html;charset=utf-8")
	public String file(MultipartRequest mReq, HttpServletRequest req) {
		// ���� ������ ���� �� mReq.getFileMap().get("")

		MultipartFile file = mReq.getFile("file");
		if(file != null) {
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			fileName = "My" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
			System.out.println(fileName);
			// ���丮 �������� ������ ����
			makeDir(req);	
				
			if(file.getSize() > 0){			
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/");
				
				System.out.println( fileName + " : " + realImgPath);
				System.out.println( "fileSize : " + file.getSize());					
												
			 	try {
			 		// �̹������� ����
					file.transferTo(new File(realImgPath, fileName));										
				} catch (Exception e) {
					e.printStackTrace();
				} 
									
			}else{
				// �̹������� ���н�
				fileName = "FileFail.jpg";
				String realImgPath = req.getSession().getServletContext()
						.getRealPath("/resources/" + fileName);
				System.out.println(fileName + " : " + realImgPath);
						
			}			
		
		}				
		
		return "";
	}
	
	
	public String fileUpload(String category, MultipartFile file, HttpServletRequest request) {
		// ���ε��� ������ ��ġ
		// D:\Study_Spring\Workspace\.metadata\.plugins\org...er.core\tmp1\wtpwebapps\iot\resources
		// String path =
		// request.getSession().getServletContext().getRealPath("resources");
		// d://app/iot
		String path = "d://app" + request.getContextPath();

		// upload/profile/2022/08/23
		String upload = "/upload/" + category + new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
		// D:\Study_Spring\Wo....\iot\resources/upload/profile/2022/08/23
		path += upload;

		// �ش� ��� ������ ������ �����
		File folder = new File(path);
		if (!folder.exists())
			folder.mkdirs();

		// ���� ���ε�
		// ���ϸ� ����id�� ���δ�
		// dafqer32-g38fgfa_abc.png
		String uuid = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

		try {
			file.transferTo(new File(path, uuid));
		} catch (Exception e) {
		}

		// /upload/profile/2022/08/23/dafqer32-g38fgfa_abc.png
		return upload + "/" + uuid;
	}

	
	
	
	
}
