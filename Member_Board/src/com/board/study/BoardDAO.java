package com.board.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public Connection getConn() {
		String url = "jdbc:mysql://localhost:3306/boardex?characterEncoding=utf-8&serverTimeZone=UTC";
		String user = "root";
		String password = "admin1234";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("연결 실패");
		}
		return conn;
	}

	public int boardInsert(BoardDTO dto) {
		conn=getConn();
		String sql="";
		int b_num=0;
	    int succ=0;
	    try {
			sql="select max(board_num) from memberBoard";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				b_num=rs.getInt(1);
				b_num+=1;
			} else {
				b_num=1;
			}
			
			sql="insert into memberBoard values(?,?,?,?,?,?,?,?,?,now())";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, b_num);
			ps.setString(2, dto.getBoard_id());
			ps.setString(3, dto.getBoard_subject());
			ps.setString(4, dto.getBoard_content());
			ps.setString(5, dto.getBoard_file());
			ps.setInt(6, b_num);
			ps.setInt(7, 0);
			ps.setInt(8, 0);
			ps.setInt(9, 0);
			succ=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("boardInsert() Exception");
		} finally {
			dbClose();
		}
		
		return succ;
	}
	
	public int getListCount() {
		conn=getConn();
		String sql="select count(*) from memberBoard";
		int listCount=0;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				listCount=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getListCount() Exception");
		} finally {
			dbClose();
		}
		return listCount;
	}

	public ArrayList<BoardDTO> getBoardList(int page, int limit) {
		conn=getConn();
		String sql = "select * from memberBoard order by board_re_ref desc,"
				+"board_re_seq asc limit ?, 10";
		int startRow=(page-1)*10;
		ArrayList<BoardDTO> list= new ArrayList<BoardDTO>();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, startRow);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_num(rs.getInt("board_num"));
				dto.setBoard_id(rs.getString("board_id"));
				dto.setBoard_subject(rs.getString("board_subject"));
				dto.setBoard_content(rs.getString("board_content"));
				dto.setBoard_file(rs.getString("board_file"));
				dto.setBoard_re_ref(rs.getInt("board_re_ref"));
				dto.setBoard_re_lev(rs.getInt("board_re_lev"));
				dto.setBoard_re_seq(rs.getInt("board_re_seq"));
				dto.setBoard_readcount(rs.getInt("board_readcount"));
				dto.setBoard_date(rs.getString("board_date"));
				list.add(dto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getBoardList() Exception");
		} finally {
			dbClose();
		}
		
		return list;
	}
	
	public BoardDTO getDetail(int board_num) {
		conn = getConn();
		String sql = "SELECT * FROM memberBoard WHERE board_num = ?";
		BoardDTO dto = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_num);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setBoard_num(rs.getInt("board_num"));
				dto.setBoard_id(rs.getString("board_id"));
				dto.setBoard_subject(rs.getString("board_subject"));
				dto.setBoard_content(rs.getString("board_content"));
				dto.setBoard_file(rs.getString("board_file"));
				dto.setBoard_re_ref(rs.getInt("board_re_ref"));
				dto.setBoard_re_lev(rs.getInt("board_re_lev"));
				dto.setBoard_re_seq(rs.getInt("board_re_seq"));
				dto.setBoard_readcount(rs.getInt("board_readcount"));
				dto.setBoard_date(rs.getString("board_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getDetail() Exception!!!");
		} finally {
			dbClose();
		}
		return dto;
	}
	
	public void readCount(int board_num) {
		conn =getConn();
		String sql = "UPDATE memberBoard SET board_readcount = ";
		sql += "board_readcount + 1 WHERE board_num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_num);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("readCount() Excetption!!!");
		} finally {
			dbClose();
		}
	}
	
	public boolean isBoardWriter(int board_num, String id) {
		conn = getConn();
		String sql = "SELECT * FROM memberBoard WHERE board_num = ?";
		boolean result = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_num);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				if(id.equals(rs.getString("board_id"))) {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("isBoardWriter() Exception!!!");
		} finally {
			dbClose();
		}
		return result;
	}
	
	public int boardDelete(int board_num) {
		conn = getConn();
		String sql = "DELETE FROM memberBoard WHERE board_num = ?";
		int succ = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board_num);
			succ = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("boardDelete() Exception!!!");
		} finally {
			dbClose();
		}
		return succ;
	}
	
	public int boardUpdate(BoardDTO dto) {
		conn = getConn();
		String sql = "UPDATE memberBoard SET board_subject = ?, ";
		sql += "board_content = ? WHERE board_num = ?";
		int succ = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getBoard_subject());
			ps.setString(2, dto.getBoard_content());
			ps.setInt(3, dto.getBoard_num());
			succ = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("boardUpdate() Exception!!!");
		} finally {
			dbClose();
		}
		return succ;
	}
	
	private void dbClose() {
		try {
			if(rs!=null)
				rs.close();
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dbClose() Exception");
		}
		
	}
}
