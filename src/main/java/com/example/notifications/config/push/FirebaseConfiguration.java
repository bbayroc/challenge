package com.example.notifications.config.push;

public final class FirebaseConfiguration {

    private final String projectId;

    private final String credentials;

    private FirebaseConfiguration(Builder builder) {

        this.projectId = builder.projectId;
        this.credentials = builder.credentials;

    }

    public String getProjectId() {

        return projectId;

    }

    public String getCredentials() {

        return credentials;

    }

    public static Builder builder() {

        return new Builder();

    }

    public static final class Builder {

        private String projectId;

        private String credentials;

        public Builder projectId(
                String projectId) {

            this.projectId = projectId;

            return this;

        }

        public Builder credentials(
                String credentials) {

            this.credentials = credentials;

            return this;

        }

        public FirebaseConfiguration build() {

            return new FirebaseConfiguration(this);

        }

    }

}