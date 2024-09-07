package com.example.ai.domain.sercurity.service;




import com.example.ai.domain.sercurity.service.realm.JwtRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.DefaultSubjectFactory;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.filter.authc.AnonymousFilter;

import org.apache.shiro.web.filter.authc.LogoutFilter;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;


import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author 林圣涛
 * Shiro 配置启动
 */
public class ShiroConfig {
    @Bean
    public SubjectFactory subjectFactory(){
        class JwtDefaultSubjectFactory extends DefaultSubjectFactory{
            @Override
            public Subject createSubject(SubjectContext context){
                context.setSessionCreationEnabled(false);
                return super.createSubject(context);
            }
        }
        return new JwtDefaultSubjectFactory();
    }

    @Bean
    public Realm realm() {
        return  new JwtRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =
                new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        //关闭ShiroDAO功能
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator =
                new DefaultSessionStorageEvaluator();
        //不需要将Shiro Session 中的东西存到任何地方（包括Http Session 中）
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectFactory(subjectFactory());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        shiroFilter.setLoginUrl("/unauthenticated");
        shiroFilter.setUnauthorizedUrl("/unauthorized");
        //添加jwt过滤器
        Map<String, Filter> filterMap = new HashMap<>();
        //设置过滤器【anon\logout可以不设置】
        filterMap.put("anon",new AnonymousFilter());
        filterMap.put("jwt",new JwtFilter());
        filterMap.put("logout",new LogoutFilter());
        shiroFilter.setFilters(filterMap);
        //拦截器，指定方法走哪个拦截器【login->anon】 【logout->logout】 【verify->jwt】
        Map<String,String> filterRuleMap = new LinkedHashMap<>();
        filterRuleMap.put("/login","anon");
        filterRuleMap.put("/logout","logout");
        filterRuleMap.put("/verify","jwt");
        shiroFilter.setFilterChainDefinitionMap(filterRuleMap);
        return shiroFilter;
    }
}
