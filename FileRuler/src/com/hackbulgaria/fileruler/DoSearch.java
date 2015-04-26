package com.hackbulgaria.fileruler;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

public class DoSearch {

    private ArrayList<String> phrases;

    public DoSearch(String searchPhrase) {
        String[] splited = searchPhrase.split(",");

        for (int i = 0; i < splited.length; i++) {
            this.phrases.add(splited[i]);
        }
    }

    public ArrayList<Path> search(MoviesCollecion collection) {
        ArrayList<Path> list = new ArrayList<Path>();

        try {
            // build a lucene index
            System.out.println("rebuildIndexes --start");
            Indexer indexer = new Indexer();
            indexer.rebuildIndexes(collection);
            System.out.println("rebuildIndexes --done");

            // and retrieve the top 100 result
            System.out.println("performSearch on " + "\"" + this.phrases.toString() + "\"");
            SearchEngine se = new SearchEngine();
            TopDocs topDocs = null;
            for (int i = 0; i < this.phrases.size(); i++) {
                topDocs = se.performSearch(this.phrases.get(i), 100);
            }

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

    public ArrayList<Path> search(ImageCollection collection) {
        ArrayList<Path> list = new ArrayList<Path>();

        try {
            // build a lucene index
            System.out.println("rebuildIndexes --start");
            Indexer indexer = new Indexer();
            indexer.rebuildIndexes(collection);
            System.out.println("rebuildIndexes --done");

            // and retrieve the top 100 result
            System.out.println("performSearch on " + "\"" + this.phrases.toString() + "\"");
            SearchEngine se = new SearchEngine();
            TopDocs topDocs = null;
            for (int i = 0; i < this.phrases.size(); i++) {
                topDocs = se.performSearch(this.phrases.get(i), 100);
            }

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
