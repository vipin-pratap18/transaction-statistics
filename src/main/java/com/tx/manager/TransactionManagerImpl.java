package com.tx.manager;

import com.tx.dal.TransactionDAL;
import com.tx.model.Transaction;
import com.tx.model.TransactionStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author VipinK
 *
 */
@Service
public class TransactionManagerImpl implements TransactionManager {

	private final Logger LOG = LoggerFactory.getLogger(TransactionManagerImpl.class);
	@Autowired
	private TransactionDAL txDAL;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return txDAL.saveTransaction(transaction);
    }

    @Override
    public TransactionStatistics getTransactionsStatistics() {
        return txDAL.getTransactionsStatistics();
    }
}
