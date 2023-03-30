package com.toast.endpoint.controller;

import com.toast.common.dto.DeptDTO;
import com.toast.common.response.R;
import com.toast.common.type.DeptResponseType;
import com.toast.endpoint.controller.abs.AbstractEndpointController;
import com.toast.endpoint.service.IDeptEndpointService;
import com.toast.jwt.annotation.JWTCheckToken;
import io.seata.spring.annotation.GlobalTransactional;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
@RestController
@RequestMapping("/hr/endpoint/dept/*")
public class DeptEndpointController extends AbstractEndpointController {
    @Autowired
    private IDeptEndpointService deptEndpointService;
    @JWTCheckToken(role = "dept", action = "dept:list")
    @GetMapping("list")
    public R<List<DeptDTO>> list() {
        return R.data(this.deptEndpointService.list());
    }

    @JWTCheckToken(role = "dept", action = "dept:edit")
    @GetMapping("get")
    public R<DeptDTO> get(long deptno) {
        return R.data(this.deptEndpointService.get(deptno));
    }

    @JWTCheckToken(role = "dept", action = "dept:edit")
    @PutMapping
    public R<DeptResponseType> edit(@RequestBody DeptDTO dto) {
        return R.data(this.deptEndpointService.edit(dto));
    }

    @JWTCheckToken(role = "dept", action = "dept:edit")
    @PostMapping("editMgr")
    @GlobalTransactional // 全局事务处理
    public Object editMgr(@RequestBody DeptDTO dto) {
        return this.deptEndpointService.editMgr(dto);
    }

    @JWTCheckToken(role = "dept", action = "dept:add")
    @PostMapping
    public R<DeptResponseType> add(@RequestBody DeptDTO dto) {
        return R.data(this.deptEndpointService.add(dto));
    }

    @JWTCheckToken(role = "dept", action = "dept:remove")
    @DeleteMapping
    @GlobalTransactional // 全局事务处理
    public Object remove(long deptno) {
        return R.data(this.deptEndpointService.remove(deptno));
    }
}
