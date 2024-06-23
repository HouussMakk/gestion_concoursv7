package ma.emsi.conours_v8;

import ma.emsi.conours_v8.entities.Concours;
import ma.emsi.conours_v8.entities.Diplome;
import ma.emsi.conours_v8.repositories.ConcoursRepository;
import ma.emsi.conours_v8.repositories.DiplomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ConoursV8Application implements CommandLineRunner {
    @Autowired
    DiplomeRepository diplomeRepository;
    @Autowired
    ConcoursRepository concoursRepository;


    public static void main(String[] args) {
        SpringApplication.run(ConoursV8Application.class, args);
    }

    @Override
    public void run(String... args) {
        Concours c1 = new Concours("INFO IT","ref1011","Dev WEb,",0,0,0,2023);
        Concours c2 = new Concours("Finance","ref1013","Comptablity,",0,0,0,2023);
        concoursRepository.save(c1);
        concoursRepository.save(c2);
    }




}
