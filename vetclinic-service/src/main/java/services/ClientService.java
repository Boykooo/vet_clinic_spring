package services;

import dto.AnimalDto;
import dto.ClientDto;
import entities.Animal;
import entities.Client;
import exceptions.ObjectAlreadyExistException;
import exceptions.ObjectNotFoundException;
import forms.ClientRequestForm;
import mongoEntities.ClientRequest;
import mongoEntities.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repository.ClientRepository;
import repository.ClientRequestRepository;
import util.DateManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Service
public class ClientService implements GenericService<ClientDto, String> {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AnimalService animalService;
    @Autowired
    private ClientRequestRepository clientRequestRepository;

    public List<ClientDto> findAll() {
        List<ClientDto> users = new ArrayList<ClientDto>();
        clientRepository.findAll().stream().forEach(
                (Client client) -> users.add(convertToDto(client))
        );

        return users;
    }

    public ClientDto findById(String key) {
        return convertToDto(clientRepository.findOne(key));
    }

    public void add(ClientDto clientDto) throws ObjectAlreadyExistException {
        Client client = clientRepository.findOne(clientDto.getEmail());
        if (client == null) {
            clientRepository.save(convertToEntity(clientDto));
        } else {
            throw new ObjectAlreadyExistException();
        }

    }

    public void update(ClientDto clientDto) throws ObjectNotFoundException {
        Client client = clientRepository.findOne(clientDto.getEmail());

        if (client != null) {

            clientDto.setRegDate(client.getRegDate());
            clientRepository.save(convertToEntity(clientDto));
        } else {
            throw new ObjectNotFoundException();
        }

    }

    public void delete(String key) {
        clientRepository.delete(key);
    }

    public ClientRequest findLastClientRequest(String email) {
        return clientRequestRepository.findLastClientRequest(
                email,
                new Sort(Sort.Direction.DESC, "history")
        );
    }

    public void addRequest(ClientRequestForm requestForm) {

        requestForm.employeeEmail = animalService.findById(requestForm.animalId)
                .getPatient()
                .getEmployee()
                .getEmail();

        RequestInfo info = new RequestInfo(requestForm.header, requestForm.description, requestForm.employeeEmail,
                DateManager.getCurrentFormattedDate(), new ArrayList<>());

        ClientRequest clientRequest = clientRequestRepository.findByAnimalId(requestForm.animalId);
        if (clientRequest != null) {
            clientRequest.addRequestInfo(info);
        } else {
            clientRequest = new ClientRequest(requestForm.animalId, requestForm.clientEmail, info);
        }

        clientRequestRepository.save(clientRequest);

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
