package com.tx.controller;

import com.tx.manager.TransactionManager;
import com.tx.model.Transaction;
import com.tx.model.TransactionStatistics;
import com.tx.validator.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
	@Autowired
	private RequestValidator requestValidator;

	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public void saveTransaction(@RequestBody Transaction transaction, HttpServletResponse response){
		String validationResponse = requestValidator.validateTransaction(transaction);
		if(validationResponse != null){
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return;
		}
		txManager.saveTransaction(transaction);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return;
	}

	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public TransactionStatistics getTransactionsStatistics(HttpServletResponse response){
		return txManager.getTransactionsStatistics();
	}

}
