package com.example.demo.SpringRestSpringSecurityHibernate.controller;

import com.example.demo.SpringRestSpringSecurityHibernate.entity.EmployeeIdentity;
import org.springframework.stereotype.Component;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import java.io.Serializable;

@Component
public class CustomBackendIdConverter implements BackendIdConverter {
    @Override
    public Serializable fromRequestId(String s, Class<?> aClass) {
        if(s != null){
            String[] idParts = s.split("&");
            EmployeeIdentity employeeIdentity = new EmployeeIdentity();
            employeeIdentity.setCompanyId(idParts[0]);
            employeeIdentity.setCompanyId(idParts[0]);
            return employeeIdentity;
        }
        return BackendIdConverter.DefaultIdConverter.INSTANCE.fromRequestId(s, aClass);
    }

    @Override
    public String toRequestId(Serializable serializable, Class<?> aClass) {
        EmployeeIdentity employeeIdentity = (EmployeeIdentity)serializable;
        return String.format("%s&%s", employeeIdentity.getCompanyId(), employeeIdentity.getEmployeeId());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

//    @Override
//    public boolean supports(Class<?> delimiter) {
//        return true;
//    }
//
//    @Override
//    public Serializable fromRequestId(String id, Class<?> entityType) {
//        if (id == null) {
//            return null;
//        }
//
//        //first decode url string
//        if (!id.contains(" ") && id.toUpperCase().contains("%7B")) {
//            try {
//                id = URLDecoder.decode(id, "UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //deserialize json string to ID object
//        Object idObject = null;
//        for (Method method : entityType.getDeclaredMethods()) {
//            if (method.isAnnotationPresent(Id.class) || method.isAnnotationPresent(EmbeddedId.class)) {
//                idObject = JSON.parseObject(id, method.getGenericReturnType());
//                break;
//            }
//        }
//
//        //get dao class from spring
//        Object daoClass = null;
//        try {
//            daoClass = SpringUtil.getBean(Class.forName("com.example.db.dao." + entityType.getSimpleName() + "DAO"));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        //get the entity with given primary key
//        JpaRepository simpleJpaRepository = (JpaRepository) daoClass;
//        Object entity = simpleJpaRepository.findOne((Serializable) idObject);
//        return (Serializable) entity;
//
//    }
//
//    @Override
//    public String toRequestId(Serializable id, Class<?> entityType) {
//        if (id == null) {
//            return null;
//        }
//
//        String jsonString = JSON.toJSONString(id);
//
//        String encodedString = "";
//        try {
//            encodedString = URLEncoder.encode(jsonString, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return encodedString;
//    }
}