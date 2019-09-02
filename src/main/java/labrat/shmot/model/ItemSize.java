package labrat.shmot.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.HashMap;

@Embeddable
public class ItemSize implements Serializable {

    @Column(nullable = false)
    public byte Size;

    public byte AdditionSize;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public SizeType Type;

    public ItemSize(){
        Type=SizeType.A;
    }

    public ItemSize(byte size, byte add, SizeType type){
        Size=size;
        AdditionSize =add;
        Type=type;
    }

    public boolean hasAddition(){
        return false;//todo
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ItemSize)) return false;
        ItemSize r = (ItemSize)obj;
        return Type==r.Type && Size==r.Size && AdditionSize ==r.AdditionSize;
    }

    @Override
    public int hashCode() {
        return Size*71+ AdditionSize +Type.ordinal()*17;
    }

    @Override
    public Object clone() {
        return new ItemSize(Size, AdditionSize,Type);
    }

    public enum SizeType{
        A,
        B;

        static HashMap<Integer,SizeType> hashMap;

        static {
            hashMap = new HashMap<>();
            for(SizeType st : SizeType.values())
                hashMap.put(st.ordinal(),st);
        }

        static SizeType valueOf(int i){
            return hashMap.get(i);
        }
    }
}
