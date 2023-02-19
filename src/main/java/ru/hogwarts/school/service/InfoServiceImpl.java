package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    @Value("8080")
    private Integer serverPort;

    @Override
    public Integer portNumber() {
        return serverPort;
    }
}
