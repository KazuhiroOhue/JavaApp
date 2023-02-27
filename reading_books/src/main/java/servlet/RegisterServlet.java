package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.AccountDAO;
import model.entity.Login;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * アカウント登録ページへ移動します
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * アカウント登録を行い、メインページに移動します
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		// Loginクラスのインスタンスを生成し、値を格納
		Login register = new Login(userName, password);
		// DAOを呼び出し、既存ユーザ名と重複しないかを確認
		AccountDAO bl = new AccountDAO();
		boolean result = bl.registerExecute(register);
		
		/**
		 * Trueであれば、アカウントを登録
		 */
		if(result) {
			// アカウント情報をDBに保存
			bl.register(register);
			// 新アカウントIDを取得、セッションスコープに保存
			int userId = bl.getId(userName);
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("userName", userName);
			// ログインメッセージの作成
			StringBuilder sb = new StringBuilder();
			sb.append(userName);
			sb.append("さん、新規登録できました！");
			String msg = sb.toString();
			request.setAttribute("msg", msg);
			// フォワード
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("Main");
			dispatcher.forward(request, response);
		
		/**
		 * Falseであれば、登録不可のメッセージを表示
		 */
		} else {
			// メッセージの作成
			String msg = "そのアカウント名は既に登録済みです！";
			request.setAttribute("error_msg", msg);
			// フォワード
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			rd.forward(request, response);
			
		}
	}

}
