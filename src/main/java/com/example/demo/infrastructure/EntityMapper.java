package com.example.demo.infrastructure;

import com.example.demo.client.data.AddressDTO;
import com.example.demo.client.domain.Address;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EntityMapper {
    private final ModelMapper modelMapper;



    public Address mapToAddress(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }
}
