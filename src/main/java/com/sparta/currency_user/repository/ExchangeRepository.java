package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    List<Exchange> findByUserId(Long userId);
}
