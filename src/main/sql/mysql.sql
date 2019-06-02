/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50643
Source Host           : localhost:3306
Source Database       : myblog

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-05-24 17:20:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_blog`
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` varchar(255) NOT NULL,
  `c_title` varchar(255) DEFAULT NULL,
  `c_content` text,
  `c_uid` varchar(255) DEFAULT NULL,
  `c_time` date DEFAULT NULL,
  `c_firstid` varchar(255) DEFAULT NULL,
  `c_secondid` varchar(255) DEFAULT NULL,
  `c_lable_id` varchar(255) DEFAULT NULL,
  `c_pic_adr` varchar(255) DEFAULT NULL,
  `c_md` varchar(255) DEFAULT NULL,
  `c_value` int(50) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES ('0673d00f-2ca6-4e6e-a79b-fb81e5e13e1c', 'Left Join 连表查询', '<h3 id=\"h3-mysql\"><a name=\"MySQL\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>MySQL</h3>', 'afac49fd-8c30-4e3b-a21c-646d92b48608', '2019-05-01', '11', '211', '4fe93ede-9766-420e-a5b9-75e5af1b39be', null, null, '5');
INSERT INTO `t_blog` VALUES ('06774322-a462-4d4b-a3f7-f79822a9957f', 'SpringAOP', '<p>spring - aop 使用方式总结</p>\n<p>方式一：接口</p>\n<p>  前置增强      MethodBeforeAdvice</p>\n<p>  后置增强      AfterReturningAdvice</p>\n<p>  异常抛出增强  ThrowsAdvice</p>\n<p>  环绕增强      MethodInterceptor</p>\n<p>  注意：还需要在applicationContext.xml文件中进行aop相关的配置</p>\n<pre><code>    &lt;aop:config&gt;\n</code></pre><p>&lt;aop:pointcut expression=&quot;execution(public void *User())&quot; id=&quot;addoptpointcut&quot;/&gt;</p>\n<p>&lt;aop:advisor advice-ref=&quot;logbefore&quot; pointcut-ref=&quot;addoptpointcut&quot; /&gt;<br>&lt;/aop:config&gt;</p>\n<pre><code>    &lt;aop:config&gt;\n</code></pre><p>&lt;aop:pointcut expression=&quot;execution(* spring_basic.UserService.*(..))&quot; id=&quot;userServiceOptPointcut&quot;/&gt;<br>&lt;!— </p>\n<p>&lt;aop:advisor advice-ref=&quot;exceptionAdvice&quot; pointcut-ref=&quot;userServiceOptPointcut&quot; /&gt;<br>—&gt;</p>\n<p>&lt;aop:advisor advice-ref=&quot;logger&quot; pointcut-ref=&quot;userServiceOptPointcut&quot; /&gt;<br>&lt;/aop:config&gt;</p>\n<p>方式二：注解</p>\n<p>  前置增强      <a href=\"https://github.com/Before\" title=\"&#64;Before\" class=\"at-link\">@Before</a>(“execution(<em> service.</em>.*(..))”)</p>\n<p>  后置增强      <a href=\"https://github.com/AfterReturning\" title=\"&#64;AfterReturning\" class=\"at-link\">@AfterReturning</a>(pointcut=”execution(<em> service.</em>.*(..))”, returning=”result”)</p>\n<p>  异常抛出增强  <a href=\"https://github.com/AfterThrowing\" title=\"&#64;AfterThrowing\" class=\"at-link\">@AfterThrowing</a>(pointcut=”execution(<em> service.</em>.*(..))”, throwing=”ex”)</p>\n<p>  环绕增强      <a href=\"https://github.com/Around\" title=\"&#64;Around\" class=\"at-link\">@Around</a>(“execution(<em> service.</em>.*(..))”)</p>\n<p>  注意：还需要在applicationContext.xml文件中添加如下配置</p>\n<pre><code>    &lt;aop:aspectj-autoproxy&gt;&lt;/aop:aspectj-autoproxy&gt;\n</code></pre><p>方式三：Scheme</p>\n<p>  首先：添加普通的类，里面按要求编写方法</p>\n<p>  public class Logger {</p>\n<p>public void before(JoinPoint jp){</p>\n<p>}</p>\n<p>public void after(JoinPoint jp, Object result){</p>\n<p>}</p>\n<p>public void aterThrowing(JoinPoint jp, Exception ex){</p>\n<p>}</p>\n<p>public Object aroundExecute(ProceedingJoinPoint pjp) throws Throwable{</p>\n<p>}</p>\n<p>  }</p>\n<p>  其次：在applicationContext.xml中配置aop-aspect相关的配置</p>\n<pre><code>    &lt;aop:config&gt;\n</code></pre><p>&lt;aop:pointcut expression=&quot;execution(* service.*.*(..))&quot; id=&quot;optpointcut&quot;/&gt;\n\n</p>\n<p>&lt;aop:aspect ref=&quot;mylogger&quot;&gt;</p>\n<p>&lt;aop:before method=&quot;before&quot; pointcut-ref=&quot;optpointcut&quot; /&gt;</p>\n<p>&lt;aop:after-returning method=&quot;after&quot; returning=&quot;result&quot; pointcut-ref=&quot;optpointcut&quot; /&gt;</p>\n<p>&lt;aop:after-throwing method=&quot;aterThrowing&quot; throwing=&quot;ex&quot; pointcut-ref=&quot;optpointcut&quot; /&gt;</p>\n<p>&lt;aop:around method=&quot;aroundExecute&quot; pointcut-ref=&quot;optpointcut&quot;/&gt;<br>&lt;/aop:aspect&gt;</p>\n<p>&lt;/aop:config&gt;</p>', 'afac49fd-8c30-4e3b-a21c-646d92b48608', '2019-05-01', '10', '20', '1', 'http://localhost:8080/resources/upload/user/3.png', null, '12');
INSERT INTO `t_blog` VALUES ('1f310437-d958-42c6-a418-dc080b34f6f8', '1', '<h2 id=\"h2-tttt\"><a name=\"tttt\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>tttt</h2><p>2565532</p>\n<h1 id=\"h1-wwww\"><a name=\"wwww\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>wwww</h1>', '0e8f8c55-aebf-47c1-b231-59b1f56f448c', '2019-04-23', '10', null, '9d3ab0ed7f77', 'http://localhost:8080/resources/upload/user/3.png', '##tttt\n2565532\n#wwww', '11');
INSERT INTO `t_blog` VALUES ('24a4727c-c51f-49bf-8cb9-fd1539e55d09', 'SSM', '<h1 id=\"h1-ssm\"><a name=\"SSM\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>SSM</h1><p>个人博客<br>2019-04-24 13&#58;02&#58;41 星期三<br>    ssmblog<br>    666            </p>\n<blockquote>\n<p>搭建环境</p>\n</blockquote>\n<p>666</p>', '0e8f8c55-aebf-47c1-b231-59b1f56f448c', '2019-04-24', '10', null, '2ca93bef-0c70-4f9a-a147-9d3ab0ed7f77', 'http://localhost:8080/resources/upload/user/3.png', '#SSM\n个人博客\n2019-04-24 13:02:41 星期三\n    ssmblog\n	666			\n> 搭建环境', '64');
INSERT INTO `t_blog` VALUES ('2e835c7a-98e2-44f6-9d73-7717e61800e0', 'title9', '<h1 id=\"h1-u7B2Cu4E8Cu7BC7\"><a name=\"第二篇\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>第二篇</h1>', '0e8f8c55-aebf-47c1-b231-59b1f56f448c', '2019-04-23', '10', null, '15ecc3b2-509f-40c3-a98b-3b46395a459c', 'http://localhost:8080/resources/upload/user/3.png', '#第二篇', '12');
INSERT INTO `t_blog` VALUES ('40e04051-b190-4423-bc55-2901a271c98b', 'Tencent', '<h1 id=\"h1-tencent\"><a name=\"Tencent\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Tencent</h1><p><img src=\"http://localhost:80/resources/upload/1.gif\" alt=\"Tencent\" title=\"Tencent\"></p>', '0e8f8c55-aebf-47c1-b231-59b1f56f448c', '2019-04-28', '12', '', '4b16f083-51ed-4095-9b83-b85b5a703702', 'http://localhost:8080/resources/upload/user/3.png', null, '5');
INSERT INTO `t_blog` VALUES ('6c8716fa-a5e3-4695-829f-bdc409907c9f', 'JS', '<h3 id=\"h3-js\"><a name=\"JS\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>JS</h3><p>JavaScript是一种脚本语言<br><code>&lt;javascript &gt; \n&lt;/javacript&gt;</code></p>\n<table>\n<thead>\n<tr>\n<th>第一列</th>\n<th>第二列</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>11</td>\n<td>22</td>\n</tr>\n</tbody>\n</table>\n<p><img src=\"/resources/upload/1.gif\" alt=\"测试图片路径\" title=\"测试图片路径\"></p>', 'dffc4a91-45a9-45e1-b053-96b974ab118a', '2019-04-28', '', '', '47d6b1d8-65b6-41da-a257-0dede118888f', 'http://localhost:8080/resources/upload/user/3.png', null, '19');
INSERT INTO `t_blog` VALUES ('7826950f-f942-4445-b030-27ab1fd02d3a', 'SpringMVC分享', '<h1 id=\"h1-1-spring-ioc-\"><a name=\"1.谈谈对Spring IoC的理解\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1.谈谈对Spring IoC的理解</h1><p>IoC不是什么技术，而是一种设计思想，它意味着将你设计好的对象交给容器控制，而不是传统的在你的对象内部直接控制。<br>理解好Ioc的关键是要明确：</p>\n<pre><code> 谁控制谁 -- IoC容器控制了对象\n控制什么 -- 主要控制了外部资源获取（不只是对象，包括比如文件等）\n为何是反转 -- 由容器帮我们查找及注入依赖对象，对象只是被动的接受依赖对象\n哪些方面反转了 -- 依赖对象的获取被反转了\n</code></pre><p>传统做法</p>\n<h1 id=\"h1-ioc-\"><a name=\"IoC做法\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>IoC做法</h1>', 'afac49fd-8c30-4e3b-a21c-646d92b48608', '2019-05-01', '10', '20', '2', 'http://localhost:8080/resources/upload/user/3.png', null, '5');
INSERT INTO `t_blog` VALUES ('861c2e44-d29e-4f99-b7d1-4553cb344564', '多线程并发', '<p>前两种实际上很少使用，一般都是用线程池的方式比较多一点。</p>\n<p>①继承Thread类<br>public class MyThread extends Thread {<br>    <a href=\"https://github.com/Override\" title=\"&#64;Override\" class=\"at-link\">@Override</a><br>    public void run() {<br>        super.run();<br>        System.out.println(“MyThread”);<br>    }<br>}<br>1<br>2<br>3<br>4<br>5<br>6<br>7<br>Run.java</p>\n<p>public class Run {</p>\n<pre><code>public static void main(String[] args) {\n    MyThread mythread = new MyThread();\n    mythread.start();\n    System.out.println(&quot;运行结束&quot;);\n}\n</code></pre><p>}</p>\n<p>1<br>2<br>3<br>4<br>5<br>6<br>7<br>8<br>9<br>10<br>运行结果：</p>\n<p>从上面的运行结果可以看出：线程是一个子任务，CPU以不确定的方式，或者说是以随机的时间来调用线程中的run方法。</p>\n<p>②实现Runnable接口<br>推荐实现Runnable接口方式开发多线程，因为Java单继承但是可以实现多个接口。</p>\n<p>MyRunnable.java</p>\n<p>public class MyRunnable implements Runnable {<br>    <a href=\"https://github.com/Override\" title=\"&#64;Override\" class=\"at-link\">@Override</a><br>    public void run() {<br>        System.out.println(“MyRunnable”);<br>    }<br>}<br>1<br>2<br>3<br>4<br>5<br>6<br>Run.java</p>\n<p>public class Run {</p>\n<pre><code>public static void main(String[] args) {\n    Runnable runnable=new MyRunnable();\n    Thread thread=new Thread(runnable);\n    thread.start();\n    System.out.println(&quot;运行结束！&quot;);\n}\n</code></pre><p>}<br>1<br>2<br>3<br>4<br>5<br>6<br>7<br>8<br>9<br>10<br>运行结果：</p>\n<p>③使用线程池</p>\n<h2 id=\"h2--java-\"><a name=\"使用线程池的方式也是最推荐的一种方式，另外，《阿里巴巴Java开发手册》在第一章第六节并发处理这一部分也强调到“线程资源必须通过线程池提供，不允许在应用中自行显示创建线程”。这里就不给大家演示代码了，线程池这一节会详细介绍到这部分内容。\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>使用线程池的方式也是最推荐的一种方式，另外，《阿里巴巴Java开发手册》在第一章第六节并发处理这一部分也强调到“线程资源必须通过线程池提供，不允许在应用中自行显示创建线程”。这里就不给大家演示代码了，线程池这一节会详细介绍到这部分内容。</h2><p>作者：SnailClimb在csdn<br>来源：CSDN<br>原文：<a href=\"https://blog.csdn.net/qq_34337272/article/details/79640870\">https://blog.csdn.net/qq_34337272/article/details/79640870</a><br>版权声明：本文为博主原创文章，转载请附上博文链接！</p>', 'afac49fd-8c30-4e3b-a21c-646d92b48608', '2019-05-01', '10', '201', 'f1169ba4-cc43-4905-b2f7-c1d1e27da819', 'http://localhost:8080/resources/upload/user/3.png', null, '9');
INSERT INTO `t_blog` VALUES ('90f88f18-5483-4aec-b397-9d11d2e46748', 'MySQL默认引擎', '<h1 id=\"h1-mysql-\"><a name=\"MySQL默认引擎\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>MySQL默认引擎</h1>', 'afac49fd-8c30-4e3b-a21c-646d92b48608', '2019-05-01', '11', '211', '4fe93ede-9766-420e-a5b9-75e5af1b39be', 'http://localhost:8080/resources/upload/user/3.png', null, '5');
INSERT INTO `t_blog` VALUES ('92ddae88-0cf0-4134-a767-da9d329f3873', 'Spring分享', '<h3 id=\"h3-springmvc-spring-mybatis\"><a name=\"SpringMVC+Spring+Mybatis\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>SpringMVC+Spring+Mybatis</h3>', '0e8f8c55-aebf-47c1-b231-59b1f56f448c', '2019-04-24', '10', null, 'c6412cf7-bda7-49bd-adb5-fe33eeb82830', 'http://localhost:8080/resources/upload/user/3.png', '###SpringMVC+Spring+Mybatis', '9');
INSERT INTO `t_blog` VALUES ('94231978-51b2-4b67-bc57-b5a085bc7e26', 'HTML', '<h1 id=\"h1-html\"><a name=\"HTML\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>HTML</h1><p>CSs’``</p>\n<p><code>divclass</code></p>', 'dffc4a91-45a9-45e1-b053-96b974ab118a', '2019-04-28', '212', '', '499f0dd9-7423-4605-afe1-8a4270770707', 'http://localhost:8080/resources/upload/user/3.png', null, '28');
INSERT INTO `t_blog` VALUES ('9a1d950f-ce8f-4e6e-a36c-faa842bbfb93', '我的标题', '<p>@</p>\n<h1 id=\"h1-java-\"><a name=\"Java语言生态\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>Java语言生态</h1><table>\n<thead>\n<tr>\n<th>第一列</th>\n<th>第二轮</th>\n</tr>\n</thead>\n<tbody>\n<tr>\n<td>是的撒</td>\n<td>是的ds</td>\n</tr>\n<tr>\n<td>s</td>\n<td>s</td>\n</tr>\n</tbody>\n</table>\n<p><code>publci void main();</code></p>', '0e8f8c55-aebf-47c1-b231-59b1f56f448c', '2019-04-24', '10', null, '2ca93bef-0c70-4f9a-a147-9d3ab0ed7f77', 'http://localhost:8080/resources/upload/user/3.png', '@\n#Java语言生态\n|  第一列 |   第二轮|\n| ------------ | ------------ |\n|是的撒   |   是的ds|\n|  s | s  |\n`publci void main();`', '4');
INSERT INTO `t_blog` VALUES ('a40209cf-3e97-4511-a5b7-711958bf9e27', '1', '', '1f1681cf-8241-41d3-b516-9af6560e291f', '2019-05-08', '11', '', '', 'http://localhost:8080/resources/upload/user/3.png', '', '2');
INSERT INTO `t_blog` VALUES ('b1591f37-9022-4968-8611-ac8982e54a87', '添加图片', '<h1 id=\"h1-img\"><a name=\"img\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>img</h1><p><img src=\"/resources/upload/E2C5DB37990576B9AF497F3DFC0196EA.jpg\" alt=\"testimg\" title=\"testimg\"></p>', '0e8f8c55-aebf-47c1-b231-59b1f56f448c', '2019-04-24', '10', null, '9d3ab0ed7f77', 'http://localhost:8080/resources/upload/user/3.png', '#img\n\n\n\n\n![testimg](/resources/upload/E2C5DB37990576B9AF497F3DFC0196EA.jpg \"testimg\")', '3');
INSERT INTO `t_blog` VALUES ('d3ea3447-cffb-4e8d-aadb-53f92f400a74', 'md', '<h2 id=\"h2-u591Au8868u67E5u8BE2\"><a name=\"多表查询\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>多表查询</h2>', 'afac49fd-8c30-4e3b-a21c-646d92b48608', '2019-05-01', '11', '211', '4fe93ede-9766-420e-a5b9-75e5af1b39be', 'http://localhost:8080/resources/upload/user/3.png', null, '3');
INSERT INTO `t_blog` VALUES ('d6f382de-95c0-437a-8a14-24e450ff0b7e', 'test666', '<h2 id=\"h2-u591Au8868u67E5u8BE2\"><a name=\"多表查询\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>多表查询</h2>', 'afac49fd-8c30-4e3b-a21c-646d92b48608', '2019-05-01', '11', '211', '4fe93ede-9766-420e-a5b9-75e5af1b39be', 'http://localhost:8080/resources/upload/user/3.png', null, '5');
INSERT INTO `t_blog` VALUES ('de2b65e6-fc62-4545-8e83-1dc263f78409', '多变关联性能优化', '<h2 id=\"h2-u591Au8868u67E5u8BE2\"><a name=\"多表查询\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>多表查询</h2>', 'afac49fd-8c30-4e3b-a21c-646d92b48608', '2019-05-01', '11', '211', '4fe93ede-9766-420e-a5b9-75e5af1b39be', 'http://localhost:8080/resources/upload/user/3.png', null, '6');
INSERT INTO `t_blog` VALUES ('f45c193f-da95-489e-85ec-3258aadd6980', '编辑博客的标题', '<h1 id=\"h1-u5DF2u53D1u5E03u7684u535Au5BA2\"><a name=\"已发布的博客\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>已发布的博客</h1><hr>', 'afac49fd-8c30-4e3b-a21c-646d92b48608', '2019-05-01', '', '', 'c7aefb51-32d2-490e-9e81-b18f30ed1b5e', 'http://localhost:8080/resources/upload/user/3.png', '#已发布的博客\n\n------------', '11');
INSERT INTO `t_blog` VALUES ('f891edf5-a4f8-48a8-9076-dbe2f80b799e', 'MyBatis', '<p>mybatis实战教程(mybatis in action)之一：开发环境搭建<br>mybatis 的开发环境搭建，选择: eclipse j2ee 版本，mysql 5.1 ,jdk 1.7,mybatis3.2.0.jar包。这些软件工具均可以到各自的官方网站上下载。<br>首先建立一个名字为 MyBaits 的 dynamic web project </p>\n<ol>\n<li>现阶段，你可以直接建立java 工程，但一般都是开发web项目，这个系列教程最后也是web的，所以一开始就建立web工程。</li><li>将 mybatis-3.2.0-SNAPSHOT.jar，mysql-connector-java-5.1.22-bin.jar 拷贝到 web工程的lib目录.</li><li>创建mysql 测试数据库和用户表,注意，这里采用的是 utf-8 编码</li></ol>\n<p>创建用户表,并插入一条测试数据<br> 程序代码 程序代码</p>\n<p>Create TABLE <code>user</code> (<br>  <code>id</code> int(11) NOT NULL AUTO_INCREMENT,<br>  <code>userName</code> varchar(50) DEFAULT NULL,<br>  <code>userAge</code> int(11) DEFAULT NULL,<br>  <code>userAddress</code> varchar(200) DEFAULT NULL,<br>  PRIMARY KEY (<code>id</code>)<br>) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;</p>\n<p>Insert INTO <code>user</code> VALUES (‘1’, ‘summer’, ‘100’, ‘shanghai,pudong’);</p>\n<p>到此为止，前期准备工作就完成了。下面开始真正配置mybatis项目了。</p>\n<ol>\n<li>在MyBatis 里面创建两个源码目录，分别为 src_user,test_src, 用如下方式建立,鼠标右键点击 JavaResource.</li></ol>\n<ol>\n<li>设置mybatis 配置文件:Configuration.xml, 在src_user目录下建立此文件，内容如下:<br>程序代码 程序代码</li></ol>\n<p>&lt; ?xml version=”1.0” encoding=”UTF-8” ?&gt;<br>&lt; !DOCTYPE configuration PUBLIC “-//mybatis.org//DTD Config 3.0//EN”<br>“<a href=\"http://mybatis.org/dtd/mybatis-3-config.dtd&quot;&gt;\">http://mybatis.org/dtd/mybatis-3-config.dtd&quot;&gt;</a><br>&lt; configuration&gt;<br>    &lt;typeAliases&gt;<br>        &lt;typeAlias alias=&quot;User&quot; type=&quot;com.yihaomen.mybatis.model.User&quot;/&gt;<br>    &lt;/typeAliases&gt; </p>\n<pre><code>&lt;environments default=&quot;development&quot;&gt;\n    &lt;environment id=&quot;development&quot;&gt;\n    &lt;transactionManager type=&quot;JDBC&quot;/&gt;\n        &lt;dataSource type=&quot;POOLED&quot;&gt;\n        &lt;property name=&quot;driver&quot; value=&quot;com.mysql.jdbc.Driver&quot;/&gt;\n        &lt;property name=&quot;url&quot; value=&quot;jdbc:mysql://127.0.0.1:3306/mybatis&quot; /&gt;\n        &lt;property name=&quot;username&quot; value=&quot;root&quot;/&gt;\n        &lt;property name=&quot;password&quot; value=&quot;password&quot;/&gt;\n        &lt;/dataSource&gt;\n    &lt;/environment&gt;\n&lt;/environments&gt;\n\n&lt;mappers&gt;\n    &lt;mapper resource=&quot;com/yihaomen/mybatis/model/User.xml&quot;/&gt;\n&lt;/mappers&gt;\n</code></pre><p>&lt; /configuration&gt;</p>\n<ol>\n<li>建立与数据库对应的 java class,以及映射文件.<br>在src_user下建立package:com.yihaomen.mybatis.model ,并在这个 package 下建立 User 类:<br>程序代码 程序代码</li></ol>\n<p>package com.yihaomen.mybatis.model;</p>\n<p>public class User {</p>\n<pre><code>private int id;\nprivate String userName;\nprivate String userAge;\nprivate String userAddress;\n\npublic int getId() {\n    return id;\n}\npublic void setId(int id) {\n    this.id = id;\n}\npublic String getUserName() {\n    return userName;\n}\npublic void setUserName(String userName) {\n    this.userName = userName;\n}\npublic String getUserAge() {\n    return userAge;\n}\npublic void setUserAge(String userAge) {\n    this.userAge = userAge;\n}\npublic String getUserAddress() {\n    return userAddress;\n}\npublic void setUserAddress(String userAddress) {\n    this.userAddress = userAddress;\n}\n</code></pre><p>}</p>\n<p>同时建立这个User 的映射文件 User.xml:<br>程序代码 程序代码</p>\n<p>&lt; ?xml version=”1.0” encoding=”UTF-8” ?&gt;<br>&lt; !DOCTYPE mapper PUBLIC “-//mybatis.org//DTD Mapper 3.0//EN”<br>“<a href=\"http://mybatis.org/dtd/mybatis-3-mapper.dtd&quot;&gt;\">http://mybatis.org/dtd/mybatis-3-mapper.dtd&quot;&gt;</a></p>\n<p>&lt; mapper namespace=”com.yihaomen.mybatis.models.UserMapper”&gt;<br>    &lt;select id=&quot;selectUserByID&quot; parameterType=&quot;int&quot; resultType=&quot;User&quot;&gt;<br>        select * from <code>user</code> where id = #{id}<br>    &lt;/select&gt;<br>&lt; /mapper&gt;</p>\n<p>下面对这几个配置文件解释下：<br>1.Configuration.xml 是 mybatis 用来建立 sessionFactory 用的，里面主要包含了数据库连接相关东西，还有 java 类所对应的别名，比如 &lt;typeAlias alias=&quot;User&quot; type=&quot;com.yihaomen.mybatis.model.User&quot;/&gt; 这个别名非常重要，你在 具体的类的映射中，比如User.xml 中 resultType 就是对应这里的。要保持一致，当然这里的 resultType 还有另外单独的定义方式，后面再说。</p>\n<ol>\n<li>Configuration.xml 里面 的&lt;mapper resource=&quot;com/yihaomen/mybatis/model/User.xml&quot;/&gt;是包含要映射的类的xml配置文件。</li><li>在User.xml 文件里面 主要是定义各种SQL 语句，以及这些语句的参数，以及要返回的类型等.</li></ol>\n<p>开始测试<br>在test_src 源码目录下建立com.yihaomen.test这个package,并建立测试类Test:<br>程序代码 程序代码</p>\n<p>package com.yihaomen.test;</p>\n<p>import java.io.Reader;</p>\n<p>import org.apache.ibatis.io.Resources;<br>import org.apache.ibatis.session.SqlSession;<br>import org.apache.ibatis.session.SqlSessionFactory;<br>import org.apache.ibatis.session.SqlSessionFactoryBuilder;</p>\n<p>import com.yihaomen.mybatis.model.User;</p>\n<p>public class Test {<br>    private static SqlSessionFactory sqlSessionFactory;<br>    private static Reader reader; </p>\n<pre><code>static{\n    try{\n        reader    = Resources.getResourceAsReader(&quot;Configuration.xml&quot;);\n        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);\n    }catch(Exception e){\n        e.printStackTrace();\n    }\n}\n\npublic static SqlSessionFactory getSession(){\n    return sqlSessionFactory;\n}\n\npublic static void main(String[] args) {\n    SqlSession session = sqlSessionFactory.openSession();\n    try {\n    User user = (User) session.selectOne(&quot;com.yihaomen.mybatis.models.UserMapper.selectUserByID&quot;, 1);\n    System.out.println(user.getUserAddress());\n    System.out.println(user.getUserName());\n    } finally {\n    session.close();\n    }\n}\n</code></pre><p>}</p>\n<p>现在运行这个程序，是不是得到查询结果了。恭喜你，环境搭建配置成功，接下来第二章，将讲述基于接口的操作方式，增删改查。<br>整个工程目录结构如下:</p>', 'afac49fd-8c30-4e3b-a21c-646d92b48608', '2019-05-01', '10', '20', '148ba218-4bcc-4cc1-8911-ef6af7490756', 'http://localhost:8080/resources/upload/user/3.png', null, '11');

