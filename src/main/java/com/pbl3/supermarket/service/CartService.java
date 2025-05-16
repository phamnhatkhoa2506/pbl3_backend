package com.pbl3.supermarket.service;

import com.pbl3.supermarket.dto.request.AddProductToCartRequest;
import com.pbl3.supermarket.entity.Cart;
import com.pbl3.supermarket.entity.CartItem;
import com.pbl3.supermarket.entity.Customer;
import com.pbl3.supermarket.entity.Product;
import com.pbl3.supermarket.exception.AppException;
import com.pbl3.supermarket.exception.ErrorCode;
import com.pbl3.supermarket.repository.CartItemRepository;
import com.pbl3.supermarket.repository.CartRepository;
import com.pbl3.supermarket.repository.CustomerRepository;
import com.pbl3.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public boolean addToCart(AddProductToCartRequest request) {
        String productId = request.getProductId();
        int quantity = request.getQuantity();
        var securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        Customer customer = customerRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NAME_NOTFOUND));

        Product product = productRepository.findById(productId).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_ID_NOTFOUND));
        if(customer.getCart() == null) {
            Cart cart = new Cart();
            customer.setCart(cart);
            cartRepository.save(cart);
            List<CartItem> cartItems = new ArrayList<>();
            cart.setCartItemList(cartItems);
        }
        boolean is_inCart = false;
        for(CartItem cartItem : customer.getCart().getCartItemList())
        {
            if(cartItem.getProduct().getId().equals(productId))
            {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItemRepository.save(cartItem);
                is_inCart = true;
                break;
            }
        }
        if(!is_inCart)
        {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(customer.getCart());
            cartItemRepository.save(cartItem);
        }
        return true;
    }

    public boolean removeFromCart(String productId, int quantity) {
        var securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        Customer customer = customerRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NAME_NOTFOUND));

        boolean removed = false;
        for(CartItem cartItem : customer.getCart().getCartItemList()){
            if(cartItem.getProduct().getId().equals(productId)){
                if(cartItem.getQuantity() >= quantity){
                    cartItem.setQuantity(cartItem.getQuantity() - quantity);
                    if(cartItem.getQuantity() <= 0){
                        customer.getCart().getCartItemList().remove(cartItem);
                        cartItemRepository.delete(cartItem);
                        cartRepository.save(customer.getCart());
                    }
                    else cartItemRepository.save(cartItem);
                    removed = true;
                }
                else throw new AppException(ErrorCode.QUANTITY_NOT_ENOUGH);
                break;
            }
        }

        return removed;
    }
}
