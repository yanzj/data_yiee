package cn.doit.crawler.demo

import java.io.InputStream
import java.util

import org.apache.commons.io.IOUtils
import org.apache.http.Header
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet}
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import org.apache.http.message.BasicHeader

/**
  * @author: 余辉
  * @blog: https://blog.csdn.net/silentwolfyh
  * @create: 2019/10/22
  * @description:
  * 1、用于理解啥叫爬虫
  * 2、Header:请求头参数详解  https://yuhui.blog.csdn.net/article/details/102919766
  **/
object HttpClientDemo {

  def main(args: Array[String]): Unit = {

    //1、配置headers文件
    // 1-1、Accept 指定客户端能够接收的内容类型
    val h1 = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
    // 1-2、User-Agent User-Agent的内容包含发出请求的用户信息（模拟浏览器）
    val h2 = new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3314.0 Safari/537.36 SE 2.X MetaSr 1.0")
    // 1-3、头文件放入list集合中
    val headers = new util.ArrayList[Header]()
    headers.add(h1)
    headers.add(h2)

    // 2、创建客户端,设置头文件,建立客户端
     val client: CloseableHttpClient = HttpClientBuilder
       .create()
       .setDefaultHeaders(headers)
       .build()

    // 3、请求地址，创建get请求，client执行get请求
    val url = "http://www.appchina.com/"
    val request = new HttpGet(url)
    val response: CloseableHttpResponse = client.execute(request)

    // 4、获取请求页面内容【response.getEntity.getContent】,且通过IOUtils存储页面信息
    val content: InputStream = response.getEntity.getContent
    val lines: util.List[String] = IOUtils.readLines(content)

    // 5、打印请求页面内容 import scala.collection.JavaConversions._
    import scala.collection.JavaConversions._
    lines.foreach(println)

    // 6、关闭客户端
    client.close()
  }
}
