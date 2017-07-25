import enums.UserType;
import org.junit.Test;
import util.UserUtils;

import static org.junit.Assert.assertTrue;

public class UserUtilsTest {

    @Test
    public void defineUserTypeTest(){
        assertTrue(UserUtils.defineUserType("client@client.ru") == UserType.CLIENT);
        assertTrue(UserUtils.defineUserType("employee@vetclinic.ru") == UserType.EMPLOYEE);
    }
}
