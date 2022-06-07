package com.lgs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_client")
    private String codeClient;

    @Column(name = "denomination")
    private String denomination;

    @Column(name = "domaine_activite")
    private String domaineActivite;

    @Column(name = "site_web")
    private String siteWeb;

    @Column(name = "compte_face_book")
    private String compteFaceBook;

    @Column(name = "compte_twitter")
    private String compteTwitter;

    @OneToMany(mappedBy = "client")
    private Set<Agence> agences = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "clients", allowSetters = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public Client codeClient(String codeClient) {
        this.codeClient = codeClient;
        return this;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public String getDenomination() {
        return denomination;
    }

    public Client denomination(String denomination) {
        this.denomination = denomination;
        return this;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDomaineActivite() {
        return domaineActivite;
    }

    public Client domaineActivite(String domaineActivite) {
        this.domaineActivite = domaineActivite;
        return this;
    }

    public void setDomaineActivite(String domaineActivite) {
        this.domaineActivite = domaineActivite;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public Client siteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
        return this;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getCompteFaceBook() {
        return compteFaceBook;
    }

    public Client compteFaceBook(String compteFaceBook) {
        this.compteFaceBook = compteFaceBook;
        return this;
    }

    public void setCompteFaceBook(String compteFaceBook) {
        this.compteFaceBook = compteFaceBook;
    }

    public String getCompteTwitter() {
        return compteTwitter;
    }

    public Client compteTwitter(String compteTwitter) {
        this.compteTwitter = compteTwitter;
        return this;
    }

    public void setCompteTwitter(String compteTwitter) {
        this.compteTwitter = compteTwitter;
    }

    public Set<Agence> getAgences() {
        return agences;
    }

    public Client agences(Set<Agence> agences) {
        this.agences = agences;
        return this;
    }

    public Client addAgence(Agence agence) {
        this.agences.add(agence);
        agence.setClient(this);
        return this;
    }

    public Client removeAgence(Agence agence) {
        this.agences.remove(agence);
        agence.setClient(null);
        return this;
    }

    public void setAgences(Set<Agence> agences) {
        this.agences = agences;
    }

    public User getUser() {
        return user;
    }

    public Client user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", codeClient='" + getCodeClient() + "'" +
            ", denomination='" + getDenomination() + "'" +
            ", domaineActivite='" + getDomaineActivite() + "'" +
            ", siteWeb='" + getSiteWeb() + "'" +
            ", compteFaceBook='" + getCompteFaceBook() + "'" +
            ", compteTwitter='" + getCompteTwitter() + "'" +
            "}";
    }
}
