package com.bazlur;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Day010 {
  public static void main(String[] args) {
    var torontoTimeZone = ZoneId.of("America/Toronto");
    var dubaiTimeZone = ZoneId.of("Asia/Dubai");

    var takeOffTime = LocalDateTime.of(2021, Month.JUNE, 16, 21, 45);
    var zonedTakeOffTime = ZonedDateTime.of(takeOffTime, torontoTimeZone);
    var flightDuration = Duration.ofHours(12).plusMinutes(45);

    var arrivalTime = zonedTakeOffTime.plus(flightDuration);
    var zonedArrivalTime = arrivalTime.toInstant().atZone(dubaiTimeZone);

    var formattedTime = DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.FULL)
            .format(zonedArrivalTime);

    System.out.println("The flight arrives on " + formattedTime);
  }
}

//The flight arrives on Thursday, June 17, 2021 at 6:30:00 p.m. Gulf Standard Time