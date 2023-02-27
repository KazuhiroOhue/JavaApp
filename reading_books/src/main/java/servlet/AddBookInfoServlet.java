package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.ReadingBooksDAO;
import model.entity.ReadBooksBean;

/**
 * Servlet implementation class AddBookInfoServlet
 */
@WebServlet("/AddBookInfo")
public class AddBookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = null;
		//actionによって処理を分岐します
		request.setCharacterEncoding("UTF-8");
		action = request.getParameter("action");
		// セッションスコープ呼び出し
		HttpSession session = request.getSession();
		session = request.getSession();
		
		/**
		 * 新規登録画面に移動
		 */
		if (action.equals("新しい本の登録")) {
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/add-book-info.jsp");
			dispatcher.forward(request, response);
			
		/**
		 * 登録内容確認画面に移動
		 */
		} else if(action.equals("登録する")) {
			// リクエストパラメータの取得
			String aBookName = request.getParameter("aBookName");
			String aPurchaseDate = request.getParameter("aPurchaseDate");
			String aBookCategory = request.getParameter("aBookCategory");
			int aTotalPages = Integer.parseInt(request.getParameter("aTotalPages"));
			String aBookMemos = request.getParameter("aBookMemos");
			// セッションススコープに設定
			session.setAttribute("aBookName", aBookName);
			session.setAttribute("aPurchaseDate", aPurchaseDate);
			session.setAttribute("aBookCategory", aBookCategory);
			session.setAttribute("aTotalPages", aTotalPages);
			session.setAttribute("aBookMemos", aBookMemos);
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/add-book-info-confirm.jsp");
			dispatcher.forward(request, response);
		
		/**
		 * 新規登録確定
		 */
		} else if (action.equals("この内容で確定")) {
			// セッションオブジェクト読み込み
			int userId = (int) session.getAttribute("userId");
			String aBookName = (String) session.getAttribute("aBookName");
			java.util.Date aPurchaseDate = Date.valueOf((String) session.getAttribute("aPurchaseDate"));
			String aBookCategory = (String) session.getAttribute("aBookCategory");
			int aTotalPages = (int) session.getAttribute("aTotalPages");
			String aBookMemos = (String) session.getAttribute("aBookMemos");
			// beanのインスタンスを生成、値を格納
			ReadBooksBean addBook = new ReadBooksBean();
			addBook.setBookName(aBookName);
			addBook.setPurchaseDate(aPurchaseDate);
			addBook.setUserId(userId);
			addBook.setBookCategory(aBookCategory);
			addBook.setTotalPages(aTotalPages);
			addBook.setBookmemos(aBookMemos);
			// DAOを呼び出し、INSERTを実行
			ReadingBooksDAO dao = new ReadingBooksDAO();
			dao.addBook(addBook);
			// セッションオブジェクトの削除	
			session.removeAttribute("aBookName");
			session.removeAttribute("aPurchaseDate");
			session.removeAttribute("aBookCategory");
			session.removeAttribute("aTotalPages");
			session.removeAttribute("aBookMemos");
			// 完了メッセージの作成
			request.setAttribute("msg", "登録完了しました！");
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/completion-msg.jsp");
			dispatcher.forward(request, response);
			
		} else {
			System.out.println("エラーです！");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = null;
		//actionによって処理を分岐します
		request.setCharacterEncoding("UTF-8");
		action = request.getParameter("action");
		// セッションスコープ呼び出し
		HttpSession session = request.getSession();
		session = request.getSession();
		
		/**
		 * メイン画面に戻ります
		 */
		if (action.equals("やめる")) {
			// セッションオブジェクトの削除	
			session.removeAttribute("aBookName");
			session.removeAttribute("aPurchaseDate");
			session.removeAttribute("aBookCategory");
			session.removeAttribute("aTotalPages");
			session.removeAttribute("aBookMemos");
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main-menu.jsp");
			dispatcher.forward(request, response);
			
		/**
		 * 登録画面に戻ります
		 */
		} else if (action.equals("戻る")) {
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/add-book-info.jsp");
			dispatcher.forward(request, response);
			
		} else {
			System.out.println("エラーです！");
		}
	}

}
