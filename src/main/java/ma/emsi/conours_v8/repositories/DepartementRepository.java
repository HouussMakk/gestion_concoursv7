package ma.emsi.conours_v8.repositories;

import ma.emsi.conours_v8.entities.Concours;
import ma.emsi.conours_v8.entities.Departement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {
    Page<Departement> findByNomContains(String Keyword, Pageable pageable);

    @Query("select p from Departement p where p.nom like :x")
    Page<Departement> chercher(@Param("x") String Keyword, Pageable pageable);
}
