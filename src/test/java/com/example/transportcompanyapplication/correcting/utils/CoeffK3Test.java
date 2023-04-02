package com.example.transportcompanyapplication.correcting.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.transportcompanyapplication.correcting.model.ClimateType;
import org.junit.jupiter.api.Test;

class CoeffK3Test {
    /**
     * Method under test: {@link CoeffK3#getCoeffK3TO(ClimateType)}
     */
    @Test
    void testGetCoeffK3TO() {
        assertEquals(1.0d, CoeffK3.getCoeffK3TO(ClimateType.TEMPERATE));
        assertEquals(1.0d, CoeffK3.getCoeffK3TO(ClimateType.WARM_TEMPERATE));
        assertEquals(0.9d, CoeffK3.getCoeffK3TO(ClimateType.HOT_DRY));
        assertEquals(0.9d, CoeffK3.getCoeffK3TO(ClimateType.MODERATELY_COLD));
        assertEquals(0.8d, CoeffK3.getCoeffK3TO(ClimateType.COLD));
        assertEquals(0.7d, CoeffK3.getCoeffK3TO(ClimateType.VERY_COLD));
    }

    /**
     * Method under test: {@link CoeffK3#getCoeffK3KR(ClimateType)}
     */
    @Test
    void testGetCoeffK3KR() {
        assertEquals(1.0d, CoeffK3.getCoeffK3KR(ClimateType.TEMPERATE));
        assertEquals(1.0d, CoeffK3.getCoeffK3KR(ClimateType.WARM_TEMPERATE));
        assertEquals(0.9d, CoeffK3.getCoeffK3KR(ClimateType.HOT_DRY));
        assertEquals(0.9d, CoeffK3.getCoeffK3KR(ClimateType.MODERATELY_COLD));
        assertEquals(0.9d, CoeffK3.getCoeffK3KR(ClimateType.COLD));
        assertEquals(0.8d, CoeffK3.getCoeffK3KR(ClimateType.VERY_COLD));
    }
}

