package ru.sberbank.cib.exchange.domain;

import java.util.HashMap;
import java.util.Map;

public class SkillRegistry {
	private static final int idGenerator = 0;
	private static final Map<Integer, Skill> registry = new HashMap<Integer, Skill>();
	
	public int addSkill(Skill skill) {
		if (registry.containsValue(Skill skill)) {
			
		}
	}
}
