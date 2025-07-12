package br.com.luanlopes.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    public static void  copyNonNullProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target, getNullPropertyName(source));
    }

    public static String[] getNullPropertyName(Object source){
        final BeanWrapper scr = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = scr.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for(PropertyDescriptor pd: pds){
            Object srcValue = scr.getPropertyValue(pd.getName());
            if(srcValue == null){
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
