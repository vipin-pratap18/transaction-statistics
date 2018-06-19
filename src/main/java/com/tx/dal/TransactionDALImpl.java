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

/**
 * @author VipinK
 *
 * Exception thrown in this class are very generic and with no proper response code. So please ignore that part.
 * You have to create your own exception and throw that
 */
@Repository
public class TransactionDALImpl implements TransactionDAL {

    private final Logger LOG = LoggerFactory.getLogger(TransactionDALImpl.class);

    private Map<Long, Transaction> timestampToTransactionMap = new TreeMap<>(Collections.reverseOrder());

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        long timestamp = transaction.getTimestamp();
        Transaction existingTxn = timestampToTransactionMap.get(timestamp);
        if(existingTxn != null && (existingTxn.getAmount() == transaction.getAmount())){
            throw new RuntimeException("A transaction with timestamp : " + timestamp + " and amount " + transaction.getAmount() + " is already exist.");
        }
        timestampToTransactionMap.put(timestamp, transaction);

        return transaction;
    }

    @Override
    public TransactionStatistics getTransactionsStatistics() {
        TransactionStatistics statistics = new TransactionStatistics();
        double sum = 0;
        double max = 0;
        double min = 0;
        long count = 0;
        boolean first = true;
        long currentTimestamp = new Date().getTime();
        long lastSixtySecondsTimestamp = currentTimestamp - 60000;

        if(timestampToTransactionMap != null && !timestampToTransactionMap.isEmpty()){
            for(Map.Entry<Long, Transaction> entry : timestampToTransactionMap.entrySet()){
                if(entry.getKey() >= lastSixtySecondsTimestamp){
                    double amount = entry.getValue().getAmount();
                    if(first) {
                        sum += amount;
                        count = count + 1;
                        max = amount;
                        min = amount;
                        first = false;
                    }else{
                        sum += amount;
                        count = count + 1;
                        if (max <= amount) {
                            max = amount;
                        }
                        if(min >= amount){
                            min = amount;
                        }
                    }
                }else{
                    break;
                }
            }

        }
        double avg = (count == 0) ? 0 : sum / count;
        statistics.setSum(sum);
        statistics.setMax(max);
        statistics.setMin(min);
        statistics.setCount(count);
        statistics.setAvg(avg);
        return statistics;
    }

}
