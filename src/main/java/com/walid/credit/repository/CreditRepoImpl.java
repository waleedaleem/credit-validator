package com.walid.credit.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVRecord;

import com.walid.credit.model.Entity;

/**
 * @author Walid Moustafa
 */
public class CreditRepoImpl implements CreditRepo {

    private static final Logger logger = Logger.getLogger(CreditRepoImpl.class.getName());
    private static final CreditRepoImpl instance = new CreditRepoImpl();
    private Map<String, Entity> entities = new HashMap<>();

    private CreditRepoImpl() {
    }

    public static CreditRepo getInstance() {
        return instance;
    }

    @Override
    public Entity addEntity(CSVRecord record) {
        Entity entity = null;
        if (record.size() != 4) {
            logger.warning("Skipping a faulty CSV record");
        } else if (isDuplicate(record.get(0))) {
            logger.warning("Skipping a duplicate CSV record");
        } else {
            int index = 0;
            entity = new Entity();
            entity.setId(record.get(index++));
            String parentId = record.get(index++);
            entity.setLimit(Long.parseLong(record.get(index++)));
            entity.setUtilisation(Long.parseLong(record.get(index++)));
            entity.setLoaded();
            linkToParent(entity, parentId);
        }
        return entity;
    }

    private boolean isDuplicate(String entityId) {
        return entities.containsKey(entityId) && entities.get(entityId).alreadyLoaded();
    }

    private void linkToParent(Entity child, String parentId) {
        // TODO
    }
}
