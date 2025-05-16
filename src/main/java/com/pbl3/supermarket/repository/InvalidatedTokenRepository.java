package com.pbl3.supermarket.repository;

import com.pbl3.supermarket.entity.Cart;
import com.pbl3.supermarket.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}
