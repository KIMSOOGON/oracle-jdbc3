package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.BoardDao;
import util.DBUtil;
import vo.Board;

public class BoardService {
	private BoardDao boardDao;
	
	// removeBoard (게시글 삭제)
	public int removeBoard(Board paramBoard) {
		this.boardDao = new BoardDao();
		Connection conn = null;
		int removeBoard = 0;
		
		try {
			conn = DBUtil.getConnection();
			removeBoard = boardDao.removeBoard(conn, paramBoard);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return removeBoard;
	}
	
	// modifyBoard (게시글 수정)
	public int modifyBoard(Board paramBoard) {
		boardDao = new BoardDao();
		Connection conn = null;
		int modifyBoard = 0;
		
		try {
			conn = DBUtil.getConnection();
			modifyBoard = boardDao.modifyBoard(conn, paramBoard);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return modifyBoard;
	}
	
	// boardOne (게시글 상세)
	public Board boardOne(Board paramBoard) {
		this.boardDao = new BoardDao();
		Board boardOne = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			boardOne = boardDao.boardOne(conn, paramBoard);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return boardOne;
	}
	
	// addBoard (게시글 등록)
	public int insertBoard(Board paramBoard) {
		// 초기화
		int insertBoard = 0;
		Connection conn = null;
		this.boardDao = new BoardDao();
		
		try {
			conn = DBUtil.getConnection(); // DB접속
			insertBoard = boardDao.insertBoard(conn, paramBoard);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return insertBoard;
	}
	
	// BoardList (게시글 갯수)
	public int countBoard() {
		int countBoard = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();			
			this.boardDao = new BoardDao();
			countBoard = boardDao.countBoard(conn);
			conn.commit(); // 수동커밋 (DBUtil.class에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback(); // 하나라도 예외 발생 시 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return countBoard;
	}
		
	// BoardList (게시글 갯수)
	public int countBoard(String search, String word) {
		int countBoard = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();			
			this.boardDao = new BoardDao();
			countBoard = boardDao.countBoard(conn, search, word);
			conn.commit(); // 수동커밋 (DBUtil.class에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback(); // 하나라도 예외 발생 시 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return countBoard;
	}
	
	// BoardList (검색기능)
	public ArrayList<Board> getBoardListByPage(int currentPage, int rowPerPage, String search, String word) {
		ArrayList<Board> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			int beginRow = (currentPage - 1) * rowPerPage + 1;
			int endRow = beginRow + rowPerPage - 1;
			
			this.boardDao = new BoardDao();
			list = boardDao.selectBoardListByPage(conn, beginRow, endRow, search, word);
			
			conn.commit(); // 수동커밋 (DBUtil.class에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback(); // 하나라도 예외 발생 시 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
		
	// BoardList (페이징포함)
	public ArrayList<Board> getBoardListByPage(int currentPage, int rowPerPage) {
		
		/*
		 	1) connection 생성 <- DBUtil.class
		 	2) beginRow, endRow 생성 <- currentPage, rowPerPage를 가공
		*/
		
		ArrayList<Board> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			int beginRow = (currentPage - 1) * rowPerPage + 1;
			int endRow = beginRow + rowPerPage - 1;
			
			this.boardDao = new BoardDao();
			list = boardDao.selectBoardListByPage(conn, beginRow, endRow);
			
			conn.commit(); // 수동커밋 (DBUtil.class에서 conn.setAutoCommit(false);
		} catch (Exception e) {
			try {
				conn.rollback(); // 하나라도 예외 발생 시 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
