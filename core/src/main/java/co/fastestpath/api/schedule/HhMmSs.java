package co.fastestpath.api.schedule;

import java.util.regex.Pattern;

public class HhMmSs implements Comparable<HhMmSs> {

  private static final Pattern PATTERN = Pattern.compile("(?:[012345]\\d):(?:[012345]\\d):(?:[012345]\\d)");

  private final int seconds;

  public static HhMmSs create(String hHmMsS) {

    if (hHmMsS == null || !PATTERN.matcher(hHmMsS).matches()) {
      throw new IllegalArgumentException("Invalid HH:MM:SS format, " + hHmMsS);
    }

    String[] parts = hHmMsS.split(":");
    return builder()
        .hours(Integer.parseInt(parts[0]))
        .minutes(Integer.parseInt(parts[1]))
        .seconds(Integer.parseInt(parts[2]))
        .build();
  }

  public static Builder builder() {
    return new Builder();
  }

  public HhMmSs(int seconds) {
    this.seconds = seconds;
  }

  private HhMmSs(Builder builder) {
    seconds = builder.hours * 3600 + builder.minutes * 60 + builder.seconds;
  }

  public int toSeconds() {
    return seconds;
  }

  @Override
  public int compareTo(HhMmSs other) {
    return Integer.compare(toSeconds(), other.toSeconds());
  }

  public static final class Builder {
    private int hours;
    private int minutes;
    private int seconds;

    private Builder() {
    }

    public Builder hours(int hours) {
      this.hours = hours;
      return this;
    }

    public Builder minutes(int minutes) {
      this.minutes = minutes;
      return this;
    }

    public Builder seconds(int seconds) {
      this.seconds = seconds;
      return this;
    }

    public HhMmSs build() {
      return new HhMmSs(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    HhMmSs hhMmSs = (HhMmSs) o;
    return toSeconds() == hhMmSs.toSeconds();
  }

  @Override
  public int hashCode() {
    return 31 * seconds;
  }
}
