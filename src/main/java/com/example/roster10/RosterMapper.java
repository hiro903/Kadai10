package com.example.roster10;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface RosterMapper {
    @Insert({"<script>",
            "INSERT INTO staff (name, date_of_birth, nearest_station)",
            "VALUES (#{name}, ",
            "<if test='name != null'>#{name},</if>",
            "<if test='dateOfBirth != null'>#{dateOfBirth},</if>",
            "<if test='nearestStation != null'>#{nearestStation}</if>",
            "</script>"})
    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    void insert(Roster roster);
}
