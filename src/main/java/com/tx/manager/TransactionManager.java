package com.tx.manager;

import com.tx.model.Transaction;
import com.tx.model.TransactionStatistics;

import java.util.List;

/**
 * Service abstraction for possible Vessel operations.
 * @author VipinK
 */
public interface TransactionManager {


	Transaction saveTransaction(Transaction transaction);

	TransactionStatistics getTransactionsStatistics();

}
