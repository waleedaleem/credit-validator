package com.walid.credit;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import com.walid.credit.process.CreditProcessor;
import com.walid.credit.process.CreditProcessorImpl;

/**
 * @author Walid Moustafa
 */
public class Main {

    private static final String[] HELP_ALIASES = {
        "-h", "-help", "--help"
    };
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final CreditProcessor creditProcessor = CreditProcessorImpl.getInstance();

    public static void main(String[] args) throws IOException {
        if (args.length != 1 || Arrays.asList(HELP_ALIASES).contains(args[0])) {
            logger.warning("usage: java -jar credit-validator' <credits CSV file>");
            System.exit(0);
        }

        try (FileInputStream txnInputStream = new FileInputStream(args[0])) {
            creditProcessor.loadEntities(txnInputStream);
        }
    }
}
