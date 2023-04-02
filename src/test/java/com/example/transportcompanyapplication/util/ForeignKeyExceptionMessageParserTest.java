package com.example.transportcompanyapplication.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.transportcompanyapplication.exceptions.ForeignKeyException;
import org.junit.jupiter.api.Test;

class ForeignKeyExceptionMessageParserTest {
    /**
     * Method under test: {@link ForeignKeyExceptionMessageParser#parse(String)}
     */
    @Test
    void testParse() {
        ForeignKeyException actualParseResult = ForeignKeyExceptionMessageParser.parse("An error occurred");
        assertEquals("Foreign key exception", actualParseResult.getMessage());
        assertEquals("urred", actualParseResult.getDetails());
    }
}

