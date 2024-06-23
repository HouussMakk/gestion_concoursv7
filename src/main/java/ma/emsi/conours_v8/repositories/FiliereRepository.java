package ma.emsi.conours_v8.repositories;

import ma.emsi.conours_v8.entities.Filiere;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FiliereRepository extends JpaRepository<Filiere ,Long> {
    Page<Filiere> findByNomContains(String Keyword, Pageable pageable);

    @Query("select p from Filiere p where p.nom like :x")
    Page<Filiere> chercher(@Param("x") String Keyword, Pageable pageable);

}

