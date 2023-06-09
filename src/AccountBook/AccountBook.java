package AccountBook;

import AccountEntry.AccountEntry;
import Singleton.Singleton;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AccountBook {
    private Scanner sc;
    public AccountBook(){
        sc = new Scanner(System.in);
    }
    public void printAll(){
        Singleton s = Singleton.getInstance();
        for(AccountEntry ae:s.accountList){
            ae.printAll();
        }
    }
    public void addEntry(){
        System.out.print("날짜, 목적, 수입 혹은 지출 여부, 지출 금액, 세부사항 입력");
        String date = sc.next();
        String purpose = sc.next();
        String isIncome_s = sc.next();
        boolean isIncome;
        int amount = sc.nextInt();
        String detail = sc.next();

        if(isIncome_s.equals("수입")){
            isIncome = true;
        }
        else{
            isIncome = false;
        }

        Singleton s = Singleton.getInstance();
        s.accountList.add(new AccountEntry(date, purpose, isIncome, amount, detail));
    }

    public void searchEntryByDate(String date){
        Singleton s = Singleton.getInstance();
        for(AccountEntry ae : s.accountList){
            if(ae.getDate().equals(date)){
                ae.printAll();
            }
        }
    }

    public void updateEntryByPurpose(String purpose){
        Singleton s = Singleton.getInstance();
        for(AccountEntry ae : s.accountList){
            if(ae.getPurpose().equals(purpose)){
                ae.printAll();
                System.out.print("업데이트 할 번호를 입력해주세요..");
                System.out.println("1 : 날짜");
                System.out.println("2 : 용도");
                System.out.println("3 : 수입 여부");
                System.out.println("4 : 금액");
                System.out.println("5 : 상세내용");
                int selection = sc.nextInt();
                System.out.println("내용을 적어주세요.");
                String content = sc.next();

                switch (selection) {
                    case 1:{
                        ae.setDate(content);
                        break;
                    }
                    case 2:{
                        ae.setPurpose(content);
                        break;
                    }
                    case 3:{
                        ae.setIsIncome(Boolean.parseBoolean(content));
                        break;
                    }
                    case 4:{
                        ae.setAmount(Integer.parseInt(content));
                        break;
                    }
                    case 5:{
                        ae.setDetail(content);
                        break;
                    }
                }
            }
        }
    }

    public void deleteEntryByPurpose(String purpose){
        Singleton s = Singleton.getInstance();

        boolean findToggle = false;

        Iterator<AccountEntry> iterator = s.accountList.iterator();

        while (iterator.hasNext()) {
            AccountEntry ae = iterator.next();
            if (ae.getPurpose().equals(purpose)) {
                ae.printAll();
                System.out.println("이 목록을 삭제합니까? y:yes, n:no");
                String yn = sc.next();
                findToggle = true;
                if (yn.equals("y")) {
                    iterator.remove();
                }
            }
        }

        if(findToggle == false){
            System.out.println("일치하는 정보가 없습니다.");
        }
    }

    public void aggregationByMonth(){
        System.out.println("연도를 입력해주세요.");
        String year = sc.next();

        List<AccountEntry> yearList = new ArrayList<AccountEntry>();

        Singleton s = Singleton.getInstance();
        for(AccountEntry ae:s.accountList){
            String arr[] = ae.getDate().split("/");
            if(arr[0].equals(year)){
                yearList.add(ae);
            }
        }

        int ans[] = new int[13];
        for(AccountEntry y:yearList){
            String yearString[] = y.getDate().split("/");
            int month = Integer.parseInt(yearString[1]);

            if(y.getIsIncome()){
                ans[month-1] += y.getAmount();
            }
            else{
                ans[month-1] -= y.getAmount();
            }
        }

        for(int i = 0; i < 12; i++){
            System.out.println((i+1)+ "월 : " + ans[i]);
        }
    }

    public void aggregationWithTwoDate(String date1, String date2){
        Singleton s = Singleton.getInstance();
        String year1 = date1.split("/")[0];
        String month1 = date1.split("/")[1];
        String day1 = date1.split("/")[2];

        String year2 = date2.split("/")[0];
        String month2 = date2.split("/")[1];
        String day2 = date2.split("/")[2];

        int ansIncome = 0;
        int ansOutcome = 0;
        for(AccountEntry ae:s.accountList){
            String aeDate = ae.getDate();
            String aeYear = aeDate.split("/")[0];
            String aeMonth = aeDate.split("/")[1];
            String aeDay = aeDate.split("/")[2];
            boolean startCon = false;
            boolean endCon = false;

            if(Integer.parseInt(aeYear) > Integer.parseInt(year1)){
                startCon = true;
            }
            else if(Integer.parseInt(aeYear) == Integer.parseInt(year1)){
                if(Integer.parseInt(aeMonth) > Integer.parseInt(month1)){
                    startCon = true;
                }
                else if(Integer.parseInt(aeMonth) == Integer.parseInt(month1)){
                    if(Integer.parseInt(aeDay) >= Integer.parseInt(aeDay)){
                        startCon = true;
                    }
                }
            }


            if(Integer.parseInt(aeYear) < Integer.parseInt(year1)){
                endCon = true;
            }
            else if(Integer.parseInt(aeYear) == Integer.parseInt(year1)){
                if(Integer.parseInt(aeMonth) < Integer.parseInt(month1)){
                    endCon = true;
                }
                else if(Integer.parseInt(aeMonth) == Integer.parseInt(month1)){
                    if(Integer.parseInt(aeDay) <= Integer.parseInt(aeDay)){
                        endCon = true;
                    }
                }
            }

            if(startCon && endCon){
                if(ae.getIsIncome() == true){
                    ansIncome += ae.getAmount();
                }
                else if(ae.getIsIncome()==false){
                    ansOutcome += ae.getAmount();
                }
            }

        }
        System.out.println("수입 : " + ansIncome);
        System.out.println("지출 : " + ansOutcome);
    }
}
