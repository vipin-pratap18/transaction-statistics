package com.tx.manager;

import com.tx.model.Transaction;
import com.tx.model.TransactionStatistics;

import java.util.List;
import java.util.Map;

/**
 * @author VipinK
 */
public interface TransactionManager {


	Transaction saveTransaction(Transaction transaction);

	TransactionStatistics getTransactionsStatistics();

}
