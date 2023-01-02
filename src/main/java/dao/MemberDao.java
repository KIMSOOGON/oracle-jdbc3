package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Member;

public class MemberDao {
	
	// 회원탈퇴
	public int removeMemberOne(Connection conn, Member member) throws Exception {
		int removeMemberOne = 0;
		
		String sql = "DELETE from member WHERE member_id = ? AND member_pw = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		
		removeMemberOne = stmt.executeUpdate();
				
		return removeMemberOne;
	}
	
	// 회원정보 수정 - 비밀번호 및 이름 변경
	public int modifyMemberOne(Connection conn, Member modifyMember, Member memberOne) throws Exception {
		int modifyMemberOne = 0;
		
		String sql = "UPDATE member SET member_pw = ?, member_name = ? WHERE member_id = ? AND member_pw = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, modifyMember.getMemberPw());
		stmt.setString(2, modifyMember.getMemberName());
		stmt.setString(3, memberOne.getMemberId());
		stmt.setString(4, memberOne.getMemberPw());
		modifyMemberOne = stmt.executeUpdate();
		
		return modifyMemberOne;
	}
	
	// 회원정보 불러오기 메서드
	public Member memberOne(Connection conn, Member member) throws Exception {
		Member memberOne = null;
		
		String sql = "SELECT * FROM member WHERE member_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			memberOne = new Member();
			memberOne.setMemberId(rs.getString("member_id"));
			memberOne.setMemberPw(rs.getString("member_pw"));
			memberOne.setMemberName(rs.getString("member_name"));
		}
		
		return memberOne;
	}
	
	// 회원가입 메서드 (1) - 중복체크
	public boolean insertMemberCheckId(Connection conn, Member member) throws Exception{
		boolean insertMemberCheckId = false;
		
		// 중복확인 쿼리
		String sql = "SELECT * FROM member WHERE member_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {	// ID 중복값 존재할 경우, true 반환
			insertMemberCheckId = true;
		}
		
		return insertMemberCheckId;
	}
	
	// 회원가입 메서드 (2) - 가입
	public int insertMemberByIdAndPw(Connection conn, Member member) throws Exception {
		int insertMember = 0;
		
		System.out.println("[DAO 검사]");
		System.out.println(member.getMemberPw());
		
		String sql = "INSERT INTO member("
					+ "member_id, member_pw, member_name, updatedate, createdate"
					+ ") VALUES (?,?,?,sysdate,sysdate)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		stmt.setString(3, member.getMemberName());
		
		insertMember = stmt.executeUpdate();
		
		return insertMember;
	}
	
	// 로그인 메서드
	public Member selectMemberByIdAndPw(Connection conn, Member member) throws Exception {
		
		Member loginMember = null;
		
		// 로그인 쿼리
		String sql = "SELECT * FROM member WHERE member_id = ? AND member_pw = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberId());
		stmt.setString(2, member.getMemberPw());
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			loginMember = new Member();
			loginMember.setMemberId(rs.getString("member_id"));
			loginMember.setMemberPw(rs.getString("member_pw"));
			loginMember.setMemberName(rs.getString("member_name"));
			loginMember.setCreatedate(rs.getString("createdate"));
		}
		
		return loginMember;
	}
}
