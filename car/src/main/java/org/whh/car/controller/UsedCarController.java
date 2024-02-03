package org.whh.car.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.whh.car.common.Result;
import org.whh.car.common.Params;
import org.whh.car.entity.UsedCar;
import org.whh.car.service.UsedCarService;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author whh
 * @since 2023-11-30
 */
@RestController
@RequestMapping("/usedCar")
public class UsedCarController {
    @Autowired
    private UsedCarService usedCarService;


    @GetMapping("/getById")
    public Result getById(Integer id){
        UsedCar usedCar = usedCarService.getById(id);
        return Result.success(usedCar);
    }

    @GetMapping("/getListByParams")
    public Result findByParams(Params params) {
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<UsedCar> usedCars = usedCarService.findListByParams(params);
        return Result.success(PageInfo.of(usedCars));
    }


}

