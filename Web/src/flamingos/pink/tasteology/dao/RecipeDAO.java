package flamingos.pink.tasteology.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import flamingos.pink.tasteology.model.Ingredient;
import flamingos.pink.tasteology.model.Recipe;

public class RecipeDAO {
	public static boolean addRecipe(String name, String category, String ingredients
									, String quantities, String units, String tags) throws Exception {
		try {
			Connection c = DBManager.getInstance().getConnection();
			Gson g = new Gson();
			String[] ings = g.fromJson(ingredients,String[].class);
			Double[] quants = g.fromJson(ingredients,Double[].class);
			String[] unitList = g.fromJson(units,String[].class);
			String[] tagList = g.fromJson(tags, String[].class);
			
			if( getRecipeId(name) != -1 ) {
				throw new Exception("Recipe Exists");
			}
			
			int catId = getCategoryId(category);
			if( catId == -1 ) {
				String sql = "INSERT INTO tl_category(name) VALUES (?)";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1,category);
				ps.execute();
				catId = getCategoryId(category);
			}
			
			String sql = "INSERT INTO tl_recipe(name) VALUES (?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,name);
			ps.execute();
			int recipeId = getRecipeId(name);
			
			sql = "INSERT INTO tl_recipeingredient(recipeId,ingredientId,quantity,unit) VALUES"; 
			int[] ids = new int[ings.length];
			int i = 0;
			for(String s: ings) {
				ids[i] = tryAddIngredient(s);
				if( i > 0 ) {
					sql += ",";
				}
				sql += "(?,?,?,?)";
				i++;
			}
			
			if( i > 0 ) {
				ps = c.prepareStatement(sql);
				for(i = 0; i < ings.length; i++) {
					ps.setInt(4 * i + 1, recipeId);
					ps.setInt(4 * i + 2, ids[i]);
					ps.setDouble(4 * i + 3, quants[i]);
					ps.setString(4 * i + 4, unitList[i]);
				}
			}
			ps.execute();
			
			sql = "INSERT INTO tl_tag(recipeId,tag) VALUES ";
			i = 0;
			for(String s : tagList) {
				if( i > 0 ) {
					sql += ",";
				}
				sql += "(?,?)";
				i++;
			}
			
			if( i > 0 ) {
				ps = c.prepareStatement(sql);
				for(i = 0; i < tagList.length; i++) {
					ps.setInt(2 * i + 1, recipeId);
					ps.setString(2 * i + 2, tagList[i]);
				}
			}
			ps.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static int tryAddIngredient(String ingredient) {
		Connection c = DBManager.getInstance().getConnection();
		
		int ingId = getIngredientId(ingredient);
		if( ingId == -1 ) {
			String sql = "INSERT INTO tl_ingredient(name) VALUES (?)";
			PreparedStatement ps;
			try {
				ps = c.prepareStatement(sql);
				ps.setString(1, ingredient);
				ps.execute();
				ingId = getIngredientId(ingredient);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ingId;
	}
	
	public static int getIngredientId(String ingredient) {
		Connection c = DBManager.getInstance().getConnection();
		
		String sql = "SELECT ingredientId FROM tl_ingredient WHERE name = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, ingredient);
			ResultSet rs = ps.executeQuery();
			if( rs.next() ) {
				return rs.getInt("ingredientId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int getRecipeId(String name) {
		Connection c = DBManager.getInstance().getConnection();
		
		String sql = "SELECT recipeId FROM tl_recipe WHERE name = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if( rs.next() ) {
				return rs.getInt("recipeId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int getCategoryId(String category) {
		Connection c = DBManager.getInstance().getConnection();
		
		String sql = "SELECT categoryId FROM tl_category WHERE name = ?";
		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,category);
			ResultSet rs = ps.executeQuery();
			if( rs.next() ) {
				return rs.getInt("categoryId");
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
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
