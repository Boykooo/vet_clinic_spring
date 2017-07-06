package entities;

import enums.Role;

public interface BaseUser {
    Role getRole();
    String getEmail();
    String getPassword();
}
