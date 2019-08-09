CREATE TABLE `goods1` (
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