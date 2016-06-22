package test;

import java.io.IOException;
import java.util.List;

import variant.Variant;

/**
 * Created by storo on 5/18/2016.
 */
public class Test {
    private List<Variant> variants;

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public String toString() {
        String res = "";
        for (Variant variant : variants) {
            res += variant.toString();
        }
        return res;
    }

    public void toFile(String pathToFolder) throws IOException {
        for (Variant variant : variants) {
            variant.toFile(pathToFolder);
        }
    }
}
