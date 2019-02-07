package com.prb.erp.procedure.inter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class USERROLE {

    private Long id;

    private String userCd;

    private String roleCd;
}
