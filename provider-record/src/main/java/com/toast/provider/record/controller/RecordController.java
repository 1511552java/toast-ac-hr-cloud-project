package com.toast.provider.record.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.toast.common.service.IRecordService;
import com.toast.jwt.annotation.JWTCheckToken;
import com.toast.provider.record.controller.block.RecordBlockHandler;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 土司先生
 * @time 2023/3/27
 * @describe
 */
@RestController
@RequestMapping("/hr/provider/record/*")
public class RecordController {
    @Autowired
    private IRecordService recordService; // 日志记录业务层

    @ApiOperation(value = "更新记录信息", notes = "对核心业务中的更新记录进行列表展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前所在页码", required = true, dataType = "long"),
            @ApiImplicitParam(name = "lineSize", value = "每次加载的数据行数", required = true, dataType = "long"),
            @ApiImplicitParam(name = "column", value = "模糊查询列名称", required = true, dataType = "string"),
            @ApiImplicitParam(name = "keyword", value = "模糊查询关键字", required = true, dataType = "string")
    })
    @SentinelResource(value = "list", blockHandlerClass = RecordBlockHandler.class, blockHandler = "listHandler")
    @JWTCheckToken(role = "record", action = "record:list")
    @GetMapping("list")
    public Object list(Long currentPage, Long lineSize, String column, String keyword) { // 查询全部工资等级
        return this.recordService.split(currentPage, lineSize, column, keyword);
    }
}
