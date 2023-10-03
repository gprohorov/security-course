package edu.pro.securitycourse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class AccessTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void init(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context).apply(springSecurity())
                .build();
    }
    @Test
    @WithAnonymousUser
    void unableGetAllIfNotAuthorized() throws Exception {
        mockMvc.perform(get("/api/v1/patients/"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void enableGetAllIfAuthorized() throws Exception {
        mockMvc.perform(get("/api/v1/patients/"))
                .andExpect(status().isOk());
    }
    @Test
    @WithAnonymousUser
    void enableGettingIndex_Html() throws Exception {
        mockMvc.perform(get("/index.html"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"User"})
    void disableGetAllIfNotAuthorized() throws Exception {
        mockMvc.perform(get("/api/v1/patients/"))
                .andExpect(status().isForbidden());
    }
        @Test
    @WithMockUser(username = "user", password = "user")
    void enableGetAllIfUserHasCreds() throws Exception {
        mockMvc.perform(get("/api/v2/patients/"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void disableGetAllIfHasNoRole() throws Exception {
        mockMvc.perform(get("/api/v2/patients/"))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser( roles = {"ADMIN"})
    void enableGetAllIfHasRole() throws Exception {
        mockMvc.perform(get("/api/v1/patients/"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "admin", password = "admin")
    void enableGetAllIfHasCreds() throws Exception {
        mockMvc.perform(get("/api/v2/patients/"))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(username = "admin1", password = "admin", roles = {"ADMIN"})
    void enableGetAllIfHasWrongCreds() throws Exception {
        mockMvc.perform(get("/api/v1/patients/"))
                .andExpect(status().isOk());
    }

}
