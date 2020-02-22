package testData;

import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;

import java.util.Locale;

public class CouponRegistry  implements TypeRegistryConfigurer {
    @Override
    public Locale locale() {
        return null;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {


    }
}
