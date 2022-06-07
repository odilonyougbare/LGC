package com.lgs.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.lgs.domain.Agence} entity. This class is used
 * in {@link com.lgs.web.rest.AgenceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /agences?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AgenceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter codeAgence;

    private StringFilter denominationAgence;

    private StringFilter typeAgence;

    private StringFilter telephone;

    private StringFilter numeroWhatsapp;

    private StringFilter email;

    private StringFilter autreContact;

    private StringFilter quartier;

    private StringFilter arrondissement;

    private StringFilter commune;

    private StringFilter province;

    private StringFilter region;

    private LongFilter userId;

    private LongFilter clientId;

    public AgenceCriteria() {
    }

    public AgenceCriteria(AgenceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.codeAgence = other.codeAgence == null ? null : other.codeAgence.copy();
        this.denominationAgence = other.denominationAgence == null ? null : other.denominationAgence.copy();
        this.typeAgence = other.typeAgence == null ? null : other.typeAgence.copy();
        this.telephone = other.telephone == null ? null : other.telephone.copy();
        this.numeroWhatsapp = other.numeroWhatsapp == null ? null : other.numeroWhatsapp.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.autreContact = other.autreContact == null ? null : other.autreContact.copy();
        this.quartier = other.quartier == null ? null : other.quartier.copy();
        this.arrondissement = other.arrondissement == null ? null : other.arrondissement.copy();
        this.commune = other.commune == null ? null : other.commune.copy();
        this.province = other.province == null ? null : other.province.copy();
        this.region = other.region == null ? null : other.region.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.clientId = other.clientId == null ? null : other.clientId.copy();
    }

    @Override
    public AgenceCriteria copy() {
        return new AgenceCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCodeAgence() {
        return codeAgence;
    }

    public void setCodeAgence(StringFilter codeAgence) {
        this.codeAgence = codeAgence;
    }

    public StringFilter getDenominationAgence() {
        return denominationAgence;
    }

    public void setDenominationAgence(StringFilter denominationAgence) {
        this.denominationAgence = denominationAgence;
    }

    public StringFilter getTypeAgence() {
        return typeAgence;
    }

    public void setTypeAgence(StringFilter typeAgence) {
        this.typeAgence = typeAgence;
    }

    public StringFilter getTelephone() {
        return telephone;
    }

    public void setTelephone(StringFilter telephone) {
        this.telephone = telephone;
    }

    public StringFilter getNumeroWhatsapp() {
        return numeroWhatsapp;
    }

    public void setNumeroWhatsapp(StringFilter numeroWhatsapp) {
        this.numeroWhatsapp = numeroWhatsapp;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getAutreContact() {
        return autreContact;
    }

    public void setAutreContact(StringFilter autreContact) {
        this.autreContact = autreContact;
    }

    public StringFilter getQuartier() {
        return quartier;
    }

    public void setQuartier(StringFilter quartier) {
        this.quartier = quartier;
    }

    public StringFilter getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(StringFilter arrondissement) {
        this.arrondissement = arrondissement;
    }

    public StringFilter getCommune() {
        return commune;
    }

    public void setCommune(StringFilter commune) {
        this.commune = commune;
    }

    public StringFilter getProvince() {
        return province;
    }

    public void setProvince(StringFilter province) {
        this.province = province;
    }

    public StringFilter getRegion() {
        return region;
    }

    public void setRegion(StringFilter region) {
        this.region = region;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getClientId() {
        return clientId;
    }

    public void setClientId(LongFilter clientId) {
        this.clientId = clientId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AgenceCriteria that = (AgenceCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(codeAgence, that.codeAgence) &&
            Objects.equals(denominationAgence, that.denominationAgence) &&
            Objects.equals(typeAgence, that.typeAgence) &&
            Objects.equals(telephone, that.telephone) &&
            Objects.equals(numeroWhatsapp, that.numeroWhatsapp) &&
            Objects.equals(email, that.email) &&
            Objects.equals(autreContact, that.autreContact) &&
            Objects.equals(quartier, that.quartier) &&
            Objects.equals(arrondissement, that.arrondissement) &&
            Objects.equals(commune, that.commune) &&
            Objects.equals(province, that.province) &&
            Objects.equals(region, that.region) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        codeAgence,
        denominationAgence,
        typeAgence,
        telephone,
        numeroWhatsapp,
        email,
        autreContact,
        quartier,
        arrondissement,
        commune,
        province,
        region,
        userId,
        clientId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AgenceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (codeAgence != null ? "codeAgence=" + codeAgence + ", " : "") +
                (denominationAgence != null ? "denominationAgence=" + denominationAgence + ", " : "") +
                (typeAgence != null ? "typeAgence=" + typeAgence + ", " : "") +
                (telephone != null ? "telephone=" + telephone + ", " : "") +
                (numeroWhatsapp != null ? "numeroWhatsapp=" + numeroWhatsapp + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (autreContact != null ? "autreContact=" + autreContact + ", " : "") +
                (quartier != null ? "quartier=" + quartier + ", " : "") +
                (arrondissement != null ? "arrondissement=" + arrondissement + ", " : "") +
                (commune != null ? "commune=" + commune + ", " : "") +
                (province != null ? "province=" + province + ", " : "") +
                (region != null ? "region=" + region + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (clientId != null ? "clientId=" + clientId + ", " : "") +
            "}";
    }

}
