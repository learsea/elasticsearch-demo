# elasticsearch-demo
elasticsearch spring data使用demo和logstash同步方案

启动项目连接到es，自动创建索引。调用接口进行增删查改

使用logstash同步mysql数据到elasticsearch步骤：
1、使用goods.sql新增mysql表

2、把mysql文件夹、logstash_default.conf、run.bat移动到logstash的bin目录下，修改logstash_default.conf文件中相关路径

3、启动logstash，在mysql中添加数据可以看到数据自动同步到elasticsearch中
