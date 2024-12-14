package dev.tanvx.transaction_service.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    @GetMapping
    public String getTransaction() {
        return "Transaction";
    }
}
