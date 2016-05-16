DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS ingredient_kind;

DROP TABLE IF EXISTS user_ingredient;
DROP TABLE IF EXISTS user_data;

DROP TABLE IF EXISTS recipe_ingredient;
DROP TABLE IF EXISTS recipe;


DELETE FROM ingredient;
DELETE FROM ingredient_kind;
DELETE FROM recipe_ingredient;
DELETE FROM recipe;

CREATE TABLE ingredient_kind(
	kind_name		VARCHAR(20) PRIMARY KEY
);

CREATE TABLE ingredient(
	name		VARCHAR(20) PRIMARY KEY,
	quantity	DOUBLE NOT NULL DEFAULT 0.0,
	measure		VARCHAR(10),
	kind		VARCHAR(20),
	FOREIGN KEY (kind) REFERENCES ingredient_kind(kind_name) ON DELETE CASCADE
);

CREATE TABLE user_data(
	id				VARCHAR(20) PRIMARY KEY,
	first_name		VARCHAR(10) NOT NULL,
	last_name		VARCHAR(20) NOT NULL,
	gender			INT			NOT NULL,
	living_style	INT			NOT NULL DEFAULT 2
);

CREATE TABLE user_ingredient(
	name		VARCHAR(20) PRIMARY KEY,
	quantity	DOUBLE NOT NULL DEFAULT 0.0,
	measure		VARCHAR(10) NOT NULL,
	user_id		VARCHAR(20),
	FOREIGN KEY (user_id) REFERENCES user_data(id) ON DELETE CASCADE
);

CREATE TABLE recipe (
	recipe_name 	VARCHAR(20) PRIMARY KEY,
	core_ingredient	VARCHAR(20) NOT NULL,
	url				VARCHAR(200)
);

CREATE TABLE recipe_ingredient(
	ingredient_id	INT AUTO_INCREMENT PRIMARY KEY,
	name			VARCHAR(20),
	quantity		DOUBLE NOT NULL DEFAULT 0.0,
	measure			VARCHAR(10) NOT NULL,
	recipe_name		VARCHAR(20),
	FOREIGN KEY(recipe_name) REFERENCES recipe(recipe_name) ON DELETE CASCADE
);

