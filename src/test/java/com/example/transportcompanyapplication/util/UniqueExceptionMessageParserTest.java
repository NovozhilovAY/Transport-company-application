package com.example.transportcompanyapplication.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.transportcompanyapplication.exceptions.UniqueFieldException;
import org.junit.jupiter.api.Test;

class UniqueExceptionMessageParserTest {

    /**
     * Method under test: {@link UniqueExceptionMessageParser#parse(String)}
     */
    @Test
    void testParse3() {
        UniqueFieldException actualParseResult = UniqueExceptionMessageParser.parse("\"xxx\"\"xxx\"");
        assertEquals("xxx", actualParseResult.getDetails());
        assertEquals("xxx", actualParseResult.getConstraintName());
    }
}

