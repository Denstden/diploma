package ua.kiev.unicyb.parser.config.test;

import ua.kiev.unicyb.parser.config.variant.VariantConfig;

public class TestConfigData {
    private TestConfig testConfig;
    private VariantConfig variantConfig;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestConfigData{" +
                "testConfig=" + testConfig +
                ", variantConfig=" + variantConfig +
                ", name='" + name + '\'' +
                '}';
    }
}
