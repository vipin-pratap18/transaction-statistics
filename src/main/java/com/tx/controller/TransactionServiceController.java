package com.tx.controller;

import com.tx.manager.TransactionManager;
import com.tx.model.Transaction;
import com.tx.model.TransactionStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is responsible for saving transaction details and providing statistics of last 60 seconds
 *
 * @author VipinK
 */

@RestController
@RequestMapping("/txn-api/v1")
public class TransactionServiceController {

	private final Logger LOG = LoggerFactory.getLogger(TransactionServiceController.class);

	@Autowired
	private TransactionManager txManager;

	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public Transaction saveTransaction(@RequestBody Transaction transaction){
		return txManager.saveTransaction(transaction);
	}

	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public TransactionStatistics getTransactionsStatistics(){
		return txManager.getTransactionsStatistics();
	}

}
