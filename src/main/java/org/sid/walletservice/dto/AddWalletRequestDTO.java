package org.sid.walletservice.dto;

public record AddWalletRequestDTO(
        Double balance,
        String currencyCode
){}
