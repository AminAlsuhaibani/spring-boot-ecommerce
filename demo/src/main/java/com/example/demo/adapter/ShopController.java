package com.example.demo.adapter;

import com.example.demo.application.CartService;
import com.example.demo.domain.CartItem;
import com.example.demo.domain.Product;
import com.example.demo.infrastructure.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ShopController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    // Get all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> listProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    // Add a new product
    @PostMapping("/add_products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(201).body(savedProduct);
    }

    // Search products by keyword
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> results = productRepository.searchByKeyword(keyword);
        return ResponseEntity.ok(results);
    }
    // Get product details by ID
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductDetails(@PathVariable int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get()); // Return the product
        } else {
            return ResponseEntity.status(404).body("Product not found"); // Return error message
        }
    }

    // Add a product to the cart
    @PostMapping("/add_to_cart/{productId}")
    public ResponseEntity<?> addToCart(@PathVariable int productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            cartService.addToCart(productOpt.get()); // Use CartService to add to the cart
            return ResponseEntity.status(201).body("Product added to cart");
        } else {
            return ResponseEntity.status(404).body("Product not found");
        }
    }

    // View all items in the cart
    @GetMapping("/view_cart")
    public ResponseEntity<?> viewCart() {
        return ResponseEntity.ok(cartService.viewCart()); // Use CartService to view the cart
    }

    // Remove product from the cart
    @DeleteMapping("/remove_from_cart/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable int productId) {
        cartService.removeFromCart(productId); // Use CartService to remove from the cart
        return ResponseEntity.ok("Product removed from cart");
    }

    // Clear all items from the cart
    @DeleteMapping("/empty_cart")
    public ResponseEntity<?> emptyCart() {
        cartService.emptyCart(); // Use CartService to empty the cart
        return ResponseEntity.ok("All items removed from the cart");
    }

    // Checkout and calculate total and total tax
    @PostMapping("/checkout")
    public ResponseEntity<Map<String, Object>> checkout() {

        // Create a formatter for two decimal places
        DecimalFormat df = new DecimalFormat("0.00");

        // Prepare the receipt details
        List<Map<String, Object>> productDetails = new ArrayList<>();
        double total = cartService.calculateTotal();
        double totalTax = cartService.calculateTotalTax();
        double grandTotal = total + totalTax;

        // Iterate over the cart items to prepare details
        for (CartItem item : cartService.viewCart().values()) {
            Map<String, Object> productDetail = new LinkedHashMap<>();
            productDetail.put("name", item.getProduct().getName());
            productDetail.put("price", df.format(item.getProduct().getPrice()));
            productDetail.put("quantity", item.getQuantity());
            productDetail.put("subtotal", df.format(item.getTotalPrice()));
            productDetail.put("tax", df.format(item.getTotalTax()));

            productDetails.add(productDetail);
        }

        // Clear the cart after checkout
        cartService.emptyCart();

        // Prepare the response using LinkedHashMap to maintain order
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Checkout successful");
        response.put("products", productDetails);
        response.put("total", df.format(total));
        response.put("totalTax", df.format(totalTax));
        response.put("grandTotal", df.format(grandTotal));

        return ResponseEntity.ok(response);
    }
}
