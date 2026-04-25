package com.movie.ticket.payment.service;

import com.movie.ticket.payment.dto.BookingCreatedEvent;
import com.movie.ticket.payment.dto.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final Random random = new Random();

    public PaymentService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // === PAYMENT ===============================================================

    @KafkaListener(topics = "BOOKING_CREATED", groupId = "payment-group")
    public void processPayment(BookingCreatedEvent event) throws InterruptedException {
        String userName = (event.getUserName() != null) ? event.getUserName() : "User #" + event.getUserId();

        log.info("==========================================================");
        log.info("[PAYMENT] Listen  <- BOOKING_CREATED | Booking #{} | User: {} | Amount: {}",
                event.getBookingId(), userName, event.getAmount());
        log.info("[PAYMENT] Xu ly  -> Dang mo phong thanh toan (2 giay)...");

        Thread.sleep(2000);

        boolean success = random.nextDouble() < 0.8;

        if (success) {
            log.info("[PAYMENT] Ket qua -> THANH CONG (80% xac suat)");
            log.info("[PAYMENT] Publish -> PAYMENT_COMPLETED | Booking #{}", event.getBookingId());
            kafkaTemplate.send("PAYMENT_COMPLETED",
                    new PaymentEvent(event.getBookingId(), "SUCCESS", event.getUserId(), event.getUserName()));
        } else {
            log.info("[PAYMENT] Ket qua -> THAT BAI (20% xac suat)");
            log.info("[PAYMENT] Publish -> BOOKING_FAILED    | Booking #{}", event.getBookingId());
            kafkaTemplate.send("BOOKING_FAILED",
                    new PaymentEvent(event.getBookingId(), "FAILED", event.getUserId(), event.getUserName()));
        }
        log.info("==========================================================");
    }

    // === NOTIFICATION ==========================================================

    @KafkaListener(topics = "PAYMENT_COMPLETED", groupId = "notif-group")
    public void sendNotification(PaymentEvent event) {
        String userName = (event.getUserName() != null) ? event.getUserName() : "User #" + event.getUserId();

        log.info("==========================================================");
        log.info("[NOTIFICATION] Listen <- PAYMENT_COMPLETED | Booking #{}", event.getBookingId());
        log.info("[NOTIFICATION] Output -> Booking #{} thanh cong!", event.getBookingId());
        log.info("[NOTIFICATION] Gui   -> {} da dat don #{} thanh cong", userName, event.getBookingId());
        log.info("==========================================================");
    }
}
