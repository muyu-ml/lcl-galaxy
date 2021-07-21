package com.lcl.galaxy.es.index;

import lombok.Builder;
import lombok.Data;

@Data
public class ColumnIndex {
    private String type;
    private boolean store;
    private boolean index;
    private String analyzer;
}
