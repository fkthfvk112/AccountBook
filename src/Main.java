import AccountBook.AccountBook;
import FileFrocess.FileFrocess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            AccountBook accountBook = new AccountBook();
            Scanner sc = new Scanner(System.in);
            FileFrocess fc = new FileFrocess("myAccount");

            boolean loopCon = true;
            while(loopCon) {
                System.out.println("account book menu ---------------------");
                System.out.println("1. 추가");
                System.out.println("2. 용도로  검색 -> 삭제");
                System.out.println("3. 날짜로 검색");
                System.out.println("4. 목적으로 검색 -> 수정");
                System.out.println("5. 모두출력");
                System.out.println("6. 월별 집계");
                System.out.println("7. 기간별 집계");
                System.out.println("8. 불러오기");
                System.out.println("9. 저장");
                System.out.println("10. 종료");


                System.out.print("menu number >> ");
                int menuNumber = sc.nextInt();

                switch(menuNumber)
                {
                    case 1:
                        accountBook.addEntry();
                        break;
                    case 2:
                        System.out.print("용도 입력하세요. : ");
                        String purpose = sc.next();
                        accountBook.deleteEntryByPurpose(purpose);
                        break;
                    case 3:
                        System.out.print("날짜를 입력하세요. : ");
                        String date = sc.next();
                        accountBook.searchEntryByDate(date);
                        break;
                    case 4:
                        System.out.print("용도 입력하세요. : ");
                        String purpose_ = sc.next();
                        accountBook.updateEntryByPurpose(purpose_);
                        break;
                    case 5:
                        accountBook.printAll();
                        break;
                    case 6:
                        accountBook.aggregationByMonth();
                        break;
                    case 7:
                        System.out.println("두 기간을 입력하세요 ex)2023/12/12 : ");
                        String date1 = sc.next();
                        String date2 = sc.next();
                        accountBook.aggregationWithTwoDate(date1, date2);
                        break;
                    case 8:
                        fc.read();
                        break;
                    case 9:
                        fc.write();
                        break;
                    case 10:
                        loopCon = false;
                        break;
                }
            }
        }
}