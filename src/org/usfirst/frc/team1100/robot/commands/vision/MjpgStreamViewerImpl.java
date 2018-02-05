package org.usfirst.frc.team1100.robot.commands.vision;

import java.util.stream.Stream;

public class MjpgStreamViewerImpl extends MjpgStreamViewer {

  public final String urlProperty = "http://10.11.00.11:5800";

  private String url = "";

  public MjpgStreamViewerImpl() {
    url = STREAM_PREFIX + urlProperty;
  }

  @Override
  public Stream<String> streamPossibleCameraUrls() {
    return Stream.of(url);
  }

}