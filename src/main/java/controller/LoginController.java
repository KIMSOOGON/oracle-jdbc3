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


@WebServlet("/member/login")
public class LoginController extends HttpServlet {

	private MemberService memberService;
	
	// 로그인 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 로그인 되어있으면 로그인폼 진입 불가능
		
		// 로그인 세션 불러오기
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// 로그인 되어있을 경우 홈 컨트롤러로 이동
		if(loginMember != null) {
			response.sendRedirect(request.getContextPath()+"/home");
		}
		
		if(request.getParameter("checkLogin") != null) {
			request.setAttribute("checkLogin", request.getParameter("checkLogin"));
		}
		
		// 로그인 안되어있을시, 로그인폼 View 보이기
		request.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(request, response);
	}

	// 로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		/*
		 * 로그인 세션 정보 : session.setAttribute("loginMember", Member타입)
		 * redirect -> get방식 /home <- 컨트롤러 요청
		 * 
		 */
		// 로그인 전에만 진입가능
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// 이미 로그인 된 경우
		if(loginMember != null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
		// 로그인 안되어있는 경우, 로그인 진행 (파라미터값 받아)
		Member paramMember = new Member();
		paramMember.setMemberId(request.getParameter("memberId"));
		paramMember.setMemberPw(request.getParameter("memberPw"));
		System.out.println("입력한 아이디 : "+paramMember.getMemberId());
		
		// 로그인쿼리(모델) 불러오기
		this.memberService = new MemberService();
		Member returnMember = memberService.loginByIdAndPw(paramMember);
		
		boolean checkLogin = true; // 아이디나 패스워드가 제대로 입력된 경우
		
		// 로그인 실패 시,
		if(returnMember == null) {
			System.out.println("로그인 실패");
			checkLogin = false; // 아이디나 패스워드 오입력
			response.sendRedirect(request.getContextPath()+"/member/login?checkLogin="+checkLogin);
			return;
		}
		
		// 로그인 성공 시, session에 로그인정보 저장
		System.out.println("로그인 성공");
		session.setAttribute("loginMember", returnMember);
		response.sendRedirect(request.getContextPath()+"/home");
	}

}