-- ----------------------------
-- Table structure for `t_code`
-- ----------------------------
DROP TABLE IF EXISTS `t_code`;
CREATE TABLE `t_code` (
  `id` varchar(255) NOT NULL,
  `c_name` varchar(255) DEFAULT NULL,
  `c_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_code
-- ----------------------------
INSERT INTO `t_code` VALUES ('10', 'Java', '10');
INSERT INTO `t_code` VALUES ('11', '数据库', '11');
INSERT INTO `t_code` VALUES ('12', '设计模式', '12');
INSERT INTO `t_code` VALUES ('20', 'Java框架', '20');
INSERT INTO `t_code` VALUES ('201', '源码分享', '201');
INSERT INTO `t_code` VALUES ('47b1c97e-26c8-49e1-bde1-83225c726a66', 'Hadoop', '203');
INSERT INTO `t_code` VALUES ('6fd7c2c6-5e6c-47a7-80ce-fc1525ce3b86', '云计算', '202');

-- ----------------------------
-- Table structure for `t_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` varchar(255) NOT NULL,
  `c_id` varchar(255) NOT NULL,
  `c_reply_id` varchar(255) DEFAULT NULL,
  `c_content` varchar(255) DEFAULT NULL,
  `c_time` date DEFAULT NULL,
  `c_ref_blog` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('02e5dba9-5d6d-4d5f-b108-b86e469a5ab4', 'afac49fd-8c30-4e3b-a21c-646d92b48608', null, '开始你的表演', '2019-05-01', '2e835c7a-98e2-44f6-9d73-7717e61800e0');
INSERT INTO `t_comment` VALUES ('0ec74359-a3e6-41b8-a20a-54b9c8c7bf82', 'dffc4a91-45a9-45e1-b053-96b974ab118a', null, '111', '2019-04-28', '0ec74359-a3e6-41b8-a20a-54b9c8c7bf82');
INSERT INTO `t_comment` VALUES ('1', '1', null, '测试评论1', '2019-04-01', '24a4727c-c51f-49bf-8cb9-fd1539e55d09');
INSERT INTO `t_comment` VALUES ('180336f4-1a2a-479c-81b6-230d1ba00b45', 'afac49fd-8c30-4e3b-a21c-646d92b48608', null, '这个难不难？', '2019-05-01', '06774322-a462-4d4b-a3f7-f79822a9957f');
INSERT INTO `t_comment` VALUES ('1bedc5b2-dd3e-4890-9d9b-c1405e329f1c', 'afac49fd-8c30-4e3b-a21c-646d92b48608', null, '这个不会用', '2019-05-01', '92ddae88-0cf0-4134-a767-da9d329f3873');
INSERT INTO `t_comment` VALUES ('2', '1', null, '测试评论1', '2019-04-02', '24a4727c-c51f-49bf-8cb9-fd1539e55d09');
INSERT INTO `t_comment` VALUES ('4aa6c5ec-bc17-420a-9f37-feed488d4066', 'dffc4a91-45a9-45e1-b053-96b974ab118a', null, 'js怎么用？', '2019-04-28', '4aa6c5ec-bc17-420a-9f37-feed488d4066');
INSERT INTO `t_comment` VALUES ('4be95d65-f5b7-4483-a93e-87c229c5686d', 'dffc4a91-45a9-45e1-b053-96b974ab118a', null, 'js怎么用？', '2019-04-28', '4be95d65-f5b7-4483-a93e-87c229c5686d');
INSERT INTO `t_comment` VALUES ('518f2062-e9b7-482c-afe6-f5c014505e65', 'afac49fd-8c30-4e3b-a21c-646d92b48608', null, '想问下这个怎么弄才好', '2019-05-01', '92ddae88-0cf0-4134-a767-da9d329f3873');
INSERT INTO `t_comment` VALUES ('680dbd65-6225-45a6-bd3a-0cf7274d1dd9', 'dffc4a91-45a9-45e1-b053-96b974ab118a', null, '666', '2019-04-28', '680dbd65-6225-45a6-bd3a-0cf7274d1dd9');
INSERT INTO `t_comment` VALUES ('6dfa6dc6-fe20-4d80-b4a5-6ee654ae2455', 'dffc4a91-45a9-45e1-b053-96b974ab118a', null, '奶次', '2019-04-28', '6dfa6dc6-fe20-4d80-b4a5-6ee654ae2455');
INSERT INTO `t_comment` VALUES ('6e092c04-4fe4-4e74-91d7-f700700b7fc9', 'dffc4a91-45a9-45e1-b053-96b974ab118a', null, '666', '2019-04-28', '6e092c04-4fe4-4e74-91d7-f700700b7fc9');
INSERT INTO `t_comment` VALUES ('76db056e-51a7-4dda-ab3d-067952136cfc', 'dffc4a91-45a9-45e1-b053-96b974ab118a', null, '6666666', '2019-04-28', '76db056e-51a7-4dda-ab3d-067952136cfc');
INSERT INTO `t_comment` VALUES ('783d7c40-2bb8-4464-8aaf-43d06e97e6f4', 'afac49fd-8c30-4e3b-a21c-646d92b48608', null, '测试', '2019-05-01', '2e835c7a-98e2-44f6-9d73-7717e61800e0');
INSERT INTO `t_comment` VALUES ('863a5c22-8c17-47cc-bc9e-2ac9148dab69', 'dffc4a91-45a9-45e1-b053-96b974ab118a', null, '666', '2019-04-28', '863a5c22-8c17-47cc-bc9e-2ac9148dab69');
INSERT INTO `t_comment` VALUES ('88953892-4f88-40b7-a1b4-2775fa49b714', 'dffc4a91-45a9-45e1-b053-96b974ab118a', null, '333', '2019-04-28', '88953892-4f88-40b7-a1b4-2775fa49b714');
INSERT INTO `t_comment` VALUES ('88f10dde-dfec-47ef-a10d-41d192ed6a5b', '{\"cName\":\"test\",\"cPassword\":\"test\",\"cRole\":\"user\",\"cTime\":1554048000000,\"id\":\"1\"}', null, 'test', '2019-04-24', '24a4727c-c51f-49bf-8cb9-fd1539e55d09');
INSERT INTO `t_comment` VALUES ('8ae3f0d2-5556-4673-9b1c-e4d641cebc33', 'afac49fd-8c30-4e3b-a21c-646d92b48608', null, '666666', '2019-05-01', '2e835c7a-98e2-44f6-9d73-7717e61800e0');
INSERT INTO `t_comment` VALUES ('99cbb14f-8b31-4916-9431-68c70f10b0b5', '1', null, '6666', '2019-04-25', '24a4727c-c51f-49bf-8cb9-fd1539e55d09');
INSERT INTO `t_comment` VALUES ('a73d9065-5ab5-47a9-93ff-a830b17bd3de', '1', null, 'test', '2019-04-24', '24a4727c-c51f-49bf-8cb9-fd1539e55d09');
INSERT INTO `t_comment` VALUES ('c37d12a0-46ff-4bc1-bba4-36ecbc8c82e2', 'afac49fd-8c30-4e3b-a21c-646d92b48608', null, '我的评论', '2019-05-01', '2e835c7a-98e2-44f6-9d73-7717e61800e0');
INSERT INTO `t_comment` VALUES ('ccbe4543-bb6a-4e80-8a54-c69e8fd449bf', 'dffc4a91-45a9-45e1-b053-96b974ab118a', null, '6660000000000000', '2019-04-28', 'ccbe4543-bb6a-4e80-8a54-c69e8fd449bf');
INSERT INTO `t_comment` VALUES ('cf9842b6-41b2-4e82-abcd-d0bbb2b49bf6', 'afac49fd-8c30-4e3b-a21c-646d92b48608', null, '哈哈哈哈', '2019-05-01', '2e835c7a-98e2-44f6-9d73-7717e61800e0');
INSERT INTO `t_comment` VALUES ('fcfd39d0-1816-4e71-adfa-d7ac0befc4b6', 'afac49fd-8c30-4e3b-a21c-646d92b48608', null, 'AOP有什么用', '2019-05-01', '06774322-a462-4d4b-a3f7-f79822a9957f');

-- ----------------------------
-- Table structure for `t_lable`
-- ----------------------------
DROP TABLE IF EXISTS `t_lable`;
CREATE TABLE `t_lable` (
  `id` varchar(255) NOT NULL,
  `l_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_lable
-- ----------------------------
INSERT INTO `t_lable` VALUES ('1', 'Java');
INSERT INTO `t_lable` VALUES ('148ba218-4bcc-4cc1-8911-ef6af7490756', 'MyBatis');
INSERT INTO `t_lable` VALUES ('15ecc3b2-509f-40c3-a98b-3b46395a459c', '3');
INSERT INTO `t_lable` VALUES ('2', 'Spring');
INSERT INTO `t_lable` VALUES ('2ca93bef-0c70-4f9a-a147-9d3ab0ed7f77', 'Java简介');
INSERT INTO `t_lable` VALUES ('2f01bed2-6938-4715-bdc9-531226621708', '标签1');
INSERT INTO `t_lable` VALUES ('3', 'Linux');
INSERT INTO `t_lable` VALUES ('3419e64b-f9a1-4fdc-9564-6816843d9f84', '标签1');
INSERT INTO `t_lable` VALUES ('4', '设计模式');
INSERT INTO `t_lable` VALUES ('47d6b1d8-65b6-41da-a257-0dede118888f', 'JavaScript');
INSERT INTO `t_lable` VALUES ('499f0dd9-7423-4605-afe1-8a4270770707', 'HTML');
INSERT INTO `t_lable` VALUES ('4b16f083-51ed-4095-9b83-b85b5a703702', 'Tencent');
INSERT INTO `t_lable` VALUES ('4fe93ede-9766-420e-a5b9-75e5af1b39be', 'MySQL');
INSERT INTO `t_lable` VALUES ('a7e8ad4a-78c5-4108-b45b-300dd0833f31', 'Spark');
INSERT INTO `t_lable` VALUES ('c6412cf7-bda7-49bd-adb5-fe33eeb82830', 'ssm');
INSERT INTO `t_lable` VALUES ('c7aefb51-32d2-490e-9e81-b18f30ed1b5e', 'test');
INSERT INTO `t_lable` VALUES ('d2927d45-ce47-46d8-a362-ef88dec43ff9', '云计算');
INSERT INTO `t_lable` VALUES ('f1169ba4-cc43-4905-b2f7-c1d1e27da819', '多线程');

-- ----------------------------
-- Table structure for `t_message`
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` varchar(255) NOT NULL,
  `c_content` varchar(255) DEFAULT NULL,
  `c_user` varchar(255) DEFAULT NULL,
  `c_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES ('06a32c77-5d91-4c1c-ae79-810cfb703f73', '什么时候可以添加社交功能？', '0e8f8c55-aebf-47c1-b231-59b1f56f448c', '2019-05-01');
INSERT INTO `t_message` VALUES ('2e566935-4820-4929-ac16-1cd102b7de09', '我的留言', '1', '2019-04-26');
INSERT INTO `t_message` VALUES ('31b11148-af33-4779-bcd7-80c5063ff5f3', '999', 'visit67157079-ac2b-4b03-9d21-db08f90b2a9f', '2019-04-26');
INSERT INTO `t_message` VALUES ('335fdc87-eb09-46e1-99c1-5a58d6b5df15', '不错不错！', '0e8f8c55-aebf-47c1-b231-59b1f56f448c', '2019-05-01');
INSERT INTO `t_message` VALUES ('ad48f2cd-c5f2-4af8-868d-38e3912cf60a', '希望网站越来越好！', 'afac49fd-8c30-4e3b-a21c-646d92b48608', '2019-05-01');

-- ----------------------------
-- Table structure for `t_onecategory`
-- ----------------------------
DROP TABLE IF EXISTS `t_onecategory`;
CREATE TABLE `t_onecategory` (
  `id` varchar(255) NOT NULL,
  `c_code_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_onecategory
-- ----------------------------
INSERT INTO `t_onecategory` VALUES ('1111', '10');
INSERT INTO `t_onecategory` VALUES ('222222', '11');
INSERT INTO `t_onecategory` VALUES ('6', '12');
INSERT INTO `t_onecategory` VALUES ('6fd7c2c6-5e6c-47a7-80ce-fc1525ce3b86', '202');

-- ----------------------------
-- Table structure for `t_twocategory`
-- ----------------------------
DROP TABLE IF EXISTS `t_twocategory`;
CREATE TABLE `t_twocategory` (
  `id` varchar(255) NOT NULL,
  `c_code_id` int(11) DEFAULT NULL,
  `c_code_pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_twocategory
-- ----------------------------
INSERT INTO `t_twocategory` VALUES ('111111', '20', '10');
INSERT INTO `t_twocategory` VALUES ('1112', '201', '10');
INSERT INTO `t_twocategory` VALUES ('47b1c97e-26c8-49e1-bde1-83225c726a66', '203', '202');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(255) NOT NULL,
  `c_role` varchar(40) DEFAULT NULL,
  `c_name` varchar(255) DEFAULT NULL,
  `c_password` varchar(255) DEFAULT NULL,
  `c_xb` int(2) DEFAULT NULL,
  `c_img_adr` varchar(255) DEFAULT NULL,
  `c_alias` varchar(255) DEFAULT NULL,
  `c_jj` varchar(255) DEFAULT NULL,
  `c_dz` varchar(255) DEFAULT NULL,
  `c_star` varchar(255) DEFAULT NULL,
  `c_time` date DEFAULT NULL,
  `key1` varchar(255) DEFAULT NULL,
  `c_attention` varchar(255) DEFAULT NULL,
  `c_brith` date DEFAULT NULL,
  `c_fans` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('0e8f8c55-aebf-47c1-b231-59b1f56f448c', 'user', '马华疼', '123', '0', 'http://localhost:8080/resources/upload/user/3.png', '马华疼', '马华疼', null, null, '2019-04-28', null, null, '2019-04-03', null);
INSERT INTO `t_user` VALUES ('1f1681cf-8241-41d3-b516-9af6560e291f', 'user', 'bbu', '123', '1', 'http://localhost:8080/resources/upload/user/bbu.png', '蚌埠学院', '蚌埠学院官方博客！', null, null, '2019-05-02', null, null, '2019-04-01', null);
INSERT INTO `t_user` VALUES ('2c65197d-d4e4-4843-8cad-ee85606466fd', 'user', 'chw', '159', '1', '', 'asd', '123', null, null, '2019-05-05', null, null, null, null);
INSERT INTO `t_user` VALUES ('3326814a-2e87-432b-9b68-9917f7526926', 'user', '1996536648', '667788', '0', '', '小宝贝', '', null, null, '2019-05-11', null, null, '1996-11-10', null);
INSERT INTO `t_user` VALUES ('35674c56-2f92-4950-82fd-c42c16c6b50d', 'user', '王深', '123', '1', 'http://localhost:8080/resources/upload/user/3.png', '祖安小深', '骚气', null, null, '2019-04-28', null, null, '2019-04-04', null);
INSERT INTO `t_user` VALUES ('3f143ccd-3047-4ee4-8e7c-0d4f25f1e2a7', 'user', '罗永浩', '123', '1', 'http://localhost:8080/resources/upload/user/2.png', '罗永浩', '锤子柯基懂事', null, null, '2019-05-01', null, null, '2018-12-05', null);
INSERT INTO `t_user` VALUES ('585257b0-6a24-4520-a309-281a57478826', 'user', '库克', '123', '1', 'http://localhost:8080/resources/upload/user/bbu.png', '库克', '苹果懂事', null, null, '2019-05-01', null, null, '2012-05-01', null);
INSERT INTO `t_user` VALUES ('5f33cb02-0b0e-49a4-a722-1cf751323119', 'user', 'Jobs', '123', '1', 'http://localhost:8080/resources/upload/user/3.png', 'Jobs', '苹果懂事', null, null, '2019-05-01', null, null, '2012-05-01', null);
INSERT INTO `t_user` VALUES ('75d8236e-7eee-4622-9215-2c883742efee', 'user', '马云云', '123', '1', 'http://localhost:8080/resources/upload/user/2.png', 'mayun', '蚂蚁懂事', null, null, '2019-05-01', null, null, '2013-05-01', null);
INSERT INTO `t_user` VALUES ('afac49fd-8c30-4e3b-a21c-646d92b48608', 'user', '雷军', '123', '1', 'http://localhost:8080/resources/upload/user/3.png', '猴王', '花果山', null, null, '2019-04-28', null, null, '2019-04-02', null);
INSERT INTO `t_user` VALUES ('b24514b2-8297-4024-92f9-f65494e2abaf', 'user', '张一鸣', '123', '1', 'http://localhost:8080/resources/upload/user/3.png', '222', '888', null, null, '2019-04-28', null, null, '2019-04-02', null);
INSERT INTO `t_user` VALUES ('cda47134-d341-4f42-9365-1ad58525b1e0', 'user', '施勇刚', '666', '1', 'http://localhost:8080/resources/upload/user/3.png', '232', '2222', null, null, '2019-04-26', null, null, '1997-04-02', null);
