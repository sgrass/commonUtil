package org.cx.http;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HTMLParserListener;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HttpUnit {

//	 public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//	 String url = "http://item.jumeiglobal.com/ht151109p1599419t2.html";
//	 WebClient spider = new WebClient(BrowserVersion.getDefault());
//	 spider.setHTMLParserListener(HTMLParserListener.LOG_REPORTER);
//	 Page page = spider.getPage(url);
//	 System.out.println(page.getWebResponse().getContentAsString());
//	
//	 spider.close();
//	 }

	public static void main(String[] args) throws Exception {
		HttpUnit crawl = new HttpUnit();
		String url = "http://product.lefeng.com/product/66351863.html";
//		System.out.println("----------------------抓取页面时不解析js-----------------");
//		crawl.crawlPageWithoutAnalyseJs(url);
		System.out.println("----------------------抓取页面时解析js-------------------");
		crawl.crawlPageWithAnalyseJs(url);
	}

	/**
	 * 功能描述：抓取页面时不解析页面的js
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void crawlPageWithoutAnalyseJs(String url) throws Exception {
		// 1.创建连接client
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		// 2.设置连接的相关选项
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setTimeout(10000);
		// 3.抓取页面
		HtmlPage page = webClient.getPage(url);
		System.out.println(page.asXml());
		// 4.关闭模拟窗口
		webClient.closeAllWindows();
	}

	/**
	 * 功能描述：抓取页面时并解析页面的js
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void crawlPageWithAnalyseJs(String url) throws Exception {
		// 1.创建连接client
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		// 2.设置连接的相关选项
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true); // 需要解析js
		webClient.getOptions().setThrowExceptionOnScriptError(false); // 解析js出错时不抛异常
		webClient.getOptions().setTimeout(10000); // 超时时间 ms
		// 3.抓取页面
		HtmlPage page = webClient.getPage(url);
		// 4.将页面转成指定格式
		webClient.waitForBackgroundJavaScript(10000); // 等侍js脚本执行完成
		
		System.out.println(page.asXml());
		
		String filepath = "/Users/grass/Desktop/test.html";
    OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(filepath, true), "UTF-8");
		outs.write(page.asXml());
		outs.close();
		
		// 5.关闭模拟的窗口
		webClient.closeAllWindows();
	}
}
