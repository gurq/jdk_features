package trywithresource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResource {

    private void old() throws FileNotFoundException {
            BufferedReader br = new BufferedReader(new FileReader("path"));
            try {
                br.readLine();
            } catch (IOException e){
                e.printStackTrace();
            }finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    private void now(){
        try (BufferedReader br = new BufferedReader(new FileReader("path"))) {
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
