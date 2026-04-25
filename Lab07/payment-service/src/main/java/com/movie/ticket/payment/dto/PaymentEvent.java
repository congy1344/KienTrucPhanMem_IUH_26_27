package com.movie.ticket.payment.dto;

public class PaymentEvent {
    private Long bookingId;
    private String status;
    private Long userId;
    private String userName;

    public PaymentEvent() {}

    public PaymentEvent(Long bookingId, String status, Long userId, String userName) {
        this.bookingId = bookingId;
        this.status = status;
        this.userId = userId;
        this.userName = userName;
    }

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
}
