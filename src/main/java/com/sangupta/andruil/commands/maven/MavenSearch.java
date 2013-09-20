package com.sangupta.andruil.commands.maven;

import java.util.Date;
import java.util.List;

import com.sangupta.andruil.commands.base.AbstractCommand;
import com.sangupta.jerry.http.WebInvoker;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.ConsoleUtils;
import com.sangupta.jerry.util.GsonUtils;
import com.sangupta.jerry.util.UriUtils;

public class MavenSearch extends AbstractCommand {

	@Override
	public String getName() {
		return "maven-search";
	}

	@Override
	public String getHelpLine() {
		return "Search the Maven central repo for an artifact";
	}

	@Override
	public void execute(String[] arguments) {
		String search;
		if(arguments.length >= 1) {
			search = arguments[0];
		} else {
			search = ConsoleUtils.readLine("Search term: ", true);
			if(AssertUtils.isEmpty(search)) {
				System.out.println("Nothing to search.");
				return;
			}
		}
		
		String uri = "http://search.maven.org/solrsearch/select?rows=10&wt=json&q=" + UriUtils.encodeURIComponent(search);
		String response = WebInvoker.fetchResponse(uri);
		if(AssertUtils.isEmpty(response)) {
			System.out.println("Unable to fetch response from Maven central");
			return;
		}

		MavenSearchResults results = GsonUtils.getGson().fromJson(response, MavenSearchResults.class);
		System.out.print("Total results found: " + results.response.numFound);
		System.out.println("; starting at: " + results.response.start);
		
		Date d = new Date();
		int count = 0;
		for(Doc doc : results.response.docs) {
			System.out.print((++count) + ": " + doc.g + ":" + doc.a + ":" + doc.latestVersion + ", All(" + doc.versionCount + "); ");
			d.setTime(doc.timestamp);
			System.out.print(d.toString());
			
			if(AssertUtils.isNotEmpty(doc.ec)) {
				System.out.print("; ");
				for(String ec : doc.ec) {
					System.out.print(ec.substring(1) + " ");
				}
			}
			
			System.out.println("");
		}
	}
	
	private static class MavenSearchResults {
		
		private Response response;
	}
	
	private static class Response {

		public int numFound;
		
		public int start;
		
		public List<Doc> docs;
	}
	
	private static class Doc {
		
		public String id;
		
		public String g;
		
		public String a;
		
		public String latestVersion;
		
		public long timestamp;
		
		public int versionCount;
		
		public List<String> ec;
	}

}
