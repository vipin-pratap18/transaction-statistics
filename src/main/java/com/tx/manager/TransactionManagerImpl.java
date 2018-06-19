package com.tx.manager;

import com.tx.dal.TransactionDAL;
import com.tx.exception.DuplicateTransactionException;
import com.tx.model.Transaction;
import com.tx.model.TransactionStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

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
        long timestamp = transaction.getTimestamp();
        Transaction existingTxn = txDAL.getTransaction(transaction);
        if(existingTxn != null){
            throw new DuplicateTransactionException(transaction + " is already exist.");
        }
        return txDAL.saveTransaction(transaction);
    }

    @Override
    public TransactionStatistics getTransactionsStatistics() {
        Map<Long, Transaction> timestampToTransactionMap = txDAL.getAllTransactions();
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
