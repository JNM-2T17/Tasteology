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

import dao.DBManager;
import dao.RecipeDAO;

@Controller
public class TheController {
	@RequestMapping("/")
	public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello");
		request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
	}
	
	@RequestMapping("/search")
	public void search( @RequestParam(value="param") String param
						,@RequestParam(value="searchStr") String searchStr
						,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("results", RecipeDAO.getRecipes(param,searchStr));
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
