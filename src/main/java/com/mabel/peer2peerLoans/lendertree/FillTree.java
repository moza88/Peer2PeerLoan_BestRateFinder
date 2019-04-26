package com.mabel.peer2peerLoans.lendertree;


import java.io.IOException;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import com.mabel.peer2peerLoans.lendertree.LendingInfoTree;

public class FillTree {
	
	LendingInfoTree insertLendingInfo = new LendingInfoTree();
//	Results results = new Results();

	public LendingInfoTree uploadFile(String file, int loanAmount) throws IOException {

		Reader reader = Files.newBufferedReader(Paths.get(file));

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
        csvParser.close();
		return insertLendingInfo;
	}
}

