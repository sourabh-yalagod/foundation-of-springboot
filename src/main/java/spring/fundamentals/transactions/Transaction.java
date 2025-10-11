package spring.fundamentals.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.UserTransactionAdapter;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

// approach 1 using platformtransaction obj
@Component
@Transactional(transactionManager = "customTransactionManager")
public class Transaction {
    @Autowired
    PlatformTransactionManager transactionManager;
    @Autowired
    TransactionTemplate transactionTemplate;

    public void programmaticApproach1() {
        TransactionStatus status = transactionManager.getTransaction(null);
        try {
            System.out.println("Transaction Running...!");
            transactionManager.commit(status);
        } catch (RuntimeException e) {
            transactionManager.rollback(status);
            throw new RuntimeException(e);
        } finally {
            System.out.println("Transaction handled programmatically");
        }
    }
    public void programmaticApproach2() {
        TransactionCallback<TransactionStatus> callback = (status)-> {
            System.out.println("business logic");
            return status;
        };
        transactionTemplate.execute(callback);
    }
}
