package archiver;

import java.io.InputStream;
import java.io.OutputStream;

public interface Archiver {
    OutputStream compress(OutputStream out) throws Exception;
    InputStream decompress(InputStream in) throws Exception;
}