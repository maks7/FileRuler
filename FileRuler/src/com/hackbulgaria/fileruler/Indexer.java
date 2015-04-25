package com.hackbulgaria.fileruler;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field; 
import org.apache.lucene.document.StringField; 
import org.apache.lucene.document.TextField; 
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Indexer {
    
    /** Creates a new instance of Indexer */
    public Indexer() {
    }
 
    private IndexWriter indexWriter = null;
    
    public IndexWriter getIndexWriter(boolean create) throws IOException {
        if (indexWriter == null) {
            Directory indexDir = FSDirectory.open(Paths.get("index-directory"));
            IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
            indexWriter = new IndexWriter(indexDir, config);
        }
        return indexWriter;
   }    
   
    public void closeIndexWriter() throws IOException {
        if (indexWriter != null) {
            indexWriter.close();
        }
   }
    
    public void indexHotel(Movie movie) throws IOException {

        System.out.println("Indexing hotel: " + movie);
        IndexWriter writer = getIndexWriter(false);
        Document doc = new Document();
//        doc.add(new StringField("id", movie.getId(), Field.Store.YES));
        doc.add(new StringField("name", movie.getName(), Field.Store.YES));
        doc.add(new StringField("yearOfRelease", String.format("%s", movie.getYearOfRelease()), Field.Store.YES));
        doc.add(new StringField("actors", movie.getActors().toString(), Field.Store.YES));
        doc.add(new StringField("scenarist", movie.getScenarist(), Field.Store.YES));
        doc.add(new StringField("director", movie.getDirector(), Field.Store.YES));
        doc.add(new StringField("runtime", String.format("%s", movie.getRuntime()), Field.Store.YES));
        doc.add(new StringField("path", String.format("%s", movie.getFilePath().toString()), Field.Store.YES));
        //Add doc.add for different class vars.
        //doc.add(new StringField("city", movie.getCity(), Field.Store.YES));
//        String fullSearchableText = movie.getName() + " " + movie.getCity() + " " + movie.getDescription();
        String fullSearchableText = movie.toString();
        doc.add(new TextField("content", fullSearchableText, Field.Store.NO));
        writer.addDocument(doc);
    }   
    
    public void rebuildIndexes(MoviesCollecion collection) throws IOException {
          //
          // Erase existing index
          //
          getIndexWriter(true);
          //
          // Index all Accommodation entries
          //
          CopyOnWriteArrayList<Movie> movies = collection.getMovies();
          for(Movie movie : movies) {
              indexHotel(movie);              
          }
          //
          // Don't forget to close the index writer when done
          //
          closeIndexWriter();
     }    
}

