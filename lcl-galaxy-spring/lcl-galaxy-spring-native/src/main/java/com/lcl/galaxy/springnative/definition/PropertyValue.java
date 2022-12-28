package com.lcl.galaxy.springnative.definition;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PropertyValue {
    private String name;
    private Object value;
}
