package com.sparta.currency_user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExchangeRequestDto {
    private Long userId;
    private Long currencyId;
    private double amountInKrw;


}
