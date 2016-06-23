package ua.kiev.unicyb.generator;

import java.util.ArrayList;
import java.util.List;

import ua.kiev.unicyb.exception.UnsupportedQuestionTypeException;
import ua.kiev.unicyb.variant.VariantFactory;
import ua.kiev.unicyb.parser.config.test.TestConfigData;
import ua.kiev.unicyb.parser.config.variant.VariantConfig;
import ua.kiev.unicyb.test.Test;
import ua.kiev.unicyb.variant.Variant;

public class Generator {
    private TestConfigData testConfigData;

    public void setTestConfigData(TestConfigData testConfigData) {
        this.testConfigData = testConfigData;
    }

    public Test generate() throws UnsupportedQuestionTypeException {
        List<Variant> variants = new ArrayList<>();
        VariantConfig variantConfig = testConfigData.getVariantConfig();
        VariantFactory variantFactory = new VariantFactory();
        variantFactory.setConfig(variantConfig);
        for (int i = 1; i <= testConfigData.getTestConfig().getCountOfVariants(); i++) {
            variants.add(variantFactory.getVariant(i));
        }
        Test test = new Test();
        test.setVariants(variants);
        return test;
    }
}
