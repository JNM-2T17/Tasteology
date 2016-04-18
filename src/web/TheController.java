package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import flamingos.pink.tasteology.dao.DBManager;
import flamingos.pink.tasteology.dao.RecipeDAO;
import flamingos.pink.tasteology.dao.UserDAO;

@Controller
public class TheController {
	@RequestMapping("/")
	public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( request.getSession().getAttribute("userId") == null ) {
			request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
		}
	}
	
	@RequestMapping("/search")
	public void search( @RequestParam(value="param") String param
						,@RequestParam(value="searchStr") String searchStr
						,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("results", RecipeDAO.getRecipes(param,searchStr));
		request.getRequestDispatcher("WEB-INF/view/search.jsp").forward(request,response);
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public void register( @RequestParam(value="username") String username
							,@RequestParam(value="password") String password
							,@RequestParam(value="fName") String fName
							,@RequestParam(value="lName") String lName
							,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result = UserDAO.addUser(username,password,fName,lName);
		request.setAttribute("registerStatus", result);
		if( result ) {
			request.getSession().setAttribute("userId", UserDAO.getId(username));
		}
		request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
	}
	
	@RequestMapping("/registerMobile")
	@ResponseBody
	public void registerMobile( @RequestParam(value="username") String username
							,@RequestParam(value="password") String password
							,@RequestParam(value="fName") String fName
							,@RequestParam(value="lName") String lName
							,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result = UserDAO.addUser(username,password,fName,lName);
		if( result ) {
			request.getSession().setAttribute("userId", UserDAO.getId(username));
		}
		response.getWriter().print(result);
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public void login(@RequestParam(value="username") String username
			,@RequestParam(value="password") String password
			,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			boolean result = UserDAO.checkPass(username, password);
			request.setAttribute("loginStatus",result ? "success" : "wrongPass");
			if( result ) {
				request.getSession().setAttribute("userId", UserDAO.getId(username));
			}
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("loginStatus","noUser");
		}
		request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
	}
	
	@RequestMapping("/loginMobile")
	@ResponseBody
	public void loginMobile(@RequestParam(value="username") String username
			,@RequestParam(value="password") String password
			,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reply = "";
		try {
			boolean result = UserDAO.checkPass(username, password);
			reply = result ? ("" + UserDAO.getId(username)) : "wrongPass";
			if( result ) {
				request.getSession().setAttribute("userId", UserDAO.getId(username));
			}
		}catch(Exception e) {
			e.printStackTrace();
			reply = "noUser";
		}
		response.getWriter().print(reply);
	}
	
	@RequestMapping("/reloginMobile")
	@ResponseBody
	public void reloginMobile(@RequestParam(value="id") String id
			,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( UserDAO.verifyId(id)) {
			request.getSession().setAttribute("userId", id);
		}
		response.getWriter().print(id);
	}
	
//	@RequestMapping("/Query")
//	@ResponseBody
//	public void query(	@RequestParam(value="table") String table,
//						@RequestParam(value="groupBy") String groupByStr,
//						@RequestParam(value="whereCols") String whereColsStr,
//						@RequestParam(value="whereRange") String whereRangeStr,
//						@RequestParam(value="whereVals") String whereValsStr,
//			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	}
}
