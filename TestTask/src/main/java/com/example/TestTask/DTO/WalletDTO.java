package com.example.TestTask.DTO;

import jakarta.validation.constraints.NotEmpty;

public class WalletDTO {
    private int uuid;
    private int amount;
    private String operationType;

    public WalletDTO(int uuid, int amount, String operationType) {
        this.uuid = uuid;
        this.amount = amount;
        this.operationType = operationType;
    }

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

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
