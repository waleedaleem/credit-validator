package com.walid.credit.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * @author Walid Moustafa
 */
public class CreditProcessorImpl implements CreditProcessor {

    private static final CreditProcessor instance = new CreditProcessorImpl();

    private CreditProcessorImpl() {
    }

    public static CreditProcessor getInstance() {
        return instance;
    }

    @Override
    public void loadEntities(InputStream transactionInputStream) throws IOException {
        try (Reader in = new InputStreamReader(transactionInputStream)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records) {

                // TODO add read entity to repo
            }
        }
    }

}
