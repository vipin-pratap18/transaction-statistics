/**
 *
 */
package com.tx.dal;

import com.tx.model.Transaction;
import com.tx.model.TransactionStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author VipinK
 *
 */
@Repository
public class TransactionDALImpl implements TransactionDAL {

    private final Logger LOG = LoggerFactory.getLogger(TransactionDALImpl.class);

    private Map<Long, Transaction> timestampToTransactionMap = new ConcurrentSkipListMap<>(Collections.reverseOrder());

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        timestampToTransactionMap.put(transaction.getTimestamp(), transaction);
        return transaction;
    }


    @Override
    public Map<Long, Transaction> getAllTransactions() {
        return timestampToTransactionMap;
    }

    @Override
    public Transaction getTransaction(Transaction transaction) {
        long timestamp = transaction.getTimestamp();
        Transaction existingTxn = timestampToTransactionMap.get(timestamp);
        if(existingTxn != null && existingTxn.equals(transaction)){
            return existingTxn;
        }
        return null;
    }

}
