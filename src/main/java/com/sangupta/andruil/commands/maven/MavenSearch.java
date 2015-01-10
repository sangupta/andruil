/**
 *
 * andruil - Java command line shell
 * Copyright (c) 2012-2015, Sandeep Gupta
 * 
 * http://www.sangupta/projects/andruil
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.sangupta.andruil.commands.maven;

import java.util.Date;
import java.util.List;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;
import com.sangupta.jerry.http.WebInvoker;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.ConsoleUtils;
import com.sangupta.jerry.util.GsonUtils;
import com.sangupta.jerry.util.UriUtils;

/**
 * Command to help search for maven artifacts.
 *  
 * @author sangupta
 *
 */
public class MavenSearch extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "mvn-search";
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
		
		Date date = new Date();
		int count = 0;
		for(Doc document : results.response.docs) {
			System.out.print((++count) + ": " + document.g + ":" + document.a + ":" + document.latestVersion + ", All(" + document.versionCount + "); ");
			date.setTime(document.timestamp);
			System.out.print(date.toString());
			
			if(AssertUtils.isNotEmpty(document.ec)) {
				System.out.print("; ");
				for(String ec : document.ec) {
					System.out.print(ec.substring(1) + " ");
				}
			}
			
			System.out.println();
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
	
		// unused variable
//		public String id;
		
		public String g;
		
		public String a;
		
		public String latestVersion;
		
		public long timestamp;
		
		public int versionCount;
		
		public List<String> ec;
	}

}