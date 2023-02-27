package servlet;

import java.io.IOException;

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
 * Servlet implementation class BookInfoServlet
 */
@WebServlet("/BookInfo")
public class BookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 本のIDを取得
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		// DAOを呼び出し、詳細情報を取得
		ReadingBooksDAO dao = new ReadingBooksDAO();
		ReadBooksBean readBook = dao.selectBook(bookId);
		// セッションオブジェクトの取得・属性の設定
		HttpSession session = request.getSession();
		session.setAttribute("readBook", readBook);
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/book-info.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// セッションオブジェクトの削除	
		HttpSession session = request.getSession();
		session.removeAttribute("readBook");
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("Main");
		dispatcher.forward(request, response);
		
	}

}
