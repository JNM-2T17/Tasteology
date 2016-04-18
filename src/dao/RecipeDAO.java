package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ingredient;
import model.Recipe;

public class RecipeDAO {
	public static List<Recipe> getRecipes(String param, String searchStr) {
		Connection c = DBManager.getInstance().getConnection();
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		String sql = "";
		
		switch(param) {
			case "name":
				sql = "SELECT recipeId "
							  +	"FROM tl_recipe R " 
							  + "WHERE R.name LIKE ? "
							  + "ORDER by R.name";
				break;
			case "ingredient":
				sql = "SELECT R.recipeId "
							  + "FROM tl_recipeingredient RI INNER JOIN tl_recipe R "
							  + "ON R.recipeId = RI.recipeId "
							  + "INNER JOIN tl_ingredient I "
							  + "ON I.ingredientId =  RI.ingredientId "
							  + "WHERE I.name LIKE ? "
							  + "ORDER by R.name";
				break;
			case "tag":
				sql = "SELECT R.recipeId "
							  + "FROM tl_recipe R INNER JOIN tl_tag T "
							  + "ON R.recipeId = T.recipeId "
							  + "WHERE T.tag LIKE ? "
							  + "ORDER by R.name";

				break;
			case "category":
				sql = "SELECT R.recipeId "
							  + "FROM tl_recipe R INNER JOIN tl_category C "
							  + "ON R.category = C.categoryId "
							  + "WHERE C.name LIKE ? "
							  + "ORDER by R.name";
				break;
			default: return recipes;
		}
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, "%" + searchStr + "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("recipeId");
				Recipe r = getRecipe(id);
				recipes.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return recipes;
	}
	
	public static Recipe getRecipe(int id) {
		Connection c = DBManager.getInstance().getConnection();
		
		try {
			String sql = "SELECT R.name, C.name as category " 
						+ "FROM tl_recipe R INNER JOIN tl_category C "
						+ " ON R.category = C.categoryId "
						+ "WHERE recipeId = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				sql = "SELECT name, quantity, unit " 
						+ "FROM tl_ingredient I INNER JOIN tl_recipeingredient RI" 
						+ "   ON I.ingredientId = RI.ingredientId "
						+ "WHERE recipeId = ?";
				ps = c.prepareStatement(sql);
				ResultSet rs1 = ps.executeQuery();
				ArrayList<Ingredient> ings = new ArrayList<Ingredient>();
				while(rs1.next()) {
					String name = rs1.getString("name");
					double quantity = rs1.getDouble("quantity");
					String unit = rs1.getString("unit");
					ings.add(new Ingredient(name,quantity,unit));
				}
				
				sql = "SELECT tag " 
						+ "FROM tl_tag"
						+ "WHERE recipeId = ?";
				ps = c.prepareStatement(sql);
				rs1 = ps.executeQuery();
				ArrayList<String> tags = new ArrayList<String>();
				while(rs1.next()) {
					tags.add(rs1.getString("tag"));
				}
				
				return new Recipe(rs.getString("name"),rs.getString("category"),ings,tags);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
