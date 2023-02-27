package servlet;

import java.io.IOException;
import java.util.Date;

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
 * Servlet implementation class EditBookInfoServlet
 */
@WebServlet("/EditBookInfo")
public class EditBookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBookInfoServlet() {
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
		request.setCharacterEncoding("UTF-8"); // 文字化け防止
		action = request.getParameter("action");
		// セッションスコープ呼び出し
		HttpSession session = request.getSession();
		session = request.getSession();
		
		/**
		 * 編集画面に移動
		 */
		if (action.equals("編集する")) {
			// セッションオブジェクトを読み込み、値を取得
			ReadBooksBean ebook = (ReadBooksBean) session.getAttribute("readBook");
			int eBookId = ebook.getBookId();
			String eBookName = ebook.getBookName();
			Date ePurchaseDate = ebook.getPurchaseDate();
			String eBookCategory = ebook.getBookCategory();
			int eTotalPages = ebook.getTotalPages();
			String eBookMemos = ebook.getBookmemos();
			//セッションススコープに設定
			session.setAttribute("eBookId", eBookId);
			session.setAttribute("eBookName", eBookName);
			session.setAttribute("ePurchaseDate", ePurchaseDate);
			session.setAttribute("eBookCategory", eBookCategory);
			session.setAttribute("eTotalPages", eTotalPages);
			session.setAttribute("eBookMemos", eBookMemos);
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit-book-info.jsp");
			dispatcher.forward(request, response);
		
			/**
			 * 編集確認画面に移動
			 */
		} else if (action.equals("変更する")) {
			// リクエストパラメータの取得
			String eBookName = request.getParameter("eBookName");
			String ePurchaseDate = request.getParameter("ePurchaseDate");
			String eBookCategory =request.getParameter("eBookCategory");
			int eTotalPages = Integer.parseInt(request.getParameter("eTotalPages"));
			String eBookMemos = request.getParameter("eBookMemos");
			// セッションスコープに設定
			session.setAttribute("eBookName", eBookName);
			session.setAttribute("ePurchaseDate", ePurchaseDate);
			session.setAttribute("eBookCategory", eBookCategory);
			session.setAttribute("eTotalPages", eTotalPages);
			session.setAttribute("eBookMemos", eBookMemos);
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit-book-info-confirm.jsp");
			dispatcher.forward(request, response);
		
		/**
		 * 	編集内容の確定
		 */
		} else if(action.equals("この内容で確定")) {
			// セッションオブジェクトを読み込み
			int eBookId = (int) session.getAttribute("eBookId");
			String eBookName = (String) session.getAttribute("eBookName");
			Date ePurchaseDate = java.sql.Date.valueOf((String) session.getAttribute("ePurchaseDate"));
			String eBookCategory = (String) session.getAttribute("eBookCategory");
			int eTotalPages = (int) session.getAttribute("eTotalPages");
			String eBookMemos = (String) session.getAttribute("eBookMemos");
			// beanのインスタンスを生成し、値を格納
			ReadBooksBean editBook = new ReadBooksBean();
			editBook.setBookId(eBookId);
			editBook.setBookName(eBookName);
			editBook.setPurchaseDate(ePurchaseDate);
			editBook.setBookCategory(eBookCategory);
			editBook.setTotalPages(eTotalPages);
			editBook.setBookmemos(eBookMemos);
			// DAOを呼び出し、UPDATEを実行
			ReadingBooksDAO dao = new ReadingBooksDAO();
			dao.updateBook(editBook);
			// セッションスコープ内の情報を削除
			session.removeAttribute("eBookName");
			session.removeAttribute("ePurchaseDate");
			session.removeAttribute("eBookCategory");
			session.removeAttribute("eTotalPages");
			session.removeAttribute("eBookMemos");
			// 完了メッセージの作成
			request.setAttribute("msg", "更新完了しました！");
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/completion-msg.jsp");
			dispatcher.forward(request, response);
			
			} else {
				System.out.println("エラーです！！");
			}
				
		}

	/**
	 * 編集画面に戻ります
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = null;
		//actionによって処理を分岐します
		request.setCharacterEncoding("UTF-8"); // 文字化け防止
		action = request.getParameter("action");
		// セッションスコープ呼び出し
		HttpSession session = request.getSession();
		session = request.getSession();
		
		/**
		 * 詳細表示画面に戻ります
		 */
		if(action.equals("やめる")) {
			// セッションオブジェクトの削除			
			session.removeAttribute("eBookId");
			session.removeAttribute("eBookName");
			session.removeAttribute("ePurchaseDate");
			session.removeAttribute("eBookCategory");
			session.removeAttribute("eTotalPages");
			session.removeAttribute("eBookMemos");
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/book-info.jsp");
			dispatcher.forward(request, response);
		
		/**
		 * 編集画面に戻ります
		 */
		} else if (action.equals("編集画面に戻る")) {
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit-book-info.jsp");
			dispatcher.forward(request, response);
			
		} else {
			System.out.println("エラーです！！");
		}
	}

}
