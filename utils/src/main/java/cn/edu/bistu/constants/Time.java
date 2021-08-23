package cn.edu.bistu.constants;

public enum Time {
    MILLISECOND(1L),
    SECOND(1L * 1000),
    MINUTE(1L * 1000 * 60),
    HOUR(1L * 1000 * 60 * 60),
    DAY(1L * 1000 * 60 * 60 * 24),
    WEEK(1L * 1000 * 60 * 60 * 24 * 7);

    private Long value;

    Time(Long value) {
        this.value=value;
    }

    public Long getValue() {
        return this.value;
    }


}
