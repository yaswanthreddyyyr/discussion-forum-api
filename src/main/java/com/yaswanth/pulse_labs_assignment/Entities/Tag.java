package com.yaswanth.pulse_labs_assignment.Entities;

import org.springframework.stereotype.Component;

@Component
public class Tag {
    private long tagId;
    private String tagName;

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Discussion{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                '}';
    }

}
