package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.ReadingRecordsDAO;

/**
 * Servlet implementation class DeleteRRecordServlet
 */
@WebServlet("/DeleteRRecord")
public class DeleteRRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
//		System.out.println("つながりました"); 
		String action = null;
		//actionによって処理を分岐します
		request.setCharacterEncoding("UTF-8");
		action = request.getParameter("action");
		// セッションオブジェクトの取得
		HttpSession session = request.getSession();
		
		/**
		 * 削除確認画面
		 */
		if(action.equals("削除する")){
			// フォワード
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/jsp/delete-r-record-confirm.jsp");
			dispatcher.forward(request, response);
			
		/**
		 * 削除確定
		 */
		} else if(action.equals("削除してOK")){
			// 読書記録IDを取得
			int readingId = (int) session.getAttribute("eReadingId");
			// DAOのインスタンスを生成し、DELETEを実行
			ReadingRecordsDAO rrd = new ReadingRecordsDAO();
			rrd.deleteRecord(readingId);
			// 完了メッセージと、actionをリクエストスコープに設定
			request.setAttribute("msg", "削除完了しました！");
			request.setAttribute("action", "returnRRList");
			// フォワード
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/jsp/completion-msg.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 編集画面にフォワード
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/WEB-INF/jsp/edit-r-record.jsp");
		dispatcher.forward(request, response);
	}

}
