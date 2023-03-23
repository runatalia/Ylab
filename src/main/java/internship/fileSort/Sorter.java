package internship.fileSort;

import java.io.*;
import java.util.*;


public class Sorter {
  public File sortFile(File dataFile) throws IOException {
    final Comparator<Long> cmp = Comparator.naturalOrder();
    List<File> files = sortInBatch(dataFile);
    Queue<BinaryFileBuffer> priorityQueue = new PriorityQueue<BinaryFileBuffer>(11,
                new Comparator<BinaryFileBuffer>() {
              public int compare(BinaryFileBuffer i, BinaryFileBuffer j) {
              return cmp.compare(i.peek(), j.peek());
              }
            }
        );

    for (File f : files) {
      BinaryFileBuffer bfb = new BinaryFileBuffer(f);
      priorityQueue.add(bfb);
    }
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));) { //здесь с ридера на райтер поменяла
      while (priorityQueue.size() > 0) {
        BinaryFileBuffer bfb = priorityQueue.poll();
        long r = bfb.pop();
        writer.write(Long.toString(r));
        writer.newLine();
        if (bfb.empty()) {
          bfb.close();
          bfb.originalfile.delete();// we don't need you anymore
        } else {
          priorityQueue.add(bfb); // add it back
        }
      }
    } finally {
      for (BinaryFileBuffer bfb : priorityQueue) {
          bfb.close();
      }
    }
    return dataFile;
  }

  public static List<File> sortInBatch(File dataFile) throws IOException {
    List<File> files = new ArrayList<File>();
    List<Long> tmplist = new ArrayList<Long>();
    long blocksize = maxSizeOfBlocks(dataFile);
    try (Scanner reader = new Scanner(new FileInputStream(dataFile))) {
    Long line = 0L;
      while (reader.hasNextLong()) {
        long currentblocksize = 0;
        while ((currentblocksize < blocksize)
                        && (reader.hasNextLong())) {
          line = reader.nextLong();
          tmplist.add(line);
          currentblocksize += line.toString().length();
          }
        files.add(sortAndSave(tmplist));
          tmplist.clear();
      }
    } catch (EOFException oef) {
      if (tmplist.size() > 0) {
        files.add(sortAndSave(tmplist));
        tmplist.clear();
      }
    }
    return files;
  }

  public static long maxSizeOfBlocks(File file) {
    long sizeoffile = file.length();
    final int MAXTEMPFILES = 1024;
    long blocksize = sizeoffile / MAXTEMPFILES;
    long freeMemory = Runtime.getRuntime().freeMemory();
    if (blocksize < freeMemory / 2) {
    blocksize = freeMemory / 2;
    } else {
      if (blocksize >= freeMemory) {
          System.err.println("Возможно переполнение памяти. ");
      }
    }
    return blocksize;
  }

  public static File sortAndSave(List<Long> tmplist) throws IOException {
    Collections.sort(tmplist);
    File newtmpfile = File.createTempFile("sortInBatch", "flatfile");
    newtmpfile.deleteOnExit();
    try (BufferedWriter fbw = new BufferedWriter(new FileWriter(newtmpfile));) {
      for (Long r : tmplist) {
        fbw.write(r.toString());
        fbw.newLine();
      }
    }
    return newtmpfile;
  }
}
