package com.pismo.txnroutine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.pismo*")
@EntityScan("com.pismo*")
public class TxnroutineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TxnroutineApplication.class, args);
	}

}
