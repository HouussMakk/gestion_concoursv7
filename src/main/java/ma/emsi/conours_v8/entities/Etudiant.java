package ma.emsi.conours_v8.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.Date;
import java.util.List;

@Entity @Builder @NoArgsConstructor @AllArgsConstructor @Data
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cne;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private String nomLycée;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateNaissance;
    private double moyenneBac;
    private int anneeBac;
    private File imageBac;
    @ManyToOne
    private Diplome Diplome;
    @OneToMany
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Inscription> inscriptions;

}
