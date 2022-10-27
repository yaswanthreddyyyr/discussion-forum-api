package com.yaswanth.pulse_labs_assignment.Entities;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class Discussion {
    private Long discussionId;
    private String discussion;
    private Long createdAt;
    private List<Tag> tags= Collections.emptyList();

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
        this.discussion = discussion.trim();
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Discussion{" +
                "discussionId=" + discussionId +
                ", discussion='" + discussion + '\'' +
                ", createdAt=" + createdAt +
                ", tags=" + tags +
                '}';
    }
}
