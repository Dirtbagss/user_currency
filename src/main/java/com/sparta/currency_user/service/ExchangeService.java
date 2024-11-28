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
import java.util.List;

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

        double amountAfterExchange = getAmountExchange(request.getAmountInKrw(), currency);

        Exchange exchange = new Exchange(user,currency, request.getAmountInKrw(), amountAfterExchange, Status.NORMAL );
        exchangeRepository.save(exchange);
    }
    // 환전후 금액
    public double getAmountExchange(Double amountKrw, Currency currency) {
        BigDecimal exchangeRate = currency.getExchangeRate();
        BigDecimal krw = new BigDecimal(amountKrw);
        return krw.divide(exchangeRate, 2, BigDecimal.ROUND_DOWN).doubleValue();
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
