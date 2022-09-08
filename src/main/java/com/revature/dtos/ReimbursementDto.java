package com.revature.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.models.Reimbursement;

@JsonIgnoreProperties
public class ReimbursementDto {
    private String amount;
    private String submitted;
    private String description;
    private boolean resolved;

    public ReimbursementDto() {
    }

    public ReimbursementDto(String amount, String submitted, boolean resolved){
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
    }

    public ReimbursementDto(String amount, String submitted, String description, boolean resolved) {
        this.amount = amount;
        this.submitted = submitted;
        this.description = description;
        this.resolved = resolved;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
