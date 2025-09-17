package br.com.lab;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public ResponseEntity<String> get() throws InterruptedException {
        var uuid = UUID.randomUUID();
        var start = LocalDateTime.now();
        log.info("Iniciando request: {}", uuid);
        Thread.sleep(5000);
        log.info("Finalizando request: {} - Duração: {}", uuid, ChronoUnit.SECONDS.between(start, LocalDateTime.now()));
        return ResponseEntity.ok("OK");
    }

}
