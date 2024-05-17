import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {
    @Disabled
    @Test
    @Timeout(value = 22)
    void failsIfTimeExceeds22Seconds() {
        try {
            Main.main(new String[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
