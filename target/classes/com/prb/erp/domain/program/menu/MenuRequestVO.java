package com.prb.erp.domain.program.menu;

import java.util.List;

import lombok.Data;

@Data
public class MenuRequestVO {

    private List<Menu> menuList;
    private List<Menu> deletedList;
}
