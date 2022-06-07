package com.lgs.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AgenceMapperTest {

    private AgenceMapper agenceMapper;

    @BeforeEach
    public void setUp() {
        agenceMapper = new AgenceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(agenceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(agenceMapper.fromId(null)).isNull();
    }
}
