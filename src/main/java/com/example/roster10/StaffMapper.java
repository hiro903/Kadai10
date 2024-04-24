package com.example.roster10;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface StaffMapper {
    @Insert({"<script>",
            "INSERT INTO staff (name, date_of_birth, nearest_station)",
            "VALUES (",
            "<if test='name != null'>#{name},</if>",
            "<if test='dateOfBirth != null'>#{dateOfBirth},</if>",
            "<if test='nearestStation != null'>#{nearestStation}</if>",
            ")",
            "</script>"})

    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    void insert(Staff staff);
    @Select("SELECT * FROM staff WHERE id =#{id}")
    Optional<Staff> findById(int id);

}
