package com.example.TestTask.Controllers;

import com.example.TestTask.DTO.WalletDTO;
import com.example.TestTask.Models.Wallets;
import com.example.TestTask.Services.WalletServices;
import com.example.TestTask.utils.WalletErrorResponse;
import com.example.TestTask.utils.WalletNotFoundException;
import com.example.TestTask.utils.WalletNotUpdateException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletServices walletServices;

    @Autowired
    public WalletController(WalletServices walletServices) {
        this.walletServices = walletServices;
    }

    @GetMapping("/wallets/{wallets_UUID}")
    public Wallets getWallet(@PathVariable("wallets_UUID") int uuid){
        return walletServices.findOne(uuid);
    }
    @ExceptionHandler
    private ResponseEntity<WalletErrorResponse> handlerException(WalletNotFoundException e){
        WalletErrorResponse response = new WalletErrorResponse("ОШИБКА: Данный кошелек не найден");
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/wallet")
    public ResponseEntity<HttpStatus> operation(@RequestBody @Valid WalletDTO walletDTO){
        if (walletDTO.getAmount()<=0)
            throw new WalletNotUpdateException("ОШИБКА: Не верно введена сумма");
        if (walletDTO.getUuid()<=0)
            throw new WalletNotUpdateException("ОШИБКА: uuid не может быть отрицательным");
        walletServices.operation(walletDTO.getUuid(), convertToWallet(walletDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<WalletErrorResponse> handlerException(WalletNotUpdateException e){
        WalletErrorResponse response = new WalletErrorResponse(
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Wallets convertToWallet( WalletDTO walletDTO) {
        Wallets wallets = walletServices.findOne(walletDTO.getUuid());
        if (walletDTO.getOperationType().equals("DEPOSIT")){
            wallets.setAmount(wallets.getAmount() + walletDTO.getAmount());
        }
        else if (walletDTO.getOperationType().equals("WITHDRAW")){
            if(wallets.getAmount() - walletDTO.getAmount()<0)
                throw new WalletNotUpdateException("ОШИБКА: Не достаточно средств");
            else
                wallets.setAmount(wallets.getAmount() - walletDTO.getAmount());
        }
        else throw new WalletNotUpdateException("ОШИБКА: Данная операция не доступна");


        return wallets;
    }
}
