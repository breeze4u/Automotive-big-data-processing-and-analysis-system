package org.whh.car.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author whh
 * @since 2023-12-14
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Sale implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String name;

    private String time;

    private Integer sales;

    private String percentage;

    private Integer ranking;

    private String brand;

    private String grad;

    @TableField("monthSales")
    private Integer monthSales;

    @TableField("monthRanking")
    private Integer monthRanking;

    @TableField("monthPer")
    private String monthPer;


}
