package com.naveen.abac.security.spring;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserContext {
    String principal;
    List<String> roles=new ArrayList<>();
}
