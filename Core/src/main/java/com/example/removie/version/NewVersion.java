package com.example.removie.version;

import lombok.Getter;

@Getter
public class NewVersion {
    private final Integer version;

    public NewVersion(Integer version) {
        this.version = version;
    }
}
