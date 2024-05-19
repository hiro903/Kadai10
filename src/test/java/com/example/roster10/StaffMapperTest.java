package com.example.roster10;

import com.example.roster10.Staff;
import com.example.roster10.StaffMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DBRider
@SpringBootTest(classes = StaffApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
class StaffMapperTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/staff.yml")
//    @Sql(
//            scripts = {"classpath:/sqlannotation/delete-staff.sql", "classpath:/sqlannotation/insert-staff.sql"},
//            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
//    )
    @Transactional
    void ユーザーが取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/staff/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("[" +
                " {" +
                " \"id\": 1," +
                " \"name\": \"chika\"," +
                " \"dateOfBirth\": \"2000-07-01\"," +
                " \"nearestStation\": \"Tokyo\"" +
                " }," +
                " {" +
                " \"id\": 2," +
                " \"name\": \"airi\"," +
                " \"dateOfBirth\": \"2005-08-13\"," +
                " \"nearestStation\": \"Meguro\"" +
                " }," +
                " {" +
                " \"id\": 3," +
                " \"name\": \"nanami\"," +
                " \"dateOfBirth\": \"1998-10-25\"," +
                " \"nearestStation\": \"Kichijoji\"" +
                " }" +
                "]", response, JSONCompareMode.STRICT);
    }
}



//    void すべてのユーザーが取得できること() {
//        List<Staff> staff = staffMapper.findAll();
//        assertThat(staff)
//                .hasSize(3)
//                .contains(
//                        new Staff(1, "chika", LocalDate.of(2000, 7, 1), "Tokyo"),
//                        new Staff(2, "airi", LocalDate.of(2005, 8, 13), "Meguro"),
//                        new Staff(3, "nanami", LocalDate.of(1998, 10, 25), "Kichijoji")
//                );
//    }
