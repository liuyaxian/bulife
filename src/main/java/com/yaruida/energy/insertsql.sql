CREATE TABLE mytest.energy (
                               foodName varchar(100) NULL COMMENT '食物名称',
                               foodenergy FLOAT NULL COMMENT '热量（大卡）',
                               carbohydrate FLOAT NULL COMMENT '碳水化合物(克)',
                               fat FLOAT NULL COMMENT '脂肪(克)',
                               protein varchar(100) NULL COMMENT '蛋白质(克)',
                               cellulose FLOAT NULL COMMENT '纤维素(克)'
)
    ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci
COMMENT='100g实物能量表';