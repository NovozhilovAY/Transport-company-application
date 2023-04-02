package com.example.transportcompanyapplication.util;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PatchMapper.class})
@ExtendWith(SpringExtension.class)
class PatchMapperTest {
    @MockBean
    private EntityValidator<Object> entityValidator;

    @Autowired
    private PatchMapper<Object> patchMapper;

    /**
     * Method under test: {@link PatchMapper#update(Object, Object)}
     */
    @Test
    void testUpdate() {
        doNothing().when(entityValidator).validate((Object) any());
        patchMapper.update("Object", "Target");
        verify(entityValidator).validate((Object) any());
    }
}

