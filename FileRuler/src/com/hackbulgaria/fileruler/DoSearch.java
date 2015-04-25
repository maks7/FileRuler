package com.hackbulgaria.fileruler;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.ScoreDoc;

/**
 *
 * @author John
 */
public class DoSearch {

	private String searchPhrase;

	public DoSearch(String searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public void search(MoviesCollecion collection) {
		// TODO
		// Find a way to give back the path to the files.

		try {
			// build a lucene index
			System.out.println("rebuildIndexes");
			Indexer indexer = new Indexer();
			indexer.rebuildIndexes(collection);
			System.out.println("rebuildIndexes done");

			// perform search on "Notre Dame museum"
			// and retrieve the top 100 result
			System.out.println("performSearch");
			SearchEngine se = new SearchEngine();
			TopDocs topDocs = se.performSearch(searchPhrase, 100);

			System.out.println("Results found: " + topDocs.totalHits);
			ScoreDoc[] hits = topDocs.scoreDocs;
			for (int i = 0; i < hits.length; i++) {
				Document doc = se.getDocument(hits[i].doc);
//				System.out.println(doc.get("name") + " " + doc.get("actors")
//						+ " (" + hits[i].score + ")");
				System.out.println(doc.get("path"));

			}
			System.out.println("performSearch done");
		} catch (Exception e) {
			System.out.println("Exception caught.\n");
		}
	}

}

