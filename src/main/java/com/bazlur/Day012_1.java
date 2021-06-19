package com.bazlur;

import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import java.util.function.Predicate;
import java.util.regex.Pattern;

record User(String name, String email, String phoneNumber, int age) {
}

public class Day012_1 {
  public static void main(String[] args) {
    var userValidator = new UserValidator();
    var user = userValidator.validateUser("Bazlur$ Rahman", "bazlur$bazlur.com", "+1 1416-555-0305", -10);

    if (user.isInvalid()) {
      var allErrors = user.getError().intersperse(", ").fold("", String::concat);
      System.out.println("allErrors = " + allErrors);
    }
  }

  static class UserValidator {
    private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
    private static final int MIN_AGE = 18;
    private static final Predicate<String> EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$").asMatchPredicate();
    private static final Predicate<String> PHONE_NUMBER_PATTERN
            = Pattern.compile("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$\n").asMatchPredicate();

    public Validation<Seq<String>, User> validateUser(String name, String email, String phoneNumber, int age) {

      return Validation.combine(validateName(name), validateEmail(email), validatePhone(phoneNumber), validateAge(age))
              .ap(User::new);
    }

    private Validation<String, String> validateName(String name) {

      return CharSeq.of(name)
              .replaceAll(VALID_NAME_CHARS, "")
              .transform(seq -> seq.isEmpty()
                      ? Validation.valid(name)
                      : Validation.invalid("Name contain invalid characters: '" + seq.distinct().sorted()));
    }

    private Validation<String, String> validateEmail(String email) {
      return EMAIL_PATTERN.test(email) ?
              Validation.valid(email)
              : Validation.invalid("Invalid email address");
    }

    private Validation<String, String> validatePhone(String email) {
      return PHONE_NUMBER_PATTERN.test(email) ?
              Validation.valid(email)
              : Validation.invalid("Invalid email address");
    }

    private Validation<String, Integer> validateAge(int age) {
      return age >= MIN_AGE
              ? Validation.valid(age)
              : Validation.invalid("Age must be at least " + MIN_AGE);
    }
  }
}
