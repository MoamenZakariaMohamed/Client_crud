package com.example.demo.client.service;

import com.example.demo.infrastructure.EntityMapper;
import com.example.demo.infrastructure.exceptions.DataHasNotChangedException;
import com.example.demo.infrastructure.exceptions.DuplicateResourceException;
import com.example.demo.infrastructure.exceptions.NotFoundException;
import com.example.demo.infrastructure.globalResponse.GlobalResponse;
import com.example.demo.client.data.SaveClientDTO;
import com.example.demo.client.domain.Address;
import com.example.demo.client.domain.Client;
import com.example.demo.client.repository.ClientRepository;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientWriteServiceImple implements ClientWriteService {
    private final ClientRepository clientRepository;
    private final EntityMapper entityMapper;


    @Override
    public GlobalResponse saveClient(SaveClientDTO formRequest) {
        List<Address> addressList = formRequest.getAddressDTO().stream()
                .map(entityMapper::mapToAddress)
                .collect(Collectors.toList());
        Client client = Client.createClient(formRequest, addressList);

        try {
            Client submitForm = clientRepository.save(client);
            return new GlobalResponse().builder().code(200).data(submitForm).message("تم التسجيل بنجاح ").build();
        } catch (
                DataIntegrityViolationException e) {
            throw new DuplicateResourceException(e.getMessage());
        }
    }

    @Override
    public GlobalResponse updateClient(SaveClientDTO updateRequest , Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("this client not found" + clientId));
        List<Address> addressList = updateRequest.getAddressDTO().stream()
                .map(entityMapper::mapToAddress)
                .collect(Collectors.toList());

        boolean changes = false;
        changes = Client.updateIfNotSame(changes, updateRequest, addressList, client );
        if (!changes) {
            throw new DataHasNotChangedException("لم يوجد اي تغيير في البيانات ");
        }

        try {
            Client submitForm = clientRepository.save(client);
            return new GlobalResponse().builder().code(200).data(submitForm).message("تم التعديل بنجاح ").build();

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException(e.getMessage());        }
    }

}
