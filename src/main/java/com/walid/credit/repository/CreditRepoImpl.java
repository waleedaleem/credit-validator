package com.walid.credit.repository;

import java.util.ArrayList;
import java.util.List;

import com.walid.credit.model.Entity;

/**
 * @author Walid Moustafa
 */
public class CreditRepoImpl implements CreditRepo {

    private static final CreditRepoImpl instance = new CreditRepoImpl();
    private List<Entity> entities = new ArrayList<>();

    private CreditRepoImpl() {
    }

    public static CreditRepo getInstance() {
        return instance;
    }

    @Override
    public void addEntity(Entity entity) {

    }
}
