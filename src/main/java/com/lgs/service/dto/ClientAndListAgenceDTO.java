package com.lgs.service.dto;
import java.io.Serializable;
import java.util.List;

public class ClientAndListAgenceDTO implements Serializable {
    private Long id;

    private String codeClient;

    private String denomination;

    private String domaineActivite;

    private String siteWeb;

    private String compteFaceBook;

    private String compteTwitter;

    private List<AgenceDTO> agences;

    public ClientAndListAgenceDTO(){}

    public ClientAndListAgenceDTO(String codeClient, String denomination, String domaineActivite, String siteWeb, String compteFaceBook, String compteTwitter, List<AgenceDTO> agences) {
        this.codeClient = codeClient;
        this.denomination = denomination;
        this.domaineActivite = domaineActivite;
        this.siteWeb = siteWeb;
        this.compteFaceBook = compteFaceBook;
        this.compteTwitter = compteTwitter;
        this.agences = agences;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDomaineActivite() {
        return domaineActivite;
    }

    public void setDomaineActivite(String domaineActivite) {
        this.domaineActivite = domaineActivite;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getCompteFaceBook() {
        return compteFaceBook;
    }

    public void setCompteFaceBook(String compteFaceBook) {
        this.compteFaceBook = compteFaceBook;
    }

    public String getCompteTwitter() {
        return compteTwitter;
    }

    public void setCompteTwitter(String compteTwitter) {
        this.compteTwitter = compteTwitter;
    }

    public List<AgenceDTO> getAgences() {
        return agences;
    }

    public void setAgences(List<AgenceDTO> agences) {
        this.agences = agences;
    }

    @Override
    public String toString() {
        return "ClientAndListAgenceDTO{" +
            "id=" + id +
            ", codeClient='" + codeClient + '\'' +
            ", denomination='" + denomination + '\'' +
            ", domaineActivite='" + domaineActivite + '\'' +
            ", siteWeb='" + siteWeb + '\'' +
            ", compteFaceBook='" + compteFaceBook + '\'' +
            ", compteTwitter='" + compteTwitter + '\'' +
            ", agences=" + agences +
            '}';
    }
}
