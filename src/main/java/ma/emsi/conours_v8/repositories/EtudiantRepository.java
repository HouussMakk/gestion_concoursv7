package ma.emsi.conours_v8.repositories;

import ma.emsi.conours_v8.entities.Departement;
import ma.emsi.conours_v8.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Page<Etudiant> findByNomContains(String Keyword, Pageable pageable);

    @Query("select p from Etudiant p where p.nom like :x")
    Page<Etudiant> chercher(@Param("x") String Keyword, Pageable pageable);
}
