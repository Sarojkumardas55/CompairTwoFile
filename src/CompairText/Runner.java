package CompairText;
import java.io.*;

public class Runner {
    public static void main(String[] args) {
        //writeFile();
       // FileManage fileManage=new FileManage();
       // fileManage.writeFile();
        Factoryclass factoryclass=new Factoryclass();
        CompairtwoFile managefile=Factoryclass.getUser("FILEMANAGE");
        managefile.writeFile();

    }

}
interface CompairtwoFile{
     int compairFile();
    void writeFile();
}
class Factoryclass {
    public static CompairtwoFile getUser(String user){
        if(user == null){
            return null;
        }
        if(user.equalsIgnoreCase("FILECOMPAIR")){
            return new FileCompair();

        } else if(user.equalsIgnoreCase("FILEMANAGE")){
            return new FileManage();

        }

        return null;
    }
}
class  FileCompair implements CompairtwoFile{
      public int compairFile() {
        int count = 0;
        BufferedReader reader1 = null;
        BufferedReader reader2 = null;
        try {
           reader1 = new BufferedReader(new FileReader("D:\\file1.txt"));
           // reader1 = new BufferedReader(new FileReader("C:\\Users\\saroj.das\\IdeaProjects\\CompairTwoFile\\src\\saroj1.json"));
           // reader2 = new BufferedReader(new FileReader("C:\\Users\\saroj.das\\IdeaProjects\\CompairTwoFile\\src\\saroj2.json"));
          reader2 = new BufferedReader(new FileReader("D:\\file2.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            //e.printStackTrace();
        }

        String line1 = null;
        String line2 = null;
        try {
            //assert reader1 != null;
            line1 = reader1.readLine();

            line2 = reader2.readLine();
        } catch (IOException e) {
            System.out.println("IoException");
            //e.printStackTrace();
        }
        boolean areEqual = true;
        int lineNum = 1;
        while (line1 != null || line2 != null) {
            if (line1 == null || line2 == null) {
                areEqual = false;

                break;
            } else if (!line1.equalsIgnoreCase(line2)) {
                areEqual = false;

                break;
            }

            try {
                line1 = reader1.readLine();


                line2 = reader2.readLine();
            } catch (IOException e) {
                System.out.println("IoException");
                // e.printStackTrace();
            }

            lineNum++;
            //System.out.println("lineNum"+lineNum);
        }

        if (areEqual) {
            System.out.println("Two files have same content.");
            String line;
            //int count = 0;

            //Opens a file in read mode
            FileReader file = null;
            try {
                file = new FileReader("D:\\file1.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader br = new BufferedReader(file);

            //Gets each line till end of file is reached
            while (true) {
                try {
                    if ((line = br.readLine()) == null) break;

                    //Splits each line into words
                    String words[] = line.split(" ");
                    //Counts each word
                    count = count + words.length;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Number of words present in given file: " + count);
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Two files have different content. They differ at line " + lineNum);

            System.out.println("File1 has " + line1 + " and File2 has " + line2 + " at line " + lineNum);
        }

        try {
            reader1.close();

            reader2.close();
        } catch (IOException e) {
            System.out.println("IoException");
            //e.printStackTrace();
        }
        return count;
    }

    @Override
    public void writeFile() {

    }
}
    class FileManage implements CompairtwoFile {
    FileCompair fileCompair=new FileCompair();


        public static void createNewFile() {

            try {
                File file = new File("Saroj1.json");
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        @Override
        public int compairFile() {
            return 0;
        }

        public void writeFile() {
            //int x = CompairText.FileCompair.compairFile();
            int x=fileCompair.compairFile();

            System.out.println("COUNT = "+x);
            if (x == 0) {
                try {
                    FileWriter myWriter = new FileWriter("Saroj.json");
                    myWriter.write("{two files are different}");
                    //System.out.println();

                    myWriter.close();

                    System.out.println("");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

            } else {
                try {
                    FileWriter myWriter = new FileWriter("Saroj.json");
                    myWriter.write("No of words " + x);
                    //System.out.println();"Country":"India"

                    myWriter.close();

                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }

        }
    }

