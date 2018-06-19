/**
 * 
 */
package com.tx.dal;

import com.tx.model.Transaction;
import com.tx.model.TransactionStatistics;

import java.util.List;
import java.util.Map;

/**
 * @author VipinK
 *
 */
public interface TransactionDAL {


    Transaction saveTransaction(Transaction transaction);

    Map<Long,Transaction> getAllTransactions();

    Transaction getTransaction(Transaction transaction);
}
