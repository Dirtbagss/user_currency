package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.UserExchangeCountResponseDto;
import com.sparta.currency_user.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    List<Exchange> findByUserId(Long userId);

    @Query("SELECT new com.sparta.currency_user.dto.UserExchangeCountResponseDto(COUNT(e), SUM(e.amountInKrw)) FROM Exchange e WHERE e.user.id = :userId")
    UserExchangeCountResponseDto getExchangesByUserId(@Param("userId") Long userId);
}
