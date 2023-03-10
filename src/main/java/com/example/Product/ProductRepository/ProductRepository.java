package com.example.Product.ProductRepository;




import org.springframework.data.repository.CrudRepository;


import com.example.Product.Product.Product;




public interface ProductRepository extends CrudRepository<Product, Integer> 
//public interface ProductRepository extends JpaRepository<Product, Integer> 
{
  
  
}
