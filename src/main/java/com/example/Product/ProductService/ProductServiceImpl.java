package com.example.Product.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Product.Exception.ProductException;
import com.example.Product.Product.Product;
import com.example.Product.ProductDTO.ProductDTO;
import com.example.Product.ProductRepository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	
	@Autowired
    ProductRepository productRepository;

	@Override
	public Integer addProduct(ProductDTO productDTO) throws ProductException {
		// TODO Auto-generated method stub
		Optional<Product> optional = productRepository.findById(productDTO.getProductId());
		if(optional.isPresent()) {
			throw new ProductException("PRODUCT_ALREADY_EXISTS");
		}
		Product p = new Product();
		p.setPrice(productDTO.getPrice());
		p.setProductId(productDTO.getProductId());
		p.setProductName(productDTO.getProductName());
		p.setYear(productDTO.getYear());
		
		productRepository.save(p);
		return p.getProductId();
	}
    @Override
	public List<ProductDTO> getAllProducts() throws ProductException {
		Iterable<Product> itr= productRepository.findAll();
		List<ProductDTO> l1 = new ArrayList<ProductDTO>();
		itr.forEach(product -> {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setPrice(product.getPrice());
			productDTO.setProductId(product.getProductId());
			productDTO.setProductName(product.getProductName());
			productDTO.setYear(product.getYear());
			l1.add(productDTO);
			
			
		});
		
		
		
		return l1;
		
	}
	@Override
	public ProductDTO getProduct(Integer productId) throws ProductException {
		// TODO Auto-generated method stub
	Optional<Product>optional =	productRepository.findById(productId);
	Product product = optional.orElseThrow(()->new ProductException("PRODUCT_NOT_EXISTS"));
	ProductDTO productDTO = new ProductDTO();
	productDTO.setPrice(product.getPrice());
	productDTO.setProductId(product.getProductId());
	productDTO.setProductName(product.getProductName());
	productDTO.setYear(product.getYear());
		return productDTO;
	}
	@Override
	public void updateProductPrice(Integer productId, Integer price) throws ProductException {
		// TODO Auto-generated method stub
		Optional<Product> optional = productRepository.findById(productId);
		Product product = optional.orElseThrow(()->new ProductException("PRODUCT_NOT_EXISTS"));
		product.setPrice(price);
		productRepository.save(product);
	}
	@Override
	public void deleteProduct(Integer productId) throws ProductException {
		// TODO Auto-generated method stub
		Optional<Product> optional = productRepository.findById(productId);
		Product product = optional.orElseThrow(()->new ProductException("PRODUCT_NOT_EXISTS"));
		productRepository.deleteById(productId);
		
	
		
	}
    


}
