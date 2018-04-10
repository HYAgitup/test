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
		response.setContentType("text/html; charset=UTF-8"); // 设置响应结果的格式为text/html，字符集为UTF-8
        response.setCharacterEncoding("UTF-8"); // 设置响应结果的字符编码为UTF-8
        // 禁止缓存
        response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        response.setHeader("Cache-Control", "post-check=0,pre-check=0");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");

        PrintWriter out = response.getWriter();
        out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        // 从httpRequest()方法中获得请求参数值
        // 通过httpRequest()方法封装的请求参数被编码为UTF-8格式，此处若想还原原来的编码格式，则需要通过UTF-8格式解码

        String userName = request.getParameter("name");
        String result = "<font color='green'>该用户名可以使用</font>";
        Connection con=null;
        try {
			con=dbUtil.getCon();
			boolean check =userDao.check(con, userName); // 查找数据库是否存在该用户名
	        if (check) {
	            result = "<font color='red'>该用户已经被使用</font>";
	        } 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.getWriter().print(result); // 将结果输出到response响应流中
	}
	

}
