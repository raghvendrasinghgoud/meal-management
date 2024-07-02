package com.meal.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.meal.helper.Utility;

@MappedSuperclass
public class Auditable{

	// instance meta info

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    protected Date createdDate;

    @OneToOne
    @JoinColumn(updatable = false)
    protected User createdBy;

   
    @Temporal(TemporalType.TIMESTAMP)
    protected Date modifiedDate;

    @OneToOne
    protected User modifiedBy;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

    @PrePersist
    public void setBeforeSave() {
        this.createdBy = Utility.getCurrentUser();
        this.createdDate = new Date();
    }

    @PreUpdate
    public void setBeforeUpdate() {
        this.modifiedBy = Utility.getCurrentUser();
        this.modifiedDate = new Date();
    }
}