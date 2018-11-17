package com.heyu.spider.page.controller.dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yunji.oms.common.Constants;

import com.yunji.oms.admin.exception.CustomException;
import com.yunji.oms.admin.utils.ExportUtils;
import com.yunji.oms.stock.entity.common.PageData;
import com.yunji.oms.stock.entity.common.PageUtils;
import com.yunji.oms.common.entity.Response;
import com.yunji.sso.client.annotation.RequiresPermissions;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.yunji.oms.stock.api.common.ICacheService;

import com.heyu.spider.page.entity.dict.Dict;
import com.heyu.spider.page.api.dict.IDictService;

@RequestMapping("\dict")
@Controller
public class DictController {

    @Autowired
    private IDictService dictService;
    @Autowired
    private  ICacheService cacheService;


    @RequestMapping("")
    @RequiresPermissions("oms:\dict:list")
    public ModelAndView toPage() {
        return new ModelAndView("/views/\dict.html");
    }

    /**
    *   分页查询列表
    */
    @RequiresPermissions("oms:\dict:list")
    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public PageData<Dict> getList(Dict dict, PageUtils pageUtils){
        return dictService.findByPage(dict,pageUtils);
    }

    /**
    *   增加
    */
    @RequiresPermissions("oms:\dict:add")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Response add(Dict dict) {
        Response response=new Response();
        Boolean flag=dictService.insert(dict);
        if(!flag) {
            response.setCode(Response.FLAG_FAIL);
            response.setMessage("请检查编码或者名称是否重复!");
        }else{
            response.setCode(Response.FLAG_SUCCESS);
        }
        return response;
    }
    /**
    *   修改
    */
    @RequiresPermissions("oms:\dict:update")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Response update(Dict dict) {
        Response response=new Response();
        Boolean flag=dictService.update(dict);
        if(!flag) {
            response.setCode(Response.FLAG_FAIL);
            response.setMessage("请检查编码或者名称是否重复!");
        }else{
            response.setCode(Response.FLAG_SUCCESS);
        }
        return response;
    }

    /**
    *   删除
    */
    @RequiresPermissions("oms:\dict:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public Response delete(Integer id) {
        Response response=new Response();
        Integer result=dictService.deleteById(id);
        if(result!=0) {
            response.setCode(Response.FLAG_FAIL);
            response.setMessage(id+":"+result);
        }else{
        response.setCode(Response.FLAG_SUCCESS);
        }
        return response;
    }

    @RequestMapping(value = "/doExport", method = RequestMethod.POST)
    @RequiresPermissions("oms:\dict:export")
    public void doExport(Dict dict, String titles, String fieldNames, String requestId,HttpServletResponse response) throws CustomException.DataExportException {
        try {
            PageUtils pageUtils = new PageUtils();
            pageUtils.setPage(1L);
            pageUtils.setRows(5000);
            List<Dict> list = dictService.findByPage(dict, pageUtils).getRows();
            String fileName = "渠道列表";
            String expression = "";
            ExportUtils.exportExcel(list, titles, fieldNames, expression, response, fileName);
            cacheService.delCache(Constants.DOWNLOAD_LOADING+requestId);
        }
        catch (Exception e) {
            throw new CustomException.DataExportException("数据导出出现异常，" + e.getMessage());
        }
    }

}
