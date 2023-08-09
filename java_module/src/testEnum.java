/**
 * @date 2023/6/27
 */
public enum testEnum {
    AAA(1);

    private int code;

    testEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
