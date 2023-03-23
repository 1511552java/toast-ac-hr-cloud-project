package com.toast.provider.rating.contorller;

import com.toast.common.dto.RatingDTO;
import com.toast.common.response.R;
import com.toast.common.service.IRatingService;
import com.toast.jwt.annotation.JWTCheckToken;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe
 */
@RestController
@RequestMapping("/hr/provider/rating/*")
public class RatingController {
    @Autowired
    private IRatingService ratingService;

    @JWTCheckToken(role = "member")
    @ApiOperation(value = "工资等级列表", notes = "查看公司全部工资等级数据")
    @GetMapping("list")
    public R<List<RatingDTO>> list() {
        return R.data(ratingService.list());
    }

    @JWTCheckToken(role = "member")
    @ApiOperation(value = "等级信息", notes = "根据等级编号获取该等级的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rtid", value = "等级ID",
                    required = true, dataType = "string")
    })
    @GetMapping("get")
    public R<RatingDTO> get(String rtid) {
        return R.data(ratingService.get(rtid));
    }
}
