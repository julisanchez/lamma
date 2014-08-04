package iolamma4j.stackoverflow;

import com.google.common.collect.Lists;
import io.lamma.Date;
import io.lamma.Dates;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * http://stackoverflow.com/questions/2689379/how-to-get-a-list-of-dates-between-two-dates-in-java
 */
public class Q2689379 {

    @Test
    public void test() {
        List<Date> expected = Lists.newArrayList(new Date(2014, 6, 29), new Date(2014, 6, 30), new Date(2014, 7, 1));
        List<Date> actual = Dates.from(2014, 6, 29).to(2014, 7, 1).build();
        assertEquals(expected, actual);
    }

}
