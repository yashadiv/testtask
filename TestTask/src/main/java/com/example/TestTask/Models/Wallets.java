package com.example.TestTask.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "wallets")
public class Wallets {

    @Id
    @Column(name = "uuid")
    private int uuid;

    @Column
    @Min(value = 0, message = "Недостаточно средств")
    private int amount;

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Wallets() {
    }

    public Wallets(int uuid, int amount) {
        this.uuid = uuid;
        this.amount = amount;
    }
    public Wallets(int uuid) {
        this.uuid = uuid;
    }
}
