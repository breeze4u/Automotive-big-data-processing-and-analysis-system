package org.whh.car.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author whh
 * @since 2023-12-26
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class CarPredict implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String brand;

    private String local;

    private String time;

    private String mileage;

    private String sprice;

    private String price;

  public CarPredict(String brand, String local, String time, String mileage, String sprice) {
    this.brand = brand;
    this.local = local;
    this.time = time;
    this.mileage = mileage;
    this.sprice = sprice;
  }

  public CarPredict() {
  }
}
