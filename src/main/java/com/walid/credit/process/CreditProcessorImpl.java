package com.walid.credit.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.walid.credit.repository.CreditRepo;

/**
 * @author Walid Moustafa
 */
public class CreditProcessorImpl implements CreditProcessor {

    private static final CreditProcessorImpl instance = new CreditProcessorImpl();
    private CreditRepo repo;

    private CreditProcessorImpl() {
    }

    public static CreditProcessor getInstance(CreditRepo repo) {
        instance.setRepo(repo);
        return instance;
    }

    @Override
    public void loadEntities(InputStream transactionInputStream) throws IOException {
        try (Reader in = new InputStreamReader(transactionInputStream)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records) {

                // TODO read entity to repo
            }
        }
    }

    private void setRepo(CreditRepo repo) {
        this.repo = repo;
    }
}
