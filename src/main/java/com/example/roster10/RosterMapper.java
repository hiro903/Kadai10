package com.example.roster10;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface RosterMapper {
    @Insert({"<script>",
            "INSERT INTO staff (name, date_of_birth, nearest_station)",
            "VALUES (#{name}, ",
            "<if test='dateOfBirth != null'>#{dateOfBirth},</if>",
            "#{nearest_station})",
            "</script>"})
    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    void insert(Roster roster);
}
