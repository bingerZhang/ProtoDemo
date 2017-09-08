/**
 *
 */
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.test.proto.PersonModel.*;
import com.test.proto.AddressBookModel.*;

public class Main {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Person.Builder builder = Person.newBuilder();
        builder.setId(1);
        builder.setName("robin");
        builder.setEmail("robin@163.com");
        builder.setGenderValue(Gender.MAN_VALUE);

        PhoneNumber.Builder pb = PhoneNumber.newBuilder();
        pb.setNumber("123456789");
        pb.setType(PhoneType.HOME);

        builder.addPhone(pb);
        //builder.setPhone(0,pb); bug

        ComplexObject.Builder complexObjectBuilder = ComplexObject.newBuilder();
        MapVaule.Builder mapValueBuilder = MapVaule.newBuilder();
        mapValueBuilder.setId(11);
        mapValueBuilder.setMapValue("mapValue1");
        complexObjectBuilder.putMap("key1",mapValueBuilder.build());

        builder.setComplexObject(complexObjectBuilder);

        Person person = builder.build();

        // 生成最终的对象
        String personStr = person.toString();
        System.out.println("before: " + personStr);
        System.out.println("size: " + personStr.length());
        System.out.println();
        System.out.println("===========Person Byte==========");
        byte[] personByteArray = person.toByteArray();
        for(byte b : personByteArray){
            System.out.print(b);
        }
        System.out.println();
        System.out.println("bytes size: " + personByteArray.length);
        System.out.println();
        ByteString byteString = person.toByteString();
        System.out.println(byteString.toString());
        System.out.println("================================");

        //模拟接收Byte[]，反序列化成Person类
        byte[] byteArray =person.toByteArray();
        Person persion2 = Person.parseFrom(byteArray);
        System.out.println("after :" + persion2.toString());
        System.out.println("equal: " + person.equals(persion2));

        System.out.println("================================");
        AddressBook.Builder addressBookBuilder = AddressBook.newBuilder();
        addressBookBuilder.addPerson(person);
        addressBookBuilder.addPerson(persion2);


        System.out.println(addressBookBuilder.build().toString());
    }
}
