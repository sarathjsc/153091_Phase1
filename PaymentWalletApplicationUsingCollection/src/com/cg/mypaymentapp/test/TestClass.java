package com.cg.mypaymentapp.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class TestClass {

	static WalletService service;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
		Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Arjun", "9791487555",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Sanju", "8106701111",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Deepu", "9866249100",new Wallet(new BigDecimal(7000)));
				
		 data.put("9791487555", cust1);
		 data.put("8106701111", cust2);	
		 data.put("9866249100", cust3);	
			service= new WalletServiceImpl(data);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=InvalidInputException.class)
	public void testCreateAccount1() 
	{
		service.createAccount(null, "9942221102", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount2() 
	{
		service.createAccount("", "9942221102", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount3() 
	{
		service.createAccount("appu", "999", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount4() 
	{
		service.createAccount("appu", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount5() 
	{
		service.createAccount("", "", new BigDecimal(1500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount6() 
	{
		service.createAccount("Arjun", "9791487555", new BigDecimal(9000));
	}
	
	
	@Test
	public void testCreateAccount7() 
	{
		Customer actual=service.createAccount("Thozha", "9698495659", new BigDecimal(5000));
		Customer expected=new Customer("Thozha", "9698495659", new Wallet(new BigDecimal(5000)));
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testCreateAccount8() 
	{
		Customer actual=service.createAccount("Karthik", "9963808055", new BigDecimal(0));
		Customer expected=new Customer("Karthik", "9963808055", new Wallet(new BigDecimal(0)));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCreateAccount9() 
	{
		Customer actual=service.createAccount("Karthik", "9963808055", new BigDecimal(5000.75));
		Customer expected=new Customer("Karthik", "9963808055", new Wallet(new BigDecimal(5000.75)));
		
		assertEquals(expected, actual);
	}


	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance10() 
	{
		service.showBalance(null);		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance11() 
	{
		service.showBalance("12345");		
	}
	
	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance12() 
	{
		service.showBalance("97914875552");		
	}
	
	
	@Test
	public void testShowBalance13() 
	{
		Customer customer=service.showBalance("9791487555");
		BigDecimal expectedResult=new BigDecimal(9000);
		BigDecimal obtainedResult=customer.getWallet().getBalance();
		
		assertEquals(expectedResult, obtainedResult);
		
	}

	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer14() 
	{
		service.fundTransfer("9866249100", "9866249100", new BigDecimal(5000));		
	}

	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer15() 
	{
		service.fundTransfer("9791487555", "9866249100", new BigDecimal(12000));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer16() 
	{
		service.fundTransfer("9791487555", "9866249100", new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer17() 
	{
		service.fundTransfer("9791487555", "", new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer18() 
	{
		service.fundTransfer("", "9866249100", new BigDecimal(500));		
	}
	
	
	@Test
	public void testFundTransfer19() 
	{
		Customer customer=service.fundTransfer("9791487555", "9866249100", new BigDecimal(500));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8500);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFundTransfer20() 
	{
		Customer customer=service.fundTransfer("9791487555", "9866249100", new BigDecimal(550.50));
		BigDecimal expected=customer.getWallet().getBalance();
		BigDecimal actual=new BigDecimal(8449.50);
		
		assertEquals(expected, actual);
	}
	
	
	@Test(expected=InsufficientBalanceException.class)
	public void testFundTransfer21() 
	{
		Customer customer=service.fundTransfer("9791487555", "9866249100", new BigDecimal(15000));	
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer22() 
	{
		service.fundTransfer("", "9866249100", new BigDecimal(-100));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer23() 
	{
		service.fundTransfer(null, null, new BigDecimal(0));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer24() 
	{
		service.fundTransfer("9866249100", null, new BigDecimal(50));		
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer25() 
	{
		service.fundTransfer("9866249100", "8106701111", new BigDecimal(0));		
	}

	@Test(expected=InvalidInputException.class)
	public void testDepositAmount26() 
	{
		service.depositAmount("9942221102", new BigDecimal(500));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount27() 
	{
		service.depositAmount("9866249100", new BigDecimal(200000));
	}
	

	
	@Test(expected=InsufficientBalanceException.class)
	public void testWithdrawAmount28() 
	{
		service.withdrawAmount("9791487555", new BigDecimal(15000));	
	}
	
	@Test(expected=InvalidInputException.class)
	public void testWithdrawAmount29() 
	{
		service.withdrawAmount("9963808055", new BigDecimal(5000));	
	}

}
