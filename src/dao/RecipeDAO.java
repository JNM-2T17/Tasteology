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
		return null;
	}
	
	public static Recipe getRecipe(int id) {
		Connection c = DBManager.getInstance().getConnection();
		
		try {
			String sql = "SELECT id, name FROM tl_recipe WHERE id = ?";
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
			
				sql = "SELECT name " 
						+ "FROM tl_category C INNER JOIN tl_recipecategory RC" 
						+ "   ON C.categoryId = RC.categoryId "
						+ "WHERE recipeId = ?";
				ps = c.prepareStatement(sql);
				rs1 = ps.executeQuery();
				ArrayList<String> categories = new ArrayList<String>();
				while(rs1.next()) {
					categories.add(rs1.getString("name"));
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
				
				return new Recipe(rs.getString("name"),ings,categories,tags);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
