package com.yaswanth.pulse_labs_assignment.Entities;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetResponse {
    private int statusCode;
    private String statusMessage;
    private Long pageNumber;
    private boolean hasMore = true;
    private List<Discussion> discussions;


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public List<Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(List<Discussion> discussions) {
        this.discussions = discussions;
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", statusMessage='" + statusMessage + '\'' +
                ", discussions=" + discussions +
                ", pageNumber=" + pageNumber +
                ", hasMore=" + hasMore +
                '}';
    }
}
