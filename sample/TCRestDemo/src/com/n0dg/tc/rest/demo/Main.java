package com.n0dg.tc.rest.demo;

import java.util.Map;
import java.util.Set;

import org.apache.http.auth.UsernamePasswordCredentials;

import com.n0dg.tc.rest.TCServer;

public class Main {

	public static void main(String[] args) {
		System.out.println("Demo application for N0dGrand87 TeamCity Rest API Java wrapper");

		TCServer tc = new TCServer("10.1.2.15", 8081);
		tc.setCredentials(new UsernamePasswordCredentials("grand", "cghfdtlkbdjcnm"));
		
		Map<String, String> projects;
		projects = tc.getProjects();
		if (projects != null) {
			Set<String> projectsIds = projects.keySet();

			System.out.println("Project name \t\tProject ID");
			for (String pId : projectsIds) {
				System.out.println(projects.get(pId) + "\t\t(" + pId + ")");

				Map<String, String> configs = tc.getConfigurations(projects.get(pId));
				if (configs != null) {
					Set<String> configsIds = configs.keySet();
					if (configsIds.size() > 0) {
						System.out.println("\t\t\t\tConfig name \t\tConfig ID");
						for (String cId : configsIds) {
							System.out.println("\t\t\t\t" + configs.get(cId) + "\t\t(" + cId + ")");
						}
					}
				}

			}
		} else {
			System.out.println("Information not availble");
		}
	}
}
