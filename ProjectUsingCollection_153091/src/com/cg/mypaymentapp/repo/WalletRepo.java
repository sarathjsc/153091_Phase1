package com.cg.mypaymentapp.repo;
import java.util.List;
import com.cg.mypaymentapp.beans.Customer;
public interface WalletRepo {
    public boolean save(Customer customer);
	public Customer findOne(String mobileNo);
	public void saveTransaction(String mobileNo, String statement);
	public List getTransaction(String mobileNo);
}
