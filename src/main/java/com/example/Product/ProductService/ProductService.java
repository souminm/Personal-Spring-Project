package com.example.Product.ProductService;

import java.util.List;

import com.example.Product.Exception.ProductException;
import com.example.Product.ProductDTO.ProductDTO;

public interface ProductService {

	public Integer addProduct(ProductDTO productDTO) throws ProductException;
	public List<ProductDTO> getAllProducts() throws ProductException;
	public ProductDTO getProduct(Integer productId) throws ProductException;
	public void updateProductPrice(Integer productId, Integer price) throws ProductException;
	public void deleteProduct(Integer productId) throws ProductException;
	
}
