package model.dto.Student;

import java.time.LocalDateTime;

public class ApplicationStatusDto {
    private int userID;
    private String submissionStatus;
    private LocalDateTime editTime;
    private String applicationName;

    // Constructor, getters and setters
    public ApplicationStatusDto(int userID, String submissionStatus, LocalDateTime editTime, String applicationName) {
        this.userID = userID;
        this.submissionStatus = submissionStatus;
        this.editTime = editTime;
        this.applicationName = applicationName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(String submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}

