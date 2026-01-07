package com.electron.handle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatEnum {

    PAGE_NUMBER_DEFAULT(0),

    PAGE_SIZE_DEFAULT(10);

    private Integer value;
}
