import app.SpringApp;
import dto.ClientDto;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import services.ClientService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringApp.class})
@WebAppConfiguration
@TestPropertySource("/application.properties")
@Transactional
public class ClientServiceTest  {

    @Autowired
    private ClientService clientService;

    @Test
    public void add() throws ObjectAlreadyExistException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse("2017-07-24");
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        ClientDto dto = new ClientDto("testEmail@test", "testPassword", "testPhoneNumber", "testFirstName", "testLastName", sql);
        clientService.add(dto);

        assertTrue(clientService.findById(dto.getEmail()) != null);
    }

    @Test
    public void update() throws ObjectAlreadyExistException, ParseException, ObjectNotFoundException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse("2017-07-24");
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        ClientDto dto = new ClientDto("testEmail@test", "testPassword", "testPhoneNumber", "testFirstName", "testLastName", sql);

        clientService.add(dto);
        dto.setPhoneNumber("newPhoneNumber");
        clientService.update(dto);

        assertTrue(clientService.findById(dto.getEmail()).getPhoneNumber().equals("newPhoneNumber"));
    }

    @Test
    public void delete() throws ObjectAlreadyExistException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = format.parse("2017-07-24");
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        ClientDto dto = new ClientDto("testEmail@test", "testPassword", "testPhoneNumber", "testFirstName", "testLastName", sql);

        clientService.add(dto);
        clientService.delete(dto.getEmail());

        assertTrue(clientService.findById(dto.getEmail()) == null);
    }

}
