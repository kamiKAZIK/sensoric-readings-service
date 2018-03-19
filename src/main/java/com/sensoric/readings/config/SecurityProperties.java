package com.sensoric.readings.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "sensoric.security")
public class SecurityProperties {
    private UserProperties user;
    private UserProperties manager;

    public void setUser(UserProperties user) {
        this.user = user;
    }

    public UserProperties getUser() {
        return user;
    }

    public void setManager(UserProperties manager) {
        this.manager = manager;
    }

    public UserProperties getManager() {
        return manager;
    }

    public List<UserProperties> getUsers() {
        return Arrays.asList(user, manager);
    }

    public static final class UserProperties {
        private String name;
        private String password;
        private String[] roles;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        }

        public String[] getRoles() {
            return roles;
        }
    }
}
