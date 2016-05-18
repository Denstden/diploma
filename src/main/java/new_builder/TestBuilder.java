package new_builder;

import new_parser2.config.test.TestConfigData;
import new_parser2.config.variant.VariantConfig;
import test.Test;
import variant.Variant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by storo on 5/18/2016.
 */
public class TestBuilder {
    private TestConfigData testConfigData;

    public void setTestConfigData(TestConfigData testConfigData) {
        this.testConfigData = testConfigData;
    }

    public Test build() {
        List<Variant> variants = new ArrayList<>();
        VariantConfig variantConfig = testConfigData.getVariantConfig();
        VariantBuilder variantBuilder = new VariantBuilder();
        variantBuilder.setConfig(variantConfig);
        for (int i = 1; i <= testConfigData.getTestConfig().getCountOfVariants(); i++) {
            variants.add(variantBuilder.build(i));
        }
        Test test = new Test();
        test.setVariants(variants);
        return test;
    }
}
