package org.whh.car.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author whh
 * @since 2023-12-09
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Car implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String name;

    private String brand;

    private String model;

    private String structure;

    private String endurance;

    private String motor;

    private String gearbox;

    private String charge;

    private String score;

    private String style;

    private String price;

    private String pic;


}
