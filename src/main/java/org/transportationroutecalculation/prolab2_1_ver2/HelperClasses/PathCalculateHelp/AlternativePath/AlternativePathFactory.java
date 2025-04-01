package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AlternativePathFactory implements InitializingBean {

    private final ApplicationContext applicationContext;
    private Map<String, AlternativePath> pathStrategies;

    @Autowired
    public AlternativePathFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet(){
        Map<String, AlternativePath> beans = applicationContext.getBeansOfType(AlternativePath.class);
        pathStrategies = new HashMap<>();

        for (Map.Entry<String, AlternativePath> entry : beans.entrySet()) {
            String beanName = entry.getKey().toLowerCase();
            AlternativePath strategy = entry.getValue();
            pathStrategies.put(beanName, strategy);
        }
    }

    public AlternativePath getPathStrategy(String type) {
        AlternativePath strategy = pathStrategies.get(type.toLowerCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown alternative path type: " + type);
        }
        return strategy;
    }

    public Map<String, AlternativePath> getAllStrategies() {
        return new HashMap<>(pathStrategies);
    }
}