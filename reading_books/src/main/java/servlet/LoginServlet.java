/**
 * 卒業制作
 */
package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.AccountDAO;
import model.dao.ReadingBooksDAO;
import model.entity.Login;
import model.entity.ReadBooksBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * ログアウト用のメソッド
     * セッションを無効にし、ログアウト画面に移動します
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		// セッションを無効にする
		HttpSession session = request.getSession();
		session.invalidate();
		// フォワード
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/logout.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		// Loginクラスのインスタンスを生成し、値を格納
		Login login = new Login(userName, password);
		// DAOを呼び出し、アカウントが存在するかを確認
		AccountDAO AD = new AccountDAO();
		Login result = AD.execute(login);
		int resultId = result.getUserId();
		String resultName = result.getUserName();
		
		/**
		 * 登録済みであれば、メイン画面に移動
		 */
		if (resultName != null) {
			// セッションスコープにユーザーIDを保存
			HttpSession session = request.getSession();
			session.setAttribute("userId", resultId);
			session.setAttribute("userName", resultName);
			// ログインメッセージの作成
			StringBuilder sb = new StringBuilder();
			sb.append(result.getUserName());
			sb.append("さん、おかえりなさい");
			String msg = sb.toString();
			request.setAttribute("msg", msg);
			// ユーザの本リストを取得して、セッションスコープに保存
			ReadingBooksDAO rb = new ReadingBooksDAO();
			List<ReadBooksBean> bookList = rb.selectAllBooks(resultId);
			session.setAttribute("bookList", bookList);
			// フォワード
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/jsp/main-menu.jsp");
			dispatcher.forward(request, response);
		
		/**
		 * アカウントがなければ、ログイン失敗メッセージを表示
		 */
		} else {
			// メッセージの作成
			String msg = "ログインに失敗しました！！";
			request.setAttribute("error_msg", msg);
			// フォワード
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			rd.forward(request, response);
		}
	}

}
