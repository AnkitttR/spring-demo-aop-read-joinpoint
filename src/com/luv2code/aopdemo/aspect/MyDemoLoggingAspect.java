package com.luv2code.aopdemo.aspect;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.dao.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	
	
	//@Before("execution(public void add*())")  //any method call void addAccount() please call this
	//@Before("execution(void add*())") //any method call void add*() please call this
	//@Before("execution(* add*(..))") //any method call "any return type" add*() please call this
	
	
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")   //any method call "any return type" add*() please call this
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		
		System.out.println("\n=====>>> Executing @Before advice on method");
		
		//display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature(); //JoinPoint interface ke variable theJoinPoint ki help se getSignature() method call karenge
		
		System.out.println("Method: " + methodSig);
		
		//display the method arguments
				
		//get args
		Object[] args = theJoinPoint.getArgs();
		
		//loop thru args
		for (Object tempArg: args) {
			System.out.println(tempArg);
			
			if (tempArg instanceof Account) {
				//downcast and print Account specific stuff 
				
				Account theAccount = (Account) tempArg;
				
				System.out.println("account name: " + theAccount.getName());
				System.out.println("account level: " + theAccount.getLevel());
			}
		}
		
	}
	

}
