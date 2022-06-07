package com.lgs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Agence.
 */
@Entity
@Table(name = "agence")
public class Agence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_agence")
    private String codeAgence;

    @Column(name = "denomination_agence")
    private String denominationAgence;

    @Column(name = "type_agence")
    private String typeAgence;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "numero_whatsapp")
    private String numeroWhatsapp;

    @Column(name = "email")
    private String email;

    @Column(name = "autre_contact")
    private String autreContact;

    @Column(name = "quartier")
    private String quartier;

    @Column(name = "arrondissement")
    private String arrondissement;

    @Column(name = "commune")
    private String commune;

    @Column(name = "province")
    private String province;

    @Column(name = "region")
    private String region;

    @ManyToOne
    @JsonIgnoreProperties(value = "agences", allowSetters = true)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties(value = "agences", allowSetters = true)
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeAgence() {
        return codeAgence;
    }

    public Agence codeAgence(String codeAgence) {
        this.codeAgence = codeAgence;
        return this;
    }

    public void setCodeAgence(String codeAgence) {
        this.codeAgence = codeAgence;
    }

    public String getDenominationAgence() {
        return denominationAgence;
    }

    public Agence denominationAgence(String denominationAgence) {
        this.denominationAgence = denominationAgence;
        return this;
    }

    public void setDenominationAgence(String denominationAgence) {
        this.denominationAgence = denominationAgence;
    }

    public String getTypeAgence() {
        return typeAgence;
    }

    public Agence typeAgence(String typeAgence) {
        this.typeAgence = typeAgence;
        return this;
    }

    public void setTypeAgence(String typeAgence) {
        this.typeAgence = typeAgence;
    }

    public String getTelephone() {
        return telephone;
    }

    public Agence telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNumeroWhatsapp() {
        return numeroWhatsapp;
    }

    public Agence numeroWhatsapp(String numeroWhatsapp) {
        this.numeroWhatsapp = numeroWhatsapp;
        return this;
    }

    public void setNumeroWhatsapp(String numeroWhatsapp) {
        this.numeroWhatsapp = numeroWhatsapp;
    }

    public String getEmail() {
        return email;
    }

    public Agence email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAutreContact() {
        return autreContact;
    }

    public Agence autreContact(String autreContact) {
        this.autreContact = autreContact;
        return this;
    }

    public void setAutreContact(String autreContact) {
        this.autreContact = autreContact;
    }

    public String getQuartier() {
        return quartier;
    }

    public Agence quartier(String quartier) {
        this.quartier = quartier;
        return this;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getArrondissement() {
        return arrondissement;
    }

    public Agence arrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
        return this;
    }

    public void setArrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
    }

    public String getCommune() {
        return commune;
    }

    public Agence commune(String commune) {
        this.commune = commune;
        return this;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getProvince() {
        return province;
    }

    public Agence province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return region;
    }

    public Agence region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public User getUser() {
        return user;
    }

    public Agence user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public Agence client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agence)) {
            return false;
        }
        return id != null && id.equals(((Agence) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Agence{" +
            "id=" + getId() +
            ", codeAgence='" + getCodeAgence() + "'" +
            ", denominationAgence='" + getDenominationAgence() + "'" +
            ", typeAgence='" + getTypeAgence() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", numeroWhatsapp='" + getNumeroWhatsapp() + "'" +
            ", email='" + getEmail() + "'" +
            ", autreContact='" + getAutreContact() + "'" +
            ", quartier='" + getQuartier() + "'" +
            ", arrondissement='" + getArrondissement() + "'" +
            ", commune='" + getCommune() + "'" +
            ", province='" + getProvince() + "'" +
            ", region='" + getRegion() + "'" +
            "}";
    }
}
