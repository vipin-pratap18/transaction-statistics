/**
 * 
 */
package com.tx.dal;

import com.tx.model.Transaction;
import com.tx.model.TransactionStatistics;

import java.util.List;

/**
 * @author VipinK
 *
 */
public interface TransactionDAL {


    Transaction saveTransaction(Transaction transaction);

    TransactionStatistics getTransactionsStatistics();

}
