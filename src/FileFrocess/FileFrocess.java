package FileFrocess;

import AccountEntry.AccountEntry;
import Singleton.Singleton;

import java.io.*;

public class FileFrocess {

    private File file = null;

    public FileFrocess(String filename) {
        file = new File("d:/temp/" + filename + ".txt");

        try {
            if(file.createNewFile()) {
                System.out.println("파일 생성 성공!");
            }else {
                System.out.println("파일 생성 실패~");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write() {

        Singleton s = Singleton.getInstance();

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

            for (int i = 0; i < s.accountList.size(); i++) {
                AccountEntry ae = s.accountList.get(i);
                pw.println(ae.getAll());
            }

            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("파일에 저장되었습니다");
    }

    public void read() {

        Singleton s = Singleton.getInstance();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str = "";
            while((str = br.readLine()) != null) {

                String split[] = str.split("-");

                AccountEntry ae = new AccountEntry(
                        split[0],
                        split[1],
                        Boolean.parseBoolean(split[2]),
                        Integer.parseInt(split[3]),
                        split[4]);
                s.accountList.add(ae);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("데이터를 모두 읽어들였습니다");
    }

}