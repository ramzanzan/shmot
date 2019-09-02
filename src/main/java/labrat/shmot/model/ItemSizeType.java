package labrat.shmot.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemSizeType implements CompositeUserType {
    @Override
    public String[] getPropertyNames() {
        return new String[]{"Size","AdditionSize","Type"};
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[]{
                StandardBasicTypes.BYTE, StandardBasicTypes.BYTE, StandardBasicTypes.BYTE
        };
    }

    @Override
    public Object getPropertyValue(Object component, int property) throws HibernateException {
        ItemSize is = (ItemSize)component;
        switch (property){
            case 0: return is.Size;
            case 1: return is.AdditionSize;
            case 2: return is.Type;
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
        throw new UnsupportedOperationException("ItemSize is immutable");
//        ItemSize is = (ItemSize)component;
//        switch (property){
//            case 0: is.Size = (byte)value;
//            case 1: is.Addition = (byte)value;
//            case 2: is.Type = (ItemSize.SizeType)value;
//            default: throw new IllegalArgumentException();
//        }
    }

    @Override
    public Class returnedClass() {
        return ItemSize.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x.equals(y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        return new ItemSize(rs.getByte(names[0]),
                rs.getByte(names[1]),
                ItemSize.SizeType.valueOf(rs.getByte(names[2])));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if(value==null){
            st.setNull(index++,StandardBasicTypes.BYTE.sqlType());
            st.setNull(index++,StandardBasicTypes.BYTE.sqlType());
            st.setNull(index,StandardBasicTypes.BYTE.sqlType());
        }else {
            ItemSize is = (ItemSize)value;
            st.setByte(index++,is.Size);
            st.setByte(index++,is.AdditionSize);
            st.setByte(index,(byte)is.Type.ordinal());
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return ((ItemSize)value).clone();
    }

    @Override
    public boolean isMutable() { return true; }

    @Override
    public Serializable disassemble(Object value, SharedSessionContractImplementor session) throws HibernateException {
        return (Serializable)((ItemSize)value).clone();
    }

    @Override
    public Object assemble(Serializable cached, SharedSessionContractImplementor session, Object owner) throws HibernateException {
        return ((ItemSize)cached).clone();
    }

    @Override
    public Object replace(Object original, Object target, SharedSessionContractImplementor session, Object owner) throws HibernateException {
        return original;
    }
}
