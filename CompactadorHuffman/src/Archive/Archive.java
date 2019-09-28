//Joszef Barrionuevo

package Archive;

import java.io.*;
import java.io.UnsupportedEncodingException;
import java.util.zip.CRC32;

public class Archive {

    private long lengthInitial = 0;
    private long lengthFinal = 0;
    private CRC32 crc = new CRC32();
    private BufferedReader br;
    private Writer out;
    private File file;
    private FileInputStream arq;
    private InputStreamReader read;


    public BufferedReader open(String archive) throws FileNotFoundException, IOException {
        file = new File(archive);
        if (!file.exists()) {
            return null;
        }
        lengthInitial = file.length();
        arq = new FileInputStream(file);
        read = new InputStreamReader(arq, "ISO-8859-1");
        br = new BufferedReader(read);
        return br;
    }

    public void save(String archive, String content, boolean put) throws IOException {
        file = new File(archive);
        out = new BufferedWriter(new OutputStreamWriter(  new FileOutputStream(file,put),"ISO-8859-1")   );
        out.write(content);
        checkCRC(content);
        out.close();
        lengthFinal = file.length();
    }

    public long getLengthInitial(){
        return lengthInitial;
    }

    public long getLengthFinal(){
        return lengthFinal;
    }

    private int calculateCRC(String content) throws FileNotFoundException, IOException{
        byte[] textToBytes = content.getBytes();
        crc.update(textToBytes);
        return (int) crc.getValue();
    }

    public void checkCRC(String content) throws IOException {
        System.out.println("CRC: "+calculateCRC(content) );
        /*long start = System.nanoTime();
        for ( int i = 0; i < 100_000100_000L; i++ ) {
            calculateCRC(content);
        }
        long stop = System.nanoTime();
        System.out.println( ( stop - start ) / 1_000_0001_000_000L + " segundos para calcular 100.000 hashes CRC32" );*/
    }
}
