package model;

import java.util.List;

public class Recipe {
	private String name;
	private List<Ingredient> ingredients;
	private List<String> categories;
	private List<String> tags;
	
	public Recipe() {
		super();
	}

	public Recipe(String name, List<Ingredient> ingredients,
			List<String> categories, List<String> tags) {
		super();
		this.name = name;
		this.ingredients = ingredients;
		this.categories = categories;
		this.tags = tags;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
