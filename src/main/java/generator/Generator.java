package generator;

import java.util.ArrayList;
import java.util.List;

import variant.VariantFabric;
import parser.config.test.TestConfigData;
import parser.config.variant.VariantConfig;
import test.Test;
import variant.Variant;

public class Generator {
    private TestConfigData testConfigData;

    public void setTestConfigData(TestConfigData testConfigData) {
        this.testConfigData = testConfigData;
    }

    public Test generate() {
        List<Variant> variants = new ArrayList<>();
        VariantConfig variantConfig = testConfigData.getVariantConfig();
        VariantFabric variantFabric = new VariantFabric();
        variantFabric.setConfig(variantConfig);
        for (int i = 1; i <= testConfigData.getTestConfig().getCountOfVariants(); i++) {
            variants.add(variantFabric.getVariant(i));
        }
        Test test = new Test();
        test.setVariants(variants);
        return test;
    }
}
