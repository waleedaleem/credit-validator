package com.walid.credit.repository;

import org.apache.commons.csv.CSVRecord;

import com.walid.credit.model.Entity;

/**
 * @author Walid Moustafa
 */
public interface CreditRepo {

    Entity addEntity(CSVRecord record);
}
