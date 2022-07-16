import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.sql.*;

public class claContact {
    String Name;
    String MobileNo;
    String Email;
    String Address;

    String strFileName = "contactRAN";
    int iLengthRecord = iLengthName+iLengthMobileNo+iLengthAddress+iLengthEmail+52;
    static int iLengthName = 20;
    static int iLengthMobileNo = 11;
    static int iLengthEmail = 40;
    static int iLengthAddress = 40;

    public claContact(String name, String MobileNo, String Email, String Address) {
        name = name.trim();
        MobileNo = MobileNo.trim();
        Email = Email.trim();
        Address = Address.trim();

        this.Name = name.length()>20? name.substring(0,20): name;
        this.MobileNo = MobileNo.length()>11? MobileNo.substring(0,11):MobileNo;
        this.Email = Email.length()>40?Email.substring(0,40):Email;
        this.Address = Address.length()>40?Address.substring(0,40):Address;

    }

    public String getName() {
        return Name;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public String getEmail() {
        return Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    @Override
    public String toString() {

        String str = null;
        str =  "{" +
                "\"Name\":\"" + Name + "\"" +
                ",\"MobileNo\":\"" + MobileNo  + "\"" +
                ",\"Email\":\"" + Email  + "\"" +
                ",\"Address\":\"" + Address  + "\""+
                 "}\n";
        str = String.format("{\"Name\":\"%20s\",\"MobileNo\":\"%11s\",\"Email\":\"%40s\",\"Address\":\"%40s\"}\n",
                Name,MobileNo, Email,Address);
        System.out.println("toString len = " + str.length());
        return str;
    }


    public boolean insert( ) {
        System.out.println("\n to insert ...");

        // we can do it through  Resource/proerties way  key-value, but REad only, can't update.
        if ( this.locate() >= 0) {    // already exist
            System.out.println(" already exist, not insert , end");
            return false;
        }


        String fileName = new String(strFileName);
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
             // Write a message
            raf.seek(raf.length());
            raf.writeUTF(this.toString());
            //            raf.writeChars(this.toString());  waste too much space
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        System.out.println("  inserted end");

        return true;
    }

    public boolean delete( ) {
        System.out.println("\n to delete record ...");
        String strDataRead = new String();
        long lPos = this.locate( );
        System.out.println("lPos  =  " + lPos);

        if (lPos < 0)  { System.out.println("lPos  < 0  not found, end " + lPos); return false ; }

        try (RandomAccessFile raf = new RandomAccessFile(strFileName, "rw")) {

            for ( int i =0; ; i++) {
                System.out.println("  loop lPost= " + lPos);
                if ( lPos + iLengthRecord >= raf.length() ) {
                    System.out.println("  moving end of the file, will setLength, end deleting ");
                    raf.setLength(lPos);
                    return true;
                }
                raf.seek(lPos + iLengthRecord);

                String msg = raf.readUTF();
                raf.seek(lPos);
                raf.writeUTF(msg);

                // lPos = raf.getFilePointer();
                lPos = lPos + iLengthRecord;
            }
        } catch (EOFException e ) {
            System.out.println(" end of file, still not found !");
            e.printStackTrace();
            return false;
        } catch (  IOException e ) {
            e.printStackTrace();
            return false;
        }

     }

    public boolean update( ) {
        System.out.println("\n to update record ...");
        String strDataRead = new String();
        long lPos = this.locate( );
        System.out.println("lPos  =  " + lPos);
        if (lPos < 0)  {
            System.out.println("lPos < 0  not found, end update" + lPos);
            return false ;
        }

        try (RandomAccessFile raf = new RandomAccessFile(strFileName, "rw")) {
                raf.seek(lPos );
                String msg = this.toString();
                raf.writeUTF(msg);
                System.out.println(" updated , end ");

        } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                return false;
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
      return true;
    }

    public long locate() {
        String fileName = strFileName;
        File fileObject = new File(fileName);
        JSONObject json1 = new JSONObject();
        String strMobileNo =null;

        System.out.println("\n to locate...[" + this.MobileNo+"]");

        if (!fileObject.exists()) {
            System.out.println(" find not existed, end locate, -1 ...");
            return -1;
        }
        // Open the file in read-write mode
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {

            long lPos = 0 ;
            for ( int i = 0 ;  ; i ++ ) {
                lPos = i * iLengthRecord;
                if (lPos >= raf.length()) {
                    System.out.println(" reach the end of the file, not located ");
                    return -1;
                 }
                raf.seek( lPos );
                String msg = raf.readUTF();
                System.out.println(msg.length()  + " length, row Text: " +   msg);

                json1 = JSON.parseObject(msg);

            //    System.out.println("    json: " + json1.toJSONString());

                strMobileNo = (String) json1.get("MobileNo");

                if (strMobileNo.trim().equals(this.MobileNo.trim())) {
                    System.out.println(" locate it, end [" + strMobileNo + "] at lPos = "+ lPos );
                    return lPos;
                }

            }
        } catch (EOFException e ) {
            System.out.println(" EOF end of file exception ,   met err!");
            e.printStackTrace();
            return -1;
        } catch (  IOException e ) {
            System.out.println(" IO exception ,   met err!");
            e.printStackTrace();
            return -1;
        }

        // return -1;
    }
    public String find( ) {
        System.out.println("\n to find record ...");
        String strDataRead = new String();
        long lPos = this.locate( );
        System.out.println("lPos  =  " + lPos);
        String msg = "";
        if (lPos < 0)  {
            System.out.println("lPos < 0  not located, end find" + lPos);
            return msg ;
        }

        try (RandomAccessFile raf = new RandomAccessFile(strFileName, "r")) {
            raf.seek(lPos );
            msg = raf.readUTF();
            System.out.println(" find  , end ");
            return msg;

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return msg;
        } catch (IOException ex) {
            ex.printStackTrace();
            return msg;
        }
        // return true;
    }

    public boolean dbinsert( ) {
        System.out.println("\n to insert db ...");
        if ( (this.Name== null) || this.Name.equals("") )  {

        }
        try (
                // Step 1: Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/dbdemo?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "root", "123456"); // for MySQL only

                // Step 2: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();
        ) {
            // Step 3 & 4: Execute a SQL INSERT|DELETE statement via executeUpdate(),
            //   which returns an int indicating the number of rows affected.



            // INSERT a record
            String sqlInsert = "insert into Contact values (" +this.Name+"," + this.MobileNo + "," +  this.Email + "," + this.Address + " )";
            System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
            int countInserted = stmt.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");


        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources
        return true;
    }

    public String dbfind() {
        System.out.println("\nto find db record ...");
        System.out.println("object.toString()="+this.toString());
        String strReturn = new String();
        JSONObject jsonOut = new JSONObject();

        try (
                // Step 1: Construct a database 'Connection' object called 'conn'
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/dbdemo?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "root", "123456");   // For MySQL only
                // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

                // Step 2: Construct a 'Statement' object called 'stmt' inside the Connection created
                Statement stmt = conn.createStatement();
        ) {
            // Step 3: Write a SQL query string. Execute the SQL query via the 'Statement'.
            //  The query result is returned in a 'ResultSet' object called 'rset'.
            String strSelect = "select Name,MobileNo, Email,Address from Contact where MobileNo=\""+ this.MobileNo +"\"";
            System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging

            ResultSet rset = stmt.executeQuery(strSelect);

            // Step 4: Process the 'ResultSet' by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
            System.out.println("The records selected are:");
            int rowCount = 0;
            // Row-cursor initially positioned before the first row of the 'ResultSet'.
            // rset.next() inside the whole-loop repeatedly moves the cursor to the next row.
            // It returns false if no more rows.
            while (rset.next()) {   // Repeatedly process each row
                String Name = rset.getString("Name");  // retrieve a 'double'-cell in the row

                String MobileNo = rset.getString("MobileNo");  // retrieve a 'double'-cell in the row

                String Email = rset.getString("Email");  // retrieve a 'double'-cell in the row
                String Address = rset.getString("Address");       // retrieve a 'int'-cell in the row
                System.out.println(Name + ", " + MobileNo + ", " + Email + ", " + Address);
                ++rowCount;
                this.Name = Name;
                this.MobileNo = MobileNo;
                this.Email = Email;
                this.Address = Address;
            }
            System.out.println("Total number of records = " + rowCount);
            if ( rowCount == 0 ) {
                jsonOut.put("ReturnCode","SUCC");
                jsonOut.put("ReturnMesg","not found");
                System.out.println("return: "+jsonOut.toString());
                return  jsonOut.toString();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            jsonOut.put("ReturnCode","FAIL");
            jsonOut.put("ReturnMesg","Db error");
            return  jsonOut.toString();
        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)

        System.out.println("end of find contact.toString()="+this.toString());
        jsonOut.put("ReturnCode","0000");
        jsonOut.put("ReturnMesg","SUCC");
        jsonOut.put("data.Name", this.Name);
        jsonOut.put("data.MobileNo", this.MobileNo);
        jsonOut.put("data.Email", this.Email);
        jsonOut.put("data.Address", this.Address);

        return jsonOut.toString();


     }
}
