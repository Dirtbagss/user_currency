package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExchangeResponseDto {
    private Long ExchangeId;
    private Long userId;
    private Long currencyId;
    private double amountInKrw;
    private double amountAfterExchange;
    private Status status;

    public static ExchangeResponseDto toDto(Exchange exchange) {
        return new ExchangeResponseDto(
                exchange.getId(),
                exchange.getUser().getId(),
                exchange.getCurrency().getId(),
                exchange.getAmountInKrw(),
                exchange.getAmountAfterExchange(),
                exchange.getStatus()
        );
    }
}