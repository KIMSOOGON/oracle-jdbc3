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

@WebServlet("/member/removeMember")
public class RemoveMemberController extends HttpServlet {

	// 회원탈퇴 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// View -> /WEB-INF/view/member/removeMember.jsp
		
		request.getRequestDispatcher("/WEB-INF/view/member/removeMember.jsp").forward(request, response);
	}

	private MemberService memberService;
	
	// 회원탈퇴 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// redirect -> get방식 /member/logout <- 컨트롤러 요청
		
		// 파라미터 값 받아오기 (패스워드)
		String memberPw = request.getParameter("memberPw");
		
		// 로그인 세션 불러오기
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// paramMember에 데이터 저장
		Member paramMember = new Member();
		paramMember.setMemberId(loginMember.getMemberId());
		paramMember.setMemberPw(memberPw);
		
		// 탈퇴 모델 진행
		this.memberService = new MemberService();
		int removeMemberOne = memberService.removeMemberOne(paramMember);
		if(removeMemberOne == 1) {
			System.out.println("회원탈퇴 성공");
			response.sendRedirect(request.getContextPath()+"/member/logout");
			return;
		} else {
			System.out.println("회원탈퇴 실패");
			response.sendRedirect(request.getContextPath()+"/member/removeMember");
			return;
		}
	}

}
