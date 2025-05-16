package com.pbl3.supermarket.controller;

import com.pbl3.supermarket.dto.request.AddProductToCartRequest;
import com.pbl3.supermarket.dto.request.CustomerRequest.CustomerCreationRequest;
import com.pbl3.supermarket.dto.request.CustomerRequest.CustomerUpdateRequest;
import com.pbl3.supermarket.dto.response.ApiResponse;
import com.pbl3.supermarket.dto.response.CartItemResponse;
import com.pbl3.supermarket.dto.response.CustomerResponse;
import com.pbl3.supermarket.dto.response.ReceiptResponse;
import com.pbl3.supermarket.service.CartService;
import com.pbl3.supermarket.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    
    @Autowired
    private CartService cartService;

    @PostMapping
    ApiResponse<CustomerResponse> createCustomer(@RequestBody @Valid CustomerCreationRequest request){
        return ApiResponse.<CustomerResponse>builder()
                .message("[OK] Created a customer")
                .result(customerService.createCustomer(request))
                .build();
    }

    @GetMapping("/id/{customerID}")
    ApiResponse<CustomerResponse> getCustomer(@PathVariable("customerID") String customerID){
        return ApiResponse.<CustomerResponse>builder()
                .message("[OK] Get in4 about a customer")
                .result(customerService.getCustomerById(customerID))
                .build();
    }

    @GetMapping
    ApiResponse<List<CustomerResponse>> getAllCustomers(){
        return ApiResponse.<List<CustomerResponse>>builder()
                .message("[OK] Get all customers")
                .result(customerService.getAllCustomer())
                .build();
    }
    
    @DeleteMapping("/{customerID}")
    ApiResponse<Boolean> deleteCustomer(@PathVariable("customerID") String customerID){
        return ApiResponse.<Boolean>builder()
                .message("[OK] Deleted a customer")
                .result(customerService.deleteCustomerById(customerID))
                .build();
    }

    @PatchMapping("/{customerId}")
    ApiResponse<CustomerResponse> updateCustomer(@PathVariable("customerId") String customerID, @Valid @RequestBody CustomerUpdateRequest request){
        return ApiResponse.<CustomerResponse>builder()
                .message("[OK] Updated a customer")
                .result(customerService.updateCustomer(request))
                .build();
    }

    @PatchMapping("/pass")
    ApiResponse<Boolean> updatePassword(@Valid @RequestBody CustomerUpdateRequest request) {
        return ApiResponse.<Boolean>builder()
                .message("[OK] Updated a customer's password")
                .result(customerService.updatePassword(request))
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<CustomerResponse> getMyInfo()
    {
        return ApiResponse.<CustomerResponse>builder()
                .message("[OK] Get My Info")
                .result(customerService.getMyInfo())
                .build();
    }

    @GetMapping("/myCart")
    ApiResponse<List<CartItemResponse>> getMyCart()
    {
        return ApiResponse.<List<CartItemResponse>>builder()
                .message("[OK] Get My Cart")
                .result(customerService.getMyCart())
                .build();

    }

    @GetMapping("/myCart/size")
    ApiResponse<Integer> getMyCartSize(){
        return ApiResponse.<Integer>builder()
                .message("[OK] Get My Cart Size")
                .result(customerService.getNumberOfProductInCart())
                .build();
    }

    @PostMapping("/addToCart")
    ApiResponse<Boolean> addToCart(@RequestBody AddProductToCartRequest request){ //String productId, int quantity
        return ApiResponse.<Boolean>builder()
                .message("[OK] Add product to a cart")
                .result(cartService.addToCart(request))
                .build();
    }

    @PostMapping("/removeFromCart")
    ApiResponse<Boolean> removeFromCart(@RequestBody AddProductToCartRequest request ){
        String productId = request.getProductId();
        int quantity = request.getQuantity();

        return ApiResponse.<Boolean>builder()
                .message("[OK] Removed" + productId + " from Cart")
                .result(cartService.removeFromCart(productId, quantity))
                .build();
    }

    @PostMapping("/order")
    ApiResponse<ReceiptResponse> order()
    {
        return ApiResponse.<ReceiptResponse>builder()
                .message("[OK] Ordered, remove all thing about cart")
                .result(customerService.order())
                .build();
    }

    @GetMapping("/order")
    ApiResponse<List<ReceiptResponse>> getOrderHistory(){
        return ApiResponse.<List<ReceiptResponse>>builder()
                .message("[OK] Get Order History")
                .result(customerService.getOrderHistory())
                .build();
    }
}
