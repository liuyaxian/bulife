-- mytest.blog definition

CREATE TABLE `blog` (
                        `id` varchar(50) NOT NULL COMMENT '博客id',
                        `title` varchar(100) NOT NULL COMMENT '博客标题',
                        `author` varchar(30) NOT NULL COMMENT '博客作者',
                        `create_time` datetime NOT NULL COMMENT '创建时间',
                        `views` int(30) NOT NULL COMMENT '浏览量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- mytest.new_user definition

CREATE TABLE `new_user` (
                            `id` int(10) NOT NULL,
                            `name` varchar(30) DEFAULT NULL,
                            `pwd` varchar(30) DEFAULT NULL,
                            `age` int(11) DEFAULT NULL,
                            `insert_time` datetime NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- mytest.ppdata_info definition

CREATE TABLE `ppdata_info` (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `fund_id` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
                               `fund_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
                               `inception_date` date DEFAULT NULL,
                               `register_number` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
                               `instime` datetime DEFAULT NULL,
                               `updatetime` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `fund_id` (`fund_id`) USING BTREE,
                               KEY `register_number` (`register_number`) USING BTREE,
                               KEY `inception_date` (`inception_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=372649 DEFAULT CHARSET=utf8;


-- mytest.shop_info definition

CREATE TABLE `shop_info` (
                             `shopid` int(11) DEFAULT NULL COMMENT '店铺id',
                             `userid` int(11) DEFAULT NULL COMMENT '用户id',
                             `last_active_time` datetime DEFAULT NULL COMMENT '最后一次活跃时间',
                             `vacation` int(11) DEFAULT NULL COMMENT '是否休假',
                             `place` varchar(255) DEFAULT NULL COMMENT '地址',
                             `portrait` varchar(50) DEFAULT NULL COMMENT '肖像',
                             `username` varchar(50) DEFAULT NULL COMMENT '用户名',
                             `is_shopee_verified` int(11) DEFAULT NULL COMMENT '是否在虾皮认证',
                             `is_official_shop` int(11) DEFAULT NULL COMMENT '是否官方店铺',
                             `shop_location` varchar(255) DEFAULT NULL COMMENT '店铺位置',
                             `item_count` int(4) DEFAULT NULL COMMENT '店铺产品数量',
                             `rating_star` int(4) DEFAULT NULL COMMENT '好评率',
                             `response_rate` double DEFAULT NULL COMMENT '回复率',
                             `session_info` varchar(255) DEFAULT NULL,
                             `name` varchar(255) DEFAULT NULL COMMENT '店铺名称',
                             `ctime` datetime DEFAULT NULL COMMENT '入驻时间',
                             `response_time` datetime DEFAULT NULL COMMENT '响应时间',
                             `follower_count` int(4) DEFAULT NULL COMMENT '粉丝数',
                             `show_official_shop_label` int(4) DEFAULT NULL COMMENT '显示官方店铺标签',
                             `rating_bad` int(4) DEFAULT NULL COMMENT '差评',
                             `rating_good` int(4) DEFAULT NULL COMMENT '好评',
                             `rating_normal` int(4) DEFAULT NULL COMMENT '中评',
                             `session_infos` int(4) DEFAULT NULL,
                             `show_shopee_mission` int(1) DEFAULT NULL COMMENT '展示虾皮任务',
                             `status` int(1) DEFAULT NULL COMMENT '店铺状态',
                             `is_preferred_plus_seller` int(1) DEFAULT NULL COMMENT '是否首选卖家'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- mytest.teacher definition

CREATE TABLE `teacher` (
                           `id` int(10) NOT NULL,
                           `name` varchar(10) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- mytest.`user` definition

CREATE TABLE `user` (
                        `id` int(10) NOT NULL,
                        `name` varchar(30) DEFAULT NULL,
                        `pwd` varchar(30) DEFAULT NULL,
                        `age` int(11) DEFAULT NULL,
                        `insert_time` datetime NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- mytest.user_test definition

CREATE TABLE `user_test` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `name` varchar(20) NOT NULL,
                             `pwd` varchar(20) NOT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;


-- mytest.student definition

CREATE TABLE `student` (
                           `id` int(10) NOT NULL,
                           `name` varchar(10) DEFAULT NULL,
                           `tid` int(10) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `fktid` (`tid`),
                           CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;