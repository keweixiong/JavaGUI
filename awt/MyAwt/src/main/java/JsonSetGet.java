import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

//      load data into Json one by one
//      load data into Json from string
//      show Json contents
public class JsonSetGet {
        public static void main(String [] args)   {
                JSONObject json1 = new JSONObject();

                // put() data one by one;
                System.out.println("to load data into Json with put() ...");
                json1.put("address",null);
                json1.put("age",2);
                json1.put("boolean",true);
                json1.put("name","Michael Jackson");

                /**  print the content in JSON with  get() or string format */
                System.out.println("\n1 get(age)  ="+json1.get("age")+1);
                System.out.println("get(name) ="+json1.get("name")+1);
                System.out.println("print the full Json content  toJSONString() or toString()");
                System.out.println("Json1.toString():"+json1.toString());
                System.out.println("Json2.toJSONString():"+json1.toJSONString());

                // load jason data from string
                System.out.println("\n2 load Json data from String and print it");
                JSONObject json2 = new JSONObject();
                String str = json1.toJSONString();
                json2 =JSON.parseObject(str);
                json2.put("zoo ", "addition zoo");
                System.out.println("Json2.toString():"+json2.toString());
                System.out.println("Json2.toJSONString():"+json2.toJSONString());

                // update or init the value with   SET, and  multi-level value
                System.out.println("\n3 update the value with set()");
                JSONPath.set(json1,"name","new name");
                JSONPath.set(json1, "data.part1", "part1");
                JSONPath.set(json1, "data.part2", "part2");

                System.out.println("Json1.toString():"+json1.toString() );


                // merge 2 json ,     add json1, json2 to json3
                System.out.println("\n4 merge 2 json with putAll()");
                JSONObject json3 = new JSONObject();
                System.out.println("Json3.toString():"+json3.toString() );
                System.out.println("Json1.toString():"+json1.toString() );

                json3.putAll(json1);   /** will replace the same name value */
                System.out.println("Json3.toString() after putAll(json1):"+json3.toString() );
                json3.putAll(json2);
                System.out.println("Json2.toString():"+json2.toString() );
                System.out.println("Json3.toString() after putAll(json1)(json2):"+json3.toString() );
                IOMyLog.mylog( "console", "json3= " + json3.toString());

        }
    }