package com.lcl.galaxy.es.index;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class IndexMapping {
    private String type;
    private List<ColumnIndex> properties;
}
