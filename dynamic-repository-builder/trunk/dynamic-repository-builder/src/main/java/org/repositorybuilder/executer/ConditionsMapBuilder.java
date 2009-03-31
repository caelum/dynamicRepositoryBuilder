package org.repositorybuilder.executer;

import java.util.HashMap;
import java.util.Map;

import org.repositorybuilder.operation.RepositoryOperation;

/**
 * @author leonardobessa
 * 
 */
public class ConditionsMapBuilder {

    public Map<String, Object> getMap(RepositoryOperation operation) {
        String replacedName = operation.getName().replaceFirst("find(All)?", "");
        Map<String, Object> map = new HashMap<String, Object>();
        if (replacedName.startsWith("By")) {
            replacedName = replacedName.replaceFirst("By", "");
            String[] strings = replacedName.split("And");
            for (int i = 0; i < strings.length; i++) {
                String attribute = strings[i].substring(0, 1).toLowerCase() + strings[i].substring(1);
                map.put(attribute, operation.getArguments()[i]);
            }
        }
        return map;
    }

}
