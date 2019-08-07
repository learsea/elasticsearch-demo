# elasticsearch-demo
elasticsearch spring data使用demo和logstash同步方案

启动项目链接到es，自动创建索引。调用接口进行增删查改

使用logstash同步mysql数据到elasticsearch步骤：
1、新增表
CREATE TABLE `goods` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名称',
  `property` varchar(500) NOT NULL DEFAULT '' COMMENT '商品属性 冒号分隔',
  `supplier` varchar(50) NOT NULL DEFAULT '' COMMENT '供应商',
  `property_value` varchar(500) NOT NULL DEFAULT '' COMMENT '属性值 冒号分隔',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `properties` varchar(500) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
2、把mysql文件夹、logstash_default.conf、run.bat移动到logstash的bin目录下，修改logstash_default.conf文件中相关路径
3、启动logstash，在mysql中添加数据可以看到数据自动同步到elasticsearch中
