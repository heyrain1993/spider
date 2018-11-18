package com.heyu.spider.page.jsSort;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.heyu.spider.page.jdSort.SpiderJD;
import com.heyu.spider.page.jdSort.api.IDictService;
import com.heyu.spider.page.jdSort.dao.TitleDao;
import com.heyu.spider.page.jdSort.entity.Dict;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SortTest {

    @Autowired
    private IDictService dictService;

    @Autowired
    private SpiderJD spiderJD;

    @Autowired
    private TitleDao titleDao;

    @Test
    public void test(){
        /*List<Dict> list = dictService.findAllList();
        System.out.println(list);*/
        try {
            //spiderJD.spiderTitle();
            spiderJD.spiderOption();

            //titleDao.findAllList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSpiderFirstPage(){
        /*List<Dict> list = dictService.findAllList();
        System.out.println(list);*/
        try {
            //spiderJD.spiderTitle();
            spiderJD.spiderFirstPage();
            //titleDao.findAllList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() throws IOException {
        //https://list.jd.com/list.html?cat=1713,4855,4859
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        String url = "https://list.jd.com/list.html?cat=1713,4855,4859";
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        HtmlPage page = webClient.getPage(url);
        System.out.println(" //等待JS驱动dom完成获得还原后的网页 ");
        webClient.waitForBackgroundJavaScript(10000);
        System.out.println("  网页内容 ");
        System.out.println( page.asXml());
        //webClient.closeAllWindows();
    }
}
