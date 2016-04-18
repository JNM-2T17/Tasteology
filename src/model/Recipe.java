package model;

import java.util.List;

public class Recipe {
	private String name;
	private String category;
	private List<Ingredient> ingredients;
	private List<String> tags;
	
	public Recipe() {
		super();
	}

	public Recipe(String name, String category, List<Ingredient> ingredients,
			List<String> tags) {
		super();
		this.name = name;
		this.category = category;
		this.ingredients = ingredients;
		this.tags = tags;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
