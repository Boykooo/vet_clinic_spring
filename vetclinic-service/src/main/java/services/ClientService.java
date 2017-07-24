package services;

import dao.SequenceDao;
import dto.AnimalDto;
import dto.ClientDto;
import entities.Animal;
import entities.Client;
import enums.RequestStatus;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import forms.ClientRequestForm;
import entities.issue.Issue;
import entities.issue.IssueInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import dao.ClientDao;
import dao.IssueDao;
import util.DateManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


@Service
public class ClientService implements GenericService<ClientDto, String> {

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private AnimalService animalService;
    @Autowired
    private IssueDao issueDao;
    @Autowired
    private SequenceDao sequenceDao;
    @Autowired
    private MongoTemplate mongoTemplate;

    private final String seqName = "issueInfo";


    public List<ClientDto> findAll() {
        List<ClientDto> users = new ArrayList<ClientDto>();
        clientDao.findAll().stream().forEach(
                (Client client) -> users.add(convertToDto(client))
        );

        return users;
    }

    public ClientDto findById(String key) {
        return convertToDto(clientDao.findOne(key));
    }

    public void add(ClientDto clientDto) throws ObjectAlreadyExistException {
        Client client = clientDao.findOne(clientDto.getEmail());
        if (client == null) {
            clientDao.save(convertToEntity(clientDto));
        } else {
            throw new ObjectAlreadyExistException();
        }

    }

    public void update(ClientDto clientDto) throws ObjectNotFoundException {
        Client client = clientDao.findOne(clientDto.getEmail());

        if (client != null) {

            clientDto.setRegDate(client.getRegDate());
            clientDao.save(convertToEntity(clientDto));
        } else {
            throw new ObjectNotFoundException();
        }

    }

    public void delete(String key) {
        clientDao.delete(key);
    }

    public IssueInfo findLastClientRequest(String email) {

        GroupOperation groupOpt = group("history");
        MatchOperation matchOpt = match(new Criteria("clientEmail").is(email));
        SortOperation sortOpt = sort(new Sort(Sort.Direction.DESC, "_id.requestDate"));
        UnwindOperation unwind = Aggregation.unwind("history");

        Aggregation aggregation = Aggregation.newAggregation(matchOpt, unwind, groupOpt, sortOpt);

        AggregationResults<IssueInfo> aggregate = mongoTemplate.aggregate(aggregation, "request", IssueInfo.class);

        List<IssueInfo> mappedResults = aggregate.getMappedResults();

        return mappedResults.size() > 0
                ? aggregate.getMappedResults().get(0)
                : null;
    }

    public void addRequest(ClientRequestForm requestForm) {

        AnimalDto foundAnimal = animalService.findById(requestForm.animalId);

        if (foundAnimal.getPatient().getEmployee() != null) {
            requestForm.employeeEmail = foundAnimal.getPatient().getEmployee().getEmail();

            IssueInfo info = new IssueInfo(sequenceDao.getNextSequenceId(seqName), requestForm.header, requestForm.description,
                    requestForm.employeeEmail, DateManager.getCurrentDate(), new ArrayList<>(), RequestStatus.OPEN);

            Issue issue = issueDao.findByAnimalId(requestForm.animalId, null);
            if (issue != null) {
                issue.addRequestInfo(info);
            } else {
                issue = new Issue(requestForm.animalId, requestForm.clientEmail, info);
            }

            issueDao.save(issue);
        }

    }

    public void closeRequest() {

    }

    @Override
    public List<ClientDto> getLimit(Integer startPage, Integer amount) {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }

    private ClientDto convertToDto(Client client) {
        ClientDto clientDto = convertToDtoWithoutDepend(client);

        List<AnimalDto> animals = new ArrayList<>();
        client.getAnimals().stream().forEach(
                (Animal animal) -> animals.add(animalService.convertToDto(animal))
        );

        clientDto.setAnimals(animals);

        return clientDto;
    }

    public ClientDto convertToDtoWithoutDepend(Client client) {
        ClientDto clientDto = new ClientDto();
        if (client != null) {
            clientDto.setEmail(client.getEmail());
            clientDto.setFirstName(client.getFirstName());
            clientDto.setLastName(client.getLastName());
            clientDto.setPassword(client.getPassword());
            clientDto.setPhoneNumber(client.getPhoneNumber());
            clientDto.setRegDate(client.getRegDate());
        }

        return clientDto;
    }

    public Client convertToEntity(ClientDto dto) {
        Client client = new Client();
        client.setEmail(dto.getEmail());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        client.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getRegDate() == null) {
            java.util.Date currDate = new java.util.Date();
            client.setRegDate(new Date(currDate.getTime()));
        } else {
            client.setRegDate(dto.getRegDate());
        }

        return client;
    }
}
