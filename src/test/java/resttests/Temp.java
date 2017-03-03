package resttests;

import com.epam.springapp.dataModel.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class Temp {

    RestTemplate restTemplate;

    @BeforeClass
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testSometest() {
        restTemplate.getForObject("", User.class);
    }

}
