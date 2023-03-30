package com.toast.endpoint.controller;

import com.toast.common.dto.EmpDTO;
import com.toast.common.response.R;
import com.toast.common.type.EmpResponseType;
import com.toast.endpoint.service.IEmpEndpointService;
import com.toast.jwt.annotation.JWTCheckToken;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/29
 * @describe
 */
@Slf4j
@RestController
@RequestMapping("/hr/endpoint/emp/*")
public class EmpEndpointController {
    @Autowired
    private IEmpEndpointService empEndpointService;

    @JWTCheckToken(role = "emp", action = "emp:add")
    @GetMapping("pre_add")
    public R<Map<String, Object>> preAdd() {    // 雇员增加前查询
        return R.data(this.empEndpointService.preAdd());
    }

    @JWTCheckToken(role = "emp", action = "emp:add")
    @PostMapping("add")
    @GlobalTransactional // 全局事务处理
    public R<EmpResponseType> add(@RequestBody EmpDTO dto) {    // 雇员增加
        return R.data(this.empEndpointService.add(dto));
    }

    @JWTCheckToken(role = "emp", action = "emp:list")
    @GetMapping("list")
    public R<Map<String, Object>> list(Long currentPage, Long lineSize, String column, String keyword) { // 查询全部工资等级
        return R.data(this.empEndpointService.split(currentPage, lineSize, column, keyword));
    }

    @JWTCheckToken(role = "emp", action = "emp:edit")
    @GetMapping("pre_edit")
    public R<Map<String, Object>> preEdit(long empno) {    // 雇员更新前查询
        return R.data(this.empEndpointService.preEdit(empno));
    }


    @JWTCheckToken(role = "emp", action = "emp:edit")
    @PostMapping("edit")
    @GlobalTransactional
    public R<EmpResponseType> edit(@RequestBody EmpDTO dto) {    // 雇员增加
        return R.data(this.empEndpointService.edit(dto));
    }

    @JWTCheckToken(role = "emp", action = "emp:remove")
    @DeleteMapping("remove")
    @GlobalTransactional
    public R<EmpResponseType> remove(long empno) {
        return R.data(this.empEndpointService.remove(empno));
    }
}
