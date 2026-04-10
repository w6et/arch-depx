package base_jdk_new_jep._record;

record Color(byte red, byte green, byte blue) {
    public Color(int r, int g, int b) {
        this(checkByte(r), checkByte(g), checkByte(b));
    }

    private static byte checkByte(int x) {
        if (x < 0 || x > 255) throw new IllegalArgumentException();
        return (byte) (x & 0xff);
    }

    // Provided automatically: red(), green(), blue(),
    // toString(), equals(Object), hashCode()

    public Color mix(Color that) {
        return new Color(avg(red, that.red),
                         avg(green, that.green),
                         avg(blue, that.blue));
    }

    private static byte avg(byte b1, byte b2) {
        return (byte) (((b1 & 0xff) + (b2 & 0xff)) / 2);
    }
}