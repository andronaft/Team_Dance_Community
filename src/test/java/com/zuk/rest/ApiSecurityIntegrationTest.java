package com.zuk.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuk.model.User;
import com.zuk.model.UserProfile;
import com.zuk.service.UserProfileService;
import com.zuk.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApiSecurityIntegrationTest {

    private static final String PASSWORD = "correct-horse-battery";

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;
    @Value("${app.upload.dir}")
    private String uploadDir;

    @Test
    void registeredUserMustBeActivatedBeforeLogin() throws Exception {
        String username = uniqueName();
        register(username, PASSWORD)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("NOT_ACTIVE"))
                .andExpect(jsonPath("$.token").doesNotExist());

        login(username, PASSWORD).andExpect(status().isUnauthorized());

        userService.activateUser(userService.findByUsername(username).getId());

        login(username, PASSWORD)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    void wrongPasswordAndUnknownUserGetTheSameAnswer() throws Exception {
        String username = activeUser();

        String wrongPassword = login(username, "wrong-password-123").andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString();
        String unknownUser = login("nobody-" + UUID.randomUUID(), PASSWORD).andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString();

        assertThat(wrongPassword).isEqualTo(unknownUser);
    }

    @Test
    void registrationRejectsShortPasswordAndDuplicateUsername() throws Exception {
        register(uniqueName(), "short").andExpect(status().isBadRequest());

        String username = uniqueName();
        register(username, PASSWORD).andExpect(status().isCreated());
        register(username, PASSWORD).andExpect(status().isBadRequest());
    }

    @Test
    void adminEndpointsNeedAdminRole() throws Exception {
        mvc.perform(get("/api/v1/admin/users/findByUsername/").param("username", "admin"))
                .andExpect(status().isUnauthorized());

        mvc.perform(get("/api/v1/admin/users/findByUsername/").param("username", "admin")
                        .header(HttpHeaders.AUTHORIZATION, bearer(activeUser())))
                .andExpect(status().isForbidden());

        mvc.perform(get("/api/v1/admin/users/findByUsername/").param("username", "admin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer_" + tokenFor("admin", "admin-password")))
                .andExpect(status().isOk());
    }

    @Test
    void deepLinksCanBeSavedAnonymouslyButOnlyAdminsCanReadThem() throws Exception {
        mvc.perform(post("/api/deep/save").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"from\":\"instagram\",\"url_webview\":\"https://example.com\"}"))
                .andExpect(status().isOk());

        mvc.perform(get("/api/deep/getall")).andExpect(status().isUnauthorized());
        mvc.perform(get("/api/deep/getall").header(HttpHeaders.AUTHORIZATION, bearer(activeUser())))
                .andExpect(status().isForbidden());
    }

    @Test
    void userCannotChangeOwnRatingOrLevel() throws Exception {
        String username = activeUser();

        mvc.perform(post("/api/v1/users/profile/updateProfile/")
                        .header(HttpHeaders.AUTHORIZATION, bearer(username))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"about\":\"I dance salsa\",\"rating\":100,\"level\":99}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.about").value("I dance salsa"));

        UserProfile profile = userProfileService.findById(userService.findByUsername(username).getId());
        assertThat(profile.getAbout()).isEqualTo("I dance salsa");
        assertThat(profile.getRating()).isNull();
        assertThat(profile.getLevel()).isNull();
    }

    @Test
    void uploadIgnoresClientFileNameSoPathTraversalIsImpossible() throws Exception {
        String username = activeUser();
        MockMultipartFile file = new MockMultipartFile("file", "../../evil.png", "image/png", new byte[]{1, 2, 3});

        String body = mvc.perform(multipart("/api/v1/users/upload/upload/").file(file)
                        .header(HttpHeaders.AUTHORIZATION, bearer(username)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String storedName = objectMapper.readTree(body).get("img_url").asText();
        assertThat(storedName).doesNotContain("..").doesNotContain("/").endsWith(".png");
        assertThat(Files.exists(Path.of(uploadDir).resolve(storedName))).isTrue();
    }

    @Test
    void uploadRejectsNonImages() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "script.sh", "text/x-shellscript", "rm -rf /".getBytes());

        mvc.perform(multipart("/api/v1/users/upload/upload/").file(file)
                        .header(HttpHeaders.AUTHORIZATION, bearer(activeUser())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void tokenValidationEndpointHandlesMissingAndBrokenTokens() throws Exception {
        mvc.perform(get("/api/v1/token/validate")).andExpect(jsonPath("$.validate").value(false));
        mvc.perform(get("/api/v1/token/validate").header(HttpHeaders.AUTHORIZATION, "Bearer_garbage"))
                .andExpect(jsonPath("$.validate").value(false));
        mvc.perform(get("/api/v1/token/validate").header(HttpHeaders.AUTHORIZATION, bearer(activeUser())))
                .andExpect(jsonPath("$.validate").value(true));
    }

    @Test
    void corsOnlyAllowsConfiguredOrigins() throws Exception {
        mvc.perform(options("/api/v1/news/")
                        .header(HttpHeaders.ORIGIN, "http://localhost:3000")
                        .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:3000"));

        mvc.perform(options("/api/v1/news/")
                        .header(HttpHeaders.ORIGIN, "https://evil.example")
                        .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET"))
                .andExpect(status().isForbidden());
    }

    private String activeUser() throws Exception {
        String username = uniqueName();
        register(username, PASSWORD).andExpect(status().isCreated());
        User user = userService.findByUsername(username);
        userService.activateUser(user.getId());
        return username;
    }

    private String bearer(String username) throws Exception {
        return "Bearer_" + tokenFor(username, PASSWORD);
    }

    private String tokenFor(String username, String password) throws Exception {
        String body = login(username, password).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JsonNode json = objectMapper.readTree(body);
        return json.get("token").asText();
    }

    private org.springframework.test.web.servlet.ResultActions register(String username, String password) throws Exception {
        Map<String, String> request = Map.of(
                "username", username,
                "password", password,
                "email", username + "@example.com",
                "firstName", "Test",
                "lastName", "User");
        return mvc.perform(post("/api/v1/auth/register").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }

    private org.springframework.test.web.servlet.ResultActions login(String username, String password) throws Exception {
        return mvc.perform(post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("username", username, "password", password))));
    }

    private static String uniqueName() {
        return "user" + UUID.randomUUID().toString().substring(0, 8);
    }
}
