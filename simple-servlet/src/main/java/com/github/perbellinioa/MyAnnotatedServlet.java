package com.github.perbellinioa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author perbellinioa
 *
 */
@WebServlet(name="MyAnnoServlet", urlPatterns={"/annoServlet", "/annServ"})
public class MyAnnotatedServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1417938279137572040L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try(PrintWriter writer = resp.getWriter()) {
			
			writer.println("<html>");
			writer.println("<h1>");
			writer.println("My annotation based HttpServlet");
			writer.println("</h1>");
			writer.println("<p>");
			writer.println("<a>");
			writer.println("My Context Path: " + req.getContextPath());
			writer.println("</a>");
			writer.println("</p>");
			writer.println("<p>");
			writer.println("<a>");
			writer.println("My Path Info: " + req.getPathInfo());
			writer.println("</a>");
			writer.println("</p>");
			writer.println("<p>");
			writer.println("<a>");
			writer.println("My Servlet Path: " + req.getServletPath());
			writer.println("</a>");
			writer.println("</p>");
			writer.println("</html>");
			writer.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
