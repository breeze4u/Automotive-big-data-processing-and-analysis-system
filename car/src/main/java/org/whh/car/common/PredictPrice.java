package org.whh.car.common;

import lombok.Data;

@Data
public class PredictPrice {
    private String price1;
    private String price2;

    public PredictPrice() {
    }

    public PredictPrice(String price1, String price2) {
        this.price1 = price1;
        this.price2 = price2;
    }
}
