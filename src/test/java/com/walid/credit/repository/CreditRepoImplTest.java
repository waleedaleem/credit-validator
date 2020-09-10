package com.walid.credit.repository;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Triplet;
import org.junit.BeforeClass;
import org.junit.Test;

import com.walid.credit.process.CreditProcessor;
import com.walid.credit.process.CreditProcessorImpl;

/**
 * @author wmoustafa
 */
public class CreditRepoImplTest {

    // a list of test nodes <id, combined sub-entity limit, combined utilisation>
    private static List<Triplet<String, Long, Long>> expectedResult;
    private static ClassLoader classLoader;
    private CreditProcessor processor;
    private CreditRepo repo;

    @BeforeClass
    public static void setExpectedResult() {
        expectedResult = new ArrayList<>();
        expectedResult.add(Triplet.with("A", 90L, 60L));
        expectedResult.add(Triplet.with("B", 80L, 60L));
        expectedResult.add(Triplet.with("C", 0L, 20L));
        expectedResult.add(Triplet.with("D", 0L, 30L));
        expectedResult.add(Triplet.with("E", 100L, 230L));
        expectedResult.add(Triplet.with("F", 0L, 80L));

        classLoader = CreditRepoImplTest.class.getClassLoader();
    }

    public void loadEntities(String entityFile) throws IOException {
        InputStream entityStream = classLoader.getResourceAsStream(entityFile);
        repo = CreditRepoImpl.getInstance();
        processor = CreditProcessorImpl.getInstance(repo);
        processor.loadEntities(entityStream);
    }

    @Test
    public void testEntityRepoInitialisation() throws IOException {
        loadEntities("credit-limits.csv");
        assertEquals(expectedResult, ((CreditRepoImpl) repo).entitiesAsTriplets());
    }

    @Test
    public void testEntityRepoInitialisation_revers_ordered() throws IOException {
        loadEntities("credit-limits-reverse.csv");
        assertEquals(expectedResult, ((CreditRepoImpl) repo).entitiesAsTriplets());
    }

    @Test
    public void testEntityRepoInitialisation_shuffled() throws IOException {
        loadEntities("credit-limits-shuffled.csv");
        assertEquals(expectedResult, ((CreditRepoImpl) repo).entitiesAsTriplets());
    }
}
