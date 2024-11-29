package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.dto.UserExchangeCountResponseDto;
import com.sparta.currency_user.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchange")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    //환전 요청 수행
    @PostMapping
    public ResponseEntity<String> ExchangeRequest(@RequestBody ExchangeRequestDto exchangeRequestDto){
        exchangeService.RequestExchange(exchangeRequestDto);
        return ResponseEntity.ok().body("정상적으로 환전요청했습니다 .");
    }
    //환전 요청 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<ExchangeResponseDto>> getExchange(@PathVariable Long userId){
        List<ExchangeResponseDto> exchanges = exchangeService.getExchanges(userId);
        return new ResponseEntity<>(exchanges, HttpStatus.OK);
    }
    //환전 요청 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateExchange(@PathVariable Long id){
        exchangeService.updateExchange(id);
        return ResponseEntity.ok().body("요청이 취소되었습니다.");
    }
    //환전 요청 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExchange(@PathVariable Long id){
        exchangeService.deleteExchange(id);
        return ResponseEntity.ok().body("요청이 삭제되었습니다.");
    }
    //고객의 모든 환전 요청 조회
    @GetMapping("/{userId}/counts")
    public ResponseEntity<UserExchangeCountResponseDto> getExchangeCount(@PathVariable Long userId){
        return ResponseEntity.ok().body(exchangeService.getExchangeUserCount(userId));
    }




}
