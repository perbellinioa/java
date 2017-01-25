package com.github.perbellinioa;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author perbellinioa
 *
 */
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = -7875438549537404163L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try (PrintWriter out = resp.getWriter()) {
			out.println("MyServler doGet Response");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try (PrintWriter out = resp.getWriter()) {
			out.println("MyServler doPost Response");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
