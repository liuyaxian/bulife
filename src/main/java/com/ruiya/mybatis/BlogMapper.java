package com.ruiya.mybatis;

import org.apache.ibatis.annotations.Select;

public interface BlogMapper {
    Blog selectBlog(int id);

    @Select("SELECT * FROM blog WHERE id = #{id}")
    Blog selectBlog1(int id);
}
