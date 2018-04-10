package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DbUtil;
import dao.Userdao;


/**
 * Servlet implementation class ajax1
 */
public class Ajax1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil=new DbUtil();
	Userdao userDao=new Userdao();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ajax1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 this.doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8"); // ������Ӧ����ĸ�ʽΪtext/html���ַ���ΪUTF-8
        response.setCharacterEncoding("UTF-8"); // ������Ӧ������ַ�����ΪUTF-8
        // ��ֹ����
        response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        response.setHeader("Cache-Control", "post-check=0,pre-check=0");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");

        PrintWriter out = response.getWriter();
        out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        // ��httpRequest()�����л���������ֵ
        // ͨ��httpRequest()������װ���������������ΪUTF-8��ʽ���˴����뻹ԭԭ���ı����ʽ������Ҫͨ��UTF-8��ʽ����

        String userName = request.getParameter("name");
        String result = "<font color='green'>���û�������ʹ��</font>";
        Connection con=null;
        try {
			con=dbUtil.getCon();
			boolean check =userDao.check(con, userName); // �������ݿ��Ƿ���ڸ��û���
	        if (check) {
	            result = "<font color='red'>���û��Ѿ���ʹ��</font>";
	        } 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.getWriter().print(result); // ����������response��Ӧ����
	}
	

}
