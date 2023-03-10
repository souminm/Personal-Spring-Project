package com.example.Product.ProductController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Product.Exception.ProductException;
import com.example.Product.Product.Product;
import com.example.Product.ProductDTO.ProductDTO;
import com.example.Product.ProductService.ProductService;

import io.swagger.annotations.ApiOperation;


@ApiOperation(value = "/Product",tags = "ProductController")
@RestController
@RequestMapping(value="/Product")
public class ProductAPI {

	@Autowired
	ProductService productService;
	
	@Autowired
	private Environment environment;
	
	@ApiOperation(value = "Add a product",response = Product.class)
	@PostMapping(value="/AddProducts")
	public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) throws ProductException{
		Integer productId = productService.addProduct(productDTO);
		String successMessage = environment.getProperty("API_INSERT_SUCCESS") +" "+productId;
		return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
	}
	@ApiOperation(value = "Get all products",response = Iterable.class)
	@GetMapping(value="/GetAllProducts")
	public ResponseEntity<List<ProductDTO>> getAllProducts() throws ProductException{
		List<ProductDTO> l1 = productService.getAllProducts();
		
		return new ResponseEntity<List<ProductDTO>>(l1, HttpStatus.OK);
		
	}
	@ApiOperation(value ="Get Product based on productId",response = Product.class)
	@GetMapping(value = "/GetProduct/{productId}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer productId) throws ProductException{
		ProductDTO productDTO = productService.getProduct(productId);
		
		return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
		
	}
	@ApiOperation(value ="Update Product based on productId",response = Product.class)
	@PutMapping(value = "/UpdateProduct/{productId}")
	public ResponseEntity<String> updateProductPrice(@PathVariable Integer productId,@RequestBody ProductDTO  productDTO) throws ProductException{
		productService.updateProductPrice(productId, productDTO.getPrice());
		String successMessage = environment.getProperty("API_UPDATE_SUCCESS");
		
		return new ResponseEntity<String>(successMessage, HttpStatus.OK);
		
	}
	@ApiOperation(value ="delete Product based on productId",response = Product.class)
	@DeleteMapping(value = "/deleteProduct/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) throws ProductException{
		
		productService.deleteProduct(productId);
		String successMessage = productId+" "+environment.getProperty("API_DELETE_SUCCESS");
		return new ResponseEntity<String>(successMessage, HttpStatus.OK);
		
	}
}
