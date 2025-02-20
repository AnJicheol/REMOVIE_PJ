package com.example.removie_read_server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LatestVersion {
    private Integer latestVersion;

    public LatestVersion(Integer latestVersion) {
        this.latestVersion = latestVersion;
    }

    public int differenceFrom(Integer version) {
        return latestVersion - version;
    }

    public static LatestVersion empty() {
        return new LatestVersion(null);
    }
}
