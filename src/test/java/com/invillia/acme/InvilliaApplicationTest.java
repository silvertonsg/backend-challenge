package com.invillia.acme;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import org.springframework.test.context.ActiveProfiles;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= DEFINED_PORT, classes = InvilliaApplication.class)
@ActiveProfiles("test")
public class InvilliaApplicationTest {
    
    @Test
    public void loadContext(){
        
    }

}
