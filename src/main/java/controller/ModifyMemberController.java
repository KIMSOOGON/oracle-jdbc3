package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.Member;


@WebServlet("/member/modifyMember")
public class ModifyMemberController extends HttpServlet {

	// 회원수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * VIEW -> /WEB-INF/view/member/modifyMember.jsp
		 * 
		 */
		
		// view 연결
		request.getRequestDispatcher("/WEB-INF/view/member/modifyMember.jsp").forward(request, response);
	}

	private MemberService memberService;
	// 회원수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * redirect -> get방식 /member/memberOne?key값 <- 컨트롤러 요청
		 * 
		 */
		request.setCharacterEncoding("utf-8");
		
		// 세션값 받아오기
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// 파라미터값 받아오기
		String memberPw = request.getParameter("memberPw");
		String newMemberPw = request.getParameter("newMemberPw");
		String memberName = request.getParameter("memberName");
		System.out.println("[회원정보수정 액션] 입력된 수정할 이름 : "+memberName);
		
		// 기존 정보(기존패스워드 및 아이디) memberOne에 저장
		Member memberOne = new Member();
		memberOne.setMemberId(loginMember.getMemberId());
		memberOne.setMemberPw(memberPw);
		System.out.println("기존 아이디(세션에서 불러옴) : "+loginMember.getMemberId());
		
		// 새로 수정된 정보(새 패스워드 및 이름) modifyMember에 저장
		Member modifyMember = new Member();
		modifyMember.setMemberPw(newMemberPw);
		modifyMember.setMemberName(memberName);
		
		// 수정작업 진행
		this.memberService = new MemberService();
		int modifyMemberOne = memberService.modifyMemberOne(modifyMember, memberOne);
		if(modifyMemberOne == 1) {
			System.out.println("회원정보 수정 성공");
			loginMember.setMemberName(memberName);
			loginMember.setMemberPw(newMemberPw);
			response.sendRedirect(request.getContextPath()+"/member/memberOne");
			return;
		} else {
			System.out.println("회원정보 수정 실패");
			response.sendRedirect(request.getContextPath()+"/member/modifyMember");
			return;
		}
	}

}
