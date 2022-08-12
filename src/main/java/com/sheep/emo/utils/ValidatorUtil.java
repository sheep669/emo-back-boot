package com.sheep.emo.utils;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/8/2 11:50
 */
public class ValidatorUtil {

    private static final Validator VALIDATOR;

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation
            .byProvider(HibernateValidator.class)
            .configure()
            // true 遇到一个错误立即返回
            .failFast(true)
            .buildValidatorFactory();

    static {
        VALIDATOR = VALIDATOR_FACTORY.getValidator();
    }

    /**
     * 校验参数
     *
     * @param t 泛型 任意类型
     * @return Map<String, String>
     * @author sheep669
     * @created at 2022/8/2 21:15
     */
    public static <T> Map<String, Object> valid(T t) {
        Set<ConstraintViolation<Object>> validate = VALIDATOR.validate(t);
        Map<String, Object> rMap = new HashMap<>(16);
        for (ConstraintViolation<Object> constraintViolation : validate) {
            rMap.put("msg", constraintViolation.getMessage());
        }
        return rMap;
    }
}

