package com.walid.credit.process;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Walid Moustafa
 */
public interface CreditProcessor {

    void loadEntities(InputStream transactionInputStream) throws IOException;

    void report();
}
