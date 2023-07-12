package com.crud.client.client.api;

import com.crud.client.client.data.SaveClientDTO;
import com.crud.client.client.domain.Client;
import com.crud.client.client.repository.ClientRepository;
import com.crud.client.client.service.ClientWriteService;
import com.crud.client.infrastructure.exceptions.NotFoundException;
import com.crud.client.infrastructure.globalResponse.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client")
@Validated

public class ClientApi {
    private final ClientWriteService clientWriteService;
    private final ClientRepository clientRepository;



    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    public GlobalResponse saveClient(@Valid @RequestBody SaveClientDTO formRequest ) {
        return clientWriteService.saveClient(formRequest);
    }


    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public GlobalResponse updateClient(@Valid @RequestBody SaveClientDTO updateRequest, @PathVariable("id") Long reportId) {
        return  clientWriteService.updateClient(updateRequest, reportId);
    }


    @GetMapping("all")
    public GlobalResponse reportsDetail() throws Exception {
       Collection<Client> client = clientRepository.findAll();
        return new GlobalResponse().builder().code(200).data(client).message("تم  بنجاح ").build();

    }

    @GetMapping("{id}")
    public GlobalResponse reportDetail(@PathVariable Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("this client not found" + id));
        return new GlobalResponse().builder().code(200).data(client).message("تم  بنجاح ").build();

    }

}
