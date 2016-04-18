-----------------------------
Search Recipe by Recipe name
-----------------------------
SELECT R.name
FROM tl_recipe R 
WHERE <searchStr> = R.name;

----------------------------
Search Recipe by Ingredient
----------------------------
SELECT R.name
FROM tl_recipeingredient RI, tl_recipe R, tl_ingredient I
WHERE R.recipeId = RI.recipeId AND I.ingredientId =  RI.ingredientId
AND <searchStr> = I.name
ORDER by R.name;

----------------------------
Search Recipe by Category
----------------------------

SELECT R.name
FROM tl_recipe R, tl_recipecategory RC, tl_category C
WHERE R.recipeId = RC.recipeId AND C.categoryId = RC.categoryId 
AND <searchStr> = C.name
ORDER by R.name;

-----------------------------
Search Recipe by Tags
-----------------------------
SELECT R.name
FROM tl_recipe R, tl_tag T
WHERE R.recipeId = T.recipeId
AND <searchStr> = T.tag
ORDER by R.name;

-------------------------------
Check User
-------------------------------
SELECT U.username
FROM user U
WHERE <searchStr> = U.username;

-------------------------------
Check Password
-------------------------------
SELECT U.password
From user U
WHERE <searchStr> = U.password;