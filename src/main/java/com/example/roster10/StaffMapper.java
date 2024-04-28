package com.example.roster10;

import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
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

    @Update("UPDATE staff SET name = #{name}, date_of_birth = #{dateOfBirth}, nearest_station = #{nearestStation} WHERE id = #{id}")
    void updateStaff(Staff staff);

    @Delete("DELETE FROM staff WHERE id = #{id}")
    void deleteById(Staff staff);
}
