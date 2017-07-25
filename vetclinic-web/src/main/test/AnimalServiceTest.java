import app.SpringApp;
import dto.AnimalDto;
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
import services.AnimalService;
import services.ClientService;
import util.DateManager;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringApp.class})
@WebAppConfiguration
@TestPropertySource("/application.properties")
@Transactional
public class AnimalServiceTest {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private ClientService clientService;

    @Test
    public void add() throws ObjectAlreadyExistException {
        AnimalDto dto = new AnimalDto("testName", 10, "testDescr", DateManager.getCurrentSqlDate(), new ClientDto("testEmail@test"), null);
        AnimalDto added = animalService.add(dto);

        assertTrue(animalService.findById(added.getId()) != null);

    }

    @Test
    public void update() throws ObjectAlreadyExistException, ObjectNotFoundException {
        AnimalDto dto = new AnimalDto("testName", 10, "testDescr", DateManager.getCurrentSqlDate(), new ClientDto("testEmail@test"), null);
        AnimalDto added = animalService.add(dto);
        added.setAge(20);
        animalService.update(added);

        assertTrue(animalService.findById(added.getId()).getAge().equals(added.getAge()));
    }

    @Test
    public void delete()  {
        AnimalDto dto = new AnimalDto("testName", 10, "testDescr", DateManager.getCurrentSqlDate(), new ClientDto("testEmail@test"), null);
        AnimalDto added = animalService.add(dto);
        animalService.delete(added.getId());

        assertTrue(animalService.findById(added.getId()) == null);
    }
}
