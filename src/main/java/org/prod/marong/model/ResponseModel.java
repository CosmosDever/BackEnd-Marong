package org.prod.marong.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseModel<T> {
    private String statusCode;
    private String statusMessage;
    private T data;

}
