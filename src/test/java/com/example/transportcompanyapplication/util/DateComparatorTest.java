package com.example.transportcompanyapplication.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.dto.DateInterval;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class DateComparatorTest {
    /**
     * Method under test: {@link DateComparator#equal(Date, Date)}
     */
    @Test
    void testEqual() {
        assertFalse(DateComparator.equal(mock(Date.class), mock(Date.class)));
    }

    /**
     * Method under test: {@link DateComparator#intervalIncludesDate(DateInterval, Date)}
     */
    @Test
    void testIntervalIncludesDate() {
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);
        Date date1 = mock(Date.class);
        when(date1.getTime()).thenReturn(10L);
        DateInterval interval = new DateInterval(date, date1);

        Date date2 = mock(Date.class);
        when(date2.getTime()).thenReturn(10L);
        assertTrue(DateComparator.intervalIncludesDate(interval, date2));
        verify(date).getTime();
        verify(date1).getTime();
        verify(date2, atLeast(1)).getTime();
    }

    /**
     * Method under test: {@link DateComparator#intervalIncludesDate(DateInterval, Date)}
     */
    @Test
    void testIntervalIncludesDate2() {
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(Long.MAX_VALUE);
        Date date1 = mock(Date.class);
        when(date1.getTime()).thenReturn(10L);
        DateInterval interval = new DateInterval(date, date1);

        Date date2 = mock(Date.class);
        when(date2.getTime()).thenReturn(10L);
        assertFalse(DateComparator.intervalIncludesDate(interval, date2));
        verify(date).getTime();
        verify(date2).getTime();
    }

    /**
     * Method under test: {@link DateComparator#intervalIncludesDate(DateInterval, Date)}
     */
    @Test
    void testIntervalIncludesDate3() {
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);
        Date date1 = mock(Date.class);
        when(date1.getTime()).thenReturn(1L);
        DateInterval interval = new DateInterval(date, date1);

        Date date2 = mock(Date.class);
        when(date2.getTime()).thenReturn(10L);
        assertFalse(DateComparator.intervalIncludesDate(interval, date2));
        verify(date).getTime();
        verify(date1).getTime();
        verify(date2, atLeast(1)).getTime();
    }
}

