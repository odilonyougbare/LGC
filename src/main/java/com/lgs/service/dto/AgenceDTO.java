package com.lgs.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.lgs.domain.Agence} entity.
 */
public class AgenceDTO implements Serializable {
    
    private Long id;

    private String codeAgence;

    private String denominationAgence;

    private String typeAgence;

    private String telephone;

    private String numeroWhatsapp;

    private String email;

    private String autreContact;

    private String quartier;

    private String arrondissement;

    private String commune;

    private String province;

    private String region;


    private Long userId;

    private String userLogin;

    private Long clientId;

    private String clientDenomination;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeAgence() {
        return codeAgence;
    }

    public void setCodeAgence(String codeAgence) {
        this.codeAgence = codeAgence;
    }

    public String getDenominationAgence() {
        return denominationAgence;
    }

    public void setDenominationAgence(String denominationAgence) {
        this.denominationAgence = denominationAgence;
    }

    public String getTypeAgence() {
        return typeAgence;
    }

    public void setTypeAgence(String typeAgence) {
        this.typeAgence = typeAgence;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNumeroWhatsapp() {
        return numeroWhatsapp;
    }

    public void setNumeroWhatsapp(String numeroWhatsapp) {
        this.numeroWhatsapp = numeroWhatsapp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAutreContact() {
        return autreContact;
    }

    public void setAutreContact(String autreContact) {
        this.autreContact = autreContact;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientDenomination() {
        return clientDenomination;
    }

    public void setClientDenomination(String clientDenomination) {
        this.clientDenomination = clientDenomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AgenceDTO)) {
            return false;
        }

        return id != null && id.equals(((AgenceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AgenceDTO{" +
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
            ", userId=" + getUserId() +
            ", userLogin='" + getUserLogin() + "'" +
            ", clientId=" + getClientId() +
            ", clientDenomination='" + getClientDenomination() + "'" +
            "}";
    }
}
