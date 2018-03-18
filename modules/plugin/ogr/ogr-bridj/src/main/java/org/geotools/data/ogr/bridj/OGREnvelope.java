package org.geotools.data.ogr.bridj;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

/**
 * <i>native declaration : /home/aaime/devel/gdal/gdal-1.8.0/ogr/ogr_core.h:455</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a
 * href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource
 * projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a
 * href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("ogr")
public class OGREnvelope extends StructObject {
  public OGREnvelope() {
    super();
  }

  public OGREnvelope(Pointer pointer) {
    super(pointer);
  }

  @Field(0)
  public double MinX() {
    return this.io.getDoubleField(this, 0);
  }

  @Field(0)
  public OGREnvelope MinX(double MinX) {
    this.io.setDoubleField(this, 0, MinX);
    return this;
  }

  @Field(1)
  public double MaxX() {
    return this.io.getDoubleField(this, 1);
  }

  @Field(1)
  public OGREnvelope MaxX(double MaxX) {
    this.io.setDoubleField(this, 1, MaxX);
    return this;
  }

  @Field(2)
  public double MinY() {
    return this.io.getDoubleField(this, 2);
  }

  @Field(2)
  public OGREnvelope MinY(double MinY) {
    this.io.setDoubleField(this, 2, MinY);
    return this;
  }

  @Field(3)
  public double MaxY() {
    return this.io.getDoubleField(this, 3);
  }

  @Field(3)
  public OGREnvelope MaxY(double MaxY) {
    this.io.setDoubleField(this, 3, MaxY);
    return this;
  }
}
