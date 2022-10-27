package com.yaswanth.pulse_labs_assignment.Entities;

import org.springframework.stereotype.Component;

@Component
public class Request {
    private Long discussionId;
    private String discussion;
    private String tags;

    public Long getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(Long discussionId) {
        this.discussionId = discussionId;
    }

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Request{" +
                "discussionId=" + discussionId +
                ", discussion='" + discussion + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
