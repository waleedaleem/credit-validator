package com.walid.credit.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.walid.credit.repository.CreditRepo;

/**
 * @author Walid Moustafa
 */
public class CreditProcessorImpl implements CreditProcessor {

    private static final Logger logger = Logger.getLogger(CreditProcessorImpl.class.getName());

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
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreEmptyLines().parse(
                    in);
            for (CSVRecord record : records) {
                repo.addEntity(record);
            }
        }
    }

    @Override
    public void report() {
        repo.validateEntities();
        List<String> validEntityIds = repo.getValidEntityIds();
        StringBuilder sbValid = new StringBuilder("Valid Entities (No breaches):\n");
        validEntityIds.forEach(id -> sbValid.append(id).append(", "));
        System.out.println(sbValid.toString());

        List<String> breachEntityIds = repo.getLimitBreachEntityIds();
        StringBuilder sbBreach = new StringBuilder("Limit Breach Entities:\n");
        breachEntityIds.forEach(id -> sbBreach.append(id).append(", "));
        System.out.println(sbBreach.toString());

        List<String> misconfigEntityIds = repo.getLimitMisconfigEntityIds();
        StringBuilder sbMisconfig = new StringBuilder("Limit Misconfiguration Entities:\n");
        misconfigEntityIds.forEach(id -> sbMisconfig.append(id).append(", "));
        System.out.println(sbMisconfig.toString());
    }

    private void setRepo(CreditRepo repo) {
        this.repo = repo;
    }
}
