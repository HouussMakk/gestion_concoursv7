package ma.emsi.conours_v8.repositories;

import ma.emsi.conours_v8.entities.Inscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    Page<Inscription> findByReference(String Keyword, Pageable pageable);

    @Query("select p from Inscription p where p.reference like :x")
    Page<Inscription> chercher(@Param("x") String Keyword, Pageable pageable);

}
