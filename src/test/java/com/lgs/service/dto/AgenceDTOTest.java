package com.lgs.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lgs.web.rest.TestUtil;

public class AgenceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AgenceDTO.class);
        AgenceDTO agenceDTO1 = new AgenceDTO();
        agenceDTO1.setId(1L);
        AgenceDTO agenceDTO2 = new AgenceDTO();
        assertThat(agenceDTO1).isNotEqualTo(agenceDTO2);
        agenceDTO2.setId(agenceDTO1.getId());
        assertThat(agenceDTO1).isEqualTo(agenceDTO2);
        agenceDTO2.setId(2L);
        assertThat(agenceDTO1).isNotEqualTo(agenceDTO2);
        agenceDTO1.setId(null);
        assertThat(agenceDTO1).isNotEqualTo(agenceDTO2);
    }
}
