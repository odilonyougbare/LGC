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
 * Criteria class for the {@link com.lgs.domain.Client} entity. This class is used
 * in {@link com.lgs.web.rest.ClientResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /clients?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ClientCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter codeClient;

    private StringFilter denomination;

    private StringFilter domaineActivite;

    private StringFilter siteWeb;

    private StringFilter compteFaceBook;

    private StringFilter compteTwitter;

    private LongFilter agenceId;

    private LongFilter userId;

    public ClientCriteria() {
    }

    public ClientCriteria(ClientCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.codeClient = other.codeClient == null ? null : other.codeClient.copy();
        this.denomination = other.denomination == null ? null : other.denomination.copy();
        this.domaineActivite = other.domaineActivite == null ? null : other.domaineActivite.copy();
        this.siteWeb = other.siteWeb == null ? null : other.siteWeb.copy();
        this.compteFaceBook = other.compteFaceBook == null ? null : other.compteFaceBook.copy();
        this.compteTwitter = other.compteTwitter == null ? null : other.compteTwitter.copy();
        this.agenceId = other.agenceId == null ? null : other.agenceId.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
    }

    @Override
    public ClientCriteria copy() {
        return new ClientCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(StringFilter codeClient) {
        this.codeClient = codeClient;
    }

    public StringFilter getDenomination() {
        return denomination;
    }

    public void setDenomination(StringFilter denomination) {
        this.denomination = denomination;
    }

    public StringFilter getDomaineActivite() {
        return domaineActivite;
    }

    public void setDomaineActivite(StringFilter domaineActivite) {
        this.domaineActivite = domaineActivite;
    }

    public StringFilter getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(StringFilter siteWeb) {
        this.siteWeb = siteWeb;
    }

    public StringFilter getCompteFaceBook() {
        return compteFaceBook;
    }

    public void setCompteFaceBook(StringFilter compteFaceBook) {
        this.compteFaceBook = compteFaceBook;
    }

    public StringFilter getCompteTwitter() {
        return compteTwitter;
    }

    public void setCompteTwitter(StringFilter compteTwitter) {
        this.compteTwitter = compteTwitter;
    }

    public LongFilter getAgenceId() {
        return agenceId;
    }

    public void setAgenceId(LongFilter agenceId) {
        this.agenceId = agenceId;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ClientCriteria that = (ClientCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(codeClient, that.codeClient) &&
            Objects.equals(denomination, that.denomination) &&
            Objects.equals(domaineActivite, that.domaineActivite) &&
            Objects.equals(siteWeb, that.siteWeb) &&
            Objects.equals(compteFaceBook, that.compteFaceBook) &&
            Objects.equals(compteTwitter, that.compteTwitter) &&
            Objects.equals(agenceId, that.agenceId) &&
            Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        codeClient,
        denomination,
        domaineActivite,
        siteWeb,
        compteFaceBook,
        compteTwitter,
        agenceId,
        userId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (codeClient != null ? "codeClient=" + codeClient + ", " : "") +
                (denomination != null ? "denomination=" + denomination + ", " : "") +
                (domaineActivite != null ? "domaineActivite=" + domaineActivite + ", " : "") +
                (siteWeb != null ? "siteWeb=" + siteWeb + ", " : "") +
                (compteFaceBook != null ? "compteFaceBook=" + compteFaceBook + ", " : "") +
                (compteTwitter != null ? "compteTwitter=" + compteTwitter + ", " : "") +
                (agenceId != null ? "agenceId=" + agenceId + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
            "}";
    }

}
