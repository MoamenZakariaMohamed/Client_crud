package com.example.demo.client.service;

import com.example.demo.infrastructure.globalResponse.GlobalResponse;
import com.example.demo.client.data.SaveClientDTO;

public interface ClientWriteService {
    GlobalResponse saveClient(SaveClientDTO formRequest );
    GlobalResponse updateClient(SaveClientDTO formRequest , Long clientId );

}
