package business;

import data.Person;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class RandomIO {
    public static final int FRAME_WIDTH = 350;
    public static final int FRAME_HEIGHT = 450;

    /*
     * @ Function Name      : addPerson
     * @ Function Params    : Person person
     * @ Function Purpose   : This method is used to check data already exist and add person data into file
     */
    public static Boolean addPerson(Person person){
        boolean isAdd =false;
        ArrayList<Person> personList=getPersonList();

        if(personList==null){
            personList = new ArrayList<>();
            personList.add(person);
        }else {
            personList.add(person);
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/data/personInfo.dat"));
            oos.writeObject(personList);
            oos.close();
            isAdd =true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Not able to find file.");
        } catch (IOException e) {
            System.out.println("Something happen while writing data into file." + e.getLocalizedMessage());
        }
        return isAdd;
    }

    /*
     * @ Function Name      : findPerson
     * @ Function Params    : String record
     * @ Function Purpose   : This method is used to find person data from file and return person object
     */
    public static Person findPerson(String record){
        Person person=null;
        ArrayList<Person> personList=getPersonList();
        if(personList!=null) {
            System.out.println(personList);
            for (int i = 0; i < personList.size(); i++) {
                if (record.equalsIgnoreCase(personList.get(i).record)) {
                    person = personList.get(i);
                }
            }
        }
        return person;
    }

    /*
     * @ Function Name      : getPersonList
     * @ Function Params    : String record
     * @ Function Purpose   : This method is used to get the list of the person data from file.
     */
    public static ArrayList<Person> getPersonList(){
        File file = new File("src/data/personInfo.dat");
        ArrayList<Person> personList = null;
        if(file.exists()) {
            try (ObjectInputStream inputStream  = new ObjectInputStream(new FileInputStream(file))){
                personList= (ArrayList<Person>) inputStream.readObject();
                System.out.println("Data Read !!");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Not able to find File");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Not able to read file.");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return personList;
    }

    /*
     * @ Function Name      : isRecordAlready
     * @ Function Params    : String record
     * @ Function Purpose   : This method is used to get the list of the products data from file.
     */
    public static Boolean isRecordAlready(String record){
        boolean isAvailable=false;
        ArrayList<Person> personList=getPersonList();
        if(personList!=null){
            for(int i=0;i<personList.size();i++){
                if(record.equalsIgnoreCase(personList.get(i).record)){
                    isAvailable=true;
                }
            }
        }
        return isAvailable;
    }

    /*
     * @ Function Name      : isValidNumber
     * @ Function Params    : String record
     * @ Function Purpose   : This method is used for validate the numbers
     */
    public static boolean isValidNumber(String text) {
        try {
            double number = Double.parseDouble(text);
            return !(number <= 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
