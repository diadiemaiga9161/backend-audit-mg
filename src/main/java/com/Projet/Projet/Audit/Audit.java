package com.Projet.Projet.Audit;


import com.Projet.Projet.Audit.TypeAudit.TypeAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "audit")
@NoArgsConstructor
@AllArgsConstructor
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tauxconformite;
    private String datedebut;
    private String datefin;
    private int nonconformite;

    @OneToOne
    @JoinColumn(name = "id_typeAudit")
    private TypeAudit typeAudit;
}
