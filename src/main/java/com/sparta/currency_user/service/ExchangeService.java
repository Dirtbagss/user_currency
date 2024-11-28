package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.entity.Status;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final UserService userService;
    private final CurrencyService currencyService;

    //환전요청
    public void RequestExchange(ExchangeRequestDto request) {
        User user = userService.findUserById(request.getUserId());
        Currency currency = currencyService.findCurrencyById(request.getCurrencyId());

        String amountAfterExchange = getAmountExchange(request.getAmountInKrw(), currency);

        Exchange exchange = new Exchange(user,currency, request.getAmountInKrw(), amountAfterExchange, Status.NORMAL );
        exchangeRepository.save(exchange);
    }
    // 환전후 금액
    public String getAmountExchange(BigDecimal amountKrw, Currency currency) {
        BigDecimal exchangeRate = currency.getExchangeRate();
        BigDecimal amountExchange = amountKrw.multiply(exchangeRate);
        if(Objects.equals(currency.getCurrencyName(), "JPY")){
           return amountExchange.setScale(0, RoundingMode.FLOOR)+currency.getSymbol();
        }
        DecimalFormat formatter = new DecimalFormat("#.00");
        String formattedValue = formatter.format(amountExchange);
        return formattedValue+currency.getSymbol();
    }
    //수정
    public void updateExchange(Long id) {
        Exchange exchange = findExchangeById(id);
        exchange.setStatus(Status.CANCELLED);
        exchangeRepository.save(exchange);
    }
    //삭제
    public void deleteExchange(Long id) {
        exchangeRepository.deleteById(id);
    }
    //단건 조회
    private Exchange findExchangeById(Long id) {
        return exchangeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }



    //환전
    public List<ExchangeResponseDto> getExchanges(Long userId) {
        List<Exchange> exchanges = exchangeRepository.findByUserId(userId);
        return exchanges.stream().map(ExchangeResponseDto::toDto).toList();
    }



}
