import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
@WebAppConfiguration
@Slf4j
public class TestMocMvc {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void test() throws Exception {
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/mvc/str").param("name", "123"));
        MvcResult mvcResult = perform
                                     .andExpect(MockMvcResultMatchers.view().name("hello"))
                                     .andExpect(MockMvcResultMatchers.status().isOk())
                                     .andDo(MockMvcResultHandlers.print())
                                     .andReturn();
        log.info("========================================{}", mvcResult.getHandler());
    }

}
