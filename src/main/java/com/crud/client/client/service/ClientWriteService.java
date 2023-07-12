package com.crud.client.client.service;

import com.crud.client.client.data.SaveClientDTO;
import com.crud.client.infrastructure.globalResponse.GlobalResponse;

public interface ClientWriteService {
    GlobalResponse saveClient(SaveClientDTO formRequest );
    GlobalResponse updateClient(SaveClientDTO formRequest , Long clientId );

}
