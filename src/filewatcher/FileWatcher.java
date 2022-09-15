package filewatcher;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;

public class FileWatcher {

  public static void main(String[] args) throws IOException {
    Path tmpDir = Paths.get("/tmp");
    WatchService watcherService = Paths.get("/tmp").getFileSystem().newWatchService();
    tmpDir.register(watcherService, StandardWatchEventKinds.ENTRY_CREATE,
        StandardWatchEventKinds.ENTRY_DELETE);
  }
}
