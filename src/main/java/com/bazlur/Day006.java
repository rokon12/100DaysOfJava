package com.bazlur;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class Day006 {

  public static void main(String[] args) throws IOException, InterruptedException {
    watchIt("/Users/bazlur/myDir");
  }

  private static void watchIt(String toWatch) throws IOException, InterruptedException {
    var watcher = FileSystems.getDefault().newWatchService();
    var dir = FileSystems.getDefault().getPath(toWatch);
    var watchKey = dir.register(watcher, ENTRY_MODIFY);

    while (true) {
      watchKey = watcher.take();
      watchKey.pollEvents()
              .stream()
              .filter(event -> event.kind() == ENTRY_MODIFY)
              .forEach(Day006::publishChangeEvent);
      if (!watchKey.reset()) {
        break;
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static void publishChangeEvent(WatchEvent<?> event) {
    WatchEvent<Path> ev = (WatchEvent<Path>) event;
    Path filename = ev.context();
    System.out.println(filename + " has changed.");
  }
}
