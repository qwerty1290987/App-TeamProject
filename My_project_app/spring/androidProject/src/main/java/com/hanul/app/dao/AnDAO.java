package com.hanul.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hanul.app.dto.BoardListDTO;
import com.hanul.app.dto.GymDTO;
import com.hanul.app.dto.MemberDTO;
import com.hanul.app.dto.TimerListDTO;
import com.hanul.app.dto.TimerListIComDTO;
import com.hanul.app.dto.TrainerDTO;

public class AnDAO {
	DataSource dataSource;

	public AnDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/teamAll");
			/*dataSource = (DataSource) context.lookup("java:/comp/env/CSS");*/
		} catch (NamingException e) {
			e.getMessage();
		}

	}
	
	//7. 회원가입 : command에서 넘어온 값을 받는다
	public int anJoin(MemberDTO dto) {
		
		// 데이터베이스와 연결하여 원하는 결과물을 얻는다
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int state = -100;
		
		
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into member(id, pw, name, phone, email) " + 
			               "values('" + dto.getId() + "', '" + dto.getPw() + "', '" + dto.getName() + "', '" + 
					        			dto.getPhone() + "', '" + dto.getEmail() + "' )";
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
			
			if (state > 0) {
				System.out.println(state + "삽입성공");				
			} else {
				System.out.println(state + "삽입실패");
			}
			
		} catch (Exception e) {			
			System.out.println("에러 :"+e.getMessage());
		} finally {
			try {				
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}

		//8. 원하는 값을 리턴시킨다
		return state;
		
	}
	
	public MemberDTO anLogin(String idin, String pwin) {

    	MemberDTO adto = null;
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select id , name ,email , phone"					
							+ " from member" 
							+ " where id = '" + idin + "' and pw = '" + pwin + "' ";
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email"); 
				String phone = resultSet.getString("phone");

				adto = new MemberDTO(id, name, email, phone );							
			}	
			
			System.out.println("MemberDTO id : " + adto.getId());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}

		return adto;

	}
	
		public ArrayList<GymDTO> selectMembers() {
		
		ArrayList<GymDTO> dtos = new ArrayList<GymDTO>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from gym"	;	
							
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				int gym_id = resultSet.getInt("gym_id");
				String gym_name = resultSet.getString("gym_name");
				String address = resultSet.getString("address");
				String telephone_number = resultSet.getString("telephone_number");
				int gym_price = resultSet.getInt("gym_price");
				String gym_picture = resultSet.getString("gym_picture"); 

				dtos.add(new GymDTO(gym_id, gym_name, address, telephone_number,gym_price,  gym_picture));							
			}	
			
			System.out.println("dtos size : " + dtos.size());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}

		return dtos;
		
	}
		public ArrayList<GymDTO> trainersgym(String trainersgymname) {
			
			ArrayList<GymDTO> dtos = new ArrayList<GymDTO>();
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;		
			
			
			
			try {
				connection = dataSource.getConnection();
				String query = "select * from gym "
						+ " where gym_name = '" + trainersgymname + "' "	;	
				
				prepareStatement = connection.prepareStatement(query);
				resultSet = prepareStatement.executeQuery();
				
				while (resultSet.next()) {
					int gym_id = resultSet.getInt("gym_id");
					String gym_name = resultSet.getString("gym_name");
					String address = resultSet.getString("address");
					String telephone_number = resultSet.getString("telephone_number");
					int gym_price = resultSet.getInt("gym_price");
					String gym_picture = resultSet.getString("gym_picture"); 
					
					dtos.add(new GymDTO(gym_id, gym_name, address, telephone_number,gym_price,  gym_picture));							
				}	
				
				System.out.println("dtos size : " + dtos.size());
				
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			} finally {
				try {			
					
					if (resultSet != null) {
						resultSet.close();
					}
					if (prepareStatement != null) {
						prepareStatement.close();
					}
					if (connection != null) {
						connection.close();
					}	
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					
				}
			}
			
			return dtos;
			
		}

		public ArrayList<TrainerDTO> trainerList() {
			

			ArrayList<TrainerDTO> dtos = new ArrayList<TrainerDTO>();
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;		
			
			try {
				connection = dataSource.getConnection();
				String query = ""
						+ "select g.gym_name ,  t.trainer_name, t.phone_number , t.price ,t.trainer_picture "
						+ " from gym g , trainer t  "
						+ " where g.gym_id = t.gym_id  "	;	
								
				prepareStatement = connection.prepareStatement(query);
				resultSet = prepareStatement.executeQuery();
				
				while (resultSet.next()) {
					
					String gym_name = resultSet.getString("gym_name");
					String trainer_name = resultSet.getString("trainer_name");
					String phone_number = resultSet.getString("phone_number");
					int price = resultSet.getInt("price");
					String gym_picture = resultSet.getString("trainer_picture"); 

					dtos.add(new TrainerDTO(gym_name, trainer_name, phone_number,
							price, gym_picture));							
				}	
				
				System.out.println("dtos size : " + dtos.size());
				
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			} finally {
				try {			
					
					if (resultSet != null) {
						resultSet.close();
					}
					if (prepareStatement != null) {
						prepareStatement.close();
					}
					if (connection != null) {
						connection.close();
					}	

				} catch (Exception e) {
					e.printStackTrace();
				} finally {

				}
			}
			
			
			return dtos;
		}
		
		public ArrayList<TrainerDTO> trainerofgym(int id) {
			
			
			ArrayList<TrainerDTO> dtos = new ArrayList<TrainerDTO>();
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;		
			
			try {
				connection = dataSource.getConnection();
				String query = ""
						+ "select g.gym_name ,  t.trainer_name, t.phone_number , t.price ,t.trainer_picture "
						+ " from gym g , trainer t  "
						+ " where g.gym_id = t.gym_id  "
						+ " and g.gym_id = " + id;	
				
				prepareStatement = connection.prepareStatement(query);
				resultSet = prepareStatement.executeQuery();
				
				while (resultSet.next()) {
					
					String gym_name = resultSet.getString("gym_name");
					String trainer_name = resultSet.getString("trainer_name");
					String phone_number = resultSet.getString("phone_number");
					int price = resultSet.getInt("price");
					String gym_picture = resultSet.getString("trainer_picture"); 
					
					dtos.add(new TrainerDTO(gym_name, trainer_name, phone_number,
							price, gym_picture));							
				}	
				
				System.out.println("dtos size : " + dtos.size());
				
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			} finally {
				try {			
					
					if (resultSet != null) {
						resultSet.close();
					}
					if (prepareStatement != null) {
						prepareStatement.close();
					}
					if (connection != null) {
						connection.close();
					}	
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					
				}
			}
			
			
			return dtos;
		}


	
		public void timerSave(String id , String title , int hour , int minuit , int second) {
			
			
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;		
			
			int state1 = 0;
			int state2 = 0;
			
			try {
				connection = dataSource.getConnection();
				
				String query1 = "INSERT INTO timerlist ( id , title , hour , minuit , second ) "
						+ " VALUES ( '" + id + "' ," 
						+ " '" + title + "' ," 
						+ " '" + String.valueOf(hour) + "' ,"
						+ " '" + String.valueOf(minuit) + "' ,"
						+ " '" + String.valueOf(second) + "' "
						+ ")" ;	
				prepareStatement = connection.prepareStatement(query1);
				state1 = prepareStatement.executeUpdate();
				
				
				String query2 = "INSERT INTO timerlistcom ( id , title , hour , minuit , second , reference) "
						+ " VALUES ( '" + id + "' ," 
						+ " '" + title + "' ," 
						+ " '" + String.valueOf(hour) + "' ,"
						+ " '" + String.valueOf(minuit) + "' ,"
						+ " '" + String.valueOf(second) + "' ,"
						+ " 0"
						+ ")" ;	
				prepareStatement = connection.prepareStatement(query2);
				state2 = prepareStatement.executeUpdate();
				
				
				

				
				
				if (state1 > 0) {
					System.out.println(state1 + "타이머 리스트 삽입성공");				
				} else {
					System.out.println(state1 + "타이머 리스트 삽입실패");
				}
				if (state2 > 0) {
					System.out.println(state2 + "타이머 리스트 컴 삽입성공");				
				} else {
					System.out.println(state2 + "타이머 리스트 컴 삽입실패");
				}
				
				System.out.println(" 인설트 성공했냐? " );
				
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			} finally {
				try {			
					
					if (resultSet != null) {
						resultSet.close();
					}
					if (prepareStatement != null) {
						prepareStatement.close();
					}
					if (connection != null) {
						connection.close();
					}	
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					
				}
			}
			
			
		}

		

		public ArrayList<TimerListDTO> timerList(String id) {
			
			ArrayList<TimerListDTO> dtos = new ArrayList<TimerListDTO>();
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;		
			
			try {
				connection = dataSource.getConnection();
				String query = "select * "
						+ " from timerlist  "
						+ " where id =  '" + id + "' " 	;	
								
				prepareStatement = connection.prepareStatement(query);
				resultSet = prepareStatement.executeQuery();
				
				while (resultSet.next()) {
					
					String timerId = resultSet.getString("id");
					String title = resultSet.getString("title"); 
					String hour = resultSet.getString("hour");
					String minuit = resultSet.getString("minuit");
					String second = resultSet.getString("second");
				

					dtos.add(new TimerListDTO(timerId, title, hour, minuit, second));							
				}	
				
				System.out.println("dtos size : " + dtos.size());
				
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			} finally {
				try {			
					
					if (resultSet != null) {
						resultSet.close();
					}
					if (prepareStatement != null) {
						prepareStatement.close();
					}
					if (connection != null) {
						connection.close();
					}	

				} catch (Exception e) {
					e.printStackTrace();
				} finally {

				}
			}
			
			
			return dtos;
			
		}
		
	public ArrayList<TimerListIComDTO> timerComReadeList(String id) {
			
			ArrayList<TimerListIComDTO> dtos = new ArrayList<TimerListIComDTO>();
			Connection connection = null;
			PreparedStatement prepareStatement = null;
			ResultSet resultSet = null;		
			
			try {
				connection = dataSource.getConnection();
				String query = "select * "
						+ " from timerlistcom  "
						+ " where id =  '" + id + "' ";	
								
				prepareStatement = connection.prepareStatement(query);
				resultSet = prepareStatement.executeQuery();
				
				while (resultSet.next()) {
					
					String timerId = resultSet.getString("id");
					String title = resultSet.getString("title"); 
					String hour = resultSet.getString("hour");
					String minuit = resultSet.getString("minuit");
					String second = resultSet.getString("second");
					int reference = resultSet.getInt("reference");
				

					dtos.add(new TimerListIComDTO(timerId, title, hour, minuit, second ,reference));							
				}	
				
				System.out.println("dtos size : " + dtos.size());
				
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			} finally {
				try {			
					
					if (resultSet != null) {
						resultSet.close();
					}
					if (prepareStatement != null) {
						prepareStatement.close();
					}
					if (connection != null) {
						connection.close();
					}	

				} catch (Exception e) {
					e.printStackTrace();
				} finally {

				}
			}
			
			
			return dtos;
			
		}//timerComReadeList

	public void timerUpdate(String id, String title) {
			

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		int state = 0;
		
		
		try {
			connection = dataSource.getConnection();
			//UPDATE [테이블] SET [열] = '변경할값' WHERE [조건]

					   
			String query = "update timerlistcom set reference = 1 "
					+ " where id = '" + id + "'" 
					+ " and title ='" + title + "' ";
		
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
			
			
			

			
			
			if (state > 0) {
				System.out.println(state + "타이머 수행 완료 삽입성공");				
			} else {
				System.out.println(state + "타이머 수행 완료 삽입성공");
			}
			
			
			System.out.println(" 인설트 성공했냐? " );
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}
		
	
	
	}// timerUpdate 

	public void timerDelete(String id, String title) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		int state = 0;
		
		int state2 = 0;
		
		
		try {
			connection = dataSource.getConnection();
			//DELETE [테이블] WHERE [조건]

					   
			String query = "delete timerlistcom  "
					+ " where id = '" + id + "'" 
					+ " and title ='" + title + "' ";
		
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
		
			
	
			if (state > 0) {
				System.out.println(state + "타이머 수행 완료 삭제실패");				
			} else {
				System.out.println(state + "타이머 수행 완료 삭제성공");
			}
			prepareStatement.close();
			
			String query2 = "delete timerlist  "
					+ " where id = '" + id + "'" 
					+ " and title ='" + title + "' ";
			
			prepareStatement = connection.prepareStatement(query2);
			state2 = prepareStatement.executeUpdate();
			
			
			
			if (state2 > 0) {
				System.out.println(state2 + "타이머 수행 완료 삭제실패");				
			} else {
				System.out.println(state2 + "타이머 수행 완료 삭제성공");
			}
			
			
			System.out.println(" 델리트 성공했냐? " );
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}
		
		
		
		
	}//timerDelete

	
	public ArrayList<BoardListDTO> boardMyList(String id) {
		
		
		ArrayList<BoardListDTO> dtos = new ArrayList<>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select * "
					+ " from boardlist  "
					+ " where id = '" + id + "' " ;	
			
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				
				String board_id = resultSet.getString("id");
				String title = resultSet.getString("board_title"); 
				String body = resultSet.getString("board_body");
				String count = resultSet.getString("board_count");
				
				
				dtos.add(new BoardListDTO(board_id, title, body, count));							
			}	
			
			System.out.println("dtos size : " + dtos.size());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}
		
		
		
		
		return dtos;
	}//boardMylist
	public ArrayList<BoardListDTO> boardList(String id) {
		
		
		ArrayList<BoardListDTO> dtos = new ArrayList<>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;		
		
		try {
			connection = dataSource.getConnection();
			String query = "select * "
					+ " from boardlist  " ;	
			
			prepareStatement = connection.prepareStatement(query);
			resultSet = prepareStatement.executeQuery();
			
			while (resultSet.next()) {
				
				String board_id = resultSet.getString("id");
				String title = resultSet.getString("board_title"); 
				String body = resultSet.getString("board_body");
				String count = resultSet.getString("board_count");
				
				
				dtos.add(new BoardListDTO(board_id, title, body, count));							
			}	
			
			System.out.println("dtos size : " + dtos.size());
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} finally {
			try {			
				
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
		}
		
		
		
		
		return dtos;
	}//boardlist

	public void updateBoard(String id, String title, String body) {
		
		// 데이터베이스와 연결하여 원하는 결과물을 얻는다
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int state = 0;
		
		
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into boardlist (id, board_title, board_body, board_count) " + 
			               "values('" + id + "', '" + title + "', '" + body + "', seq_board_count.nextval)" ; 
					        			
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
			
			if (state > 0) {
				System.out.println(state + "삽입성공");				
			} else {
				System.out.println(state + "삽입실패");
			}
			
		} catch (Exception e) {			
			System.out.println("에러 :"+e.getMessage());
		} finally {
			try {				
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}
		
	
		
	}//updateBoard
		
		

	

	
}
