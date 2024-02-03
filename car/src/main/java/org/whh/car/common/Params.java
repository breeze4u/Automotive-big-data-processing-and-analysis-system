package org.whh.car.common;

import lombok.Data;

@Data
public class Params {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    private String infor1;
    private String infor2;
    private String infor3;
    private String infor4;
}
