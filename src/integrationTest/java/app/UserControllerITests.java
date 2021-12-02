package app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import app.entity.User;
import app.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("User Resource")
public class UserControllerITests {

    @Autowired
    transient MockMvc mockMvc;

    @MockBean
    transient UserRepository userRepository;

    @Test
    @DisplayName("/user (GET)")
    void getAllUsers() throws Exception {
        MvcResult response = mockMvc.perform(get("/user")).andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("/user (POST)")
    void addUser() throws Exception {
        String user = "{\"username\":\"username\",\"email\":\"string@string.com\"}";
        MvcResult response = mockMvc
            .perform(post("/user").content(user).contentType(APPLICATION_JSON))
            .andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("/user/{id} (PUT)")
    void updateUser() throws Exception {
        String user = "{\"username\":\"username\",\"email\":\"string@string.com\"}";
        MvcResult response = mockMvc
            .perform(put("/user/1").content(user).contentType(APPLICATION_JSON))
            .andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("/user/{id} (GET)")
    void getUserById() throws Exception {
        when(userRepository.findById(1)).thenReturn(Optional.of(new User()));
        MvcResult response = mockMvc.perform(get("/user/1")).andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @DisplayName("/user/{id} (DELETE)")
    void deleteUser() throws Exception {
        MvcResult response = mockMvc.perform(delete("/user/1")).andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }
}
