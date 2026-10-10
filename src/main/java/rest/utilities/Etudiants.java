package rest.utilities;

import entities.Etudiant;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import java.util.List;
import java.util.ArrayList;

@XmlRootElement(name = "etudiants")
@XmlAccessorType(XmlAccessType.FIELD)
public class Etudiants {

    @XmlElement(name = "etudiant")
    private List<EtudiantXml> etudiants = new ArrayList<>();

    public Etudiants() {
    }

    public Etudiants(List<Etudiant> liste) {
        for (Etudiant etudiant : liste) {
            etudiants.add(new EtudiantXml(etudiant));
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {
            "anneeEtude", "email", "identifiant", "nom", "prenom"
    })
    public static class EtudiantXml {

        private int anneeEtude;
        private String email;
        private String identifiant;
        private String nom;
        private String prenom;

        public EtudiantXml() {
        }

        public EtudiantXml(Etudiant etudiant) {
            this.anneeEtude = etudiant.getAnneeEtude();
            this.email = etudiant.getEmail();
            this.identifiant = etudiant.getIdentifiant();
            this.nom = etudiant.getNom();
            this.prenom = etudiant.getPrenom();
        }
    }
}