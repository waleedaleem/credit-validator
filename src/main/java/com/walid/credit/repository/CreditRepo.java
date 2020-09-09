package com.walid.credit.repository;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.walid.credit.model.Entity;

/**
 * @author Walid Moustafa
 */
public interface CreditRepo {

    Entity addEntity(CSVRecord record);

    void validateEntities();

    List<String> getValidEntityIds();

    List<String> getLimitBreachEntityIds();

    List<String> getLimitMisconfigEntityIds();
}
