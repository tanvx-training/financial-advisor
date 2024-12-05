package dev.tanvx.profile_service.infrastructure.constants;

public class UserRegex {

  public static final String USER_EMAIL_REGEXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

  public static final String PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

  public static final String FULL_NAME_REGEXP = "^[A-Za-z]+([ '-][A-Za-z]+)*$";
}
