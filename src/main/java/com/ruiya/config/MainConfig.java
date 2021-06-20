package com.ruiya.config;

import org.springframework.web.bind.annotation.*;

/**
 * @author admin
 *
 * //@Controller
 * //@ResponseBody
 */

@RestController
public class MainConfig {

    /**
     * 查询所有员工
     * @return
     */
    @GetMapping("/emps")
    public String queryEmps(){
        return "all emps";
    }

    /**
     * 查询某个员工(来到修改页面)
     * @param id
     * @return
     */
    @GetMapping("/emp/{id}")
    public String queryEmpById(@PathVariable String id){
        return "one emp";
    }

    /***
     * 来到添加页面
     * @return
     */
    @GetMapping("/emp")
    public String queryEmp(){
        return "one emp";
    }

    /**
     * 添加员工
     * @return
     */
    @PostMapping("/main/query")
    public String queryPostMain(){
        return "success post";
    }

    @PutMapping("emp")
    public String modifyEmp(){
        return  "";
    }
}
