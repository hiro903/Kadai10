package com.example.roster10.service;

import com.example.roster10.Staff;
import com.example.roster10.StaffRequest;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DBRider
public class StaffControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    @Sql("/sqlannotation/delete-staff.sql")
    @DataSet("datasets/staff.yml")
    @ExpectedDataSet("expected_datasets/after_insert.yml")
    public void スタッフの情報を渡したら登録されてスタッフの情報が返ってくること() {
        StaffRequest staffRequest = new StaffRequest("Anna", LocalDate.of(2001, 1, 1), "Tokyo");
        ResponseEntity<Staff> postResponse = restTemplate.postForEntity(getRootUrl() + "/staff", staffRequest, Staff.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Staff result = postResponse.getBody();
        assertThat(result).isNotNull();
        if (result != null) {
            assertThat(result.getName()).isEqualTo("Anna");
        }
    }

    @Test
    @DataSet("datasets/staff.yml")
    @ExpectedDataSet("expected_datasets/after_update.yml")
    public void 指定したIDのスタッフの情報を渡したら更新されること() {
        Staff updatedInfo = new Staff(1, "Anna", LocalDate.of(2001, 1, 1), "Tokyo");

        restTemplate.put(getRootUrl() + "/staff/1", updatedInfo);

        ResponseEntity<Staff> getResponse = restTemplate.getForEntity(getRootUrl() + "/staff/1", Staff.class);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().getName()).isEqualTo("Anna");
    }

    @Test
    @DataSet("datasets/staff.yml")
    @ExpectedDataSet("expected_datasets/after_find_all.yml")
    public void 登録しているスタッフの情報を全件取得すること() {
        ResponseEntity<Staff[]> response = restTemplate.getForEntity(getRootUrl() + "/staff", Staff[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(3); // Assuming 3 staff members after setup
    }

    @Test
    @DataSet("datasets/staff.yml")
    @ExpectedDataSet("expected_datasets/after_delete.yml")
    public void 登録しているスタッフの情報のIDを指定して削除すること() {
        restTemplate.delete(getRootUrl() + "/staff/1");
        ResponseEntity<Staff> getResponse = restTemplate.getForEntity(getRootUrl() + "/staff/1", Staff.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DataSet("datasets/staff.yml")
    @ExpectedDataSet("expected_datasets/staff_not_found.yml")
    public void 登録されていないIDを指定したらエラーコードが返ってくること() {
        ResponseEntity<Staff> response = restTemplate.getForEntity(getRootUrl() + "/staff/999", Staff.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DataSet("datasets/staff.yml")
    @ExpectedDataSet("expected_datasets/after_delete_not_found.yml")
    public void 登録されていないIDを指定して削除しようとしたらエラーコードが返ってくること() {
        restTemplate.delete(getRootUrl() + "/staff/999");
        ResponseEntity<Staff> getResponse = restTemplate.getForEntity(getRootUrl() + "/staff/999", Staff.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DataSet("datasets/staff.yml")
    @ExpectedDataSet("expected_datasets/update_not_found.yml")
    public void 登録されていないIDを指定して更新しようとしたらエラーコードが返ってくること() {
        Staff updatedInfo = new Staff(999, "Anna", LocalDate.of(2001, 1, 1), "Shibuya");

        restTemplate.put(getRootUrl() + "/staff/999", updatedInfo);

        ResponseEntity<Staff> getResponse = restTemplate.getForEntity(getRootUrl() + "/staff/999", Staff.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
