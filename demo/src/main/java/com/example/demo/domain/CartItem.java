package com.example.demo.domain;

public class CartItem {

    private Product product;   // The product in the cart
    private int quantity;      // Quantity of the product
    private double totalPrice; // Total price based on quantity
    private double totalTax;    // field to store the calculated tax


    // Constructor to initialize a new CartItem
    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;  // Start with quantity 1
        this.totalPrice = product.getPrice(); 
        this.totalTax = calculateTax(); 
    }

    public void incrementQuantity() {
        this.quantity++;
        this.totalPrice = this.quantity * product.getPrice();
    }

    // Decrement the quantity by 1
    public void decrementQuantity() {
        if (quantity > 1) {
            this.quantity--;
            this.totalPrice = this.quantity * product.getPrice();  
        }
    }
    private double calculateTax() {
        return this.product.getPrice() * this.product.getTaxRate();
    }
    // Getter for the quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter for the quantity and update the total price accordingly
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = quantity * product.getPrice();  
        this.totalTax = calculateTax();
    }

    // Getter for the total price
    public double getTotalPrice() {
        return totalPrice;
    }

    // Setter for the total price
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Getter for the product
    public Product getProduct() {
        return product;
    }

    // Setter for the product
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }
}
