package com.jetbrains.aplicatiequiz.dto;

import com.jetbrains.aplicatiequiz.models.Attempt;

import java.time.LocalDateTime;

public class AttemptDTO {

    private Long id;
    private LocalDateTime attemptDate;
    private LocalDateTime timestamp;
    private String shareableLink;

    public AttemptDTO() {}


    public AttemptDTO(Attempt attempt) {
        this.id = attempt.getId();
        this.attemptDate = attempt.getAttemptDate();
        this.timestamp = attempt.getTimestamp();
        this.shareableLink = attempt.getShareableLink();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getAttemptDate() { return attemptDate; }
    public void setAttemptDate(LocalDateTime attemptDate) { this.attemptDate = attemptDate; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getShareableLink() { return shareableLink; }
    public void setShareableLink(String shareableLink) { this.shareableLink = shareableLink; }
}
