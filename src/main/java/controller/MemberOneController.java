package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Member;

@WebServlet("/member/memberOne")
public class MemberOneController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * VIEW -> /WEB-INF/view/member/memberOne.jsp
		 * 메뉴구성
		 * 1) 회원정보수정
		 * 2) 회원탈퇴
		 * 
		 */
		// 로그인 되어있으면 로그인폼 진입 불가능
		
		// 로그인 세션 불러오기
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// 로그인 안되어있을 경우 홈 컨트롤러로 이동
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
		
		// 로그인 되어있을시, 회원정보 View 보이기		
		request.getRequestDispatcher("/WEB-INF/view/member/memberOne.jsp").forward(request, response);
	}

}
