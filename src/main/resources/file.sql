create database if not exists db_product;


use db_product;


create table Product (
	product_id BIGINT(10) PRIMARY KEY,
	product_name varchar(30) NOT NULL,
	year date NOT NULL, 
	price BIGINT(10) NOT NULL
);

INSERT INTO Product VALUES (1001, 'LG Amoled TV', '2010-02-03', 54000);
INSERT INTO Product VALUES (1002, 'Sony Bravia','1993-01-15',70000);


commit;
SELECT * FROM Product;
