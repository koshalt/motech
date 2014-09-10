package org.motechproject.mds.lookup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.mds.dto.FieldDto;
import org.motechproject.mds.dto.LookupDto;
import org.motechproject.mds.dto.LookupFieldDto;
import org.motechproject.mds.ex.LookupExecutorException;
import org.motechproject.mds.query.QueryParams;
import org.motechproject.mds.service.DefaultMotechDataService;
import org.motechproject.mds.testutil.FieldTestHelper;
import org.motechproject.mds.util.Order;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class LookupExecutorTest {

    private static final String LOOKUP_NAME = "MY lookup";
    private static final String LOOKUP_METHOD_NAME = "find";
    private static final String STR_FIELD_NAME = "strField";
    private static final String INT_FIELD_NAME = "intField";

    private static final String STR_ARG = "expectedStrArg";
    private static final int INT_ARG = 27;
    public static final long COUNT = 2;

    private static final int PAGE = 3;
    private static final int PAGE_SIZE = 15;
    private static final String SORT_FIELD = INT_FIELD_NAME;
    private static final Order.Direction DIRECTION = Order.Direction.ASC;

    private TestLookupService dataService = new TestLookupService();

    private LookupExecutor lookupExecutor;

    @Before
    public void setUp() {
        LookupDto lookupDto = new LookupDto(LOOKUP_NAME, false, false,
                asList(new LookupFieldDto(1L, "strField", LookupFieldDto.Type.VALUE),
                        new LookupFieldDto(2L, "intField", LookupFieldDto.Type.VALUE)),
                false, LOOKUP_METHOD_NAME);

        List<FieldDto> fields = Arrays.asList(
                FieldTestHelper.fieldDto(1L, "strField", String.class.getName(), "strFieldDisp", null),
                FieldTestHelper.fieldDto(2L, "intField", Integer.class.getName(), "intFieldDisp", null)
        );

        lookupExecutor = new LookupExecutor(dataService, lookupDto, fields);
    }

    @Test
    public void shouldExecuteALookupWithoutQueryParams() throws LookupExecutorException {
        Map<String, Object> lookupMap = new HashMap<>();
        lookupMap.put(STR_FIELD_NAME, STR_ARG);
        lookupMap.put(INT_FIELD_NAME, INT_ARG);

        List result = (List) lookupExecutor.execute(lookupMap);

        assertEquals(dataService.find(STR_ARG, INT_ARG), result);
    }

    @Test
    public void shouldExecuteALookupWithQueryParams() throws LookupExecutorException {
        Map<String, Object> lookupMap = new HashMap<>();
        lookupMap.put(STR_FIELD_NAME, STR_ARG);
        lookupMap.put(INT_FIELD_NAME, INT_ARG);
        QueryParams queryParams = new QueryParams(PAGE, PAGE_SIZE, new Order(SORT_FIELD, DIRECTION));

        List result = (List) lookupExecutor.execute(lookupMap, queryParams);

        assertEquals(dataService.find(STR_ARG, INT_ARG, queryParams), result);
    }

    @Test
    public void shouldExecuteCountLookup() throws LookupExecutorException {
        Map<String, Object> lookupMap = new HashMap<>();
        lookupMap.put(STR_FIELD_NAME, STR_ARG);
        lookupMap.put(INT_FIELD_NAME, INT_ARG);

        long result = lookupExecutor.executeCount(lookupMap);

        assertEquals(COUNT, result);
    }

    private class TestClass {
        public int intField;
        public String strField;

        private TestClass(int intField, String strField) {
            this.intField = intField;
            this.strField = strField;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (!(o instanceof TestClass)) {
                return false;
            }

            TestClass other = (TestClass) o;

            return Objects.equals(intField, other.intField) && Objects.equals(strField, other.strField);

        }

        @Override
        public int hashCode() {
            return Objects.hash(intField, strField);
        }
    }

    public class TestLookupService extends DefaultMotechDataService<TestClass> {

        public List<TestClass> find(String strField, Integer intField) {
            assertParams(strField, intField);
            return asList(new TestClass(1, "firstRecord"), new TestClass(2, "secondRecord"));
        }

        public List<TestClass> find(String strField, Integer intField, QueryParams queryParams) {
            assertParams(strField, intField);
            assertQueryParams(queryParams);
            return asList(new TestClass(1, "firstRecord"));
        }

        public long countFind(String strField, Integer intField) {
            assertParams(strField, intField);
            return COUNT;
        }

        private void assertParams(String strParam, int intParam) {
            assertEquals(STR_ARG, strParam);
            assertEquals(INT_ARG, intParam);
        }

        private void assertQueryParams(QueryParams queryParams) {
            assertNotNull(queryParams);
            assertEquals(Integer.valueOf(PAGE), queryParams.getPage());
            assertEquals(Integer.valueOf(PAGE_SIZE), queryParams.getPageSize());
            assertNotNull(queryParams.getOrder());
            assertEquals(SORT_FIELD, queryParams.getOrder().getField());
            assertEquals(DIRECTION, queryParams.getOrder().getDirection());
        }

        @Override
        public Class<TestClass> getClassType() {
            return TestClass.class;
        }
    }
}
