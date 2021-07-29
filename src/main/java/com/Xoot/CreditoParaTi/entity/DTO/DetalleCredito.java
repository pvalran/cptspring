package com.Xoot.CreditoParaTi.entity.DTO;

import java.util.List;

public class DetalleCredito {
    private CustomerDTO customer;
    private List<DocumentDTO> documents;
    private AdditionalInformationDTO additionalies;
    private List<EconomicDependientiesDto> dependents;
    private SpouseDTO spouse;
    private WorkDTO work;
    private List<ReferenceDTO> references;

    public List<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }

    public AdditionalInformationDTO getAdditionalies() {
        return additionalies;
    }

    public void setAdditionalies(AdditionalInformationDTO additionalies) {
        this.additionalies = additionalies;
    }

    public List<EconomicDependientiesDto> getDependents() {
        return dependents;
    }

    public void setDependents(List<EconomicDependientiesDto> dependents) {
        this.dependents = dependents;
    }

    public SpouseDTO getSpouse() {
        return spouse;
    }

    public void setSpouse(SpouseDTO spouse) {
        this.spouse = spouse;
    }

    public WorkDTO getWork() {
        return work;
    }

    public void setWork(WorkDTO work) {
        this.work = work;
    }

    public List<ReferenceDTO> getReferences() {
        return references;
    }

    public void setReferences(List<ReferenceDTO> references) {
        this.references = references;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
