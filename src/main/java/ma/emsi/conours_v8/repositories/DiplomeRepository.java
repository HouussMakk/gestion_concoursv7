package ma.emsi.conours_v8.repositories;

import ma.emsi.conours_v8.entities.Concours;
import ma.emsi.conours_v8.entities.Diplome;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiplomeRepository extends JpaRepository<Diplome,Long> {
    Page<Diplome> findByNomContains(String Keyword, Pageable pageable);

    @Query("select p from Diplome p where p.nom like :x")
    Page<Diplome> chercher(@Param("x") String Keyword, Pageable pageable);
}
