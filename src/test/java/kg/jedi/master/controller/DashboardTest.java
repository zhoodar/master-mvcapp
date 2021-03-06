package kg.jedi.master.controller;

import kg.jedi.master.common.PathNames;
import kg.jedi.master.repository.MasterRepository;
import kg.jedi.master.repository.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Jedi on 1/23/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class DashboardTest {

    private MockMvc mockMvc;

    @Mock
    private MasterRepository masterRepositoryMock;

    @Mock
    private ReviewRepository reviewRepository;

    private DashboardController controllerUnderTest;

    @Before
    public void setUp() throws Exception {
        this.controllerUnderTest = new DashboardController(masterRepositoryMock, reviewRepository);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(controllerUnderTest)
                .build();
    }

    @Test
    public void index_noArgs_shouldForwardToIndexPage() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/")).andExpect(status().isOk()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertThat(response.getForwardedUrl()).isEqualTo(PathNames.INDEX_PAGE);
    }

}