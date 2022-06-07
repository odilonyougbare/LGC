package com.lgs.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.lgs.domain.Client} entity.
 */
public class ClientDTO implements Serializable {
    
    private Long id;

    private String codeClient;

    private String denomination;

    private String domaineActivite;

    private String siteWeb;

    private String compteFaceBook;

    private String compteTwitter;


    private Long userId;

    private String userLogin;
    
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientDTO)) {
            return false;
        }

        return id != null && id.equals(((ClientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientDTO{" +
            "id=" + getId() +
            ", codeClient='" + getCodeClient() + "'" +
            ", denomination='" + getDenomination() + "'" +
            ", domaineActivite='" + getDomaineActivite() + "'" +
            ", siteWeb='" + getSiteWeb() + "'" +
            ", compteFaceBook='" + getCompteFaceBook() + "'" +
            ", compteTwitter='" + getCompteTwitter() + "'" +
            ", userId=" + getUserId() +
            ", userLogin='" + getUserLogin() + "'" +
            "}";
    }
}
