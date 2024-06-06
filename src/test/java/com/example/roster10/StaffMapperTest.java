package com.example.roster10;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;


@DBRider
@SpringBootTest(classes = StaffApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
class StaffMapperTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/staff.yml")
    @Transactional
    void ユーザーが取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/staff"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        String expectedResponse = """
                [
                    {
                        "id": 1,
                        "name": "chika",
                        "dateOfBirth": "2000-07-01",
                        "nearestStation": "Tokyo"
                    },
                    {
                        "id": 2,
                        "name": "airi",
                        "dateOfBirth": "2005-08-13",
                        "nearestStation": "Meguro"
                    },
                    {
                        "id": 3,
                        "name": "nanami",
                        "dateOfBirth": "1998-10-25",
                        "nearestStation": "Kichijoji"
                    }
                ]
                """;

        JSONAssert.assertEquals(expectedResponse, response, JSONCompareMode.STRICT);
    }
}
