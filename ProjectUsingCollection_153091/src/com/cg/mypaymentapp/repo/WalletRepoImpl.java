package com.cg.mypaymentapp.repo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.cg.mypaymentapp.beans.Customer;
public class WalletRepoImpl implements WalletRepo{
	private Map<String, Customer> data; 
	private Map<String,List<String>> transaction= new HashMap<String,List<String>>();
	List<String> value= new ArrayList<String>();
	public WalletRepoImpl(Map<String, Customer> data){
		super();
		this.data = data;
	}
    public boolean save(Customer customer){
         data.put(customer.getMobileNo(), customer);
          return true;
    }
   public Customer findOne(String mobileNo){
		Customer customer=null;
        customer=data.get(mobileNo);
		return customer;
	}
	@Override
	public void saveTransaction(String mobileNo,String statement) {
		if(transaction.containsKey(mobileNo)){
			value=transaction.get(mobileNo);
		}
		else{
			value=new ArrayList<String>();
		}
		value.add(statement);
		transaction.put(mobileNo, value);
	}
	@Override
	public List<String> getTransaction(String mobileNo){
		value=transaction.get(mobileNo);
		return value;
	}
}
