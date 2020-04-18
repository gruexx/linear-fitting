package com.example.linearfitting.entity.data;

import lombok.Builder;
import lombok.Data;

/**
 * @author
 */
@Builder
@Data
public class DataVO {
    private Double k;

    private Double b;

    private Double fittingError;
}
