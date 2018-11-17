package com.heyu.spider.page.jdSort.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/dict/filed")
@Controller
public class DictFiledController {

    /*@Autowired
    private IDictFiledService dictFiledService;
    @Autowired
    private  ICacheService cacheService;


    @RequestMapping("")
    @RequiresPermissions("oms:\dict\filed:list")
    public ModelAndView toPage() {
        return new ModelAndView("/views/\dict\filed.html");
    }

    *//**
    *   ��ҳ��ѯ�б�
    *//*
    @RequiresPermissions("oms:\dict\filed:list")
    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public PageData<DictFiled> getList(DictFiled dictFiled, PageUtils pageUtils){
        return dictFiledService.findByPage(dictFiled,pageUtils);
    }

    *//**
    *   ����
    *//*
    @RequiresPermissions("oms:\dict\filed:add")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Response add(DictFiled dictFiled) {
        Response response=new Response();
        Boolean flag=dictFiledService.insert(dictFiled);
        if(!flag) {
            response.setCode(Response.FLAG_FAIL);
            response.setMessage("���������������Ƿ��ظ�!");
        }else{
            response.setCode(Response.FLAG_SUCCESS);
        }
        return response;
    }
    *//**
    *   �޸�
    *//*
    @RequiresPermissions("oms:\dict\filed:update")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Response update(DictFiled dictFiled) {
        Response response=new Response();
        Boolean flag=dictFiledService.update(dictFiled);
        if(!flag) {
            response.setCode(Response.FLAG_FAIL);
            response.setMessage("���������������Ƿ��ظ�!");
        }else{
            response.setCode(Response.FLAG_SUCCESS);
        }
        return response;
    }

    *//**
    *   ɾ��
    *//*
    @RequiresPermissions("oms:\dict\filed:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public Response delete(Integer id) {
        Response response=new Response();
        Integer result=dictFiledService.deleteById(id);
        if(result!=0) {
            response.setCode(Response.FLAG_FAIL);
            response.setMessage(id+":"+result);
        }else{
        response.setCode(Response.FLAG_SUCCESS);
        }
        return response;
    }

    @RequestMapping(value = "/doExport", method = RequestMethod.POST)
    @RequiresPermissions("oms:\dict\filed:export")
    public void doExport(DictFiled dictFiled, String titles, String fieldNames, String requestId,HttpServletResponse response) throws CustomException.DataExportException {
        try {
            PageUtils pageUtils = new PageUtils();
            pageUtils.setPage(1L);
            pageUtils.setRows(5000);
            List<DictFiled> list = dictFiledService.findByPage(dictFiled, pageUtils).getRows();
            String fileName = "�����б�";
            String expression = "";
            ExportUtils.exportExcel(list, titles, fieldNames, expression, response, fileName);
            cacheService.delCache(Constants.DOWNLOAD_LOADING+requestId);
        }
        catch (Exception e) {
            throw new CustomException.DataExportException("���ݵ��������쳣��" + e.getMessage());
        }
    }*/

}
