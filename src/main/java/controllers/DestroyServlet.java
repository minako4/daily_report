package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class DestroyServlet
 */
@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            // セッションスコープからFollowerのIDを取得して
            // 該当のID1件のみをデータベースから取得
            Follow f = em.find(Follow.class, (Integer)(request.getSession().getAttribute("follow_id")));

            em.getTransaction().begin();
            em.remove(f);       // データ削除
            em.getTransaction().commit();
            em.close();

            // セッションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("follower");

            // indexページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/?action=Top&command=index");
        }
    }
}
