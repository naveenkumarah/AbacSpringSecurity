package com.naveen.abac.security.policy;

import java.util.List;

public interface PolicyDefinition {
	public List<PolicyRule> getAllPolicyRules();
}