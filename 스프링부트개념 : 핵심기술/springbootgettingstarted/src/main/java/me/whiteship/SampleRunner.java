package me.whiteship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(SampleRunner.class);

    @Value("${keesun.name}")
    private String name;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        logger.debug("======================");
        logger.debug(name);
        logger.debug("======================");
    }
}
