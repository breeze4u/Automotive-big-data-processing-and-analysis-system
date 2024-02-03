package org.whh.car.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.whh.car.common.Result;
import org.whh.car.common.Params;
import org.whh.car.entity.Sale;
import org.whh.car.service.SaleService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author whh
 * @since 2023-12-14
 */
@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;


    @GetMapping("/getListByParams")
    public Result findByParams(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Sale> salesList = saleService.findListByParams(params);
        return Result.success(PageInfo.of(salesList));
    }

    @GetMapping("/getName")
    public  Result getName(){
        ArrayList<Map<String, Object>> name = saleService.getName();
        return Result.success(name);
    }
    @GetMapping("/getBrand")
    public  Result getBrand(){
        ArrayList<Map<String, Object>> brand = saleService.getBrand();
        return Result.success(brand);
    }

    @GetMapping("/bin")
    public Result BinNameSalesByMonth(Params params){
        List<Map<String, Object>> binByTime = saleService.getBinNameSalesByMonth(params);
        return Result.success(binByTime);
    }

    @GetMapping("/bar")
    public Result BarNameSalesByMonth(Params params){
        HashMap<String, Object> barByTime = saleService.getBarNameSalesByMonth(params);
        return Result.success(barByTime);
    }

    @GetMapping("/binMonthSalesByNameYear")
    public Result BinMonthSalesByNameYear(Params params){
        List<Map<String, Object>> binByTime = saleService.getBinMonthSalesByNameYear(params);
        return Result.success(binByTime);
    }

    @GetMapping("/barMonthSalesByNameYear")
    public Result BarMonthSalesByNameYear(Params params){
        HashMap<String, Object> barByNameAndTime = saleService.getBarMonthSalesByNameYear(params);
        return Result.success(barByNameAndTime);
    }

    @GetMapping("/binBrandSaleByNameMonth")
    public Result BinBrandSaleByNameMonth(Params params){
        List<Map<String, Object>> binBrandSaleByNameMonth = saleService.getBinBrandSaleByNameMonth(params);
        return Result.success(binBrandSaleByNameMonth);
    }

    @GetMapping("/barBrandSaleByNameMonth")
    public Result BarBrandSaleByNameMonth(Params params){
        HashMap<String, Object> barBrandSaleByNameMonth = saleService.getBarBrandSaleByNameMonth(params);
        return Result.success(barBrandSaleByNameMonth);
    }



    @GetMapping("/binBrandSaleByMonth")
    public Result BinBrandSaleByMonth(Params params){
        List<Map<String, Object>> binBrandSaleByNameMonth = saleService.getBinBrandSaleByMonth(params);
        return Result.success(binBrandSaleByNameMonth);
    }

    @GetMapping("/barBrandSaleByMonth")
    public Result BarBrandSaleByMonth(Params params){
        HashMap<String, Object> barBrandSaleByNameMonth = saleService.getBarBrandSaleByMonth(params);
        return Result.success(barBrandSaleByNameMonth);
    }



}

