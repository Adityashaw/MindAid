package com.example.mindaid.Model;

import javax.persistence.*;

@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    public int paymentId;
    @Column(name = "user_id")
    public int userId;
    @Column(name = "doc_id")
    public int docId;
    @Column(name = "schedule_date")
    public String scheduleDate;
    @Column(name = "schedule_time")
    public String scheduleTime;
    @Column(name = "schedule_duration")
    public int scheduleDuration;
    @Column(name = "schedule_id")
    public int scheduleId;
    @Column(name = "name_on_card")
    public String nameOnCard;
    @Column(name = "card_number")
    public String cardNumber;
    @Column(name = "mm")
    public String mm;
    @Column(name = "year")
    public String year;
    @Column(name = "ccv")
    public String ccv;
    @Column(name = "first_name")
    public String firstName;
    @Column(name = "last_name")
    public  String lastName;
    @Column(name = "email")
    public String email;
    @Column(name = "confirm_email")
    public String confirmEmail;
    @Column(name = "telephone")
    public String telephone;
    @Column(name = "session_link")
    public String sessionLink;

    @Column(name = "contact_media")
    public String contactMedia;

    @Column(name = "active_status")
    public int activeStatus;

    @Column(name = "approval")
    public String approval;

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public int getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(int activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getContactMedia() {
        return contactMedia;
    }

    public void setContactMedia(String contactMedia) {
        this.contactMedia = contactMedia;
    }

    public String getSessionLink() {
        return sessionLink;
    }

    public void setSessionLink(String sessionLink) {
        this.sessionLink = sessionLink;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public int getScheduleDuration() {
        return scheduleDuration;
    }

    public void setScheduleDuration(int scheduleDuration) {
        this.scheduleDuration = scheduleDuration;
    }
}
