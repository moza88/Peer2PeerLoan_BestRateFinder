package com.mabel.peer2peerLoans.lendertree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import com.mabel.peer2peerLoans.Input;
import com.mabel.peer2peerLoans.Results;
import com.mabel.peer2peerLoans.calculation.CalculatePayment;
import com.mabel.peer2peerLoans.calculation.FindingRate;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mabel.peer2peerLoans.lendertree.LendingInfoTree;

public class FillTree {
	
	LendingInfoTree insertLendingInfo = new LendingInfoTree();
//	Results results = new Results();

	public LendingInfoTree uploadFile(String file, int loanAmount) throws IOException {

		Reader reader = Files.newBufferedReader(Paths.get(file));
		System.out.println(file);

        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withSkipHeaderRecord()
                .withFirstRecordAsHeader()
                .withTrim());
    	
        for (CSVRecord csvRecord : csvParser) {
            // Accessing values by the names assigned to each column
            String name = csvRecord.get("Lender");
            String rate = csvRecord.get("Rate");
            String lendingAmount = csvRecord.get("Available");
            /*
            System.out.println("---------------");
            System.out.println("Lender : " + name);
            System.out.println("Rate : " + rate);
            System.out.println("Available : " + lendingAmount);
            */
            insertLendingInfo.insert(name, Double.parseDouble(rate), Integer.parseInt(lendingAmount));
        }

    //    results.printResults(loanAmount);


		return insertLendingInfo;

	}

	public LendingInfoTree uploadFile() {
		// TODO Auto-generated method stub
		return insertLendingInfo;
	}

	/*
	public void printResutls() {
		Input input = new Input();
		FindingRate findingRate = new FindingRate();
		System.out.println(insertLendingInfo.root);
		findingRate.findRate(input.getLoanAmount(), insertLendingInfo.root);
	
		System.out.println("Final Rate: " + findingRate.quotedRate(insertLendingInfo.root));
	    Results results = new Results();
	    CalculatePayment calcPayment = new CalculatePayment();
	    
	    System.out.println("Monthly Payment: " + calcPayment.monthlyPayment(0.07, 36, 1000));
	    System.out.println("Total Payment: " + calcPayment.totalRepayment(0.07, 36, 1000));
	}*/
}

