package net.cs50.finance.models.dao;

import net.cs50.finance.models.StockTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Chris Bay on 5/17/15.
 */

@Transactional
@Repository
public interface StockTransactionDao extends CrudRepository<StockTransaction, Integer> {

}
