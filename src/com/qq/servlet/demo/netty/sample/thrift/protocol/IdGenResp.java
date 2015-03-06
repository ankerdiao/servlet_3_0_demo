/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.qq.servlet.demo.netty.sample.thrift.protocol;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-1-19")
public class IdGenResp implements org.apache.thrift.TBase<IdGenResp, IdGenResp._Fields>, java.io.Serializable, Cloneable, Comparable<IdGenResp> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("IdGenResp");

  private static final org.apache.thrift.protocol.TField GEN_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("genId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField ERR_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("errCode", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField ERR_MSG_FIELD_DESC = new org.apache.thrift.protocol.TField("errMsg", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField OUT_RESERVED_FIELD_DESC = new org.apache.thrift.protocol.TField("outReserved", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new IdGenRespStandardSchemeFactory());
    schemes.put(TupleScheme.class, new IdGenRespTupleSchemeFactory());
  }

  public long genId; // required
  public long errCode; // required
  public String errMsg; // optional
  public String outReserved; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    GEN_ID((short)1, "genId"),
    ERR_CODE((short)2, "errCode"),
    ERR_MSG((short)3, "errMsg"),
    OUT_RESERVED((short)4, "outReserved");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // GEN_ID
          return GEN_ID;
        case 2: // ERR_CODE
          return ERR_CODE;
        case 3: // ERR_MSG
          return ERR_MSG;
        case 4: // OUT_RESERVED
          return OUT_RESERVED;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __GENID_ISSET_ID = 0;
  private static final int __ERRCODE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.ERR_MSG,_Fields.OUT_RESERVED};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.GEN_ID, new org.apache.thrift.meta_data.FieldMetaData("genId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.ERR_CODE, new org.apache.thrift.meta_data.FieldMetaData("errCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.ERR_MSG, new org.apache.thrift.meta_data.FieldMetaData("errMsg", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.OUT_RESERVED, new org.apache.thrift.meta_data.FieldMetaData("outReserved", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(IdGenResp.class, metaDataMap);
  }

  public IdGenResp() {
  }

  public IdGenResp(
    long genId,
    long errCode)
  {
    this();
    this.genId = genId;
    setGenIdIsSet(true);
    this.errCode = errCode;
    setErrCodeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public IdGenResp(IdGenResp other) {
    __isset_bitfield = other.__isset_bitfield;
    this.genId = other.genId;
    this.errCode = other.errCode;
    if (other.isSetErrMsg()) {
      this.errMsg = other.errMsg;
    }
    if (other.isSetOutReserved()) {
      this.outReserved = other.outReserved;
    }
  }

  public IdGenResp deepCopy() {
    return new IdGenResp(this);
  }

  @Override
  public void clear() {
    setGenIdIsSet(false);
    this.genId = 0;
    setErrCodeIsSet(false);
    this.errCode = 0;
    this.errMsg = null;
    this.outReserved = null;
  }

  public long getGenId() {
    return this.genId;
  }

  public IdGenResp setGenId(long genId) {
    this.genId = genId;
    setGenIdIsSet(true);
    return this;
  }

  public void unsetGenId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __GENID_ISSET_ID);
  }

  /** Returns true if field genId is set (has been assigned a value) and false otherwise */
  public boolean isSetGenId() {
    return EncodingUtils.testBit(__isset_bitfield, __GENID_ISSET_ID);
  }

  public void setGenIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __GENID_ISSET_ID, value);
  }

  public long getErrCode() {
    return this.errCode;
  }

  public IdGenResp setErrCode(long errCode) {
    this.errCode = errCode;
    setErrCodeIsSet(true);
    return this;
  }

  public void unsetErrCode() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ERRCODE_ISSET_ID);
  }

  /** Returns true if field errCode is set (has been assigned a value) and false otherwise */
  public boolean isSetErrCode() {
    return EncodingUtils.testBit(__isset_bitfield, __ERRCODE_ISSET_ID);
  }

  public void setErrCodeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ERRCODE_ISSET_ID, value);
  }

  public String getErrMsg() {
    return this.errMsg;
  }

  public IdGenResp setErrMsg(String errMsg) {
    this.errMsg = errMsg;
    return this;
  }

  public void unsetErrMsg() {
    this.errMsg = null;
  }

  /** Returns true if field errMsg is set (has been assigned a value) and false otherwise */
  public boolean isSetErrMsg() {
    return this.errMsg != null;
  }

  public void setErrMsgIsSet(boolean value) {
    if (!value) {
      this.errMsg = null;
    }
  }

  public String getOutReserved() {
    return this.outReserved;
  }

  public IdGenResp setOutReserved(String outReserved) {
    this.outReserved = outReserved;
    return this;
  }

  public void unsetOutReserved() {
    this.outReserved = null;
  }

  /** Returns true if field outReserved is set (has been assigned a value) and false otherwise */
  public boolean isSetOutReserved() {
    return this.outReserved != null;
  }

  public void setOutReservedIsSet(boolean value) {
    if (!value) {
      this.outReserved = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case GEN_ID:
      if (value == null) {
        unsetGenId();
      } else {
        setGenId((Long)value);
      }
      break;

    case ERR_CODE:
      if (value == null) {
        unsetErrCode();
      } else {
        setErrCode((Long)value);
      }
      break;

    case ERR_MSG:
      if (value == null) {
        unsetErrMsg();
      } else {
        setErrMsg((String)value);
      }
      break;

    case OUT_RESERVED:
      if (value == null) {
        unsetOutReserved();
      } else {
        setOutReserved((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case GEN_ID:
      return Long.valueOf(getGenId());

    case ERR_CODE:
      return Long.valueOf(getErrCode());

    case ERR_MSG:
      return getErrMsg();

    case OUT_RESERVED:
      return getOutReserved();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case GEN_ID:
      return isSetGenId();
    case ERR_CODE:
      return isSetErrCode();
    case ERR_MSG:
      return isSetErrMsg();
    case OUT_RESERVED:
      return isSetOutReserved();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof IdGenResp)
      return this.equals((IdGenResp)that);
    return false;
  }

  public boolean equals(IdGenResp that) {
    if (that == null)
      return false;

    boolean this_present_genId = true;
    boolean that_present_genId = true;
    if (this_present_genId || that_present_genId) {
      if (!(this_present_genId && that_present_genId))
        return false;
      if (this.genId != that.genId)
        return false;
    }

    boolean this_present_errCode = true;
    boolean that_present_errCode = true;
    if (this_present_errCode || that_present_errCode) {
      if (!(this_present_errCode && that_present_errCode))
        return false;
      if (this.errCode != that.errCode)
        return false;
    }

    boolean this_present_errMsg = true && this.isSetErrMsg();
    boolean that_present_errMsg = true && that.isSetErrMsg();
    if (this_present_errMsg || that_present_errMsg) {
      if (!(this_present_errMsg && that_present_errMsg))
        return false;
      if (!this.errMsg.equals(that.errMsg))
        return false;
    }

    boolean this_present_outReserved = true && this.isSetOutReserved();
    boolean that_present_outReserved = true && that.isSetOutReserved();
    if (this_present_outReserved || that_present_outReserved) {
      if (!(this_present_outReserved && that_present_outReserved))
        return false;
      if (!this.outReserved.equals(that.outReserved))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_genId = true;
    list.add(present_genId);
    if (present_genId)
      list.add(genId);

    boolean present_errCode = true;
    list.add(present_errCode);
    if (present_errCode)
      list.add(errCode);

    boolean present_errMsg = true && (isSetErrMsg());
    list.add(present_errMsg);
    if (present_errMsg)
      list.add(errMsg);

    boolean present_outReserved = true && (isSetOutReserved());
    list.add(present_outReserved);
    if (present_outReserved)
      list.add(outReserved);

    return list.hashCode();
  }

  @Override
  public int compareTo(IdGenResp other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetGenId()).compareTo(other.isSetGenId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGenId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.genId, other.genId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetErrCode()).compareTo(other.isSetErrCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErrCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.errCode, other.errCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetErrMsg()).compareTo(other.isSetErrMsg());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErrMsg()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.errMsg, other.errMsg);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOutReserved()).compareTo(other.isSetOutReserved());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOutReserved()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.outReserved, other.outReserved);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("IdGenResp(");
    boolean first = true;

    sb.append("genId:");
    sb.append(this.genId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("errCode:");
    sb.append(this.errCode);
    first = false;
    if (isSetErrMsg()) {
      if (!first) sb.append(", ");
      sb.append("errMsg:");
      if (this.errMsg == null) {
        sb.append("null");
      } else {
        sb.append(this.errMsg);
      }
      first = false;
    }
    if (isSetOutReserved()) {
      if (!first) sb.append(", ");
      sb.append("outReserved:");
      if (this.outReserved == null) {
        sb.append("null");
      } else {
        sb.append(this.outReserved);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class IdGenRespStandardSchemeFactory implements SchemeFactory {
    public IdGenRespStandardScheme getScheme() {
      return new IdGenRespStandardScheme();
    }
  }

  private static class IdGenRespStandardScheme extends StandardScheme<IdGenResp> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, IdGenResp struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // GEN_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.genId = iprot.readI64();
              struct.setGenIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ERR_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.errCode = iprot.readI64();
              struct.setErrCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ERR_MSG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.errMsg = iprot.readString();
              struct.setErrMsgIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // OUT_RESERVED
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.outReserved = iprot.readString();
              struct.setOutReservedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, IdGenResp struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(GEN_ID_FIELD_DESC);
      oprot.writeI64(struct.genId);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(ERR_CODE_FIELD_DESC);
      oprot.writeI64(struct.errCode);
      oprot.writeFieldEnd();
      if (struct.errMsg != null) {
        if (struct.isSetErrMsg()) {
          oprot.writeFieldBegin(ERR_MSG_FIELD_DESC);
          oprot.writeString(struct.errMsg);
          oprot.writeFieldEnd();
        }
      }
      if (struct.outReserved != null) {
        if (struct.isSetOutReserved()) {
          oprot.writeFieldBegin(OUT_RESERVED_FIELD_DESC);
          oprot.writeString(struct.outReserved);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class IdGenRespTupleSchemeFactory implements SchemeFactory {
    public IdGenRespTupleScheme getScheme() {
      return new IdGenRespTupleScheme();
    }
  }

  private static class IdGenRespTupleScheme extends TupleScheme<IdGenResp> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, IdGenResp struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetGenId()) {
        optionals.set(0);
      }
      if (struct.isSetErrCode()) {
        optionals.set(1);
      }
      if (struct.isSetErrMsg()) {
        optionals.set(2);
      }
      if (struct.isSetOutReserved()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetGenId()) {
        oprot.writeI64(struct.genId);
      }
      if (struct.isSetErrCode()) {
        oprot.writeI64(struct.errCode);
      }
      if (struct.isSetErrMsg()) {
        oprot.writeString(struct.errMsg);
      }
      if (struct.isSetOutReserved()) {
        oprot.writeString(struct.outReserved);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, IdGenResp struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.genId = iprot.readI64();
        struct.setGenIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.errCode = iprot.readI64();
        struct.setErrCodeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.errMsg = iprot.readString();
        struct.setErrMsgIsSet(true);
      }
      if (incoming.get(3)) {
        struct.outReserved = iprot.readString();
        struct.setOutReservedIsSet(true);
      }
    }
  }

}

