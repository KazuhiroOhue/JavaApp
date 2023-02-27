package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
 * Servlet implementation class EditRRecordServlet
 */
@WebServlet("/EditRRecord")
public class EditRRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditRRecordServlet() {
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
		// セッションオブジェクトの取得
		HttpSession session = request.getSession();
		
		/**
		 * 編集画面に移動
		 */
		if (Objects.isNull(action)) {
			// 読書記録IDを取得
			int readingId = Integer.parseInt(request.getParameter("readingId"));
			// DAOを呼び出し、詳細情報を取得
			ReadingRecordsDAO rrd = new ReadingRecordsDAO();
			ReadBooksBean rbb = rrd.selectRecord(readingId);
			// セッションススコープに設定
			session.setAttribute("eReadingId", readingId);
			session.setAttribute("eReadingDate", rbb.getReadingDate());
			session.setAttribute("eBookName", rbb.getBookName());
			session.setAttribute("eReadingPages", rbb.getReadPages());
			session.setAttribute("eReadingMemos", rbb.getReadingMemos());
			// ユーザが所持している本のリストを取得・セッションスコープに保存
			int userId = (int) session.getAttribute("userId");
			List<String>bookTitles = rrd.selectBookTitles(userId);
			session.setAttribute("bookTitles", bookTitles);
			// フォワード
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/jsp/edit-r-record.jsp");
			dispatcher.forward(request, response);
			
		}else {
			
			/**
			 * 編集確認画面に移動
			 */
			if (action.equals("変更する")) {
				// リクエストパラメータの取得
				String eReadingDate = request.getParameter("eReadingDate");
				String eBookName = request.getParameter("eBookName");
				int eReadingPages = Integer.parseInt(request.getParameter("eReadingPages"));
				String eReadingMemos = request.getParameter("eReadingMemos");
				// セッションスコープに設定
				session.setAttribute("eReadingDate", eReadingDate);
				session.setAttribute("eBookName", eBookName);
				session.setAttribute("eReadingPages", eReadingPages);
				session.setAttribute("eReadingMemos", eReadingMemos);
				// フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit-r-record-confirm.jsp");
				dispatcher.forward(request, response);
			
			/**
			 * 編集内容の確定
			 */
			}else if(action.equals("この内容で確定")) {
				// セッションオブジェクト読み込み
				int readingId = (int) session.getAttribute("eReadingId");
				Date eReadingDate = java.sql.Date.valueOf((String) session.getAttribute("eReadingDate"));
				String eBookName = (String) session.getAttribute("eBookName");
				int eReadingPages = (int) session.getAttribute("eReadingPages");
				String eReadingMemos = (String) session.getAttribute("eReadingMemos");
				// beanのインスタンスを生成し、値を格納
				ReadBooksBean rbb = new ReadBooksBean();
				rbb.setReadingId(readingId);
				rbb.setReadingDate(eReadingDate);
				rbb.setBookName(eBookName);
				rbb.setReadPages(eReadingPages);
				rbb.setReadingMemos(eReadingMemos);
				// DAOを呼び出し、UPDATEを実行
				ReadingRecordsDAO dao = new ReadingRecordsDAO();
				dao.updateRecord(rbb);
				// セッションスコープ内の情報を削除
				session.removeAttribute("eReadingId");
				session.removeAttribute("eReadingDate");
				session.removeAttribute("eBookName");
				session.removeAttribute("eReadingPages");
				session.removeAttribute("eReadingMemos");
				// 完了メッセージと、actionをリクエストスコープに設定
				request.setAttribute("msg", "読書記録の更新完了しました！");
				request.setAttribute("action", "returnRRList");
				// フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/completion-msg.jsp");
				dispatcher.forward(request, response);
			}
		
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
		// セッションオブジェクトの取得
		HttpSession session = request.getSession();
		
		/**
		 * 
		 */
		if(action.equals("やめる")) {
			// セッションスコープから、投稿途中内容を削除		
			session.removeAttribute("readingId");
			session.removeAttribute("eReadingDate");
			session.removeAttribute("eBookName");
			session.removeAttribute("eReadingPages");
			session.removeAttribute("eReadingMemos");
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/r-record-list.jsp");
			dispatcher.forward(request, response);
		
		/**
		 * 
		 */
		} else if(action.equals("編集画面に戻る")) {
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/edit-r-record.jsp");
			dispatcher.forward(request, response);
			
		}
	}

}
