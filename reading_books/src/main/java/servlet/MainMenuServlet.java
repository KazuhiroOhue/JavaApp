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

import model.dao.ReadingBooksDAO;
import model.entity.ReadBooksBean;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/Main")
public class MainMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * メインページに移動します
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// セッションスコープからユーザーIDを取得
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		// ユーザの本リストを取得して、セッションスコープに保存
		ReadingBooksDAO rb = new ReadingBooksDAO();
		List<ReadBooksBean> bookList = rb.selectAllBooks(userId);
		session.setAttribute("bookList", bookList);
		// フォワード
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/main-menu.jsp");
		rd.forward(request, response);

	}

}
