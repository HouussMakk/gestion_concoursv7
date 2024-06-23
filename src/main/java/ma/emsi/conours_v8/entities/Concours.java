package ma.emsi.conours_v8.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Concours {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Size(min = 4, max = 20)
    private String nom;
    private String reference;
    private String description;
    private int nbreEtudiantAdmisOrale;
    private int nbreEtudiantAdmisEcrit;
    private int nbreEtudiantAdmis;
    private int annee;
    @Temporal(TemporalType.DATE)
    @OneToMany
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Inscription> inscriptions;

    public Concours(String nom,String ref,String desc,int nbreEtudiantAdmisOrale,int nbreEtudiantAdmisEcrit,int nbreEtudiantAdmis,int anne)
    {
        this.nom = nom;
        this.reference = ref;
        this.description = desc;
        this.nbreEtudiantAdmisOrale = nbreEtudiantAdmisOrale;
        this.nbreEtudiantAdmisEcrit = nbreEtudiantAdmisEcrit;
        this.nbreEtudiantAdmis = nbreEtudiantAdmis;
        this.annee = anne;
    }
}
