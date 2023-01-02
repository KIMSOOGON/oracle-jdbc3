package dao;

import java.sql.*;
import java.util.ArrayList;

import vo.Board;

public class BoardDao {
	
	// 게시글 삭제 
	public int removeBoard(Connection conn, Board paramBoard) throws Exception {
		int removeBoard = 0;
		
		String sql = "DELETE from board WHERE board_no = ? AND member_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, paramBoard.getBoardNo());
		stmt.setString(2, paramBoard.getMemberId());
		
		removeBoard = stmt.executeUpdate();
		
		return removeBoard;
	}
		
	// 게시글 수정
	public int modifyBoard(Connection conn, Board paramBoard) throws Exception {
		int modifyBoard = 0;
		
		String sql = "UPDATE board SET board_title = ?, board_content = ? WHERE board_no = ? AND member_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, paramBoard.getBoardTitle());
		stmt.setString(2, paramBoard.getBoardContent());
		stmt.setInt(3, paramBoard.getBoardNo());
		stmt.setString(4, paramBoard.getMemberId());
		modifyBoard = stmt.executeUpdate();
		
		return modifyBoard;
	}
	
	// 게시글 상세 (boardOne)
	public Board boardOne(Connection conn, Board paramBoard) throws Exception {
		Board boardOne = null;
		
		String sql = "SELECT * FROM board WHERE board_no = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, paramBoard.getBoardNo());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			boardOne = new Board();
			boardOne.setBoardNo(rs.getInt("board_no"));
			boardOne.setBoardTitle(rs.getString("board_title"));
			boardOne.setBoardContent(rs.getString("board_content"));
			boardOne.setMemberId(rs.getString("member_id"));
			boardOne.setCreatedate(rs.getString("createdate"));
		}
		
		return boardOne;
	}
	
	// 게시글 추가
	public int insertBoard(Connection conn, Board board) throws Exception {
		int insertBoard = 0;
		
		String sql = "INSERT INTO BOARD ("
				+ "    board_no, board_title, board_content, member_id, updatedate, createdate"
				+ ") VALUES ("
				+ "    board_seq.nextval, ?, ?, ?, sysdate, sysdate"
				+ ")";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, board.getBoardTitle());
		stmt.setString(2, board.getBoardContent());
		stmt.setString(3, board.getMemberId());
		insertBoard = stmt.executeUpdate();
		
		return insertBoard;
	}
	
	// 게시글 갯수 구하기 (게시글의 총 갯수)
	public int countBoard(Connection conn) throws Exception {
		int countBoard = 0;
		
		String sql = "SELECT COUNT(*) FROM board";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			countBoard = rs.getInt("COUNT(*)");
		}
		return countBoard;
	}
	
	// 게시글 갯수 구하기 (검색값에 따른 총 갯수)
	public int countBoard(Connection conn, String search, String word) throws Exception {
		int countBoard = 0;
		
		String sql = "SELECT COUNT(*) FROM board WHERE "+search+" LIKE ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			countBoard = rs.getInt("COUNT(*)");
		}
		return countBoard;
	}
	
	// 게시글 목록 (검색 기능 추가된)
	public ArrayList<Board> selectBoardListByPage(Connection conn, int beginRow, int endRow, String search, String word) throws Exception {
		ArrayList<Board> list = new ArrayList<Board>();
		
		String sql = "SELECT board_no boardNo, board_title boardTitle, member_id memberId, createdate"
					+ " FROM (SELECT rownum rnum, board_no, board_title, board_content, member_id, createdate"
					+ " 		FROM (SELECT board_no, board_title, board_content, member_id, createdate"
					+ " 				FROM board WHERE "+search+" LIKE ? ORDER BY createdate DESC))"
					+ " WHERE rnum BETWEEN ? AND ?"; // WHERE rnum >= ? AND rnum <= ?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+word+"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, endRow);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Board b = new Board();
			b.setBoardNo(rs.getInt("boardNo"));
			b.setBoardTitle(rs.getString("boardTitle"));
			b.setMemberId(rs.getString("memberId"));
			b.setCreatedate(rs.getString("createdate"));
			list.add(b);
		}
		return list;
	}
	
	// 게시글 목록 (검색 X)
	public ArrayList<Board> selectBoardListByPage(Connection conn, int beginRow, int endRow) throws Exception {
		ArrayList<Board> list = new ArrayList<Board>();
		
		String sql = "SELECT board_no boardNo, board_title boardTitle, member_id memberId, createdate"
					+ " FROM (SELECT rownum rnum, board_no, board_title, member_id, createdate"
					+ " 		FROM (SELECT board_no, board_title, member_id, createdate"
					+ " 				FROM board ORDER BY createdate DESC))"
					+ " WHERE rnum BETWEEN ? AND ?"; // WHERE rnum >= ? AND rnum <= ?;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, endRow);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Board b = new Board();
			b.setBoardNo(rs.getInt("boardNo"));
			b.setBoardTitle(rs.getString("boardTitle"));
			b.setMemberId(rs.getString("memberId"));
			b.setCreatedate(rs.getString("createdate"));
			list.add(b);
		}
		return list;
	}
}
