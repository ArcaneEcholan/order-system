package cn.edu.bistu.common.config;

import cn.edu.bistu.common.BeanUtils;
import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ValidationWrapper {

    String[] requiredPropsName;

    Validator validator;

    public ValidationWrapper(Validator validator) {
        this.validator = validator;
    }


    public  List<String> checkRedundantParam(Object obj) throws IllegalAccessException {
        List<Field> allDeclaredFields = BeanUtils.getAllDeclaredFields(obj.getClass());
        List<String> redundantParams = new ArrayList<>();
        for (Field field : allDeclaredFields) {
            String propName = field.getName();


            if (!BeanUtils.isFieldNull(obj, field)) {
                int flag = 0;
                for (String requiredPropName : requiredPropsName) {
                    if (propName.equals(requiredPropName)) {
                        flag = 1;
                        break;
                    }
                }
                if(flag == 0) {
                    redundantParams.add(propName);
                }
            }
        }

        return redundantParams;
    }

    public <T> Set<ConstraintViolation<T>> validate(T object) {

        if (requiredPropsName == null) {
            return validator.validate(object);
        }

        Set<ConstraintViolation<T>> result = new HashSet<>();
        for (String name : requiredPropsName) {
            Set<ConstraintViolation<T>> ConstraintViolation = validator.validateProperty(object, name);
            if (!ConstraintViolation.isEmpty()) {
                result.addAll(ConstraintViolation);
            }
        }

        return result;
    }

    public void setRequiredPropsNameNull() {
        requiredPropsName = null;
    }

}
