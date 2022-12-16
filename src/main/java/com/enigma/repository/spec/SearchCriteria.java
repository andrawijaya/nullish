package com.enigma.repository.spec;

import com.enigma.util.QueryOperator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class SearchCriteria {
    @Setter @Getter
    private String key;
    @Setter @Getter
    private QueryOperator operator;
    @Setter @Getter
    private Object value;

}
