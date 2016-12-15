/**
 * This class consists exclusively of static methods for obtaining encoders and decoders for the Base62 encoding scheme.
 * <ul>
 * <li><a name="Basic">Basic</a>
 * <p> Uses the alphanumerical alphabet as define: [0-9][a-zA-Z].
 * The decoder rejects data that contains characters outside the base62 alphabet.
 * <li><a name="Variant">Variant</a>
 * <p> Uses the alphanumerical alphabet as define: [a-zA-Z][0-9]
 * The decoder rejects data that contains characters outside the base62 alphabet.
 * </ul>
 * <p> Unless otherwise noted, passing a {@code null} argument to a method of this class
 * will cause a {@link java.lang.NullPointerException NullPointerException} to be thrown.
 * <p>
 * Created by perbellinioa.
 */
public class Base62 {

    /**
     * Returns an {@link Encoder} that encodes using the <a href="#Basic">Basic</a> type Base62 encoding scheme.
     *
     * @return A Base62 encoder
     */
    @NotNull
    public static Encoder getEncoder() {
        return Encoder.ENCODER;
    }

    /**
     * Returns an {@link Encoder} that encodes using the <a href="#Variant">Variant</a> type Base62 encoding scheme.
     *
     * @return A Base62 encoder
     */
    @NotNull
    public static Encoder getEncoderVariant() {
        return Encoder.ENCODER_VARIANT;
    }

    /**
     * Returns a {@link Decoder} that decodes using the <a href="#Basic">Basic</a> type Base62 encoding scheme.
     *
     * @return A Base62 decoder
     */
    @NotNull
    public static Decoder getDecoder() {
        return Decoder.DECODER;
    }

    /**
     * Returns a {@link Decoder} that decodes using the <a href="#Variant">Variant</a> type Base62 encoding scheme.
     *
     * @return A Base62 decoder
     */
    @NotNull
    public static Decoder getDecoderVariant() {
        return Decoder.DECODER_VARIANT;
    }

    /**
     * This class implements an encoder for encoding Long data using the Base62 encoding scheme.
     */
    public static class Encoder {

        private static final char[] toBase62 = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        private static final char[] toBase62Variant = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };
        private static final int RADIX = toBase62.length;
        private static final Encoder ENCODER = new Encoder(false);
        private static final Encoder ENCODER_VARIANT = new Encoder(true);

        private final boolean isVariant;

        private Encoder(boolean isVariant) {
            this.isVariant = isVariant;
        }

        @NotNull
        public String encode(Long aLong) {
            final char[] base62 = isVariant ? toBase62Variant : toBase62;
            if (aLong < 0L) {
                throw new IllegalArgumentException("The value must be positive: " + aLong);
            }
            if (aLong == 0L) {
                return "0";
            }
            StringBuilder sb = new StringBuilder();
            Long copy = aLong;
            while (copy > 0) {
                sb.append(base62[(int) (copy % RADIX)]);
                copy = copy / RADIX;
            }
            return sb.reverse().toString();
        }

    }

    /**
     * This class implements a decoder for decoding String data using the Base62 encoding scheme.
     */
    public static class Decoder {
        private static final Decoder DECODER = new Decoder(false);
        private static final Decoder DECODER_VARIANT = new Decoder(true);

        private final boolean isVariant;
        private Decoder(boolean isVariant) {
            this.isVariant = isVariant;
        }

        @NotNull
        public Long decode(String s) {
            final String alphabet = isVariant ? new String(Encoder.toBase62Variant) : new String(Encoder.toBase62);
            Long aLong = 0L;
            for (int i = s.length() - 1; i >= 0; i--) {
                int index = alphabet.indexOf(s.charAt(i));
                if (index < 0)
                    throw new IllegalArgumentException(String.format("The value contain invalid character: '%s'. Valid characters are: [0-9][a-zA-Z].", s.charAt(i)));
                aLong += index * (long) Math.pow(Encoder.RADIX, s.length() - i - 1);
            }
            return aLong;
        }
    }

}
