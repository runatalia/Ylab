package internship.fileSort;

import java.io.*;

public class BinaryFileBuffer {
  public static int BUFFERSIZE = 2048;
  public BufferedReader fbr;
  public File originalfile;
  private Long cache;
  private boolean empty;

  public BinaryFileBuffer(File f) throws IOException {
    originalfile = f;
    fbr = new BufferedReader(new FileReader(f), BUFFERSIZE);
    reload();
  }

  public boolean empty() {
    return empty;
  }

  private void reload() throws IOException {
    String line;
    try {
      if ((line = fbr.readLine()) == null) {
        empty = true;
        cache = null;
      } else {
        cache = this.cache = Long.parseLong(line);
        empty = false;
      }
    } catch (EOFException oef) {
      empty = true;
      cache = null;
    }
  }

  public void close() throws IOException {
    fbr.close();
  }

  public long peek() {
    if (empty()) {
      return 0;
    }
    return cache;
  }

  public long pop() throws IOException {
    long answer = peek();
    reload();
    return answer;
  }
}

