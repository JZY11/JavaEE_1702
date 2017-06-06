## JavaEE 引言及语法
### 引言
> 两个重点
1. JSP(Java Server Pages)
2. Servlet
> 结论：JSP 的本质是 Servlet， Servlet 的本质是 Class
3. 环境配置
   
   应用服务器
   [PDF] the great java app server debate
   JSP 和 Servlet 的容器
   Tomcat
   
   [apache官网](http://www.apache.org)
   ![](https://upload.wikimedia.org/wikipedia/commons/thumb/7/7b/Tomcat-logo.svg/1200px-Tomcat-logo.svg.png)
   
   Apache Tomcat
   unzip apache-tomcat-version-windows-x86 to your_tomcat_directory
   Windows
   set environment variable:
   your_tomcat_directory/bin/setclasspath.bat
   set JAVA_HOME=your_jdk_directory
   startup or shutdown:
   your_tomcat_directory/bin/
     startup.bat
   
     or
   
     shutdown.bat
   find port using and kill task
   Win:
   > netstat -ano|findstr <port>
   > taskkill /f /pid <pid>
   
   Mac:
   $ lsof -i :<port>
   $ kill -9 <pid>
   browser:
     localhost:8080
     127.0.0.1:8080
     your_ip:8080
   Java EE Hello,world！
   HTTP(s)
   Hyper Text Transfer Protocol 超文本传输协议
   
* 下载好Tomcat后解压到自己指定的盘区下，ok后开始启动(关闭)下Tomcat应用服务器；启动：从命令行进入到Tomcat的bin目录后输入startup.bit
* 关闭：shutdown.bit (.bit为批处理命令)
* 创建基于Gradle构建的Java Web项目，让我们的IDEA与Tomcat关联，再启动Tomcat应用服务器
### Java Web项目在Tomcat中的部署
   1. 安装Tomcat参见JavaEE引言
   2. 在IDEA中关联Tomcat(让IDEA知道我们的Tomcat目录在哪)
   3. 创建Java Web项目(基于Gradle创建的Java Web项目)
   4. 在Tomcat中部署Java Web项目-- <IDEA>Run>EditConfigurations....>
   
* web项目的访问不同于我们以前的静态网页项目(直接浏览器打开就可以了)当前项目步行，必须要经过重要的工作
   (部署deploy)部署在我们的Tomcat应用服务器里面，部署之后才可以启动服务器才可运行，运行后将自动打开index.jsp页面
   
   
## JSP页面元素
i 注释：
   1. HTML注释 <!-- comment...-->
   2. JSP注释 <%--  comment...---%>
   3. Java注释  //comment   /*comment*/
   
ii 模板元素：
   * 静态HTML元素
   
iii 脚本元素：
   1. 声明declaration  <%! %>
   2. 小脚本scriptlet  <%  %>
   3. 表达式expression  <%=  %>
   
iv 指定元素：
   1. page指令  <%@ page  %>
   2. include指令   <%@ include  %>
   3. taglib指令    <%@ taglib   %>
   
v 动作元素：
    * include动作  <jsp:include page=" "/>
    
    
** 若配置charSet=UTF-8，则字符集不会出现乱码 **
    
* <pre>标签pre预先格式化，里面的内容不会有问题
    表格单元格->标签td   表格一行->标签tr

   
   