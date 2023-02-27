package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.ReadingRecordsDAO;
import model.entity.ReadBooksBean;

/**
 * Servlet implementation class AddRRecordServlet
 */
@WebServlet("/AddRRecord")
public class AddRRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRRecordServlet() {
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
		if (action.equals("読書記録の新規登録")) {
			// ユーザIDを取得
			int userId = (int) session.getAttribute("userId");
			// DAOを呼び出し、ユーザが所持している本のリストを取得・セッションスコープに保存
			ReadingRecordsDAO rrd = new ReadingRecordsDAO();
			List<String>bookTitles = rrd.selectBookTitles(userId);
			// セッションスコープに設定
			session.setAttribute("bookTitles", bookTitles);
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/add-r-record.jsp");
			dispatcher.forward(request, response);
		
		/**
		 * 登録確認画面に移動
		 */
		} else if(action.equals("登録する")){
			// リクエストパラメータの取得
			String aReadingDate = request.getParameter("aReadingDate");
			String aBookName = request.getParameter("aBookName");
			int aReadingPages = Integer.parseInt(request.getParameter("aReadingPages"));
			String aReadingMemos = request.getParameter("aReadingMemos");
			// セッションスコープに設定
			session.setAttribute("aReadingDate", aReadingDate);
			session.setAttribute("aBookName", aBookName);
			session.setAttribute("aReadingPages", aReadingPages);
			session.setAttribute("aReadingMemos", aReadingMemos);
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/add-r-record-confirm.jsp");
			dispatcher.forward(request, response);
		
		/**
		 * 新規登録確定
		 */
		} else if(action.equals("この内容で確定")) {
			// セッションオブジェクト読み込み
			int userId = (int) session.getAttribute("userId");
			Date aReadingDate = java.sql.Date.valueOf((String) session.getAttribute("aReadingDate"));
			String aBookName = (String) session.getAttribute("aBookName");
			int aReadingPages = (int) session.getAttribute("aReadingPages");
			String aReadingMemos = (String) session.getAttribute("aReadingMemos");
			// beanインスタンスを生成、値を格納
			ReadBooksBean rbb = new ReadBooksBean();
			rbb.setUserId(userId);
			rbb.setReadingDate(aReadingDate);
			rbb.setBookName(aBookName);
			rbb.setReadPages(aReadingPages);
			rbb.setReadingMemos(aReadingMemos);
			// DAOを呼び出し、INSERTを実行
			ReadingRecordsDAO dao = new ReadingRecordsDAO();
			dao.addRecord(rbb);
			// セッションスコープ内の情報を削除
			session.removeAttribute("aReadingDate");
			session.removeAttribute("aBookName");
			session.removeAttribute("aReadingPages");
			session.removeAttribute("aReadingMemos");
			// 完了メッセージと、actionをリクエストスコープに設定
			request.setAttribute("msg", "新しい読書記録を登録しました！");
			request.setAttribute("action", "returnRRList");
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
		 * 読書記録一覧画面に戻る
		 */
		if(action.equals("やめる")) {
			// セッションオブジェクトの削除
			session.removeAttribute("aReadingDate");
			session.removeAttribute("aBookName");
			session.removeAttribute("aReadingPages");
			session.removeAttribute("aReadingMemos");
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/r-record-list.jsp");
			dispatcher.forward(request, response);
		
		/**
		 * 新規登録画面に戻る
		 */
		} else if(action.equals("編集画面に戻る")) {
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/add-r-record.jsp");
			dispatcher.forward(request, response);
			
		} else {
			System.out.println("エラーです！");
		}
		
		
	}

}
