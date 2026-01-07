package com.electron.handle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SecurityEnum {

    SHA("SHA-256");

    private String value;
}
