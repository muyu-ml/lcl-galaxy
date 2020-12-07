CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` varchar(30) DEFAULT NULL COMMENT '性别',
  `address` varchar(10) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

INSERT INTO `user`(`id`, `username`, `birthday`, `sex`, `address`) VALUES (1, 'lcl', '2020-11-23', '男', '北京');
INSERT INTO `user`(`id`, `username`, `birthday`, `sex`, `address`) VALUES (3, 'qmm', '2020-11-23', '女', '北京');
INSERT INTO `user`(`id`, `username`, `birthday`, `sex`, `address`) VALUES (12, 'qmm2', '2020-11-24', '女', '北京');


CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) NOT NULL COMMENT '用户名',
  `orderId` bigint(20) DEFAULT NULL COMMENT '订单号',
  `payMoney` decimal(11,2) DEFAULT NULL COMMENT '订单实付金额',
  `orderCreateTime` datetime DEFAULT NULL COMMENT '订单下单时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

INSERT INTO `order_info`(`id`, `name`, `orderId`, `payMoney`, `orderCreateTime`) VALUES (2, 'lcl', 10001, 10.00, '2020-11-17 21:29:35');
INSERT INTO `order_info`(`id`, `name`, `orderId`, `payMoney`, `orderCreateTime`) VALUES (3, 'lcl', 10002, 12.00, '2020-11-18 21:29:53');
INSERT INTO `order_info`(`id`, `name`, `orderId`, `payMoney`, `orderCreateTime`) VALUES (4, 'lcl', 10003, 15.00, '2020-11-12 21:30:08');
INSERT INTO `order_info`(`id`, `name`, `orderId`, `payMoney`, `orderCreateTime`) VALUES (5, 'qmm', 10004, 11.00, '2020-11-24 21:30:23');


