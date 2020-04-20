package com.example.linearfitting.controller.data;

import com.example.linearfitting.entity.base.Result;
import com.example.linearfitting.entity.user.DataVO;
import com.example.linearfitting.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author
 */
@Api("数据")
@Slf4j
@RestController
@RequestMapping("/demo/data")
public class DataController {

    @Resource
    private DataService dataService;

    @ApiOperation("上传数据")
    @PostMapping("/upload")
    public Result<?> upload(@RequestParam("file") MultipartFile file) {

        return Result.success(dataService.getData(file));
    }

    @ApiOperation("处理数据")
    @GetMapping("/deal")
    public Result<?> deal(@RequestBody DataVO data) {

        return Result.success(dataService.getResult(data.getResult()));
    }
}
