package com.heyu.spider.page.jdSort.controller;

import com.heyu.spider.page.jdSort.api.IDictService;
import com.heyu.spider.page.jdSort.dao.DictDao;
import com.heyu.spider.page.jdSort.dao.DictFiledDao;
import com.heyu.spider.page.jdSort.entity.Dict;
import com.heyu.spider.page.jdSort.entity.DictFiled;
import com.heyu.spider.page.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/dict")
@Controller
public class DictController {


    @Autowired
    private IDictService dictService;

    @Autowired
    private DictDao dictDao;

    @Autowired
    private DictFiledDao dictFiledDao;

    @RequestMapping(value = "/doExport", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {

        List<Dict> list = dictService.findAllList();

        String[] title = {"一级类目", "二级类目", "三级类目"};

        String fileName = "京东类目" + System.currentTimeMillis() + ".xlsx";

        String sheetName = "类目-属性项集合";

        String[][] content = new String[list.size()][title.length];
        for (int i = 0; i < list.size(); i++) {
            Dict dict = list.get(i);
            content[i][0] = dict.getFirstDict();
            content[i][1] = dict.getSecondDict();
            content[i][2] = dict.getThridDict();
        }

        //创建HSSFWorkbook
        SXSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);


        Sheet sheet = wb.createSheet("属性项-属性值集合");
        String[] title2 = {"三级类目", "属性项名称", "属性值名称"};
        Row row = sheet.createRow(0);
        Cell cell = null;

        //创建标题
        for(int i=0;i<title2.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title2[i]);
        }

        List<Dict> dicts = dictDao.findAllList();
        int rowNum = 1;
        for(int i = 0;i < dicts.size(); i++){
            List<DictFiled> dictFileds = dictFiledDao.findByDictId(dicts.get(i).getId());
            if(dictFileds == null || dictFileds.size() == 0){
                continue;
            }
            for(int j = 0;j < dictFileds.size(); j++){
                row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(dicts.get(i).getThridDict());
                row.createCell(1).setCellValue(dictFileds.get(j).getFiled());
                row.createCell(2).setCellValue(dictFileds.get(j).getFiledValue());
                rowNum++;
            }
        }
        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"utf-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void exportFiled(){
        String[] title = {"一级类目", "二级类目", "三级类目", "属性项名称","属性值名称"};

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook  wb = new HSSFWorkbook();

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式



        //创建内容

        /*for(int i = 0; i < content.length; i++){
            row = sheet.createRow(i + 1);
            for(int j = 0; j < content[i].length; j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(content[i][j]);
            }
        }*/

        List<String> firsts = getFirstDict();
        int rowNum = 1;
        for(String first:firsts){
            // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(first);
            //创建标题
            HSSFRow row = sheet.createRow(0);
            for(int i=0;i<title.length;i++){
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(title[i]);
                cell.setCellStyle(style);
            }

            List<String> seconds = getSecondDict(first);

            HSSFCell cell = null;
            for(String second:seconds){
                List<Dict> thirds = getThird(first,second);
                for (Dict third:thirds){
                    List<DictFiled> dictFileds = dictFiledDao.findByDictId(third.getId());
                    List<String> fileds = dictFiledDao.findFiled(third.getId());


                    for(String filed:fileds){
                        List<String> filedvalues = dictFiledDao.findFiledValue(third.getId(),filed);

                        sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum + filedvalues.size() -1,3,3));
                        for(int i = 0; i < filedvalues.size(); i++){
                            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
                            row = sheet.createRow(rowNum);


                            //声明列对象
                            cell = row.createCell(4);
                            cell.setCellValue(filedvalues.get(i));
                            cell.setCellStyle(style);
                            rowNum++;
                        }
                        cell = row.createCell(3);
                        cell.setCellValue(filed);
                        cell.setCellStyle(style);

                    }
                }
            }
        }
    }

    private List<String> getSecondDict(String firstDict){
        List<String> seconds = dictDao.findSecond(firstDict);
        return seconds;
    }

    private List<String> getFirstDict(){
        List<String> firsts = dictDao.findFirst();
        return firsts;
    }

    private List<Dict> getThird(String firstDict,String secondDict){
        List<Dict> thirds = dictDao.findThird(firstDict,secondDict);
        return thirds;
    }



/**
 @RequestMapping("") public ModelAndView toPage() {
 return new ModelAndView("/views/dict.html");
 }

 *//**
     *   ��ҳ��ѯ�б�
     *//*
    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public PageData<Dict> getList(Dict dict, PageUtils pageUtils){
        return dictService.findByPage(dict,pageUtils);
    }

    *//**
     *   ����
     *//*
    @RequiresPermissions("oms:\dict:add")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Response add(Dict dict) {
        Response response=new Response();
        Boolean flag=dictService.insert(dict);
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
    @RequiresPermissions("oms:\dict:update")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Response update(Dict dict) {
        Response response=new Response();
        Boolean flag=dictService.update(dict);
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
    public void doExport(Dict dict, String titles, String fieldNames, String requestId,HttpServletResponse response) throws CustomException.DataExportException {
        try {
            PageUtils pageUtils = new PageUtils();
            pageUtils.setPage(1L);
            pageUtils.setRows(5000);
            List<Dict> list = dictService.findByPage(dict, pageUtils).getRows();
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
