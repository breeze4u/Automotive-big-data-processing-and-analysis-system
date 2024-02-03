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
 * @since 2023-11-30
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class UsedCar implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String provence;

    private String brand;

    private String mileage;

    private String time;

    private String local;

    private String member;

    private String transfers;

    private String sprice;

    private String price;

    private String url;


}
