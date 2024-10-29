package br.com.fujideia.iesp.tecback.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
@Slf4j
public class CacheController {
    private final CacheManager cacheManager;

    @GetMapping
    @Cacheable("CACHE_HELLO")
    public String hello() {
        log.info("Hello");
        return "Hell-o";
    }
}
