package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.InfoService;

import java.util.stream.IntStream;

@RestController
public class InfoController {

    private final InfoService infoService;

    private static final Logger logger = LoggerFactory.getLogger(InfoService.class);

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping(path = "/getPort")   //GET http://localhost:8080/getPort
    public ResponseEntity<Integer> getPortInfo() {
        return ResponseEntity.ok(infoService.portNumber());
    }

    @GetMapping(path = "/getValue")   //GET http://localhost:8080/getValue
    public ResponseEntity<Integer> getValue() {
        long startTime = System.currentTimeMillis();

        int sum = IntStream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, Integer::sum);

        long endTime = System.currentTimeMillis();
        logger.warn("Метод работал " + (endTime - startTime) + "ms");

        return ResponseEntity.ok(sum);
    }
}
