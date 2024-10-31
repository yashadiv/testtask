package com.example.TestTask.Repositories;

import com.example.TestTask.Models.Wallets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallets, Integer> {
}
