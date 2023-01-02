package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MemberDao;
import util.DBUtil;
import vo.Member;

public class MemberService {
	private MemberDao memberDao;
	
	// 회원탈퇴
	public int removeMemberOne(Member paramMember) {
		this.memberDao = new MemberDao();
		int removeMemberOne = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			removeMemberOne = memberDao.removeMemberOne(conn, paramMember);
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
		return removeMemberOne;
	}
	
	// 회원정보수정 - 비밀번호변경
	public int modifyMemberOne(Member modifyMember, Member memberOne) {
		this.memberDao = new MemberDao();
		int modifyMemberOne = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			modifyMemberOne = memberDao.modifyMemberOne(conn, modifyMember, memberOne);
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
		return modifyMemberOne;
	}
	
	// 회원가입 (1) 중복체크
	public boolean insertMemberCheckId(Member paramMember) {
		this.memberDao = new MemberDao();
		boolean checkId = false;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			checkId = memberDao.insertMemberCheckId(conn, paramMember);
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
		return checkId;
	}
	
	// 회원가입 (2) 가입
	public int insertMember(Member member) {
		this.memberDao = new MemberDao();
		int insertMember = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			insertMember = memberDao.insertMemberByIdAndPw(conn, member);
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
		return insertMember;
	}
	
	// 로그인
	public Member loginByIdAndPw(Member paramMember) {
		
		this.memberDao = new MemberDao();
		Connection conn = null;
		Member returnMember = null;
		
		try {
			conn = DBUtil.getConnection();
			returnMember = memberDao.selectMemberByIdAndPw(conn, paramMember);
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
		
		return returnMember;
	}
}
