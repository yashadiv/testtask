package com.example.TestTask.Services;

import com.example.TestTask.Models.Wallets;
import com.example.TestTask.Repositories.WalletRepository;
import com.example.TestTask.utils.WalletNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class WalletServices {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletServices(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallets findOne(int uuid){
        Optional<Wallets> foundWallet = walletRepository.findById(uuid);
        return foundWallet.orElseThrow(
                WalletNotFoundException::new
        );
    }
    @Transactional
    public void operation (int uuid, Wallets wallets){
        wallets.setUuid(uuid);
        walletRepository.save(wallets);
    }

}
