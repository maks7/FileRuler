package com.hackbulgaria.fileruler;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CrawlFiles extends SimpleFileVisitor<Path> {

    public final Path startingPath;

    public CrawlFiles(Path startingPath) {
        this.startingPath = startingPath;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        if (attr.isRegularFile()) {
            HDDCrawler.fillTheCollection(file.toString());
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }

    public void crawl() {
        CrawlFiles pf = new CrawlFiles(startingPath);
        try {
            Files.walkFileTree(startingPath, pf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
