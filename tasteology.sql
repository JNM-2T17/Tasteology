CREATE SCHEMA pf_tasteology;
USE pf_tasteology;

CREATE TABLE tl_user (
	userId INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(45) UNIQUE NOT NULL,
    password CHAR(60) NOT NULL,
    fName VARCHAR(45) NOT NULL,
    lName VARCHAR(45) NOT NULL,
    isAdmin TINYINT(1) NOT NULL DEFAULT 0,
    dateAdded DATETIME DEFAULT CURRENT_TIMESTAMP,
    status TINYINT(1) DEFAULT 1
) engine = innoDB;

CREATE TABLE tl_recipe (
	recipeId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    dateAdded DATETIME DEFAULT CURRENT_TIMESTAMP,
    status TINYINT(1) DEFAULT 1
) engine = innoDB;

CREATE TABLE tl_category (
	categoryId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    dateAdded DATETIME DEFAULT CURRENT_TIMESTAMP,
    status TINYINT(1) DEFAULT 1
) engine = innoDB;

CREATE TABLE tl_ingredient (
	ingredientId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    dateAdded DATETIME DEFAULT CURRENT_TIMESTAMP,
    status TINYINT(1) DEFAULT 1
) engine = innoDB;

CREATE TABLE tl_tag (
	recipeId INT,
    tag VARCHAR(45),
    dateAdded DATETIME DEFAULT CURRENT_TIMESTAMP,
    status TINYINT(1) DEFAULT 1,
    PRIMARY KEY(recipeId, tag),
    CONSTRAINT tl_tagfk_1
		FOREIGN KEY (recipeID)
        REFERENCES tl_recipe(recipeId)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE tl_like (
	recipeId INT,
    userId INT,
    dateAdded DATETIME DEFAULT CURRENT_TIMESTAMP,
    status TINYINT(1) DEFAULT 1,
    PRIMARY KEY(recipeId,userId),
    CONSTRAINT tl_likefk_1
		FOREIGN KEY (recipeId)
        REFERENCES tl_recipe(recipeId)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT tl_likefk_2
		FOREIGN KEY (userId)
        REFERENCES tl_user(userId)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE tl_recipeingredient (
	recipeId INT,
    ingredientId INT,
    quantity DOUBLE NOT NULL,
    unit VARCHAR(45) NOT NULL,
    dateAdded DATETIME DEFAULT CURRENT_TIMESTAMP,
    status TINYINT(1) DEFAULT 1,
    PRIMARY KEY(recipeId,ingredientId),
    CONSTRAINT tl_recipeingredientfk_1
		FOREIGN KEY (recipeId)
        REFERENCES tl_recipe(recipeId)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT tl_recipeingredientfk_2
		FOREIGN KEY (ingredientId)
        REFERENCES tl_ingredient(ingredientId)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) engine = innoDB;

CREATE TABLE tl_recipecategory (
	recipeId INT,
    categoryId INT,
    quantity DOUBLE NOT NULL,
    unit VARCHAR(45) NOT NULL,
    dateAdded DATETIME DEFAULT CURRENT_TIMESTAMP,
    status TINYINT(1) DEFAULT 1,
    PRIMARY KEY(recipeId,categoryId),
    CONSTRAINT tl_recipecategoryfk_1
		FOREIGN KEY (recipeId)
        REFERENCES tl_recipe(recipeId)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT tl_recipecategoryfk_2
		FOREIGN KEY (categoryId)
        REFERENCES tl_category(categoryId)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) engine = innoDB;