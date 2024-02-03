package org.whh.car.service;

import org.whh.car.common.Params;
import org.whh.car.entity.Sale;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whh
 * @since 2023-12-14
 */
public interface SaleService extends IService<Sale> {
    List<Sale> findListByParams(Params params);
    ArrayList<Map<String, Object>> getName();

    ArrayList<Map<String, Object>> getBrand();

    List<Map<String, Object>> getBinNameSalesByMonth(Params params);
    HashMap<String, Object> getBarNameSalesByMonth(Params params);


    HashMap<String, Object> getBarMonthSalesByNameYear(Params params);
    List<Map<String, Object>> getBinMonthSalesByNameYear(Params params);


    HashMap<String, Object> getBarBrandSaleByNameMonth(Params params);
    List<Map<String, Object>> getBinBrandSaleByNameMonth(Params params);


    List<Map<String, Object>> getBinBrandSaleByMonth(Params params);
    HashMap<String, Object> getBarBrandSaleByMonth(Params params);
}
