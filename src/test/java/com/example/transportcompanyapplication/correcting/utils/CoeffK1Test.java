package com.example.transportcompanyapplication.correcting.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.transportcompanyapplication.correcting.model.CategoryOfExploitationType;
import org.junit.jupiter.api.Test;

class CoeffK1Test {
    /**
     * Method under test: {@link CoeffK1#getCoeffK1TO(CategoryOfExploitationType)}
     */
    @Test
    void testGetCoeffK1TO() {
        assertEquals(1.0d, CoeffK1.getCoeffK1TO(CategoryOfExploitationType.I));
        assertEquals(0.9d, CoeffK1.getCoeffK1TO(CategoryOfExploitationType.II));
        assertEquals(0.8d, CoeffK1.getCoeffK1TO(CategoryOfExploitationType.III));
        assertEquals(0.7d, CoeffK1.getCoeffK1TO(CategoryOfExploitationType.IV));
        assertEquals(0.6d, CoeffK1.getCoeffK1TO(CategoryOfExploitationType.V));
    }

    /**
     * Method under test: {@link CoeffK1#getCoeffK1KR(CategoryOfExploitationType)}
     */
    @Test
    void testGetCoeffK1KR() {
        assertEquals(1.0d, CoeffK1.getCoeffK1KR(CategoryOfExploitationType.I));
        assertEquals(0.9d, CoeffK1.getCoeffK1KR(CategoryOfExploitationType.II));
        assertEquals(0.8d, CoeffK1.getCoeffK1KR(CategoryOfExploitationType.III));
        assertEquals(0.7d, CoeffK1.getCoeffK1KR(CategoryOfExploitationType.IV));
        assertEquals(0.6d, CoeffK1.getCoeffK1KR(CategoryOfExploitationType.V));
    }
}

