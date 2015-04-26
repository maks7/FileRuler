package com.hackbulgaria.fileruler;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

/**
 * 
 * @author John
 */
public class DoSearch {

    private String searchPhrase;

    public DoSearch(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public ArrayList<Path> search(MoviesCollecion collection) {
        ArrayList<Path> list = new ArrayList<Path>();

        try {
            // build a lucene index
            System.out.println("rebuildIndexes");
            Indexer indexer = new Indexer();
            indexer.rebuildIndexes(collection);
            System.out.println("rebuildIndexes done");

            // and retrieve the top 100 result
            System.out.println("performSearch on " + "\"" + searchPhrase + "\"");
            SearchEngine se = new SearchEngine();
            TopDocs topDocs = se.performSearch(searchPhrase, 100);

            System.out.println("Results found: " + topDocs.totalHits);
            ScoreDoc[] hits = topDocs.scoreDocs;

            for (int i = 0; i < hits.length; i++) {
                Document doc = se.getDocument(hits[i].doc);

                // System.out.println(doc.get("path"));
                list.add(Paths.get(doc.get("path")));
            }
            System.out.println("performSearch done");
        } catch (Exception e) {
            System.out.println("Exception caught.\n");
            System.out.println(e.getStackTrace());
        }

        return list;
    }

}
