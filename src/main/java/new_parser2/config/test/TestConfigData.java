package new_parser2.config.test;

import new_parser2.config.variant.VariantConfig;

/**
 * Created by storo on 5/18/2016.
 */
public class TestConfigData {
    private TestConfig testConfig;
    private VariantConfig variantConfig;

    public TestConfig getTestConfig() {
        return testConfig;
    }

    public void setTestConfig(TestConfig testConfig) {
        this.testConfig = testConfig;
    }

    public VariantConfig getVariantConfig() {
        return variantConfig;
    }

    public void setVariantConfig(VariantConfig variantConfig) {
        this.variantConfig = variantConfig;
    }

    @Override
    public String toString() {
        return "TestConfigData{" +
                "testConfig=" + testConfig +
                ", variantConfig=" + variantConfig +
                '}';
    }
}
