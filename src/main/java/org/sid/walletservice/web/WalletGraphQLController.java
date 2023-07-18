package org.sid.walletservice.web;

import org.sid.walletservice.dto.AddWalletRequestDTO;
import org.sid.walletservice.entities.Currency;
import org.sid.walletservice.entities.Wallet;
import org.sid.walletservice.entities.WalletTransaction;
import org.sid.walletservice.repositories.CurrencyRepository;
import org.sid.walletservice.repositories.WalletRepository;
import org.sid.walletservice.service.WalletService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WalletGraphQLController {
    private WalletRepository walletRepository;
    private CurrencyRepository currencyRepository;
    private WalletService walletService;

    public WalletGraphQLController(WalletRepository walletRepository, CurrencyRepository currencyRepository, WalletService walletService) {
        this.walletRepository = walletRepository;
        this.currencyRepository = currencyRepository;
        this.walletService = walletService;
    }

    @QueryMapping
    public List<Wallet> userWallets(){
        return  walletRepository.findAll();

    }
    @QueryMapping
    public Wallet walletById(@Argument String id){
        return  walletRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Wallet %s not found",id)));

    }
    @QueryMapping
    public List<Currency> currencies(){
     return currencyRepository.findAll();
    }

    @MutationMapping
    public Wallet addWallet(@Argument AddWalletRequestDTO wallet){
     return walletService.save(wallet);
    }
    @MutationMapping
    public List<WalletTransaction> walletTransfer(@Argument String sourceWalletId,
                                                  @Argument String destinationWalletId,
                                                  @Argument Double amount){
     return walletService.walletTransfer(sourceWalletId,destinationWalletId,amount);
    }
}

